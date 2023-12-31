/*
 * generated by Xtext 2.16.0
 */
package org.jactr.io2.jactr.validation

import com.google.inject.Inject
import java.util.Collections
import java.util.HashSet
import java.util.concurrent.atomic.AtomicInteger
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.jactr.io2.jactr.modelFragment.Add
import org.jactr.io2.jactr.modelFragment.Buffer
import org.jactr.io2.jactr.modelFragment.ChunkDef
import org.jactr.io2.jactr.modelFragment.ChunkType
import org.jactr.io2.jactr.modelFragment.Chunks
import org.jactr.io2.jactr.modelFragment.ConditionalSlot
import org.jactr.io2.jactr.modelFragment.Import
import org.jactr.io2.jactr.modelFragment.Match
import org.jactr.io2.jactr.modelFragment.ModelExtension
import org.jactr.io2.jactr.modelFragment.ModelFragmentPackage
import org.jactr.io2.jactr.modelFragment.ModelModule
import org.jactr.io2.jactr.modelFragment.Modify
import org.jactr.io2.jactr.modelFragment.PackageDeclaration
import org.jactr.io2.jactr.modelFragment.Parameters
import org.jactr.io2.jactr.modelFragment.Production
import org.jactr.io2.jactr.modelFragment.ProductionAction
import org.jactr.io2.jactr.modelFragment.Proxy
import org.jactr.io2.jactr.modelFragment.Remove
import org.jactr.io2.jactr.modelFragment.SimpleSlot
import org.jactr.io2.jactr.modelFragment.Value

