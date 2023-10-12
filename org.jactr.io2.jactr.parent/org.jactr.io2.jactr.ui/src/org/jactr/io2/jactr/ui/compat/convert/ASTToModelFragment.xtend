package org.jactr.io2.jactr.ui.compat.convert

import java.util.ArrayList
import java.util.Collection
import java.util.HashMap
import java.util.Map
import org.antlr.runtime.tree.CommonTree
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.EcoreUtil2
import org.jactr.io.antlr3.builder.JACTRBuilder
import org.jactr.io.antlr3.misc.ASTSupport
import org.jactr.io2.jactr.modelFragment.BooleanOperation
import org.jactr.io2.jactr.modelFragment.Buffer
import org.jactr.io2.jactr.modelFragment.ChunkDef
import org.jactr.io2.jactr.modelFragment.ChunkType
import org.jactr.io2.jactr.modelFragment.ComplexSlot
import org.jactr.io2.jactr.modelFragment.Condition
import org.jactr.io2.jactr.modelFragment.ModelFragment
import org.jactr.io2.jactr.modelFragment.ModelFragmentFactory

/**
 * converts old io CommonTree into io2.xtext.ModelFragment
 */
class ASTToModelFragment {

  def convert(CommonTree modelTree) {
    val modelFragment = ModelFragmentFactory.eINSTANCE.createModelFragment
    modelFragment.package = ModelFragmentFactory.eINSTANCE.createPackageDeclaration
    modelFragment.package.name = ASTSupport.getName(modelTree)

    // no imports
    for (module : modules(modelTree))
      modelFragment.package.modules.add(module)

    for (ext : extensions(modelTree))
      modelFragment.package.extensions.add(ext)

    modelFragment.package.buffers = buffers(modelTree)

    for (chunkType : chunkTypes(modelTree))
      modelFragment.package.elements.add(chunkType)

    // productions..
    for (production : productions(modelTree, modelFragment))
      modelFragment.package.elements.add(production)

    return modelFragment
  }

  protected def safeName(String name) {
    var str = name;
    if (Character.isDigit(name.charAt(0)))
      str = 'a' + str
    if(str.length==1)
     str = str+str
    if("true".equals(str) || "false".equals(str))
     str = str.toUpperCase 
    str.replace(".", "-")
  }

  protected def productions(CommonTree modelTree, ModelFragment modelFragment) {
    val rtn = new ArrayList
    for (productionAST : ASTSupport.getAllDescendantsWithType(modelTree, JACTRBuilder.PRODUCTION))
      rtn.add(production(productionAST, modelFragment))
    rtn
  }

  protected def production(CommonTree productionTree, ModelFragment modelFragment) {
    val production = ModelFragmentFactory.eINSTANCE.createProduction
    production.name = safeName(ASTSupport.getName(productionTree))
    val buffers = EcoreUtil2.getAllContentsOfType(modelFragment.package, Buffer).toMap [ buffer |
      buffer.name
    ]

    // conditions
    for (condition : conditions(productionTree, buffers))
      production.conditions.add(condition)

    // actions
    for (action : actions(productionTree, buffers))
      production.actions.add(action)

    // parameters
    val parametersAST = ASTSupport.getFirstDescendantWithType(productionTree, JACTRBuilder.PARAMETERS)
    if (parametersAST !== null && parametersAST.childCount != 0)
      production.parameters = parameters(parametersAST)

    production
  }

  protected def conditions(CommonTree productionTree, Map<String, Buffer> buffers) {
    val rtn = new ArrayList
    for (conditionAST : (productionTree.getFirstChildWithType(JACTRBuilder.CONDITIONS) as CommonTree).children)
      if (conditionAST instanceof CommonTree)
        switch (conditionAST.type) {
          case JACTRBuilder.MATCH_CONDITION:
            rtn.add(match(conditionAST, buffers))
          case JACTRBuilder.QUERY_CONDITION:
            rtn.add(query(conditionAST, buffers))
          case JACTRBuilder.PROXY_CONDITION:
            rtn.add(proxyCondition(conditionAST))
          case JACTRBuilder.SCRIPTABLE_CONDITION:
            rtn.add(scriptableCondition(conditionAST))
        }
    rtn
  }

