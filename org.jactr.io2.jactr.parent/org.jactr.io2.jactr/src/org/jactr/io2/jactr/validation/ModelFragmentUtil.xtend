package org.jactr.io2.jactr.validation

import com.google.common.collect.Iterables
import com.google.common.collect.LinkedListMultimap
import com.google.common.collect.Multimap
import com.google.inject.Inject
import java.util.Collections
import java.util.HashMap
import java.util.HashSet
import java.util.Map
import java.util.Set
import java.util.TreeMap
import java.util.TreeSet
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.resource.IContainer
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.resource.IResourceDescriptions
import org.eclipse.xtext.resource.IResourceServiceProvider
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider
import org.eclipse.xtext.xbase.lib.Functions.Function1
import org.jactr.io2.jactr.modelFragment.ChunkDef
import org.jactr.io2.jactr.modelFragment.ChunkType
import org.jactr.io2.jactr.modelFragment.Chunks
import org.jactr.io2.jactr.modelFragment.Import
import org.jactr.io2.jactr.modelFragment.Match
import org.jactr.io2.jactr.modelFragment.ModelFragment
import org.jactr.io2.jactr.modelFragment.ModelFragmentPackage
import org.jactr.io2.jactr.modelFragment.Overridable
import org.jactr.io2.jactr.modelFragment.Production
import org.jactr.io2.jactr.modelFragment.ProductionCondition
import org.jactr.io2.jactr.modelFragment.Proxy
import org.jactr.io2.jactr.modelFragment.Script
import org.jactr.io2.jactr.modelFragment.Value

class ModelFragmentUtil {

  @Inject ResourceDescriptionsProvider _descriptors
  @Inject IContainer.Manager _containerManager
  @Inject IQualifiedNameConverter _converter
  @Inject IResourceServiceProvider.Registry rspr

  def dump(Resource resource) {
    printExportedObjects(resource);
    printVisibleResources(resource, _descriptors.getResourceDescriptions(resource));

  }

  def void printExportedObjects(Resource resource) {
    val resServiceProvider = rspr.getResourceServiceProvider(resource.URI)
    val manager = resServiceProvider.getResourceDescriptionManager()
    val description = manager.getResourceDescription(resource)
    for (eod : description.exportedObjects) {
      println(_converter.toString(eod.qualifiedName))
    }
  }

  def void printVisibleResources(Resource resource, IResourceDescriptions index) {
    val resServiceProvider = rspr.getResourceServiceProvider(resource.URI)
    val manager = resServiceProvider.getResourceDescriptionManager()
    val description = manager.getResourceDescription(resource)
    // val description = index.getResourceDescription(resource.URI)
    for (visibleContainer : _containerManager.getVisibleContainers(description, index)) {
      for (visibleResourceDesc : visibleContainer.resourceDescriptions) {
        println(visibleResourceDesc.URI)
      }
    }
  }

  def chunkTypeHierarchy(ChunkType chunkType) {
    val visited = new HashSet
    var current = chunkType.superType;
    while (current !== null && !visited.contains(current)) {
      visited.add(current);
      current = current.superType;
    }
    visited
  }

  /**
   * all possible slots for type, set
   */
  def allSlotNames(ChunkType type) {
    var cType = type;
    val set = new HashSet

    if (type === null)
      return set

    val visited = new HashSet
    while (cType !== null) {
      // defense incase of inadvertant cycle
      if (visited.contains(cType))
        return set

      visited.add(cType)

      // check slots
      for (slot : cType.slots)
        set.add(slot.name)
      cType = cType.superType
    }

    set
  }

  /**
   * all possible slot names for a given chunkDef.
   */
  def allSlotNames(ChunkDef chunk) {
    val set = new HashSet
    var chunks = findChunksForChunkDef(chunk)

    if (chunks !== null)
      set.addAll(allSlotNames(chunks.type))

    set
  }

  private def findChunksForChunkDef(ChunkDef chunkDef) {
    if(chunkDef === null) return null as Chunks

    val chunkName = chunkDef.name
    /*
     * chunktype chunkdef
     */
    var localWrapper = EcoreUtil2.getAllContainers(chunkDef).filter(Chunks).last as Chunks
    if (localWrapper !== null)
      return localWrapper

    /*
     * somewhere else.
     */
    val root = EcoreUtil.getRootContainer(chunkDef)
    for (chunks : EcoreUtil2.getAllContentsOfType(root, Chunks)) {
      for (chunk : chunks.chunks)
        if (chunkName.equals(chunk.name))
          return chunks;
    }

    null
  }

  def guessSlots(EObject node) {

    val set = new HashSet
    val allContainers = EcoreUtil2.getAllContainers(node);

    Iterables.filter(allContainers, Match).forEach([ match |
      /*
       * look for match isa chunktype block
       */
      if (match.isa instanceof ChunkType)
        for (slot : (match.isa as ChunkType).allSlotNames)
          set.add(slot)
      if (match.isa instanceof ChunkDef)
        for (slot : (match.isa as ChunkDef).allSlotNames)
          set.add(slot)
    ])

    set
  }

