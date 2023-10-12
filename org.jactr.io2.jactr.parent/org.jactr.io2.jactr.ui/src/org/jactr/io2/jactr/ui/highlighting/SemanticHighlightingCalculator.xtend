package org.jactr.io2.jactr.ui.highlighting

import javax.inject.Inject
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.ide.editor.syntaxcoloring.DefaultSemanticHighlightingCalculator
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.util.CancelIndicator
import org.jactr.io2.jactr.modelFragment.Add
import org.jactr.io2.jactr.modelFragment.Buffer
import org.jactr.io2.jactr.modelFragment.ChunkDef
import org.jactr.io2.jactr.modelFragment.ChunkType
import org.jactr.io2.jactr.modelFragment.Chunks
import org.jactr.io2.jactr.modelFragment.ConditionalSlot
import org.jactr.io2.jactr.modelFragment.Match
import org.jactr.io2.jactr.modelFragment.ModelFragmentPackage
import org.jactr.io2.jactr.modelFragment.Modify
import org.jactr.io2.jactr.modelFragment.Parameters
import org.jactr.io2.jactr.modelFragment.Production
import org.jactr.io2.jactr.modelFragment.Query
import org.jactr.io2.jactr.modelFragment.Remove
import org.jactr.io2.jactr.modelFragment.SimpleSlot
import org.jactr.io2.jactr.modelFragment.Value
import org.jactr.io2.jactr.validation.ModelFragmentUtil

class SemanticHighlightingCalculator extends DefaultSemanticHighlightingCalculator {

  @Inject extension ModelFragmentUtil

  /**
   * return true to skip children
   */
  override highlightElement(EObject object, IHighlightedPositionAcceptor acceptor, CancelIndicator cancelIndicator) {
    if (object instanceof Value) {
      if (object.isID)
        highlightFeature(acceptor, object, ModelFragmentPackage.Literals.VALUE__NAME, HighlightingConfiguration.ID_ID)
      if (object.isVariable)
        highlightFeature(acceptor, object, ModelFragmentPackage.Literals.VALUE__NAME,
          HighlightingConfiguration.VARIABLE_ID)
    } else if (object instanceof SimpleSlot) {
      if (object.name.startsWith("="))
        highlightFeature(acceptor, object, ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME,
          HighlightingConfiguration.VARIABLE_ID)
      else
        highlightFeature(acceptor, object, ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME,
          HighlightingConfiguration.SLOT_ID)
    } else if (object instanceof ConditionalSlot) {
      if (object.name.startsWith("="))
        highlightFeature(acceptor, object, ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME,
          HighlightingConfiguration.VARIABLE_ID)
      else
        highlightFeature(acceptor, object, ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME,
          HighlightingConfiguration.SLOT_ID)
    } else if (object instanceof ChunkDef) {
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__NAME,
        HighlightingConfiguration.ID_ID)
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__OVERRIDE,
        HighlightingConfiguration.COMMENT_ID)
    } else if (object instanceof Chunks)
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.CHUNKS__TYPE, HighlightingConfiguration.TYPE_ID)
    else if (object instanceof ChunkType) {
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__NAME,
        HighlightingConfiguration.TYPE_ID)
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.CHUNK_TYPE__SUPER_TYPE,
        HighlightingConfiguration.TYPE_ID)
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__OVERRIDE,
        HighlightingConfiguration.COMMENT_ID)
    } else if (object instanceof Production) {
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__NAME,
        HighlightingConfiguration.PRODUCTION_ID);
    } else if (object instanceof Buffer) {
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__NAME,
        HighlightingConfiguration.BUFFER_ID)
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__OVERRIDE,
        HighlightingConfiguration.COMMENT_ID)
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.BUFFER__SOURCE, HighlightingConfiguration.ID_ID)
    } else if (object instanceof Parameters) {
      for (INode node : NodeModelUtils.findNodesForFeature(object, ModelFragmentPackage.Literals.PARAMETERS__ELEMENTS))
        highlightNode(acceptor, node, HighlightingConfiguration.ID_ID);

    } else if (object instanceof Match) {
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.MATCH__NAME, HighlightingConfiguration.BUFFER_ID)
      if (object.isa !== null) {
        if (object.isa.type !== null)
          highlightFeature(acceptor, object.isa, ModelFragmentPackage.Literals.ISA_BLOCK__TYPE,
            HighlightingConfiguration.TYPE_ID)
        if (object.isa.chunk !== null)
          highlightFeature(acceptor, object.isa, ModelFragmentPackage.Literals.ISA_BLOCK__CHUNK,
            HighlightingConfiguration.ID_ID)
        if (object.isa.name !== null)
          highlightFeature(acceptor, object.isa, ModelFragmentPackage.Literals.ISA_BLOCK__NAME,
            HighlightingConfiguration.VARIABLE_ID)
      }
    } else if (object instanceof Query) {
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.QUERY__NAME, HighlightingConfiguration.BUFFER_ID)
    } else if (object instanceof Add) {
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.ADD__NAME, HighlightingConfiguration.BUFFER_ID)
      if (object.isa !== null) {

        if (object.isa.type !== null)
          highlightFeature(acceptor, object.isa, ModelFragmentPackage.Literals.ISA_BLOCK__TYPE,
            HighlightingConfiguration.TYPE_ID)
        if (object.isa.chunk !== null)
          highlightFeature(acceptor, object.isa, ModelFragmentPackage.Literals.ISA_BLOCK__CHUNK,
            HighlightingConfiguration.ID_ID)
        if (object.isa.name !== null)
          highlightFeature(acceptor, object.isa, ModelFragmentPackage.Literals.ISA_BLOCK__NAME,
            HighlightingConfiguration.VARIABLE_ID)
      }
    } else if (object instanceof Modify) {
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.SIMPLE_SLOT_BLOCK__NAME,
        HighlightingConfiguration.BUFFER_ID)
    } else if (object instanceof Remove) {
      highlightFeature(acceptor, object, ModelFragmentPackage.Literals.SIMPLE_SLOT_BLOCK__NAME,
        HighlightingConfiguration.BUFFER_ID)
    }
    return false
  }
}
