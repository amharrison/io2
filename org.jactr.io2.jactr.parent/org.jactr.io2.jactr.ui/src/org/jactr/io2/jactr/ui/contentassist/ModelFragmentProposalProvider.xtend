/*
 * generated by Xtext 2.16.0
 */
package org.jactr.io2.jactr.ui.contentassist

import com.google.inject.Inject
import java.util.Collections
import java.util.HashSet
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.ui.IImageHelper
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor
import org.jactr.core.chunk.five.DefaultSubsymbolicChunk5
import org.jactr.core.extensions.IExtension
import org.jactr.core.model.basic.BasicModel
import org.jactr.core.module.IModule
import org.jactr.core.module.goal.six.buffer.DefaultGoalBuffer6
import org.jactr.core.production.action.IAction
import org.jactr.core.production.condition.ICondition
import org.jactr.core.utils.parameter.IParameterized
import org.jactr.io2.jactr.modelFragment.Add
import org.jactr.io2.jactr.modelFragment.Buffer
import org.jactr.io2.jactr.modelFragment.ChunkDef
import org.jactr.io2.jactr.modelFragment.IsaBlock
import org.jactr.io2.jactr.modelFragment.Match
import org.jactr.io2.jactr.modelFragment.ModelExtension
import org.jactr.io2.jactr.modelFragment.ModelModule
import org.jactr.io2.jactr.modelFragment.PackageDeclaration
import org.jactr.io2.jactr.modelFragment.Production
import org.jactr.io2.jactr.modelFragment.SimpleSlotBlock
import org.jactr.io2.jactr.validation.IClassNameValidator
import org.jactr.io2.jactr.validation.ModelFragmentUtil

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
class ModelFragmentProposalProvider extends AbstractModelFragmentProposalProvider {

  @Inject extension ModelFragmentUtil
  @Inject IImageHelper _imageHelper
  @Inject IClassNameValidator _validator
  @Inject ContentAssistUtils _util

  override completeChunkDef_Slots(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    if (model instanceof ChunkDef) {
      val candidateSlots = model.allSlotNames

      for (slotName : candidateSlots)
        acceptor.accept(createCompletionProposal(slotName, slotName, _imageHelper.getImage("basic/slot.gif"), context))
    }
    super.completeChunkDef_Slots(model, assignment, context, acceptor)
  }