  protected def actions(CommonTree productionTree, Map<String, Buffer> buffers) {
    val rtn = new ArrayList
    for (actionAST : (productionTree.getFirstChildWithType(JACTRBuilder.ACTIONS) as CommonTree).children)
      if (actionAST instanceof CommonTree)
        switch (actionAST.type) {
          case JACTRBuilder.ADD_ACTION:
            rtn.add(add(actionAST, buffers))
          case JACTRBuilder.MODIFY_ACTION:
            rtn.add(modify(actionAST, buffers))
          case JACTRBuilder.REMOVE_ACTION:
            rtn.add(remove(actionAST, buffers))
          case JACTRBuilder.OUTPUT_ACTION:
            rtn.add(output(actionAST))
          case JACTRBuilder.PROXY_ACTION:
            rtn.add(proxyAction(actionAST))
          case JACTRBuilder.SCRIPTABLE_ACTION:
            rtn.add(scriptableAction(actionAST))
        }

    rtn
  }

  protected def add(CommonTree addAST, Map<String, Buffer> buffers) {
    val add = ModelFragmentFactory.eINSTANCE.createAdd
    add.name = buffers.get(ASTSupport.getName(addAST))
    add.isa = isa(addAST, EcoreUtil.getRootContainer(add.name) as ModelFragment)
    for (slot : conditionalSlots(addAST))
      add.slots.add(slot)
    add
  }

  protected def isa(CommonTree isaOwnerAST, ModelFragment fragment) {
    val isa = ModelFragmentFactory.eINSTANCE.createIsaBlock
    val type = isaOwnerAST.getChild(1).type
    val txt = isaOwnerAST.getChild(1).text
    switch (type) {
      case JACTRBuilder.VARIABLE:
        isa.name = txt
      case JACTRBuilder.CHUNK_IDENTIFIER:
        isa.chunk = EcoreUtil2.getAllContentsOfType(fragment, ChunkDef).filter [ def |
          def.name.equals(txt)
        ].last
      case JACTRBuilder.CHUNK_TYPE_IDENTIFIER:
        isa.type = EcoreUtil2.getAllContentsOfType(fragment, ChunkType).filter [ def |
          def.name.equals(txt)
        ].last
    }

   

    isa
  }

  protected def modify(CommonTree modifyAST, Map<String, Buffer> buffers) {
    val modify = ModelFragmentFactory.eINSTANCE.createModify
    modify.name = buffers.get(ASTSupport.getName(modifyAST))
    for (slot : simpleSlots(modifyAST))
      modify.slots.add(slot)
    modify
  }

  protected def remove(CommonTree removeAST, Map<String, Buffer> buffers) {
    val remove = ModelFragmentFactory.eINSTANCE.createRemove
    remove.name = buffers.get(ASTSupport.getName(removeAST))
    for (slot : simpleSlots(removeAST))
      remove.slots.add(slot)
    remove
  }

  protected def output(CommonTree outputAST) {
    val output = ModelFragmentFactory.eINSTANCE.createOutput
    output.string = ASTSupport.getFirstDescendantWithType(outputAST, JACTRBuilder.STRING).text
    output
  }

  protected def match(CommonTree matchAST, Map<String, Buffer> buffers) {
    val match = ModelFragmentFactory.eINSTANCE.createMatch
    match.name = buffers.get(ASTSupport.getName(matchAST))
    match.isa = isa(matchAST, EcoreUtil.getRootContainer(match.name) as ModelFragment)
    for (slot : booleanAndConditionals(ASTSupport.getFirstDescendantWithType(matchAST, JACTRBuilder.SLOTS)))
      match.slots.add(slot)
    return match
  }

  protected def query(CommonTree queryAST, Map<String, Buffer> buffers) {
    val query = ModelFragmentFactory.eINSTANCE.createQuery
    query.name = buffers.get(ASTSupport.getName(queryAST))
    for (slot : booleanAndConditionals(ASTSupport.getFirstDescendantWithType(queryAST, JACTRBuilder.SLOTS)))
      query.slots.add(slot)
    return query
  }

