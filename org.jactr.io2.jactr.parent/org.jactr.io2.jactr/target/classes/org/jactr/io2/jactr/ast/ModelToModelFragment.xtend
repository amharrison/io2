package org.jactr.io2.jactr.ast

import java.util.ArrayList
import java.util.Collection
import java.util.HashMap
import java.util.Map
import javax.inject.Inject
import org.eclipse.collections.api.multimap.MutableMultimap
import org.eclipse.collections.impl.factory.Multimaps
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.ResourceSet
import org.jactr.core.buffer.IActivationBuffer
import org.jactr.core.chunk.IChunk
import org.jactr.core.chunktype.IChunkType
import org.jactr.core.model.IModel
import org.jactr.core.production.IProduction
import org.jactr.core.production.action.AddAction
import org.jactr.core.production.action.IAction
import org.jactr.core.production.action.ModifyAction
import org.jactr.core.production.action.OutputAction
import org.jactr.core.production.action.ProxyAction
import org.jactr.core.production.action.RemoveAction
import org.jactr.core.production.condition.ChunkCondition
import org.jactr.core.production.condition.ChunkTypeCondition
import org.jactr.core.production.condition.ProxyCondition
import org.jactr.core.production.condition.QueryCondition
import org.jactr.core.production.condition.VariableCondition
import org.jactr.core.slot.IConditionalSlot
import org.jactr.core.slot.ILogicalSlot
import org.jactr.core.slot.ISlot
import org.jactr.core.utils.parameter.IParameterized
import org.jactr.io2.jactr.modelFragment.BooleanOperation
import org.jactr.io2.jactr.modelFragment.Buffer
import org.jactr.io2.jactr.modelFragment.ChunkDef
import org.jactr.io2.jactr.modelFragment.ChunkType
import org.jactr.io2.jactr.modelFragment.Chunks
import org.jactr.io2.jactr.modelFragment.ComplexSlot
import org.jactr.io2.jactr.modelFragment.Condition
import org.jactr.io2.jactr.modelFragment.ConditionalSlot
import org.jactr.io2.jactr.modelFragment.ModelFragmentFactory
import org.jactr.io2.jactr.modelFragment.Production
import org.jactr.io2.jactr.modelFragment.ProductionAction
import org.jactr.io2.jactr.modelFragment.ProductionCondition
import org.jactr.io2.jactr.modelFragment.SimpleSlot
import org.jactr.scripting.action.ScriptableAction
import org.jactr.scripting.condition.ScriptableCondition

/**
 * converts an IModel to its meta ModelFragment
 */
class ModelToModelFragment {

  @Inject
  ResourceSet _resourceSet;

  def convert(IModel model) {
    val modelFragment = ModelFragmentFactory.eINSTANCE.createModelFragment
    modelFragment.package = ModelFragmentFactory.eINSTANCE.createPackageDeclaration
    modelFragment.package.name = model.getName

    for (module : modules(model))
      modelFragment.package.modules.add(module)

    for (ext : extensions(model))
      modelFragment.package.extensions.add(ext)

    /*
     * chunks and types, stashed in variables for
     * resolution in isa blocks and buffer contents
     */
    val variables = Multimaps.mutable.list.empty();
    for (chunkType : chunkTypes(model, variables))
      modelFragment.package.elements.add(chunkType)

    modelFragment.package.buffers = buffers(model, variables)

    for (production : model.proceduralModule.productions.get)
      modelFragment.package.elements.add(toProduction(production, variables))

    modelFragment.package.parameters = parameters(model)

//    throw new RuntimeException("Incomplete implementation")
    /*
     * assign buffer contents
     */
    modelFragment
  }

/**
 * ASTs should be associated with a resource, so we create and reuse a dummy one.
 * 
 */
  def createFakeFragment() {
    val modelFragment = ModelFragmentFactory.eINSTANCE.createModelFragment
    modelFragment.package = ModelFragmentFactory.eINSTANCE.createPackageDeclaration
    modelFragment.package.name = "dummy"
    modelFragment.package.buffers = ModelFragmentFactory.eINSTANCE.createBuffers
    
    val uri = URI.createURI("dummy:/inmemory.jactr")
    val oldResource = _resourceSet.getResource(uri, false)
    var resource = oldResource
    if(resource===null)
      resource = _resourceSet.createResource(uri)
    
    resource.contents.clear  
    resource.contents.add(modelFragment)

    modelFragment
  }

