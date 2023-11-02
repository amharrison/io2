package org.jactr.io2.jactr.util;

import java.net.URL;
import java.util.List;
import java.util.concurrent.Callable;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.eclipse.xtext.validation.Issue;
import org.jactr.core.model.IModel;
import org.jactr.io2.jactr.modelFragment.Import;
import org.jactr.io2.jactr.modelFragment.ModelFragment;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class LoadDelegate implements Callable<ModelFragment>
{
  @Inject
  public Provider<ResourceSet> resourceSetProvider;

  @Inject
  public IResourceValidator    validator;

  private URI                  _emfURI;

  public void setURI(java.net.URI uri)
  {
    _emfURI = URI.createURI(uri.toString());
  }

  protected void loadDeps(ModelFragment fragment, ResourceSet set, URI root)
  {
    for (Import imp : fragment.getPackage().getImports())
    {
      // with wildcard
      String packageName = imp.getImportedNamespace();
      packageName = packageName.substring(0, packageName.length() - 2)
          .replace(".", "/") + ".jactr";

      URI dep = null;
      URL inClasspath = IModel.class.getClassLoader().getResource(packageName);

      if (inClasspath != null)
        dep = URI.createURI(inClasspath.toString());
      else
      {
        String[] segments = packageName.split("/");
        dep = root.appendSegments(segments);
      }

      Resource loaded = set.getResource(dep, true);

      loadDeps((ModelFragment) loaded.getContents().get(0), set, root);
    }
  }

  protected String getExpectedPath(ModelFragment modelFragment)
  {
    String path = modelFragment.getPackage().getName().replace(".", "/");
    return path + ".jactr";
  }

  protected URI getRootURI(String expectedPath)
  {
    String uri = _emfURI.toString();
    uri = uri.substring(0, uri.lastIndexOf(expectedPath));
    return URI.createURI(uri);
  }

  @Override
  public ModelFragment call() throws Exception
  {
    // Load the resource
    ResourceSet set = resourceSetProvider.get(); // new XtextResourceSet();

    Resource resource = set.getResource(_emfURI, true);

    resource.load(null);
    ModelFragment modelFragment = (ModelFragment) resource.getContents().get(0);
    /*
     * our resource set is empty.. recursively load and add to the resource set,
     * then validate
     */
    URI root = getRootURI(getExpectedPath(modelFragment));
    loadDeps(modelFragment, set, root);

    // Validate the resource
    List<Issue> list = validator.validate(resource, CheckMode.ALL,
        CancelIndicator.NullImpl);
    if (!list.isEmpty()) for (Issue issue : list)
      System.err.println(issue);
    // throw new RuntimeException(list.get(0).getMessage());

    return (ModelFragment) resource.getContents().get(0);
  }
}