/**
 * generated by Xtext 2.16.0
 */
package org.jactr.io2.jactr.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.eclipse.xtext.ide.refactoring.IRenameStrategy2;
import org.eclipse.xtext.ui.editor.findrefs.IReferenceFinder;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.editor.occurrences.IOccurrenceComputer;
import org.eclipse.xtext.ui.editor.syntaxcoloring.IHighlightingConfiguration;
import org.jactr.io2.jactr.ui.contentassist.ContentAssistUtils;
import org.jactr.io2.jactr.ui.findRefs.ModelFragmentUIReferenceFinder;
import org.jactr.io2.jactr.ui.highlighting.HighlightingConfiguration;
import org.jactr.io2.jactr.ui.highlighting.SemanticHighlightingCalculator;
import org.jactr.io2.jactr.ui.hyperlink.JACTRHyperlinkHelper;
import org.jactr.io2.jactr.ui.occurrence.ModelFragmentOccurrenceComputer;
import org.jactr.io2.jactr.ui.rename.RenameStrategy;
import org.jactr.io2.jactr.ui.validation.EclipseProjectAwareClassNameValidator;
import org.jactr.io2.jactr.validation.IClassNameValidator;

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
@FinalFieldsConstructor
@SuppressWarnings("all")
public class ModelFragmentUiModule extends AbstractModelFragmentUiModule {
  public Class<? extends IHighlightingConfiguration> bindIHighlightingConfiguration() {
    return HighlightingConfiguration.class;
  }

  public Class<? extends ISemanticHighlightingCalculator> bindIdeSemanticHighlightingCalculator() {
    return SemanticHighlightingCalculator.class;
  }

  public Class<? extends IHyperlinkHelper> bindIHyperlinkHelper() {
    return JACTRHyperlinkHelper.class;
  }

  public Class<? extends IClassNameValidator> bindIClassNameValidator() {
    return EclipseProjectAwareClassNameValidator.class;
  }

  public Class<? extends ContentAssistUtils> bindContentAssistUtils() {
    return ContentAssistUtils.class;
  }

  @Override
  public Class<? extends IRenameStrategy2> bindIRenameStrategy2() {
    return RenameStrategy.class;
  }

  public Class<? extends IOccurrenceComputer> bindIOccurrenceComputer() {
    return ModelFragmentOccurrenceComputer.class;
  }

  public Class<? extends IReferenceFinder> bindReferenceFinder() {
    return ModelFragmentUIReferenceFinder.class;
  }

  public ModelFragmentUiModule(final AbstractUIPlugin arg0) {
    super(arg0);
  }
}
