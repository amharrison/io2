/*
 * generated by Xtext 2.16.0
 */
package org.jactr.io2.jactr.ui.quickfix

import javax.inject.Inject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider
import org.eclipse.xtext.ui.editor.quickfix.Fix
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
import org.eclipse.xtext.validation.Issue
import org.jactr.io2.jactr.modelFragment.ChunkType
import org.jactr.io2.jactr.modelFragment.Chunks
import org.jactr.io2.jactr.modelFragment.ModelFragment
import org.jactr.io2.jactr.modelFragment.ModelFragmentFactory
import org.jactr.io2.jactr.modelFragment.Overridable
import org.jactr.io2.jactr.validation.ModelFragmentUtil
import org.jactr.io2.jactr.validation.ModelFragmentValidator

/**
 * Custom quickfixes.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
class ModelFragmentQuickfixProvider extends DefaultQuickfixProvider {

  @Inject extension ModelFragmentUtil

  @Fix(ModelFragmentValidator.BAD_SLOT_NAME)
  def replaceSlot(Issue issue, IssueResolutionAcceptor acceptor) {
    if (issue.data !== null)
      for (slotName : issue?.data)
        acceptor.accept(issue, slotName, 'Replace with ' + slotName, 'basic/slot.gif') [ context |
          context.xtextDocument.replace(issue.offset, issue.length, slotName)
        ]
  }

  @Fix(ModelFragmentValidator.BAD_VARIABLE)
  def replaceVariableOrSlot(Issue issue, IssueResolutionAcceptor acceptor) {
    if (issue.data !== null)
      for (variable : issue?.data)
        acceptor.accept(issue, variable, 'Replace with ' + variable, 'basic/prop_ps.gif') [ context |
          context.xtextDocument.replace(issue.offset, issue.length, variable)
        ]
  }

  @Fix(ModelFragmentValidator.REDEFINED)
  def insertOverride(Issue issue, IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, "@Override", "Insert @Override", null, [ element, context |
      if (element instanceof Overridable)
        element.override = true  
    ])
  }

  @Fix(ModelFragmentValidator.UNKNOWN_CHUNK)
  def insertChunkDefSemantic(Issue issue, IssueResolutionAcceptor acceptor) {
    if (issue.data !== null)
      for (chunkName : issue?.data) {

        acceptor.accept(issue, "Create " + chunkName, "Create chunk named " + chunkName, 'basic/chunk.gif', [ element, context |
          try {

            val chunkDef = ModelFragmentFactory.eINSTANCE.createChunkDef
            chunkDef.name = chunkName

            val root = EcoreUtil.getRootContainer(element) as ModelFragment
            val chunkTypeDesc = root.chunkTypeSymbolTable.get("chunk").last
            val chunkType = EcoreUtil.resolve(chunkTypeDesc.EObjectOrProxy, root.eResource.resourceSet) as ChunkType

 /*
  * does a chunks block with chunktype of chunk already exist? if not, add it
  */
            var foundExisting = false;
            var chunks = EcoreUtil2.getAllContentsOfType(root, Chunks).findFirst[chunks|chunks.type == chunkType]

            if (chunks === null) {
              chunks = ModelFragmentFactory.eINSTANCE.createChunks
              chunks.type = chunkType
            } else
              foundExisting = true

            chunks.chunks.add(chunkDef)

            if (!foundExisting)
              root.package.elements.add(0, chunks)

          } catch (Exception e) {
            System.err.println(e)
          }
        ])
      }
  }

}