  override completeParameters_Elements(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {

    val chunks = chunkSymbolTable(model)
    for (chunk : chunks.keySet)
      acceptor.accept(createCompletionProposal(chunk, chunk, _imageHelper.getImage("basic/chunk.gif"), context))

    val productions = productionSymbolTable(model)
    for (production : productions.keySet)
      acceptor.accept(
        createCompletionProposal(production, production, _imageHelper.getImage("basic/production.gif"), context))

    super.completeParameters_Elements(model, assignment, context, acceptor)
  }

  override completeMatch_Slots(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {

    var candidateSlots = Collections.emptySet
    var node = model
    if (node instanceof IsaBlock)
      node = node.eContainer

    if (node instanceof Match) {
      if (node.isa.chunk !== null)
        candidateSlots = node.isa.chunk.allSlotNames
      if (node.isa.type !== null)
        candidateSlots = node.isa.type.allSlotNames
    }

    for (slotName : candidateSlots)
      acceptor.accept(createCompletionProposal(slotName, slotName, _imageHelper.getImage("basic/slot.gif"), context))

    super.completeMatch_Slots(model, assignment, context, acceptor)
  }

  override completeAdd_Slots(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    var candidateSlots = Collections.emptySet
    var node = model
    if (node instanceof IsaBlock)
      node = node.eContainer

    if (node instanceof Add) {
      if (node.isa.chunk !== null)
        candidateSlots = node.isa.chunk.allSlotNames
      if (node.isa.type !== null)
        candidateSlots = node.isa.type.allSlotNames
    }

    for (slotName : candidateSlots)
      acceptor.accept(createCompletionProposal(slotName, slotName, _imageHelper.getImage("basic/slot.gif"), context))

    super.completeAdd_Slots(model, assignment, context, acceptor)
  }

  override completeOrSlot_Slots(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    val candidateSlots = model.guessSlots

    for (slotName : candidateSlots)
      acceptor.accept(createCompletionProposal(slotName, slotName, _imageHelper.getImage("basic/slot.gif"), context))
    super.completeOrSlot_Slots(model, assignment, context, acceptor)
  }

  override completeAndSlot_Slots(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    val candidateSlots = model.guessSlots

    for (slotName : candidateSlots)
      acceptor.accept(createCompletionProposal(slotName, slotName, _imageHelper.getImage("basic/slot.gif"), context))
    super.completeAndSlot_Slots(model, assignment, context, acceptor)
  }

  override completeNotSlot_Slots(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    val candidateSlots = model.guessSlots

    for (slotName : candidateSlots)
      acceptor.accept(createCompletionProposal(slotName, slotName, _imageHelper.getImage("basic/slot.gif"), context))
    super.completeNotSlot_Slots(model, assignment, context, acceptor)
  }

  override completeSimpleSlotBlock_Slots(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    if (model instanceof SimpleSlotBlock) {
      val mate = EcoreUtil2.getAllContentsOfType(model.eContainer, Match).filter([ match |
        match.name.name.equals(model.name.name)
      ]).filter([ match |
        match.isa.type !== null || match.isa.chunk !== null
      ]).last as Match

      var allSlotNames = new HashSet;
      if (mate.isa.chunk !== null)
        allSlotNames = mate.isa.chunk.allSlotNames
      if (mate.isa.type !== null)
        allSlotNames = mate.isa.type.allSlotNames

      for (slotName : allSlotNames)
        acceptor.accept(createCompletionProposal(slotName, slotName, _imageHelper.getImage("basic/slot.gif"), context))
    }
    super.completeSimpleSlotBlock_Slots(model, assignment, context, acceptor)
  }

  override completeSimpleSlot_Value(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    val chunks = chunkSymbolTable(model)
    for (chunk : chunks.keySet)
      acceptor.accept(createCompletionProposal(chunk, chunk, _imageHelper.getImage("basic/chunk.gif"), context))
    super.completeSimpleSlot_Value(model, assignment, context, acceptor)
  }

  override completeConditionalSlot_Value(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    val chunks = chunkSymbolTable(model)
    for (chunk : chunks.keySet)
      acceptor.accept(createCompletionProposal(chunk, chunk, _imageHelper.getImage("basic/chunk.gif"), context))
    super.completeConditionalSlot_Value(model, assignment, context, acceptor)
  }

  override completeValue_Name(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    try {
      val production = EcoreUtil2.getContainerOfType(model, Production)
      if (production !== null)
        for (variable : production.variables)
          acceptor.accept(createCompletionProposal(variable, variable, null, context))
    } catch (Exception e) {
    }
    super.completeValue_Name(model, assignment, context, acceptor)

  }

  override completeModelModule_ModuleClass(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    // find the project for this model
    val uri = model.eResource.URI
    val subtypes = _util.getSubtypes(IModule.name, uri)
    // inspect IModule type, pull type heirarchy 
    // inspect hierarchy for subtypes
    subtypes.stream().filter([t|t.isClass]).forEach([ t |
      acceptor.accept(
        createCompletionProposal('"' + t.fullyQualifiedName + '"', t.elementName,
          _imageHelper.getImage("basic/extension.gif"), context))
    ])
    super.completeModelModule_ModuleClass(model, assignment, context, acceptor);
  }

  override completeModelExtension_ExtensionClass(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    // find the project for this model
    val uri = model.eResource.URI
    val subtypes = _util.getSubtypes(IExtension.name, uri)
    // inspect IModule type, pull type heirarchy 
    // inspect hierarchy for subtypes
    subtypes.stream().filter([t|t.isClass]).forEach([ t |
      acceptor.accept(
        createCompletionProposal('"' + t.fullyQualifiedName + '"', t.elementName,
          _imageHelper.getImage("basic/extension.gif"), context))
    ])
    super.completeModelExtension_ExtensionClass(model, assignment, context, acceptor);
  }

  override completeProxy_Name(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {

    var className = "";
    val production = model.eContainer as Production
    if (production.conditions.contains(model))
      className = ICondition.name
    else
      className = IAction.name

    if (className.length > 0) {

      val subtypes = _util.getSubtypes(className, model.eResource.URI)
      subtypes.stream().filter([t|t.isClass]).forEach([ t |
        acceptor.accept(
          createCompletionProposal('"' + t.fullyQualifiedName + '"', t.elementName,
            _imageHelper.getImage("basic/script.gif"), context))
      ])
    }

    super.completeProxy_Name(model, assignment, context, acceptor);

  }

  override completeParameter_Name(EObject model, Assignment assignment, ContentAssistContext context,
    ICompletionProposalAcceptor acceptor) {
    var pContainer = model.eContainer
    var parameterizedClass = "";
    if (pContainer instanceof ModelModule)
      parameterizedClass = pContainer.moduleClass
    if (pContainer instanceof ModelExtension)
      parameterizedClass = pContainer.extensionClass
    if (pContainer instanceof ChunkDef)
      parameterizedClass = DefaultSubsymbolicChunk5.name
    if (pContainer instanceof Buffer)
      parameterizedClass = DefaultGoalBuffer6.name
    if (pContainer instanceof Production)
      parameterizedClass = MockProductionParameters.name // since we can't instantiate subsymbolic productions directly
    if (pContainer instanceof PackageDeclaration)
      parameterizedClass = BasicModel.name
//    System.err.println(parameterizedClass+" "+pContainer)  
    if (parameterizedClass.length > 0) {
      var parameters = getParametersFromInstantiation(model, parameterizedClass)
      for (parameterName : parameters)
        acceptor.accept(createCompletionProposal('"' + parameterName + '"', parameterName, null, context))
    }
    super.completeParameter_Name(model, assignment, context, acceptor)
  }

  def getParametersFromInstantiation(EObject model, String className) {
    try {
      if (_validator.isValidClassName(model.eResource, className)) {
        var instance = _validator.instantiate(className)
        if (instance === null)
          return Collections.emptyList()
        if (instance instanceof IParameterized)
          return instance.setableParameters
        return Collections.emptyList()
      }
      return Collections.emptyList()
    } catch (Exception e) {
      return Collections.emptyList()
    }
  }
}
