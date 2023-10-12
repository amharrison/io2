package org.jactr.io2.jactr.ui.contentassist;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IType;
import org.eclipse.jdt.core.ITypeHierarchy;
import org.eclipse.jdt.core.JavaCore;

import com.google.inject.Inject;

/*
 * default logging
 */

public class ContentAssistUtils
{
  @Inject
  private IWorkspace _workspace;

  public Collection<IType> getSubtypes(String className, URI resourceLocation)
  {
    try
    {
      IFile file = _workspace.getRoot()
          .getFile(new Path(resourceLocation.toPlatformString(true)));
      IProject project = file.getProject();
      IJavaProject javaProject = JavaCore.create(project);
      IType type = javaProject.findType(className, new NullProgressMonitor());
      Collection<IType> subtypes = new ArrayList<>();
      ITypeHierarchy hierarchy = type
          .newTypeHierarchy(new NullProgressMonitor());
      scanSubtypesForInstances(type, subtypes, hierarchy);
      
      return subtypes;
    }
    catch (Exception e)
    {
      System.err.println(e);
      return Collections.emptyList();
    }
  }

  protected void scanSubtypesForInstances(IType type,
      Collection<IType> subtypes, ITypeHierarchy hierarchy) throws Exception
  {

    for (IType subtype : hierarchy.getSubtypes(type))
      if (subtype.isClass() && isConcrete(subtype, hierarchy))
        subtypes.add(subtype);
      else
        scanSubtypesForInstances(subtype, subtypes, hierarchy);
  }

  protected boolean isConcrete(IType type, ITypeHierarchy hierarchy)
  {
    IType[] subclasses = hierarchy.getSubclasses(type);
    return subclasses.length == 0;
  }
}