  protected def proxyCondition(CommonTree proxyAST) {
    val proxy = ModelFragmentFactory.eINSTANCE.createProxy
    proxy.name = ASTSupport.getFirstDescendantWithType(proxyAST, JACTRBuilder.CLASS_SPEC).text
    for (slot : simpleSlots(proxyAST))
      proxy.slots.add(slot)

    proxy
  }

  protected def proxyAction(CommonTree proxyAST) {
    val proxy = ModelFragmentFactory.eINSTANCE.createProxy
    proxy.name = ASTSupport.getFirstDescendantWithType(proxyAST, JACTRBuilder.CLASS_SPEC).text
    for (slot : simpleSlots(proxyAST))
      proxy.slots.add(slot)

    proxy
  }

  protected def scriptableCondition(CommonTree scriptAST) {
    val script = ModelFragmentFactory.eINSTANCE.createScript
    script.language = ASTSupport.getFirstDescendantWithType(scriptAST, JACTRBuilder.LANG).text
    script.script = ASTSupport.getFirstDescendantWithType(scriptAST, JACTRBuilder.SCRIPT).text

    script
  }

  protected def scriptableAction(CommonTree scriptAST) {
    val script = ModelFragmentFactory.eINSTANCE.createScript
    script.language = ASTSupport.getFirstDescendantWithType(scriptAST, JACTRBuilder.LANG).text
    script.script = ASTSupport.getFirstDescendantWithType(scriptAST, JACTRBuilder.SCRIPT).text 

    script
  }

  protected def booleanAndConditionals(CommonTree slots) {
    val rtn = new ArrayList
    if(slots === null) return rtn
    for (slotAST : slots.children)
      if (slotAST instanceof CommonTree) {
        val slot = booleanOrConditional(slotAST) as ComplexSlot
        rtn.add(slot)
      }
    rtn
  }

  protected def booleanOrConditional(CommonTree slotAST) {
    switch (slotAST.type) {
      case JACTRBuilder.SLOT: return conditionalSlot(slotAST) as ComplexSlot
      case JACTRBuilder.LOGIC: return logicalSlot(slotAST) as ComplexSlot
    }

    return null as BooleanOperation
  }

  protected def logicalSlot(CommonTree slotAST) {
    val operation = slotAST.getChild(0).type
    if (operation == JACTRBuilder.AND)
      return and(slotAST)
    else if (operation == JACTRBuilder.OR)
      return or(slotAST)
    else
      not(slotAST)
  }

  /*
   * logical slots have the operation as a child with the child slots
   */
  protected def resolveSlotsForLogical(CommonTree slotContainer) {
    val rtn = new ArrayList
    for (child : slotContainer.children)
      if (child instanceof CommonTree) {
        if (child.type == JACTRBuilder.SLOT || child.type == JACTRBuilder.LOGIC) {
          val slot = booleanOrConditional(child)
          rtn.add(slot as ComplexSlot)
        }
      }
    rtn
  }

  protected def and(CommonTree slotAST) {
    val rtn = ModelFragmentFactory.eINSTANCE.createAndSlot
    for (slot : resolveSlotsForLogical(slotAST))
      rtn.slots.add(slot)

    rtn
  }

  protected def or(CommonTree slotAST) {
    val rtn = ModelFragmentFactory.eINSTANCE.createOrSlot
    for (slot : resolveSlotsForLogical(slotAST))
      rtn.slots.add(slot)
    rtn
  }

  protected def not(CommonTree slotAST) {
    val rtn = ModelFragmentFactory.eINSTANCE.createNotSlot
    for (slot : resolveSlotsForLogical(slotAST))
      rtn.slots.add(slot)
    rtn
  }

  protected def conditionalSlots(CommonTree owner) {
    val rtn = new ArrayList
    for (slotAST : ASTSupport.getAllDescendantsWithType(owner, JACTRBuilder.SLOT)) {
      rtn.add(conditionalSlot(slotAST))
    }
    rtn
  }

  protected def conditionalSlot(CommonTree slotAST) {
    val conditional = ModelFragmentFactory.eINSTANCE.createConditionalSlot
    conditional.name = safeName(ASTSupport.getName(slotAST))
    conditional.condition = asCondition(slotAST)
    conditional.value = asValue(slotAST.getChild(2) as CommonTree)
    conditional
  }