  /**
   * sparse conversion of chunktype to ChunkType. It is sparse in that
   * the parent tree is no rendered. But all the derived slots are.
   */
  def convert(IChunkType chunkType, boolean includeChunks) {
    null
  }

  def convert(IChunk chunk) {
    val def = chunk(chunk)
    val fakeFragment = createFakeFragment
    val chunks = ModelFragmentFactory.eINSTANCE.createChunks;
    chunks.type = ModelFragmentFactory.eINSTANCE.createChunkType
    chunks.type.name = "dummy-chunks"
    chunks.chunks.add(def)
    fakeFragment.package.elements.add(chunks)
    
    def
  }

  def convert(IProduction production) {
    val variables = Multimaps.mutable.list.empty();
    val fakeFragment = createFakeFragment
    val productionDef = toProduction(production, variables)
    variables.valuesView.forEach [ node |
      if (node instanceof Production)
        fakeFragment.package.elements.add(node)
      if (node instanceof ChunkDef) {
        val chunks = ModelFragmentFactory.eINSTANCE.createChunks;
        chunks.type = ModelFragmentFactory.eINSTANCE.createChunkType
        chunks.type.name = "dummy-chunks"
        chunks.chunks.add(node)
        fakeFragment.package.elements.add(chunks)
        fakeFragment.package.elements.add(chunks.type)
      }
      if(node instanceof ChunkType)
       fakeFragment.package.elements.add(node)
      if(node instanceof Buffer)
       fakeFragment.package.buffers.buffers.add(node) 
    ]
    
    fakeFragment.package.elements.add(productionDef)

    productionDef
  }

  def convert(IActivationBuffer buffer) {
  }

  protected def modules(IModel model) {
    model.modules.map [ module |
      val m = ModelFragmentFactory.eINSTANCE.createModelModule
      m.moduleClass = module.class.name
      if (module instanceof IParameterized)
        m.parameters = parameters(module)
      m
    ]
  }

  protected def extensions(IModel model) {
    model.extensions.map [ module |
      val m = ModelFragmentFactory.eINSTANCE.createModelExtension
      m.extensionClass = module.class.name
      if (module instanceof IParameterized)
        m.parameters = parameters(module)
      m
    ]
  }

  protected def buffers(IModel model, MutableMultimap<String, EObject> variables) {
    val buffers = ModelFragmentFactory.eINSTANCE.createBuffers
    model.activationBuffers.map[b|b.toBuffer].forEach [ b |
      buffers.buffers.add(b)
      variables.put(b.name, b)
    ]
    buffers
  }

  protected def toBuffer(IActivationBuffer buffer) {
    val buff = ModelFragmentFactory.eINSTANCE.createBuffer
    buff.name = buffer.getName

    /*everything but contents */
    if (buffer instanceof IParameterized)
      buff.parameters = parameters(buffer)

    buff
  }

  protected def toProduction(IProduction production, MutableMultimap<String, EObject> variables) {
    val p = ModelFragmentFactory.eINSTANCE.createProduction
    p.name = production.symbolicProduction.name

    for (condition : production.symbolicProduction.conditions)
      p.conditions.add(toCondition(condition, variables) as ProductionCondition)

    for (action : production.symbolicProduction.actions)
      p.actions.add(toAction(action, variables) as ProductionAction)

    p.parameters = parameters(production.subsymbolicProduction)
    p
  }

