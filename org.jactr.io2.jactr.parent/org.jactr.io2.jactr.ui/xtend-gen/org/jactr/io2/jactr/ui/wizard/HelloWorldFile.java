/**
 * generated by Xtext 2.16.0
 */
package org.jactr.io2.jactr.ui.wizard;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.ui.wizard.template.AbstractFileTemplate;
import org.eclipse.xtext.ui.wizard.template.FileTemplate;
import org.eclipse.xtext.ui.wizard.template.IFileGenerator;
import org.eclipse.xtext.ui.wizard.template.StringSelectionTemplateVariable;

@FileTemplate(label = "jACT-R Model", icon = "basic/jactr.gif", description = "Create a new jACT-R model")
@SuppressWarnings("all")
public final class HelloWorldFile extends AbstractFileTemplate {
  private final StringSelectionTemplateVariable modelType = this.combo("Model Type:", new String[] { "Basic", "Embodied" }, "Type of model to generate");
  
  @Override
  public void generateFiles(final IFileGenerator generator) {
    boolean _equals = this.modelType.equals("Basic");
    if (_equals) {
      StringConcatenation _builder = new StringConcatenation();
      String _folder = this.getFolder();
      _builder.append(_folder);
      _builder.append("/");
      String _name = this.getName();
      _builder.append(_name);
      _builder.append(".jactr");
      generator.generate(_builder, ModelStubs.basicModel(this.getName()));
    } else {
      StringConcatenation _builder_1 = new StringConcatenation();
      String _folder_1 = this.getFolder();
      _builder_1.append(_folder_1);
      _builder_1.append("/");
      String _name_1 = this.getName();
      _builder_1.append(_name_1);
      _builder_1.append(".jactr");
      generator.generate(_builder_1, ModelStubs.embodiedModel(this.getName()));
    }
  }
}