  protected def asValue(CommonTree valueAST) {
    val value = ModelFragmentFactory.eINSTANCE.createValue
    val valueString = valueAST.text

    if (valueAST.type == JACTRBuilder.NUMBER)
      value.number = Double.parseDouble(valueString)
    else if (valueAST.type == JACTRBuilder.IDENTIFIER)
      value.name = valueString
    else if (valueAST.type == JACTRBuilder.VARIABLE)
      value.name = valueString
    else if (valueAST.type == JACTRBuilder.STRING) {
      if ("true".equals(valueString) || "false".equals(valueString))
        value.boolean = Boolean.parseBoolean(valueString)
      else
        value.string = valueString
    }

    value
  }

  protected def asCondition(CommonTree slotAST) {
    switch (slotAST.getChild(1).type) {
      case JACTRBuilder.EQUALS: return Condition.EQUALS
      case JACTRBuilder.NOT: return Condition.NOT
      case JACTRBuilder.LT: return Condition.LT
      case JACTRBuilder.LTE: return Condition.LTE
      case JACTRBuilder.GT: return Condition.GT
      case JACTRBuilder.GTE: return Condition.GTE
      default: throw new RuntimeException("Unknown condition type : " + slotAST.getChild(1).type)
    }
  }

  protected def chunkTypes(CommonTree modelTree) {
    val rtn = new ArrayList
    val map = new HashMap
    val chunkTypeASTs = ASTSupport.getAllDescendantsWithType(modelTree, JACTRBuilder.CHUNK_TYPE)
    for (typeAST : chunkTypeASTs) {
      val chunkType = chunkType(ASTSupport.getName(typeAST), chunkTypeASTs, map)

      // chunks wrapper  
      val chunks = ModelFragmentFactory.eINSTANCE.createChunks
      chunks.type = chunkType
      // chunks themselves
      for (chunk : chunks(typeAST))
        chunks.chunks.add(chunk)

      rtn.add(chunkType)

      if (chunks.chunks.size > 0)
        rtn.add(chunks)
    }

    rtn
  }

  protected def ChunkType chunkType(String chunkTypeName, Collection<CommonTree> chunkTypeNodes,
    Map<String, ChunkType> chunkTypes) {
    if (chunkTypes.containsKey(chunkTypeName))
      return chunkTypes.get(chunkTypeName)

    val chunkTypeAST = chunkTypeNodes.filter[node|ASTSupport.getName(node).equals(chunkTypeName)].last
    val chunkType = ModelFragmentFactory.eINSTANCE.createChunkType
    val parents = chunkTypeAST.getChild(1)

    var parentName = null as String
    if(parents.getChildCount > 0) parentName = (parents.getChild(0) as CommonTree).text

    chunkType.name = ASTSupport.getName(chunkTypeAST)

    if (parentName !== null)
      chunkType.superType = chunkType(parentName, chunkTypeNodes, chunkTypes)

    // configure chunktype
    // slots
    for (slot : simpleSlots(chunkTypeAST.getChild(2) as CommonTree))
      chunkType.slots.add(slot)

    val parametersAST = ASTSupport.getFirstDescendantWithType(chunkTypeAST, JACTRBuilder.PARAMETERS)
    if (parametersAST !== null && parametersAST.childCount != 0)
      chunkType.parameters = parameters(parametersAST)

    chunkTypes.put(chunkType.name, chunkType)

    return chunkType
  }

  protected def chunks(CommonTree chunkTypeTree) {
    val rtn = new ArrayList
    for (chunkAST : ASTSupport.getAllDescendantsWithType(chunkTypeTree, JACTRBuilder.CHUNK)) {
      val chunk = ModelFragmentFactory.eINSTANCE.createChunkDef
      chunk.name = safeName(ASTSupport.getName(chunkAST))

      // slots
      for (slot : simpleSlots(chunkAST))
        chunk.slots.add(slot)

      val parametersAST = ASTSupport.getFirstDescendantWithType(chunkAST, JACTRBuilder.PARAMETERS)
      if (parametersAST !== null && parametersAST.childCount != 0)
        chunk.parameters = parameters(parametersAST)

      rtn.add(chunk)
    }
    rtn
  }

