/**
 * generated by Xtext 2.16.0
 */
package org.jactr.io2.jactr.ui.contentassist;

import com.google.common.collect.LinkedListMultimap;
import com.google.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jdt.core.IType;
import org.eclipse.xtext.Assignment;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.ui.IImageHelper;
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext;
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.jactr.core.chunk.five.DefaultSubsymbolicChunk5;
import org.jactr.core.extensions.IExtension;
import org.jactr.core.model.basic.BasicModel;
import org.jactr.core.module.IModule;
import org.jactr.core.module.goal.six.buffer.DefaultGoalBuffer6;
import org.jactr.core.production.action.IAction;
import org.jactr.core.production.condition.ICondition;
import org.jactr.core.utils.parameter.IParameterized;
import org.jactr.io2.jactr.modelFragment.Add;
import org.jactr.io2.jactr.modelFragment.Buffer;
import org.jactr.io2.jactr.modelFragment.ChunkDef;
import org.jactr.io2.jactr.modelFragment.ChunkType;
import org.jactr.io2.jactr.modelFragment.IsaBlock;
import org.jactr.io2.jactr.modelFragment.Match;
import org.jactr.io2.jactr.modelFragment.ModelExtension;
import org.jactr.io2.jactr.modelFragment.ModelModule;
import org.jactr.io2.jactr.modelFragment.PackageDeclaration;
import org.jactr.io2.jactr.modelFragment.Production;
import org.jactr.io2.jactr.modelFragment.SimpleSlotBlock;
import org.jactr.io2.jactr.validation.IClassNameValidator;
import org.jactr.io2.jactr.validation.ModelFragmentUtil;

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
@SuppressWarnings("all")
public class ModelFragmentProposalProvider extends AbstractModelFragmentProposalProvider {
  @Inject
  @Extension
  private ModelFragmentUtil _modelFragmentUtil;
  
  @Inject
  private IImageHelper _imageHelper;
  
  @Inject
  private IClassNameValidator _validator;
  
  @Inject
  private ContentAssistUtils _util;
  
