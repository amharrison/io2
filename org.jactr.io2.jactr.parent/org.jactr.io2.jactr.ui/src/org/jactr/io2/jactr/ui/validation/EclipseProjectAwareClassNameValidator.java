package org.jactr.io2.jactr.ui.validation;

import java.net.URI;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.JavaCore;
import org.jactr.io2.jactr.validation.DefaultClassNameValidator;

import com.google.inject.Inject;

public class EclipseProjectAwareClassNameValidator
    extends DefaultClassNameValidator
{

  @Inject
  private IWorkspace _workspace;

  public EclipseProjectAwareClassNameValidator()
  {
    super();
  }

  @Override
  public boolean isValidClassName(Resource resource, String className)
  {
    boolean rtn = super.isValidClassName(resource, className);
    if (!rtn) return projectAwareIsValidClassName(resource, className);
    return rtn;
  }

  @Override
  public URI resolve(Resource resource, String resourceName)
  {
    try
    {
      IFile file = _workspace.getRoot()
          .getFile(new Path(resource.getURI().toPlatformString(true)));
      IProject project = file.getProject();
      IJavaProject javaProject = JavaCore.create(project);
      for (IClasspathEntry entry : javaProject.getRawClasspath())
        if (entry.getEntryKind() == IClasspathEntry.CPE_SOURCE)
        {
          IPath root = entry.getPath().removeFirstSegments(1); // path is to
                                                               // root, we want
                                                               // workspace
                                                               // relative
          IFile resourceFile = project.getFile(root.append(resourceName));
          if (resourceFile != null && resourceFile.exists())
            return file.getLocationURI();
        }
      return super.resolve(resource, resourceName);
    }
    catch (Exception e)
    {
      return super.resolve(resource, resourceName);
    }
  }

  private boolean projectAwareIsValidClassName(Resource resource,
      String className)
  {
    try
    {
      IFile file = _workspace.getRoot()
          .getFile(new Path(resource.getURI().toPlatformString(true)));
      IProject project = file.getProject();

      IJavaProject javaProject = JavaCore.create(project);

      IType type = javaProject.findType(className);

      return type != null;
    }
    catch (Exception e)
    {
      System.err.println(e);
      return false;
    }
  }

}