  def variables(Production node) {
    val set = new HashSet
    EcoreUtil2.getAllContentsOfType(node, ProductionCondition).forEach([ condition |

      if (condition instanceof Match)
        set.add('=' + condition.name.name)

      EcoreUtil2.getAllContentsOfType(condition, Value).forEach([ value |
        if (value.isVariable)
          set.add(value.name)
      ])
    ])

    set
  }

  def variables(Production node, Match ignoring) {
    val set = new HashSet
    EcoreUtil2.getAllContentsOfType(node, ProductionCondition).filter([n|n !== ignoring]).forEach([ condition |

      if (condition instanceof Match)
        set.add('=' + condition.name.name)

      EcoreUtil2.getAllContentsOfType(condition, Value).forEach([ value |
        if (value.isVariable)
          set.add(value.name)
      ])
    ])

    set
  }

//  def variableTable(Production node, Match ignoring) {
//    val set = new HashSet
//    EcoreUtil2.getAllContentsOfType(node, ProductionCondition).filter([n|n !== ignoring]).forEach([ condition |
//
//      if (condition instanceof Match) {
//        set.add(new EObjectDescription(QualifiedName.create('=' + condition.name.name), condition, null) as IEObjectDescription)
//      }
//
//      EcoreUtil2.getAllContentsOfType(condition, Value).forEach([ value |
//        if (value.isVariable) {
//          set.add(new EObjectDescription(QualifiedName.create(value.id), value, null) as IEObjectDescription)
//        }
//      ])
//    ])
//
//    set
//  }
  def couldHaveHiddenBindings(Production node) {

    val hasScriptOrProxy = node.conditions.filter([ condition |
      return condition instanceof Script || condition instanceof Proxy
    ]).size > 0

    val meta = "=meta"
    val hasMeta = node.conditions.filter[condition|condition instanceof Match].map[c|c as Match].filter [ match |
      meta.equals(match.isa.name) && !meta.equals(match.name.name)
    ].size > 0

    hasScriptOrProxy || hasMeta
  }

  /**
   * methods for the exported resources
   */
  def getResourceDescription(EObject o) {
    val index = _descriptors.getResourceDescriptions(o.eResource)
    index.getResourceDescription(o.eResource.URI)
  }

  def getExportedObjectDescriptions(EObject o) {
    o.resourceDescription.exportedObjects
  }

  def getExportedObjectDescriptionsByType(EObject o, EClass type) {
    o.resourceDescription.exportedObjects.filter([ object |
      object.EClass == type
    ])
  }

  /**
   * visibility
   */
  def getVisibileContainers(EObject o) {
    if (o.eResource !== null && o.eResource.URI !== null) {

      val resServiceProvider = rspr.getResourceServiceProvider(o.eResource.URI)
      val manager = resServiceProvider.getResourceDescriptionManager()
      val description = manager.getResourceDescription(o.eResource)

      val index = _descriptors.getResourceDescriptions(o.eResource)
//      val rd = index.getResourceDescription(o.eResource.URI)
      
      _containerManager.getVisibleContainers(description, index)
    } else
      Collections.emptyList
  }

  def getVisibileExportedObjectDescriptions(EObject o, EClass type) {
    o.visibileContainers.map [ container |
      container.getExportedObjectsByType(type)
    ].flatten.toSet
  }

  def localAndImportFilter(EObject root, boolean excludeLocal) {
    val startsWith = new TreeSet

    EcoreUtil2.getAllContentsOfType(root, Import).forEach [ imp |
      startsWith.add(imp.importedNamespace.substring(0, imp.importedNamespace.length - 1)) // include the dot
    ]

    if (!excludeLocal)
      if (root instanceof ModelFragment)
        startsWith.add(root.package.name + ".")

    val Function1<? super IEObjectDescription, Boolean> handler = [ desc |
      startsWith(_converter.toString(desc.qualifiedName), startsWith)
    ]

    handler
  }

  def startsWith(String string, Set<String> startsWith) {

    for (start : startsWith)
      if (string.startsWith(start))
        return true
    return false
  }

  def getRawVisibleObjectDescriptionsByType(EObject o, EClass type) {
    val allVisible = getVisibileExportedObjectDescriptions(o, type)
    val exported = getExportedObjectDescriptionsByType(o, type)
    val difference = allVisible.toSet
    difference.removeAll(exported.toSet)

    return difference.toMap [ desc |
      desc.name.lastSegment
    ]
  }

  /**
   * global scope means everything in the project is visible, 
   * we filter these results based on the import directive of the
   * resource
   */
  def getVisibleEObjectDescriptionsByType(EObject o, EClass type) {

    val allVisible = getVisibileExportedObjectDescriptions(o, type)
    val exported = getExportedObjectDescriptionsByType(o, type)
    val difference = allVisible.toSet
    difference.removeAll(exported.toSet)

    return difference.filter(localAndImportFilter(EcoreUtil.getRootContainer(o), true)).toMap [ desc |
      desc.name.lastSegment
    ]
  }

  def packageSymbolTable(EObject o) {
    val map = LinkedListMultimap.create
    val types = getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.PACKAGE_DECLARATION)
    types.forEach([ type |
      map.put(type.qualifiedName.toString, type)
    ])