  @Override
  public void completeChunkDef_Slots(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    if ((model instanceof ChunkDef)) {
      final HashSet<String> candidateSlots = this._modelFragmentUtil.allSlotNames(((ChunkDef)model));
      for (final String slotName : candidateSlots) {
        acceptor.accept(this.createCompletionProposal(slotName, slotName, this._imageHelper.getImage("basic/slot.gif"), context));
      }
    }
    super.completeChunkDef_Slots(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeParameters_Elements(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    final LinkedListMultimap<String, IEObjectDescription> chunks = this._modelFragmentUtil.chunkSymbolTable(model);
    Set<String> _keySet = chunks.keySet();
    for (final String chunk : _keySet) {
      acceptor.accept(this.createCompletionProposal(chunk, chunk, this._imageHelper.getImage("basic/chunk.gif"), context));
    }
    final LinkedListMultimap<String, IEObjectDescription> productions = this._modelFragmentUtil.productionSymbolTable(model);
    Set<String> _keySet_1 = productions.keySet();
    for (final String production : _keySet_1) {
      acceptor.accept(
        this.createCompletionProposal(production, production, this._imageHelper.getImage("basic/production.gif"), context));
    }
    super.completeParameters_Elements(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeMatch_Slots(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    Set<String> candidateSlots = Collections.<String>emptySet();
    EObject node = model;
    if ((node instanceof IsaBlock)) {
      node = ((IsaBlock)node).eContainer();
    }
    if ((node instanceof Match)) {
      ChunkDef _chunk = ((Match)node).getIsa().getChunk();
      boolean _tripleNotEquals = (_chunk != null);
      if (_tripleNotEquals) {
        candidateSlots = this._modelFragmentUtil.allSlotNames(((Match)node).getIsa().getChunk());
      }
      ChunkType _type = ((Match)node).getIsa().getType();
      boolean _tripleNotEquals_1 = (_type != null);
      if (_tripleNotEquals_1) {
        candidateSlots = this._modelFragmentUtil.allSlotNames(((Match)node).getIsa().getType());
      }
    }
    for (final String slotName : candidateSlots) {
      acceptor.accept(this.createCompletionProposal(slotName, slotName, this._imageHelper.getImage("basic/slot.gif"), context));
    }
    super.completeMatch_Slots(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeAdd_Slots(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    Set<String> candidateSlots = Collections.<String>emptySet();
    EObject node = model;
    if ((node instanceof IsaBlock)) {
      node = ((IsaBlock)node).eContainer();
    }
    if ((node instanceof Add)) {
      ChunkDef _chunk = ((Add)node).getIsa().getChunk();
      boolean _tripleNotEquals = (_chunk != null);
      if (_tripleNotEquals) {
        candidateSlots = this._modelFragmentUtil.allSlotNames(((Add)node).getIsa().getChunk());
      }
      ChunkType _type = ((Add)node).getIsa().getType();
      boolean _tripleNotEquals_1 = (_type != null);
      if (_tripleNotEquals_1) {
        candidateSlots = this._modelFragmentUtil.allSlotNames(((Add)node).getIsa().getType());
      }
    }
    for (final String slotName : candidateSlots) {
      acceptor.accept(this.createCompletionProposal(slotName, slotName, this._imageHelper.getImage("basic/slot.gif"), context));
    }
    super.completeAdd_Slots(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeOrSlot_Slots(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    final HashSet<String> candidateSlots = this._modelFragmentUtil.guessSlots(model);
    for (final String slotName : candidateSlots) {
      acceptor.accept(this.createCompletionProposal(slotName, slotName, this._imageHelper.getImage("basic/slot.gif"), context));
    }
    super.completeOrSlot_Slots(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeAndSlot_Slots(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    final HashSet<String> candidateSlots = this._modelFragmentUtil.guessSlots(model);
    for (final String slotName : candidateSlots) {
      acceptor.accept(this.createCompletionProposal(slotName, slotName, this._imageHelper.getImage("basic/slot.gif"), context));
    }
    super.completeAndSlot_Slots(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeNotSlot_Slots(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    final HashSet<String> candidateSlots = this._modelFragmentUtil.guessSlots(model);
    for (final String slotName : candidateSlots) {
      acceptor.accept(this.createCompletionProposal(slotName, slotName, this._imageHelper.getImage("basic/slot.gif"), context));
    }
    super.completeNotSlot_Slots(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeSimpleSlotBlock_Slots(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    if ((model instanceof SimpleSlotBlock)) {
      final Function1<Match, Boolean> _function = (Match match) -> {
        return Boolean.valueOf(match.getName().getName().equals(((SimpleSlotBlock)model).getName().getName()));
      };
      final Function1<Match, Boolean> _function_1 = (Match match) -> {
        return Boolean.valueOf(((match.getIsa().getType() != null) || (match.getIsa().getChunk() != null)));
      };
      Match _last = IterableExtensions.<Match>last(IterableExtensions.<Match>filter(IterableExtensions.<Match>filter(EcoreUtil2.<Match>getAllContentsOfType(((SimpleSlotBlock)model).eContainer(), Match.class), _function), _function_1));
      final Match mate = ((Match) _last);
      HashSet<String> allSlotNames = new HashSet<String>();
      ChunkDef _chunk = mate.getIsa().getChunk();
      boolean _tripleNotEquals = (_chunk != null);
      if (_tripleNotEquals) {
        allSlotNames = this._modelFragmentUtil.allSlotNames(mate.getIsa().getChunk());
      }
      ChunkType _type = mate.getIsa().getType();
      boolean _tripleNotEquals_1 = (_type != null);
      if (_tripleNotEquals_1) {
        allSlotNames = this._modelFragmentUtil.allSlotNames(mate.getIsa().getType());
      }
      for (final String slotName : allSlotNames) {
        acceptor.accept(this.createCompletionProposal(slotName, slotName, this._imageHelper.getImage("basic/slot.gif"), context));
      }
    }
    super.completeSimpleSlotBlock_Slots(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeSimpleSlot_Value(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    final LinkedListMultimap<String, IEObjectDescription> chunks = this._modelFragmentUtil.chunkSymbolTable(model);
    Set<String> _keySet = chunks.keySet();
    for (final String chunk : _keySet) {
      acceptor.accept(this.createCompletionProposal(chunk, chunk, this._imageHelper.getImage("basic/chunk.gif"), context));
    }
    super.completeSimpleSlot_Value(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeConditionalSlot_Value(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    final LinkedListMultimap<String, IEObjectDescription> chunks = this._modelFragmentUtil.chunkSymbolTable(model);
    Set<String> _keySet = chunks.keySet();
    for (final String chunk : _keySet) {
      acceptor.accept(this.createCompletionProposal(chunk, chunk, this._imageHelper.getImage("basic/chunk.gif"), context));
    }
    super.completeConditionalSlot_Value(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeValue_Name(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    try {
      final Production production = EcoreUtil2.<Production>getContainerOfType(model, Production.class);
      if ((production != null)) {
        HashSet<String> _variables = this._modelFragmentUtil.variables(production);
        for (final String variable : _variables) {
          acceptor.accept(this.createCompletionProposal(variable, variable, null, context));
        }
      }
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    super.completeValue_Name(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeModelModule_ModuleClass(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    final URI uri = model.eResource().getURI();
    final Collection<IType> subtypes = this._util.getSubtypes(IModule.class.getName(), uri);
    final Predicate<IType> _function = (IType t) -> {
      try {
        return t.isClass();
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    final Consumer<IType> _function_1 = (IType t) -> {
      String _fullyQualifiedName = t.getFullyQualifiedName();
      String _plus = ("\"" + _fullyQualifiedName);
      String _plus_1 = (_plus + "\"");
      acceptor.accept(
        this.createCompletionProposal(_plus_1, t.getElementName(), 
          this._imageHelper.getImage("basic/extension.gif"), context));
    };
    subtypes.stream().filter(_function).forEach(_function_1);
    super.completeModelModule_ModuleClass(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeModelExtension_ExtensionClass(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    final URI uri = model.eResource().getURI();
    final Collection<IType> subtypes = this._util.getSubtypes(IExtension.class.getName(), uri);
    final Predicate<IType> _function = (IType t) -> {
      try {
        return t.isClass();
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    final Consumer<IType> _function_1 = (IType t) -> {
      String _fullyQualifiedName = t.getFullyQualifiedName();
      String _plus = ("\"" + _fullyQualifiedName);
      String _plus_1 = (_plus + "\"");
      acceptor.accept(
        this.createCompletionProposal(_plus_1, t.getElementName(), 
          this._imageHelper.getImage("basic/extension.gif"), context));
    };
    subtypes.stream().filter(_function).forEach(_function_1);
    super.completeModelExtension_ExtensionClass(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeProxy_Name(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    String className = "";
    EObject _eContainer = model.eContainer();
    final Production production = ((Production) _eContainer);
    boolean _contains = production.getConditions().contains(model);
    if (_contains) {
      className = ICondition.class.getName();
    } else {
      className = IAction.class.getName();
    }
    int _length = className.length();
    boolean _greaterThan = (_length > 0);
    if (_greaterThan) {
      final Collection<IType> subtypes = this._util.getSubtypes(className, model.eResource().getURI());
      final Predicate<IType> _function = (IType t) -> {
        try {
          return t.isClass();
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      final Consumer<IType> _function_1 = (IType t) -> {
        String _fullyQualifiedName = t.getFullyQualifiedName();
        String _plus = ("\"" + _fullyQualifiedName);
        String _plus_1 = (_plus + "\"");
        acceptor.accept(
          this.createCompletionProposal(_plus_1, t.getElementName(), 
            this._imageHelper.getImage("basic/script.gif"), context));
      };
      subtypes.stream().filter(_function).forEach(_function_1);
    }
    super.completeProxy_Name(model, assignment, context, acceptor);
  }
  
  @Override
  public void completeParameter_Name(final EObject model, final Assignment assignment, final ContentAssistContext context, final ICompletionProposalAcceptor acceptor) {
    EObject pContainer = model.eContainer();
    String parameterizedClass = "";
    if ((pContainer instanceof ModelModule)) {
      parameterizedClass = ((ModelModule)pContainer).getModuleClass();
    }
    if ((pContainer instanceof ModelExtension)) {
      parameterizedClass = ((ModelExtension)pContainer).getExtensionClass();
    }
    if ((pContainer instanceof ChunkDef)) {
      parameterizedClass = DefaultSubsymbolicChunk5.class.getName();
    }
    if ((pContainer instanceof Buffer)) {
      parameterizedClass = DefaultGoalBuffer6.class.getName();
    }
    if ((pContainer instanceof Production)) {
      parameterizedClass = MockProductionParameters.class.getName();
    }
    if ((pContainer instanceof PackageDeclaration)) {
      parameterizedClass = BasicModel.class.getName();
    }
    int _length = parameterizedClass.length();
    boolean _greaterThan = (_length > 0);
    if (_greaterThan) {
      Collection<String> parameters = this.getParametersFromInstantiation(model, parameterizedClass);
      for (final String parameterName : parameters) {
        acceptor.accept(this.createCompletionProposal((("\"" + parameterName) + "\""), parameterName, null, context));
      }
    }
    super.completeParameter_Name(model, assignment, context, acceptor);
  }
  
  public Collection<String> getParametersFromInstantiation(final EObject model, final String className) {
    try {
      boolean _isValidClassName = this._validator.isValidClassName(model.eResource(), className);
      if (_isValidClassName) {
        Object instance = this._validator.<Object>instantiate(className);
        if ((instance == null)) {
          return Collections.<String>emptyList();
        }
        if ((instance instanceof IParameterized)) {
          return ((IParameterized)instance).getSetableParameters();
        }
        return Collections.<String>emptyList();
      }
      return Collections.<String>emptyList();
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        return Collections.<String>emptyList();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
}