  protected def dispatch toCondition(ChunkCondition condition, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createMatch
    ast.name = variables.get(condition.bufferName).findFirst[n|n instanceof Buffer] as Buffer
    ast.isa = ModelFragmentFactory.eINSTANCE.createIsaBlock
    ast.isa.chunk = variables.get(condition.chunk.symbolicChunk.name).findFirst[n|n instanceof ChunkDef] as ChunkDef
    if (ast.name === null) {
      ast.name = ModelFragmentFactory.eINSTANCE.createBuffer
      ast.name.name = condition.bufferName
      variables.put(ast.name.name, ast.name)
    }
    if (ast.isa.chunk === null) {
      ast.isa.chunk = ModelFragmentFactory.eINSTANCE.createChunkDef
      ast.isa.chunk.name = condition.chunk.symbolicChunk.name
      variables.put(ast.isa.chunk.name, ast.isa.chunk)
    }
    for (slot : condition.slots)
      ast.slots.add(slot(slot, false) as ComplexSlot)
    ast
  }

  protected def dispatch toCondition(ChunkTypeCondition condition, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createMatch
    ast.name = variables.get(condition.bufferName).findFirst[n|n instanceof Buffer] as Buffer
    ast.isa = ModelFragmentFactory.eINSTANCE.createIsaBlock
    ast.isa.type = variables.get(condition.chunkType.symbolicChunkType.name).
      findFirst[n|n instanceof ChunkType] as ChunkType

    if (ast.name === null) {
      ast.name = ModelFragmentFactory.eINSTANCE.createBuffer
      ast.name.name = condition.bufferName
      variables.put(ast.name.name, ast.name)
    }
    if (ast.isa.type === null) {
      ast.isa.type = ModelFragmentFactory.eINSTANCE.createChunkType
      ast.isa.type.name = condition.chunkType.symbolicChunkType.name
      variables.put(ast.isa.type.name, ast.isa.type)
    }

    for (slot : condition.slots)
      ast.slots.add(slot(slot, false) as ComplexSlot)
    ast
  }

  protected def dispatch toCondition(VariableCondition condition, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createMatch
    ast.name = variables.get(condition.bufferName).findFirst[n|n instanceof Buffer] as Buffer
    ast.isa = ModelFragmentFactory.eINSTANCE.createIsaBlock
    ast.isa.name = condition.variableName
    if (ast.name === null) {
      ast.name = ModelFragmentFactory.eINSTANCE.createBuffer
      ast.name.name = condition.bufferName
      variables.put(ast.name.name, ast.name)
    }

    for (slot : condition.slots)
      ast.slots.add(slot(slot, false) as ComplexSlot)
    ast
  }

  protected def dispatch toCondition(QueryCondition condition, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createQuery
    ast.name = variables.get(condition.bufferName).findFirst[n|n instanceof Buffer] as Buffer
    if (ast.name === null) {
      ast.name = ModelFragmentFactory.eINSTANCE.createBuffer
      ast.name.name = condition.bufferName
      variables.put(ast.name.name, ast.name)
    }
    for (slot : condition.slots)
      ast.slots.add(slot(slot, false) as ComplexSlot)
    ast
  }

  protected def dispatch toCondition(ProxyCondition condition, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createProxy
    ast.name = condition.delegateClassName
    for (slot : condition.slots)
      ast.slots.add(slot(slot, true) as SimpleSlot)
    ast
  }

  protected def dispatch toCondition(ScriptableCondition condition, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createScript
    ast.language = condition.factory.languageName
    ast.script = condition.script
    ast
  }