/**
 * This class contains custom validation rules. 
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class ModelFragmentValidator extends AbstractModelFragmentValidator {

  @Inject extension ModelFragmentUtil
  @Inject IClassNameValidator _classNameValidator

  public static val BAD_PACKAGE = 'badPackage'
  public static val BAD_SLOT_NAME = 'badSlotName'
  public static val SHADOW_SLOT_NAME = 'shadowSlotName'
  public static val CYCLIC_TYPE = "cyclicType"
  public static val BAD_VARIABLE = "badVariable"
  public static val REDEFINED = "redefined"
  public static val UNKNOWN_CHUNK = "unknownChunk"

  override handleExceptionDuringValidation(Throwable targetException) throws RuntimeException{
    targetException.printStackTrace;
    super.handleExceptionDuringValidation(targetException);
  }

  def isValidClassName(Resource resource, String className) {
    _classNameValidator.isValidClassName(resource, className)
  }

  @Check(NORMAL)
  def checkPackagePath(PackageDeclaration dec) {
    if (dec.name.indexOf('-') != -1 || dec.name.indexOf(':') != -1) {
      error("Namespaces should not include characters -,:", ModelFragmentPackage.Literals.PACKAGE_DECLARATION__NAME,
        BAD_PACKAGE)
    }

    val packagePath = dec.name.replace('.', '/')
    var path = dec.eResource.URI.path
    path = path.substring(0, path.lastIndexOf(dec.eResource.URI.fileExtension) - 1)
    if (!path.endsWith(packagePath))
      error('Namespace ' + packagePath + ' should be stored under the subpath ' + packagePath + ' not ' + path,
        ModelFragmentPackage.Literals.PACKAGE_DECLARATION__NAME, BAD_PACKAGE)

    val visibleExternal = getVisibleEObjectDescriptionsByType(dec, dec.eClass)
    val qualified = dec.name

    if (visibleExternal.containsKey(qualified)) {
      error('Package ' + dec.name + ' is already defined ' + visibleExternal.get(qualified).EObjectURI.path + '.', dec,
        ModelFragmentPackage.Literals.PACKAGE_DECLARATION__NAME, BAD_PACKAGE)
    }
  }

  @Check(FAST)
  def checkImportsOnClasspath(Import importDec) {
    var resource = importDec.importedNamespace.substring(0, importDec.importedNamespace.length - 2).replace('.', '/') +
      ".jactr";
    if (!_classNameValidator.canResolve(importDec.eResource, resource)) {
      error("Could not find " + resource + " in classpath", importDec,
        ModelFragmentPackage.Literals.IMPORT__IMPORTED_NAMESPACE);
      dump(importDec.eResource);
    }
  }

  @Check(NORMAL)
  def checkSlotValues(PackageDeclaration packageDec) {
    val symbolTable = fullSymbolTable(packageDec)

    val allValues = EcoreUtil2.getAllContentsOfType(packageDec, Value)
    allValues.forEach([ value |

      if (value.isID) {
        var literal = ModelFragmentPackage.Literals.SIMPLE_SLOT__VALUE
        if (value.eContainer instanceof ConditionalSlot)
          literal = ModelFragmentPackage.Literals.CONDITIONAL_SLOT__VALUE
        if (!symbolTable.containsKey(value.name)) {
          error(value.name + " is an unknown symbol.", value.eContainer, literal, UNKNOWN_CHUNK, value.name)
        } else {
          val ref = symbolTable.get(value.name).EClass
          if (!ref.name.equals("ChunkDef") && !ref.name.equals("ChunkType"))
            warning("Are you sure you mean " + value.name + ", it's a " + ref.name, value.eContainer, literal)
        }
      }
    ])
  }

  @Check(NORMAL)
  def checkParameterIds(Parameters parametersDef) {
    val symbolTable = fullSymbolTable(parametersDef)
    val integer = new AtomicInteger(0)
    parametersDef.elements.forEach([ id |
      if (symbolTable.get(id) === null)
        error(id + " is an unknown symbol.", parametersDef, ModelFragmentPackage.Literals.PARAMETERS__ELEMENTS,
          integer.get, UNKNOWN_CHUNK, id);
      integer.incrementAndGet
    ])
  }

  @Check(FAST)
  def checkClassnameOfModule(ModelModule module) {
    if (!isValidClassName(module.eResource, module.moduleClass))
      error(module.moduleClass + " could not be found in current classpath", module,
        ModelFragmentPackage.Literals.MODEL_MODULE__MODULE_CLASS);
  }

  @Check(FAST)
  def checkClassnameOfExtension(ModelExtension ext) {
    if (!isValidClassName(ext.eResource, ext.extensionClass))
      error(ext.extensionClass + " could not be found in current classpath", ext,
        ModelFragmentPackage.Literals.MODEL_EXTENSION__EXTENSION_CLASS);
  }

  /**
   * does this slotName exist in the chunktype hierarchy
   */
  def isValidSlot(ChunkType type, String slotName) {
    var cType = type;
    val visited = new HashSet
    while (cType !== null) {
      // defense incase of inadvertant cycle
      if (visited.contains(cType))
        return false
      visited.add(cType)
      // check slots
      for (slot : cType.slots)
        if (slot.name.equals(slotName))
          return true

      cType = type.superType
    }
    false
  }

  /*
   * Chunk validation
   */
  @Check(FAST)
  def checkSlotsOf(Chunks chunks) {
    val chunkType = chunks.type;
    val knownSlots = allSlotNames(chunkType)
    for (chunk : chunks.chunks)
      for (slot : chunk.slots)
        if (!knownSlots.contains(slot.name))
          error(slot.name + ' is not a known slot in ' + chunkType.name + ' available:' + knownSlots, slot,
            ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME, BAD_SLOT_NAME, knownSlots)
  }

  @Check(NORMAL)
  def checkUniqueChunk(ChunkDef chunkDef) {

    val existing = EcoreUtil2.getAllContentsOfType(EcoreUtil.getRootContainer(chunkDef, false), Chunks).flatMap [ chunks |
      chunks.chunks
    ].filter[node|node != chunkDef].map [ node |
      node.name
    ].filter[name|name.equals(chunkDef.name)].last

    if (existing !== null && !chunkDef.override) {
      error(chunkDef.name + ' is already defined in this file. Use @Override to replace', chunkDef,
        ModelFragmentPackage.Literals.OVERRIDABLE__NAME, REDEFINED)
      return
    }

    val visibleExternal = getVisibleEObjectDescriptionsByType(chunkDef, chunkDef.eClass)
    val qualified = chunkDef.name

    if (visibleExternal.containsKey(qualified) && !chunkDef.override)
      error(chunkDef.name + ' is already defined in ' + visibleExternal.get(qualified).EObjectURI.path +
        '. Use @Override to replace', chunkDef, ModelFragmentPackage.Literals.OVERRIDABLE__NAME, REDEFINED)
  }

  /*
   * chunktype validation
   */
  @Check(FAST)
  def checkSlotsOf(ChunkType chunkType) {
    val knownSlots = allSlotNames(chunkType.superType)
    for (slot : chunkType.slots)
      if (knownSlots.contains(slot.name))
        warning(slot.name + ' shadows a slot defined by its supertype. Default value overridden', slot,
          ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME, SHADOW_SLOT_NAME)
  }

  @Check(NORMAL)
  def checkUniqueChunkType(ChunkType chunkType) {

    val existing = EcoreUtil2.getAllContentsOfType(chunkType.eContainer, ChunkType).filter[node|node != chunkType].map [ node |
      node.name
    ].filter[name|name.equals(chunkType.name)].last

    if (existing !== null && !chunkType.override) {
      error(chunkType.name + ' is already defined elsewhere in this source. Use @Override to replace', chunkType,
        ModelFragmentPackage.Literals.OVERRIDABLE__NAME, REDEFINED)
      return
    }

    val visibleExternal = getVisibleEObjectDescriptionsByType(chunkType, chunkType.eClass)
    val qualified = chunkType.name

    if (visibleExternal.containsKey(qualified) && !chunkType.override) {
      error(chunkType.name + ' is already defined ' + visibleExternal.get(qualified).EObjectURI.path +
        '. Use @Override to replace', chunkType, ModelFragmentPackage.Literals.OVERRIDABLE__NAME, REDEFINED)
    }
  }

  @Check(NORMAL)
  def checkUniqueProduction(Production production) {

    val existing = EcoreUtil2.getAllContentsOfType(production.eContainer, Production).filter [ node |
      node != production
    ].map [ node |
      node.name
    ].filter[name|name.equals(production.name)].last

    if (existing !== null && !production.override) {
      error(production.name + ' is already defined elsewhere in this source. Use @Override to replace', production,
        ModelFragmentPackage.Literals.OVERRIDABLE__NAME, REDEFINED)
      return
    }

    val visibleExternal = getVisibleEObjectDescriptionsByType(production, production.eClass)
    val qualified = production.name

    if (visibleExternal.containsKey(qualified) && !production.override) {
      error(production.name + ' is already defined ' + visibleExternal.get(qualified).EObjectURI.path +
        '. Use @Override to replace', production, ModelFragmentPackage.Literals.OVERRIDABLE__NAME, REDEFINED)
    }
  }

  @Check(NORMAL)
  def checkUniqueBuffer(Buffer buffer) {

    val existing = EcoreUtil2.getAllContentsOfType(buffer.eContainer, Buffer).filter[node|node !== buffer].map [ node |
      node.name
    ].filter[name|name.equals(buffer.name)].last

    if (existing !== null && !buffer.override) {
      error(buffer.name + ' is already defined elsewhere in this source. Use @Override to replace', buffer,
        ModelFragmentPackage.Literals.OVERRIDABLE__NAME, REDEFINED)
      return
    }

    val visibleExternal = bufferSymbolTable(buffer)
    val qualified = buffer.name

    if (visibleExternal.get(qualified).size() > 1 && !buffer.override) {

      // remove buffer
      var inWho = visibleExternal.get(qualified).stream.map([node|node.EObjectURI.path]).filter([ name |
        !name.equals(buffer.eResource.URI.path)
      ]).findFirst;
      if (inWho.isPresent())
        error(buffer.name + ' is already defined in ' + inWho.get() + '. Use @Override to replace', buffer,
          ModelFragmentPackage.Literals.OVERRIDABLE__NAME, REDEFINED)
    }
  }

  @Check(FAST)
  def checkCyclicChunkType(ChunkType chunkType) {
    if (chunkType.chunkTypeHierarchy.contains(chunkType))
      error(chunkType.name + ' has a cyclic dependency.', chunkType.superType,
        ModelFragmentPackage.Literals.CHUNK_TYPE__SUPER_TYPE, CYCLIC_TYPE)
  }

  /**
   * Match in three flavors 
   */
  @Check(FAST)
  def checkSlotsOfMatch(Match match) {
    var allSlotNames = Collections.emptySet

    if (match.isa.type !== null)
      allSlotNames = allSlotNames(match.isa.type)
    if (match.isa.chunk !== null)
      allSlotNames = allSlotNames(match.isa.chunk)

    if (match.isa.name !== null) {
      val variableRef = match.isa
      val variables = (match.eContainer as Production).variables // (match)
      val hasProxyOrScript = (match.eContainer as Production).couldHaveHiddenBindings
      if (!variables.contains(variableRef.name))
        if (!hasProxyOrScript)
          error(variableRef.name + ' was not bound on left hand side', match, ModelFragmentPackage.Literals.MATCH__ISA,
            BAD_VARIABLE, variables)
        else
          warning(variableRef.name + ' was not explicitly bound, but could be by proxy or script', match,
            ModelFragmentPackage.Literals.MATCH__ISA, BAD_VARIABLE, variables)
//slots
      for (slot : EcoreUtil2.getAllContentsOfType(match, ConditionalSlot))
        if (slot.name.startsWith(":")) {
          error(slot.name + " is not valid. :state shorthand is not permitted. Use separate query instead", slot,
            ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_SLOT_NAME)
        } else if (slot.name.startsWith("=")) {
          if (!variables.contains(slot.name))
            if (!hasProxyOrScript)
              error(slot.name + ' was not bound on left hand side', slot,
                ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_VARIABLE, variables)
            else
              warning(slot.name + ' was not explicitly bound, but could be by proxy or script', slot,
                ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_VARIABLE, variables)
        } else
          warning(slot.name + ' could not be validated at compile. Can only be inferred at runtime. ', slot,
            ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_SLOT_NAME)
    } else // normal chunk/type 
      for (slot : EcoreUtil2.getAllContentsOfType(match, ConditionalSlot))
        if (slot.name.startsWith(":")) {
          error(slot.name + " is not valid. :state shorthand is not permitted. Use separate query instead", slot,
            ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_SLOT_NAME, allSlotNames)
        } else if (slot.name.startsWith("=")) {
          val variables = (match.eContainer as Production).variables
          val hasProxyOrScript = (match.eContainer as Production).couldHaveHiddenBindings
          if (!variables.contains(slot.name))
            if (!hasProxyOrScript)
              error(slot.name + ' was not bound on left hand side', slot,
                ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_VARIABLE, variables)
            else
              warning(slot.name + ' was not explicitly bound, but could be by proxy or script', slot,
                ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_VARIABLE, variables)
        } else if (!allSlotNames.contains(slot.name))
          error(slot.name + ' is not a known slot. available:' + allSlotNames, slot,
            ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_SLOT_NAME, allSlotNames)
  }

  /**
   * three flavors of Add (type, chunk, variable)
   */
  @Check(FAST)
  def checkSlotsOfAddWithChunkType(Add match) {
    var allSlotNames = Collections.emptySet

    if (match.isa === null) {
      val mates = EcoreUtil2.getAllContentsOfType(match.eContainer, Match).filter([ m |
        match.name.name.equals(m.name.name)
      ])
      val mate = mates.filter([ m |
        m.isa.type !== null || m.isa.chunk !== null
      ]).last as Match
      if (mate !== null) {
        if (mate.isa.type !== null)
          allSlotNames = allSlotNames(mate.isa.type)
        if (mate.isa.chunk !== null)
          allSlotNames = allSlotNames(mate.isa.chunk)
      }
    } else if (match.isa.type !== null)
      allSlotNames = allSlotNames(match.isa.type)
    else if (match.isa.chunk !== null)
      allSlotNames = allSlotNames(match.isa.chunk)

    if (match.isa !== null && match.isa.name !== null) {
      val variableRef = match.isa
      val variables = (match.eContainer as Production).variables
      val hasProxyOrScript = (match.eContainer as Production).couldHaveHiddenBindings
      if (!variables.contains(variableRef.name))
        if (!hasProxyOrScript)
          error(variableRef.name + ' was not bound on left hand side', match, ModelFragmentPackage.Literals.ADD__ISA,
            BAD_VARIABLE, variables)
        else
          warning(variableRef.name + ' was not explicitly bound, but could be by proxy or script', match,
            ModelFragmentPackage.Literals.ADD__ISA, BAD_VARIABLE, variables)

//check slots      
      for (slot : EcoreUtil2.getAllContentsOfType(match, ConditionalSlot))
        if (slot.name.startsWith("=")) {
          if (!variables.contains(slot.name))
            if (!hasProxyOrScript)
              error(slot.name + ' was not bound on left hand side', slot,
                ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_VARIABLE, variables)
            else
              warning(slot.name + ' was not explicitly bound, but could be by proxy or script', slot,
                ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_VARIABLE, variables)
        } else if (!slot.name.startsWith(':'))
          warning(slot.name + ' could not be validated at compile. Can only be inferred at runtime. ', slot,
            ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_SLOT_NAME)
    } else
      for (slot : EcoreUtil2.getAllContentsOfType(match, ConditionalSlot))
        if (slot.name.startsWith("=")) {
          val variables = (match.eContainer as Production).variables
          val hasProxyOrScript = (match.eContainer as Production).couldHaveHiddenBindings
          if (!variables.contains(slot.name))
            if (!hasProxyOrScript)
              error(slot.name + ' was not bound on left hand side', slot,
                ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_VARIABLE, variables)
            else
              warning(slot.name + ' was not explicitly bound, but could be by proxy or script', slot,
                ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_VARIABLE, variables)
        } else if (!allSlotNames.contains(slot.name) && !slot.name.startsWith(':'))
          error(slot.name + ' is not a known slot. available:' + allSlotNames, slot,
            ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, BAD_SLOT_NAME, allSlotNames)

  }

  /**
   * Modify
   */
  @Check(FAST)
  def checkSlotsOfModify(Modify modify) {
    val mates = EcoreUtil2.getAllContentsOfType(modify.eContainer, Match).filter([ match |
      modify.name.name.equals(match.name.name)
    ])
    val mate = mates.filter([ match |
      match.isa.type !== null || match.isa.chunk !== null
    ]).last as Match

    if (mates.size == 0) {
      error(modify.name.name + ' was never referenced on the left hand side', modify,
        ModelFragmentPackage.Literals.SIMPLE_SLOT_BLOCK__NAME);
      return;
    }

    var allSlotNames = new HashSet;
    if (mate !== null && mate.isa !== null) {
      if (mate.isa.type !== null)
        allSlotNames = allSlotNames(mate.isa.type)
      if (mate.isa.chunk !== null)
        allSlotNames = allSlotNames(mate.isa.chunk)
    }

    for (slot : EcoreUtil2.getAllContentsOfType(modify, SimpleSlot))
      if (slot.name.startsWith("=")) {
        val variables = (modify.eContainer as Production).variables
        val hasProxyOrScript = (modify.eContainer as Production).couldHaveHiddenBindings
        if (!variables.contains(slot.name))
          if (!hasProxyOrScript)
            error(slot.name + ' was not bound on left hand side', slot, ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME,
              BAD_VARIABLE, variables)
          else
            warning(slot.name + ' was not explicitly bound, but could be by proxy or script', slot,
              ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME, BAD_VARIABLE, variables)
      } else if (!allSlotNames.contains(slot.name))
        error(slot.name + ' is not a known slot. available:' + allSlotNames, slot,
          ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME, BAD_SLOT_NAME, allSlotNames)
  }

  /**
   * Remove
   */
  @Check(FAST)
  def checkSlotsOfRemove(Remove remove) {
    val mates = EcoreUtil2.getAllContentsOfType(remove.eContainer, Match).filter([ match |
      remove.name.name.equals(match.name.name)
    ])
    val mate = mates.filter([ match |
      match.isa.type !== null || match.isa.chunk !== null
    ]).last as Match

    if (mates.size == 0) {
      warning(remove.name.name + ' was never referenced on the left hand side', remove,
        ModelFragmentPackage.Literals.SIMPLE_SLOT_BLOCK__NAME);
      return;
    }

    var allSlotNames = new HashSet;
    if (mate !== null && mate.isa !== null) {
      if (mate.isa.type !== null)
        allSlotNames = allSlotNames(mate.isa.type)
      if (mate.isa.chunk !== null)
        allSlotNames = allSlotNames(mate.isa.chunk)
    }

    for (slot : EcoreUtil2.getAllContentsOfType(remove, SimpleSlot))
      if (slot.name.startsWith("=")) {
        val variables = (remove.eContainer as Production).variables
        val hasProxyOrScript = (remove.eContainer as Production).couldHaveHiddenBindings
        if (!variables.contains(slot.name))
          if (!hasProxyOrScript)
            error(slot.name + ' was not bound on left hand side', slot, ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME,
              BAD_VARIABLE, variables)
          else
            warning(slot.name + ' was not explicitly bound, but could be by proxy or script', slot,
              ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME, BAD_VARIABLE, variables)
      } else if (!allSlotNames.contains(slot.name))
        error(slot.name + ' is not a known slot. available:' + allSlotNames, slot,
          ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME, BAD_SLOT_NAME, allSlotNames)
  }

  @Check(FAST)
  def checkProxy(Proxy proxy) {
    val className = proxy.name
    if (!isValidClassName(proxy.eResource, className))
      error(className + " could not be found in current classpath", proxy, ModelFragmentPackage.Literals.PROXY__NAME);
  }

  @Check(FAST)
  def checkVariablesInRHS(Production production) {
    val variables = production.variables
    val hasProxyOrScript = production.couldHaveHiddenBindings

    EcoreUtil2.getAllContentsOfType(production, ProductionAction).forEach(
      [ action |

      EcoreUtil2.getAllContentsOfType(action, Value).forEach([ value |
        if (value.isVariable)
          if (!variables.contains(value.name)) {
            var literal = ModelFragmentPackage.Literals.CONDITIONAL_SLOT__VALUE;
            if (value.eContainer instanceof SimpleSlot)
              literal = ModelFragmentPackage.Literals.SIMPLE_SLOT__VALUE;
            if (!hasProxyOrScript)
              error(value.name + ' was not bound on left hand side', value.eContainer, literal, BAD_VARIABLE, variables)
            else
              warning(value.name +
                ' was not explicitly bound, but could be provided at runtime by proxy, script, or meta',
                value.eContainer, literal, BAD_VARIABLE, variables)
          }
      ])
    ])
  }

}