    map
  }

  def chunkTypeSymbolTable(EObject o) {
    val map = LinkedListMultimap.create
    val types = getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.CHUNK_TYPE)
    types.filter(localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach([ type |
      map.put(type.qualifiedName.lastSegment, type)
    ])

    map
  }

  def chunkSymbolTable(EObject o) {
    val map = LinkedListMultimap.create
    var chunks = getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.CHUNK_DEF)
    chunks.filter(localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach [ chunk |
      map.put(chunk.qualifiedName.lastSegment, chunk)
    ]
    map
  }

  def bufferSymbolTable(EObject o) {
    val map = LinkedListMultimap.create
    val buffers = getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.BUFFER)
    buffers.filter(localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach([ buffer |
      map.put(buffer.qualifiedName.lastSegment, buffer)
    ])

    map
  }

  def productionSymbolTable(EObject o) {
    val map = LinkedListMultimap.create
    val productions = getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.PRODUCTION)
    productions.filter(localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach([ production |
      map.put(production.qualifiedName.lastSegment, production)
    ])

    map
  }

  def fullSymbolTable(EObject o) {
    val map = new HashMap
    var chunks = getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.CHUNK_DEF)
    val types = getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.CHUNK_TYPE)
    val buffers = getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.BUFFER)
    val productions = getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.PRODUCTION)

    buffers.filter(localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach([ buffer |
      map.putIfAbsent(buffer.qualifiedName.lastSegment, buffer)
    ])
    types.filter(localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach([ type |
      map.putIfAbsent(type.qualifiedName.lastSegment, type)
    ])
    productions.filter(localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach([ production |
      map.putIfAbsent(production.qualifiedName.lastSegment, production)
    ])
    chunks.filter(localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach([ chunk |
      map.put(chunk.qualifiedName.lastSegment, chunk)
    ])

    map
  }

  private def toObject(IEObjectDescription descriptor, ResourceSet resourceSet) {
    EcoreUtil.resolve(descriptor.EObjectOrProxy, resourceSet)
  }

  /**
   * zip through the multimap, for each list > 1, select the one that is local overridden
   * for each selected, resolve
   * 
   */
  def Map<String, EObject> resolveSymbolTable(Resource resource, Multimap<String, IEObjectDescription> symbols) {
    val newTable = new TreeMap<String, EObject>

    for (symbolName : symbols.keySet) {
      val allDefs = symbols.get(symbolName)
      var resolved = null as EObject

      // only one? easy
      if (allDefs.size == 0)
        throw new RuntimeException("Failed to resolve " + symbolName + ". No definition found?");
      if (allDefs.size == 1)
        resolved = toObject(allDefs.last, resource.resourceSet)
      else if (allDefs.size > 1) {
        /*
         * we need to resolve them to determine their override status. select only those that are overrides
         */
        var allOverriden = allDefs.map[d|toObject(d, resource.resourceSet) as Overridable].filter[c|c.override]
        // more than one? select local
        if (allOverriden.size == 0)
          throw new RuntimeException("Failed to resolve " + symbolName + ". Multiple definitions, none overridden.")
        else if (allOverriden.size == 1) {
          resolved = allOverriden.last
        } else if (allOverriden.size > 1) {

          allOverriden = allOverriden.filter[c|c.eResource === resource]

          switch (allOverriden.size) {
            case 0:
              throw new RuntimeException(
                "Failed to resolve " + symbolName + ". Multiple overridden definitions, but none are local.")
            case 1: {
              resolved = allOverriden.last

            }
            default:
              throw new RuntimeException(
                "Failed to resolve " + symbolName + ". Multiple local overridden definitions, cannot select.")
          }
        }

      }

      newTable.put(symbolName, resolved)
    }

    newTable
  }

  def Map<String, EObject> resolveSymbolTableAny(Resource resource, Multimap<String, IEObjectDescription> symbols) {
    val newTable = new TreeMap<String, EObject>

    for (symbolName : symbols.keySet) {
      val allDefs = symbols.get(symbolName)
      var resolved = null as EObject

      // only one? easy
      if (allDefs.size == 0)
        throw new RuntimeException("Failed to resolve " + symbolName + ". No definition found?");
      if (allDefs.size == 1)
        resolved = toObject(allDefs.last, resource.resourceSet)
      else if (allDefs.size > 1) {
        resolved = toObject(allDefs.last, resource.resourceSet)
      }

      newTable.put(symbolName, resolved)
    }

    newTable
  }

  def isString(Value value) {
    value.string !== null
  }

  def isVariable(Value value) {
    if(value.name === null) return false
    if(value.name.startsWith("=")) return true
    false
  }

  def isNull(Value value) {
    if(value.name === null) return false
    return value.name.equals("null")
  }

  def isNumber(Value value) {
    value.number !== null
  }

  def isBoolean(Value value) {
    value.boolean !== null
  }

  def isID(Value value) {
    if(value.name === null) return false;

    return !value.isVariable && !value.isNull
  }
}
