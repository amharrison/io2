package org.jactr.io2.jactr.ui.wizard

import org.eclipse.xtext.ui.util.PluginProjectFactory
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.resources.IFolder
import org.eclipse.core.resources.IProject
import org.eclipse.core.runtime.SubMonitor
import org.eclipse.core.runtime.IProgressMonitor
import org.eclipse.xtext.ui.util.JREContainerProvider

class ManifestContributingPluginProjectFactory extends PluginProjectFactory {

  protected def getBreeToUse() {
//    return JREContainerProvider.getDefaultBREE();
      return "JavaSE-11";
  }
  
  protected def customizeManifest(StringBuilder builder)
  {
    builder.append("Eclipse-BuddyPolicy: registered\n")
    addToContent(builder, requiredBundles, "Eclipse-RegisterBuddy");
  }

  override createManifest(IProject project, IProgressMonitor progressMonitor) throws CoreException {
    val content = new StringBuilder("Manifest-Version: 1.0\n");
    content.append("Automatic-Module-Name: " + projectName + "\n");
    content.append("Bundle-ManifestVersion: 2\n");
    content.append("Bundle-Name: " + projectName + "\n");
    content.append("Bundle-Vendor: My Research Lab\n");
    content.append("Bundle-Version: 1.0.0.qualifier\n");
    content.append("Bundle-SymbolicName: " + projectName + "; singleton:=true\n");
    if (null !== activatorClassName) {
      content.append("Bundle-Activator: " + activatorClassName + "\n");
    }
    content.append("Bundle-ActivationPolicy: lazy\n");

    addToContent(content, requiredBundles, "Require-Bundle");
    addToContent(content, exportedPackages, "Export-Package");
    addToContent(content, importedPackages, "Import-Package");
    content.append("Bundle-RequiredExecutionEnvironment: ");
    content.append(getBreeToUse() + "\n");
    
    customizeManifest(content)
    

    val metaInf = project.getFolder("META-INF");
    val subMonitor = SubMonitor.convert(progressMonitor, 2);
    try {
      if (metaInf.exists())
        metaInf.delete(false, progressMonitor);
      metaInf.create(false, true, subMonitor.newChild(1));
      createFile("MANIFEST.MF", metaInf, content.toString(), subMonitor.newChild(1));
    } finally {
      subMonitor.done();
    }
  }
}
