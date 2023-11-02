/**
 * generated by Xtext 2.16.0
 */
package org.jactr.io2.jactr.tests;

import com.google.inject.Inject;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.extensions.InjectionExtension;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(InjectionExtension.class)
@InjectWith(ModelFragmentInjectorProvider.class)
@SuppressWarnings("all")
public class ModelFragmentParsingTest {
  @Inject
  private ParseHelper<ModelFragment> parseHelper;

  @Test
  public void loadModel() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("namespace syntax{");
      _builder.newLine();
      _builder.newLine();
      _builder.append("module \"org.jactr.core.module.declarative.six.DefaultDeclarativeModule6\"[] ");
      _builder.newLine();
      _builder.append("module \"org.jactr.core.module.procedural.six.DefaultProceduralModule6\"[]");
      _builder.newLine();
      _builder.append("module \"org.jactr.core.module.goal.six.DefaultGoalModule6\" [ ]");
      _builder.newLine();
      _builder.append("module \"org.jactr.core.module.imaginal.six.DefaultImaginalModule6\" []");
      _builder.newLine();
      _builder.newLine();
      _builder.append("buffers{");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("goal {}[]");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("imaginal {}");
      _builder.newLine();
      _builder.append("} ");
      _builder.newLine();
      _builder.newLine();
      _builder.append("chunktype firstType {slot1 = true} ");
      _builder.newLine();
      _builder.newLine();
      _builder.append("chunktype secondType extends firstType {slot2 = false}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("@Override");
      _builder.newLine();
      _builder.append("chunktype thirdType extends secondType {slot3 = null}");
      _builder.newLine();
      _builder.newLine();
      _builder.append("firstType firstInstance(slot1=\'string\')[], secondInstance(slot1=1.2)");
      _builder.newLine();
      _builder.newLine();
      _builder.append("secondType thirdInstance(slot2=firstInstance), fourthInstance(slot2=secondType)");
      _builder.newLine();
      _builder.newLine();
      _builder.append("production firstProduction{");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("goal{");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("isa firstType");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("slot1 = firstInstance");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("?imaginal{");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("state = false");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("imaginal{");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("secondInstance");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("slot1 = 1.2");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("proxy(\"org.jactr.core.production.condition.SystemPropertyCondition\"){");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("slot = true");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("slot2 = \'string\'");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("script(\"javascript\")<[[");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("var test=1;");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("function test(){");
      _builder.newLine();
      _builder.append("      ");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("]]>");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}{");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("+goal{");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("isa thirdType");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("slot3 = false");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("goal{");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("slot1 = true");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("- goal{");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("slot1 = false");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("output(\"this is output\")");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("proxy(\"org.jactr.core.production.action.StopAction\"){");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("slot = true");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("slot2 = \'string\'");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("script(\"javascript\")<[[");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("var test=1;");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("function test(){");
      _builder.newLine();
      _builder.append("      ");
      _builder.newLine();
      _builder.append("    ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("]]>");
      _builder.newLine();
      _builder.append("  ");
      _builder.append("}");
      _builder.newLine();
      _builder.append("}");
      _builder.newLine();
      final ModelFragment result = this.parseHelper.parse(_builder);
      Assertions.assertNotNull(result);
      final EList<Resource.Diagnostic> errors = result.eResource().getErrors();
      boolean _isEmpty = errors.isEmpty();
      StringConcatenation _builder_1 = new StringConcatenation();
      _builder_1.append("Unexpected errors: ");
      String _join = IterableExtensions.join(errors, ", ");
      _builder_1.append(_join);
      Assertions.assertTrue(_isEmpty, _builder_1.toString());
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
