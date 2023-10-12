package org.jactr.io2.jactr.ui.rename

import com.google.common.collect.Lists
import java.util.Collection
import java.util.HashSet
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.ide.refactoring.IRenameStrategy2
import org.eclipse.xtext.ide.refactoring.RenameContext
import org.jactr.io2.jactr.modelFragment.Add
import org.jactr.io2.jactr.modelFragment.ChunkDef
import org.jactr.io2.jactr.modelFragment.ChunkType
import org.jactr.io2.jactr.modelFragment.Chunks
import org.jactr.io2.jactr.modelFragment.ConditionalSlot
import org.jactr.io2.jactr.modelFragment.Match
import org.jactr.io2.jactr.modelFragment.Production
import org.jactr.io2.jactr.modelFragment.SimpleSlot
import org.jactr.io2.jactr.modelFragment.Value
import org.jactr.io2.jactr.modelFragment.IsaBlock

class RenameStrategy extends IRenameStrategy2.DefaultImpl {

  override applyRename(RenameContext context) {
    context.changes.filter[change|canHandle(change)].forEach [ change |
      var object = context.resourceSet.getEObject(change.targetURI, true)

      if (object instanceof Value) {
        if (object.isVariable) {
          val productions = EcoreUtil2.getAllContainers(object).filter[e|e instanceof Production]
          if (productions.size > 0) {
            allVariablesMatchingName(productions.head, object.name).forEach [ child |
              context.addModification(change)[child.doRename(change, context)]
            ]
          }
        }
      }

      /*
       * find all the chunk references in slots and productions
       */
      if (object instanceof ChunkDef)
        allValuesMatchingName(object, object.name).forEach [ child |
          context.addModification(change)[child.doRename(change, context)]
        ]

      /*
       * find all slot references for a chunktype in slots and productions
       */
      if (object instanceof SimpleSlot) {
        val container = object.eContainer;
        val slotName = object.name;
        if (container instanceof ChunkType) {
          val hierarchy = chunkTypeDescendents(container)
          allProductionsAgainst(object, hierarchy).forEach [ production |
            allSlotsMatchingNames(production, slotName).forEach [ child |
              context.addModification(change)[child.doRename(change, context)]
            ]
          ]

          allChunksOf(container, hierarchy).forEach [ chunk |
            allSlotsMatchingNames(chunk, slotName).forEach [ child |
              context.addModification(change)[child.doRename(change, context)]
            ]
          ]

        }
      }

      context.addModification(change)[doRename(change, context)]
    ]
  }

  def List<EObject> allVariablesMatchingName(EObject production, String name) {
    val set = Lists.newArrayList
    EcoreUtil2.getAllContentsOfType(production, Value).forEach([ value |
      if (value.isVariable && value.name.equals(name)) {
        set.add(value)
      }
    ])

    EcoreUtil2.getAllContentsOfType(production, IsaBlock).forEach([ value |
      if (name.equals(value.name))
        set.add(value)
    ])

    set
  }

  def List<EObject> allValuesMatchingName(EObject root, String name) {
    val set = Lists.newArrayList
    EcoreUtil2.getAllContentsOfType(EcoreUtil2.getRootContainer(root, false), Value).forEach([ value |
      if (value.isID && value.name.equals(name)) {
        set.add(value)
      }
    ])

    set
  }

  def Collection<ChunkDef> allChunksOf(EObject root, Set<ChunkType> hierarchy) {
    val set = new HashSet()
    EcoreUtil2.getAllContentsOfType(EcoreUtil2.getRootContainer(root, false), Chunks).forEach([ chunks |
      if (hierarchy.contains(chunks.type))
        set.addAll(chunks.chunks)
    ])
    set
  }

  def Collection<EObject> allProductionsAgainst(EObject root, Set<ChunkType> hierarchy) {
    val set = new HashSet()
    EcoreUtil2.getAllContentsOfType(EcoreUtil2.getRootContainer(root, false), Production).forEach([ production |

      production.conditions.filter[c|c instanceof Match].map[match|match as Match].forEach [ match |
        if (hierarchy.contains(match.isa.type))
          set.add(production)
      ]
      if (!set.contains(production)) {
        production.actions.filter[a|a instanceof Add].map[add|add as Add].forEach [ add |
          if (hierarchy.contains(add.isa.type))
            set.add(production)
        ]
      }
    ])

    set
  }

  def List<EObject> allSlotsMatchingNames(EObject root, String name) {
    val set = Lists.newArrayList
    EcoreUtil2.getAllContentsOfType(root, SimpleSlot).forEach([ value |
      if (value.name.equals(name)) {
        set.add(value)
      }
    ])
    EcoreUtil2.getAllContentsOfType(root, ConditionalSlot).forEach([ value |
      if (value.name.equals(name)) {
        set.add(value)
      }
    ])

    set
  }

  def boolean isVariable(Value value) {
    if(value.name === null) return false
    if(value.name.startsWith("=")) return true
    false
  }

  def boolean isID(Value value) {
    return value.name !== null
  }

  def chunkTypeHierarchy(ChunkType chunkType) {
    val visited = new HashSet
    visited.add(chunkType)
    var current = chunkType.superType;
    while (current !== null && !visited.contains(current)) {
      visited.add(current);
      current = current.superType;
    }
    visited
  }

  def Set<ChunkType> chunkTypeDescendents(ChunkType chunkType) {
    val potentialParents = new HashSet
    potentialParents.add(chunkType)

    EcoreUtil2.getAllContentsOfType(EcoreUtil2.getRootContainer(chunkType, false), ChunkType).forEach [ ct |
      if (ct.superType == chunkType)
        potentialParents.addAll(chunkTypeDescendents(ct))
    ]

    potentialParents
  }
}
