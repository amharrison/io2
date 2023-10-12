package org.jactr.io2.jactr.ui.wizard;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.xtext.ui.util.PluginProjectFactory;

@SuppressWarnings("all")
public class ManifestContributingPluginProjectFactory extends PluginProjectFactory {
  protected String getBreeToUse() {
    return "JavaSE-11";
  }
  
  protected void customizeManifest(final StringBuilder builder) {
    builder.append("Eclipse-BuddyPolicy: registered\n");
    this.addToContent(builder, this.requiredBundles, "Eclipse-RegisterBuddy");
  }
  
  @Override
  public void createManifest(final IProject project, final IProgressMonitor progressMonitor) throws CoreException {
    final StringBuilder content = new StringBuilder("Manifest-Version: 1.0\n");
    content.append((("Automatic-Module-Name: " + this.projectName) + "\n"));
    content.append("Bundle-ManifestVersion: 2\n");
    content.append((("Bundle-Name: " + this.projectName) + "\n"));
    content.append("Bundle-Vendor: My Research Lab\n");
    content.append("Bundle-Version: 1.0.0.qualifier\n");
    content.append((("Bundle-SymbolicName: " + this.projectName) + "; singleton:=true\n"));
    if ((null != this.activatorClassName)) {
      content.append((("Bundle-Activator: " + this.activatorClassName) + "\n"));
    }
    content.append("Bundle-ActivationPolicy: lazy\n");
    this.addToContent(content, this.requiredBundles, "Require-Bundle");
    this.addToContent(content, this.exportedPackages, "Export-Package");
    this.addToContent(content, this.importedPackages, "Import-Package");
    content.append("Bundle-RequiredExecutionEnvironment: ");
    String _breeToUse = this.getBreeToUse();
    String _plus = (_breeToUse + "\n");
    content.append(_plus);
    this.customizeManifest(content);
    final IFolder metaInf = project.getFolder("META-INF");
    final SubMonitor subMonitor = SubMonitor.convert(progressMonitor, 2);
    try {
      boolean _exists = metaInf.exists();
      if (_exists) {
        metaInf.delete(false, progressMonitor);
      }
      metaInf.create(false, true, subMonitor.newChild(1));
      this.createFile("MANIFEST.MF", metaInf, content.toString(), subMonitor.newChild(1));
    } finally {
      subMonitor.done();
    }
  }
}