  protected def dispatch toAction(AddAction action, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createAdd
    ast.name = variables.get(action.bufferName).findFirst[n|n instanceof Buffer] as Buffer
    val ref = action.referant

    ast.isa = ModelFragmentFactory.eINSTANCE.createIsaBlock

    if (ast.name === null) {
      ast.name = ModelFragmentFactory.eINSTANCE.createBuffer
      ast.name.name = action.bufferName
      variables.put(ast.name.name, ast.name)
    }

    if (ref instanceof IChunkType) {
      ast.isa.type = variables.get(ref.symbolicChunkType.name).findFirst[n|n instanceof ChunkType] as ChunkType
      if (ast.isa.type === null) {
        ast.isa.type = ModelFragmentFactory.eINSTANCE.createChunkType
        ast.isa.type.name = ref.symbolicChunkType.name
        variables.put(ast.isa.type.name, ast.isa.type)
      }
    } else if (ref instanceof IChunk) {
      ast.isa.chunk = variables.get(ref.symbolicChunk.name).findFirst[n|n instanceof ChunkDef] as ChunkDef
      if (ast.isa.chunk === null) {
        ast.isa.chunk = ModelFragmentFactory.eINSTANCE.createChunkDef
        ast.isa.chunk.name = ref.symbolicChunk.name
        variables.put(ast.isa.chunk.name, ast.isa.chunk)
      }
    } else if (ref !== null)
      ast.isa.name = ref.toString
    else {
      // ref is null? empty referant..
      ast.isa = null;
    }

    for (slot : action.slots)
      ast.slots.add(slot(slot, false) as ConditionalSlot)

    ast
  }

  protected def dispatch toAction(ModifyAction action, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createModify
    ast.name = variables.get(action.bufferName).findFirst[n|n instanceof Buffer] as Buffer
    if (ast.name === null) {
      ast.name = ModelFragmentFactory.eINSTANCE.createBuffer
      ast.name.name = action.bufferName
      variables.put(ast.name.name, ast.name)
    }
    for (slot : action.slots)
      ast.slots.add(slot(slot, true) as SimpleSlot)
    ast
  }

  protected def dispatch toAction(RemoveAction action, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createRemove
    ast.name = variables.get(action.bufferName).findFirst[n|n instanceof Buffer] as Buffer
    if (ast.name === null) {
      ast.name = ModelFragmentFactory.eINSTANCE.createBuffer
      ast.name.name = action.bufferName
      variables.put(ast.name.name, ast.name)
    }
    for (slot : action.slots)
      ast.slots.add(slot(slot, true) as SimpleSlot)
    ast
  }

  protected def dispatch toAction(OutputAction action, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createOutput
    ast.string = action.text
    ast
  }

  protected def dispatch toAction(ProxyAction action, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createProxy
    ast.name = action.delegateClassName
    for (slot : action.slots)
      ast.slots.add(slot(slot, true) as SimpleSlot)
    ast
  }

  protected def dispatch toAction(ScriptableAction action, MutableMultimap<String, EObject> variables) {
    val ast = ModelFragmentFactory.eINSTANCE.createScript
    ast.language = action.factory.languageName
    ast.script = action.script
    ast
  }

  protected def dispatch toAction(IAction action, MutableMultimap<String, EObject> variables) {
    throw new IllegalStateException("Unknown action type " + action.class.name)
  }

  protected def parameters(IParameterized parameterized) {
    val parameters = ModelFragmentFactory.eINSTANCE.createParametersBlock
    for (key : parameterized.setableParameters) {
      val parameter = ModelFragmentFactory.eINSTANCE.createParameter
      parameter.name = key
      parameter.value = ModelFragmentFactory.eINSTANCE.createValue
      parameter.value.string = parameterized.getParameter(key)

      if (parameter.value.string !== null)
        parameters.parameter.add(parameter)
    }

    parameters
  }

  /**
   * recursively visit the chunktypes, rendering the 
   * roots first, and 
   */
  protected def chunkTypes(IModel model, MutableMultimap<String, EObject> variables) {
    val chunkTypes = model.declarativeModule.chunkTypes.get
    val visited = new HashMap
    val rtn = new ArrayList
    for (chunkType : chunkTypes) {
      val chunkTypeNode = chunkType(chunkType, chunkTypes, visited)
      rtn.add(chunkTypeNode)

      variables.put(chunkTypeNode.name, chunkTypeNode)

      // now the chunks node
      val allChunks = chunkType.symbolicChunkType.chunks
      if (allChunks.size > 0) {
        var Chunks chunksWrapper = null

        for (chunk : allChunks.filter[c|c.isAStrict(chunkType)]) {
          if (chunksWrapper === null) {
            chunksWrapper = ModelFragmentFactory.eINSTANCE.createChunks
            chunksWrapper.type = chunkTypeNode
            rtn.add(chunksWrapper)
          }

          val def = chunk(chunk)
          variables.put(def.name, def)
          chunksWrapper.chunks.add(def)
        }
      }
    }

    rtn
  }