  protected def simpleSlots(CommonTree slotContainingTree) {
    val rtn = new ArrayList
    for (slotAST : ASTSupport.getAllDescendantsWithType(slotContainingTree, JACTRBuilder.SLOT)) {
      /*
       * IDENTIFIER (null), STRING (boolean), NUMBER to 
       * ID (null, variable), STRING, boolean, number
       */
      val slot = ModelFragmentFactory.eINSTANCE.createSimpleSlot
      slot.name = safeName(ASTSupport.getName(slotAST))
      slot.value = ModelFragmentFactory.eINSTANCE.createValue

      val valueAST = ASTSupport.getLastDescendant(slotAST)
      val valueString = valueAST.text;
      if (valueAST.type == JACTRBuilder.NUMBER)
        slot.value.number = Double.parseDouble(valueString)
      else if (valueAST.type == JACTRBuilder.IDENTIFIER)
        slot.value.name = safeName(valueString)
      else if (valueAST.type == JACTRBuilder.VARIABLE)
        slot.value.name = valueString
      else if (valueAST.type == JACTRBuilder.STRING) {
        if ("true".equals(valueString) || "false".equals(valueString))
          slot.value.boolean = Boolean.parseBoolean(valueString)
        else
          slot.value.string = valueString
      }

      rtn.add(slot)
    }
    rtn
  }

  protected def buffers(CommonTree modelTree) {
    val rtn = ModelFragmentFactory.eINSTANCE.createBuffers

    for (bufferAST : ASTSupport.getAllDescendantsWithType(modelTree, JACTRBuilder.BUFFER)) {
      val buffer = ModelFragmentFactory.eINSTANCE.createBuffer
      buffer.name = ASTSupport.getName(bufferAST)
      // source chunks
      // parameters..
      val parametersAST = ASTSupport.getFirstDescendantWithType(bufferAST, JACTRBuilder.PARAMETERS)
      if (parametersAST !== null && parametersAST.childCount != 0)
        buffer.parameters = parameters(parametersAST)

      rtn.buffers.add(buffer)
    }
    rtn
  }

  protected def modules(CommonTree modelTree) {
    val rtn = new ArrayList
    for (module : ASTSupport.getAllDescendantsWithType(modelTree, JACTRBuilder.MODULE)) {
      val moduleDesc = ModelFragmentFactory.eINSTANCE.createModelModule
      moduleDesc.moduleClass = ASTSupport.getFirstDescendantWithType(module, JACTRBuilder.CLASS_SPEC).text
      // parameters..
      val parametersAST = ASTSupport.getFirstDescendantWithType(module, JACTRBuilder.PARAMETERS)
      if (parametersAST !== null)
        moduleDesc.parameters = parameters(parametersAST)

      rtn.add(moduleDesc)
    }

    rtn
  }

  protected def extensions(CommonTree modelTree) {
    val rtn = new ArrayList
    for (module : ASTSupport.getAllDescendantsWithType(modelTree, JACTRBuilder.EXTENSION)) {
      val extDesc = ModelFragmentFactory.eINSTANCE.createModelExtension
      extDesc.extensionClass = ASTSupport.getFirstDescendantWithType(module, JACTRBuilder.CLASS_SPEC).text
      // parameters..
      val parametersAST = ASTSupport.getFirstDescendantWithType(module, JACTRBuilder.PARAMETERS)
      if (parametersAST !== null)
        extDesc.parameters = parameters(parametersAST)
      rtn.add(extDesc)
    }

    rtn
  }

  protected def parameters(CommonTree parameters) {
    val pBlock = ModelFragmentFactory.eINSTANCE.createParametersBlock
    for (parameterAST : ASTSupport.getAllDescendantsWithType(parameters, JACTRBuilder.PARAMETER)) {
      val name = ASTSupport.getName(parameterAST)
      val str = ASTSupport.getFirstDescendantWithType(parameterAST, JACTRBuilder.STRING).text
      val p = ModelFragmentFactory.eINSTANCE.createParameter
      p.name = name
      p.value = ModelFragmentFactory.eINSTANCE.createValue
      p.value.string = str

      pBlock.parameter.add(p)
    }
    pBlock
  }

}
