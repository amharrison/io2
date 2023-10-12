package org.jactr.io2.jactr.ui.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Provider;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.resource.SaveOptions;
import org.jactr.core.model.IModel;
import org.jactr.io2.jactr.ast.ModelToModelFragment;
import org.jactr.io2.jactr.builder.Builder;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.source.ISourceLocator;
import org.jactr.io2.jactr.ui.internal.JactrActivator;

import com.google.inject.Injector;

public class JactrUIUtilities
{
  @Inject
  public Provider<ResourceSet> resourceSetProvider;

  /**
   * load using the local project context
   * 
   * @param uriToLoad
   * @return
   */
  static public ModelFragment loadModel(URI uriToLoad)
  {
    Injector injector = JactrActivator.getInstance()
        .getInjector(JactrActivator.ORG_JACTR_IO2_JACTR_MODELFRAGMENT);
    return injector.getInstance(JactrUIUtilities.class).load(uriToLoad);
  }

  protected ModelFragment load(URI uriToLoad)
  {
    ResourceSet set = resourceSetProvider.get();
    return (ModelFragment) set.getResource(
        org.eclipse.emf.common.util.URI.createURI(uriToLoad.toString()), true)
        .getContents().get(0);
  }

  public JactrUIUtilities()
  {
  }

  static public Supplier<IModel> build(ModelFragment model)
  {
    return new Supplier<IModel>() {

      @Override
      public IModel get()
      {
        try
        {
          Builder builder = JactrActivator.getInstance()
              .getInjector(JactrActivator.ORG_JACTR_IO2_JACTR_MODELFRAGMENT)
              .getInstance(Builder.class);

          return builder.build(model);
        }
        catch (Exception e)
        {
          throw new RuntimeException(e);
        }
      }
    };
  }

  static public Supplier<ModelFragment> convert(IModel model)
  {
    return new Supplier<ModelFragment>() {

      @Override
      public ModelFragment get()
      {
        try
        {
          ModelToModelFragment m2mf = JactrActivator.getInstance()
              .getInjector(JactrActivator.ORG_JACTR_IO2_JACTR_MODELFRAGMENT)
              .getInstance(ModelToModelFragment.class);

          ModelFragment newModelFragment = m2mf.convert(model);

          return newModelFragment;
        }
        catch (Exception e)
        {
          throw new RuntimeException(e);
        }
      }

    };
  }

  static public Map<String, ISourceLocator.SourceLocation> locateSourceElements(
      ModelFragment modelFragment)
  {
    ISourceLocator locator = JactrActivator.getInstance()
        .getInjector(JactrActivator.ORG_JACTR_IO2_JACTR_MODELFRAGMENT)
        .getInstance(ISourceLocator.class);
    return locator.locateSourceElements(modelFragment);
  }

  static public void saveModel(ModelFragment fragment, URI uri) throws Exception
  {
    ResourceSet set = new ResourceSetImpl();
    Resource resource = set.createResource(
        org.eclipse.emf.common.util.URI.createURI(uri.toString()));

    resource.getContents().add(fragment);

    resource.save(SaveOptions.newBuilder().noValidation().format().getOptions()
        .toOptionsMap());
  }

  static public void saveModel(EObject root, OutputStream outputStream)
      throws IOException
  {
    Resource resource = new BinaryResourceImpl();
    resource.getContents().add(root);

    Map<Object, Object> options = new HashMap<Object, Object>();
    options.put(BinaryResourceImpl.OPTION_STYLE_DATA_CONVERTER, Boolean.TRUE);
    options.put(BinaryResourceImpl.OPTION_VERSION,
        BinaryResourceImpl.BinaryIO.Version.VERSION_1_1);

    resource.save(outputStream, options);
  }
}
