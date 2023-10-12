/**
 * generated by Xtext 2.16.0
 */
package org.jactr.io2.jactr;

import com.google.inject.Binder;
import com.google.inject.name.Names;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.findReferences.IReferenceFinder;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.scoping.impl.AbstractDeclarativeScopeProvider;
import org.eclipse.xtext.serializer.sequencer.IHiddenTokenSequencer;
import org.jactr.io2.jactr.formatting2.CommentingHiddenTokenSequencer;
import org.jactr.io2.jactr.naming.QualifiedNameProvider;
import org.jactr.io2.jactr.references.ModelFragmentReferenceFinder;
import org.jactr.io2.jactr.scoping.JactrImportedNamespaceAwareLocalScopeProvider;
import org.jactr.io2.jactr.source.DefaultSourceLocator;
import org.jactr.io2.jactr.source.ISourceLocator;
import org.jactr.io2.jactr.validation.DefaultClassNameValidator;
import org.jactr.io2.jactr.validation.IClassNameValidator;
import org.jactr.io2.jactr.validation.ModelFragmentUtil;
import org.jactr.io2.jactr.value.ModelFragmentConverters;

/**
 * Use this class to register components to be used at runtime / without the Equinox extension registry.
 */
@SuppressWarnings("all")
public class ModelFragmentRuntimeModule extends AbstractModelFragmentRuntimeModule {
  public Class<ModelFragmentUtil> bindModelFragmentUtil() {
    return ModelFragmentUtil.class;
  }
  
  public Class<? extends IClassNameValidator> bindIClassNameValidator() {
    return DefaultClassNameValidator.class;
  }
  
  public Class<? extends IHiddenTokenSequencer> bindIHiddenTokenSequencer() {
    return CommentingHiddenTokenSequencer.class;
  }
  
  @Override
  public Class<? extends IQualifiedNameProvider> bindIQualifiedNameProvider() {
    return QualifiedNameProvider.class;
  }
  
  @Override
  public Class<? extends IValueConverterService> bindIValueConverterService() {
    return ModelFragmentConverters.class;
  }
  
  public Class<? extends ISourceLocator> bindISourceLocator() {
    return DefaultSourceLocator.class;
  }
  
  @Override
  public void configureIScopeProviderDelegate(final Binder binder) {
    binder.<IScopeProvider>bind(IScopeProvider.class).annotatedWith(Names.named(AbstractDeclarativeScopeProvider.NAMED_DELEGATE)).to(
      JactrImportedNamespaceAwareLocalScopeProvider.class);
  }
  
  public Class<? extends IReferenceFinder> bindReferenceFinder() {
    return ModelFragmentReferenceFinder.class;
  }
}