  protected def ChunkDef chunk(IChunk chunk) {
    val def = ModelFragmentFactory.eINSTANCE.createChunkDef
    def.name = chunk.symbolicChunk.name

    for (slot : chunk.symbolicChunk.slots)
      def.slots.add(slot(slot, true) as SimpleSlot)

    def.parameters = parameters(chunk.subsymbolicChunk)

    def
  }

  protected def ChunkType chunkType(IChunkType toCreate, Collection<IChunkType> all, Map<String, ChunkType> visited) {
    val name = toCreate.symbolicChunkType.name
    if (visited.containsKey(name))
      return visited.get(name)

    val chunkType = ModelFragmentFactory.eINSTANCE.createChunkType
    chunkType.name = name

    if (toCreate.symbolicChunkType.parent !== null)
      chunkType.superType = chunkType(toCreate.symbolicChunkType.parent, all, visited)

    for (slot : toCreate.symbolicChunkType.slots)
      chunkType.slots.add(slot(slot, true) as SimpleSlot)

    chunkType.parameters = parameters(toCreate.subsymbolicChunkType)

    visited.put(name, chunkType)

    chunkType
  }

  protected def slot(ISlot slot, boolean forceSimple) {
    if (slot instanceof ILogicalSlot) {
      var s = null as BooleanOperation
      switch (slot.operator) {
        case ILogicalSlot.AND: s = ModelFragmentFactory.eINSTANCE.createAndSlot
        case ILogicalSlot.OR: s = ModelFragmentFactory.eINSTANCE.createOrSlot
        case ILogicalSlot.NOT: s = ModelFragmentFactory.eINSTANCE.createNotSlot
      }

      for (child : slot.slots)
        s.slots.add(slot(child, forceSimple) as ComplexSlot)

      s
    } else if (slot instanceof IConditionalSlot) {

      if (forceSimple) {
        val s = ModelFragmentFactory.eINSTANCE.createSimpleSlot
        s.name = slot.getName()
        s.value = toSlotValue(slot.getValue())
        s
      } else {

        val s = ModelFragmentFactory.eINSTANCE.createConditionalSlot
        s.name = slot.getName()
        s.value = toSlotValue(slot.getValue())
        switch (slot.condition) {
          case IConditionalSlot.EQUALS: s.condition = Condition.EQUALS
          case IConditionalSlot.LESS_THAN: s.condition = Condition.LT
          case IConditionalSlot.LESS_THAN_EQUALS: s.condition = Condition.LTE
          case IConditionalSlot.GREATER_THAN: s.condition = Condition.GT
          case IConditionalSlot.GREATER_THAN_EQUALS: s.condition = Condition.GTE
          case IConditionalSlot.NOT_EQUALS: s.condition = Condition.NOT
        }
        s
      }
    } else {
      val s = ModelFragmentFactory.eINSTANCE.createSimpleSlot
      s.name = slot.getName()
      s.value = toSlotValue(slot.getValue())
      s
    }
  }

  protected def toSlotValue(Object object) {
    var rtn = ModelFragmentFactory.eINSTANCE.createValue
    if (object === null) {
      rtn.name = "null"
    } else if (object instanceof String) {
      if (object.startsWith("="))
        rtn.name = object
      else
        rtn.string = object
    } else if (object instanceof Boolean)
      rtn.boolean = object
    else if (object instanceof Number)
      rtn.number = object.doubleValue
    else
      rtn.name = object.toString
    rtn
  }

}
