package org.jactr.io2.jactr.ui.highlighting;

import java.util.List;
import javax.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ide.editor.syntaxcoloring.DefaultSemanticHighlightingCalculator;
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.xbase.lib.Extension;
import org.jactr.io2.jactr.modelFragment.Add;
import org.jactr.io2.jactr.modelFragment.Buffer;
import org.jactr.io2.jactr.modelFragment.ChunkDef;
import org.jactr.io2.jactr.modelFragment.ChunkType;
import org.jactr.io2.jactr.modelFragment.Chunks;
import org.jactr.io2.jactr.modelFragment.ConditionalSlot;
import org.jactr.io2.jactr.modelFragment.IsaBlock;
import org.jactr.io2.jactr.modelFragment.Match;
import org.jactr.io2.jactr.modelFragment.ModelFragmentPackage;
import org.jactr.io2.jactr.modelFragment.Modify;
import org.jactr.io2.jactr.modelFragment.Parameters;
import org.jactr.io2.jactr.modelFragment.Production;
import org.jactr.io2.jactr.modelFragment.Query;
import org.jactr.io2.jactr.modelFragment.Remove;
import org.jactr.io2.jactr.modelFragment.SimpleSlot;
import org.jactr.io2.jactr.modelFragment.Value;
import org.jactr.io2.jactr.validation.ModelFragmentUtil;

@SuppressWarnings("all")
public class SemanticHighlightingCalculator extends DefaultSemanticHighlightingCalculator {
  @Inject
  @Extension
  private ModelFragmentUtil _modelFragmentUtil;
  
  /**
   * return true to skip children
   */
  @Override
  public boolean highlightElement(final EObject object, final IHighlightedPositionAcceptor acceptor, final CancelIndicator cancelIndicator) {
    if ((object instanceof Value)) {
      boolean _isID = this._modelFragmentUtil.isID(((Value)object));
      if (_isID) {
        this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.VALUE__NAME, HighlightingConfiguration.ID_ID);
      }
      boolean _isVariable = this._modelFragmentUtil.isVariable(((Value)object));
      if (_isVariable) {
        this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.VALUE__NAME, 
          HighlightingConfiguration.VARIABLE_ID);
      }
    } else {
      if ((object instanceof SimpleSlot)) {
        boolean _startsWith = ((SimpleSlot)object).getName().startsWith("=");
        if (_startsWith) {
          this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME, 
            HighlightingConfiguration.VARIABLE_ID);
        } else {
          this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME, 
            HighlightingConfiguration.SLOT_ID);
        }
      } else {
        if ((object instanceof ConditionalSlot)) {
          boolean _startsWith_1 = ((ConditionalSlot)object).getName().startsWith("=");
          if (_startsWith_1) {
            this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, 
              HighlightingConfiguration.VARIABLE_ID);
          } else {
            this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.CONDITIONAL_SLOT__NAME, 
              HighlightingConfiguration.SLOT_ID);
          }
        } else {
          if ((object instanceof ChunkDef)) {
            this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__NAME, 
              HighlightingConfiguration.ID_ID);
            this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__OVERRIDE, 
              HighlightingConfiguration.COMMENT_ID);
          } else {
            if ((object instanceof Chunks)) {
              this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.CHUNKS__TYPE, HighlightingConfiguration.TYPE_ID);
            } else {
              if ((object instanceof ChunkType)) {
                this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__NAME, 
                  HighlightingConfiguration.TYPE_ID);
                this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.CHUNK_TYPE__SUPER_TYPE, 
                  HighlightingConfiguration.TYPE_ID);
                this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__OVERRIDE, 
                  HighlightingConfiguration.COMMENT_ID);
              } else {
                if ((object instanceof Production)) {
                  this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__NAME, 
                    HighlightingConfiguration.PRODUCTION_ID);
                } else {
                  if ((object instanceof Buffer)) {
                    this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__NAME, 
                      HighlightingConfiguration.BUFFER_ID);
                    this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.OVERRIDABLE__OVERRIDE, 
                      HighlightingConfiguration.COMMENT_ID);
                    this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.BUFFER__SOURCE, HighlightingConfiguration.ID_ID);
                  } else {
                    if ((object instanceof Parameters)) {
                      List<INode> _findNodesForFeature = NodeModelUtils.findNodesForFeature(object, ModelFragmentPackage.Literals.PARAMETERS__ELEMENTS);
                      for (final INode node : _findNodesForFeature) {
                        this.highlightNode(acceptor, node, HighlightingConfiguration.ID_ID);
                      }
                    } else {
                      if ((object instanceof Match)) {
                        this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.MATCH__NAME, HighlightingConfiguration.BUFFER_ID);
                        IsaBlock _isa = ((Match)object).getIsa();
                        boolean _tripleNotEquals = (_isa != null);
                        if (_tripleNotEquals) {
                          ChunkType _type = ((Match)object).getIsa().getType();
                          boolean _tripleNotEquals_1 = (_type != null);
                          if (_tripleNotEquals_1) {
                            this.highlightFeature(acceptor, ((Match)object).getIsa(), ModelFragmentPackage.Literals.ISA_BLOCK__TYPE, 
                              HighlightingConfiguration.TYPE_ID);
                          }
                          ChunkDef _chunk = ((Match)object).getIsa().getChunk();
                          boolean _tripleNotEquals_2 = (_chunk != null);
                          if (_tripleNotEquals_2) {
                            this.highlightFeature(acceptor, ((Match)object).getIsa(), ModelFragmentPackage.Literals.ISA_BLOCK__CHUNK, 
                              HighlightingConfiguration.ID_ID);
                          }
                          String _name = ((Match)object).getIsa().getName();
                          boolean _tripleNotEquals_3 = (_name != null);
                          if (_tripleNotEquals_3) {
                            this.highlightFeature(acceptor, ((Match)object).getIsa(), ModelFragmentPackage.Literals.ISA_BLOCK__NAME, 
                              HighlightingConfiguration.VARIABLE_ID);
                          }
                        }
                      } else {
                        if ((object instanceof Query)) {
                          this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.QUERY__NAME, HighlightingConfiguration.BUFFER_ID);
                        } else {
                          if ((object instanceof Add)) {
                            this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.ADD__NAME, HighlightingConfiguration.BUFFER_ID);
                            IsaBlock _isa_1 = ((Add)object).getIsa();
                            boolean _tripleNotEquals_4 = (_isa_1 != null);
                            if (_tripleNotEquals_4) {
                              ChunkType _type_1 = ((Add)object).getIsa().getType();
                              boolean _tripleNotEquals_5 = (_type_1 != null);
                              if (_tripleNotEquals_5) {
                                this.highlightFeature(acceptor, ((Add)object).getIsa(), ModelFragmentPackage.Literals.ISA_BLOCK__TYPE, 
                                  HighlightingConfiguration.TYPE_ID);
                              }
                              ChunkDef _chunk_1 = ((Add)object).getIsa().getChunk();
                              boolean _tripleNotEquals_6 = (_chunk_1 != null);
                              if (_tripleNotEquals_6) {
                                this.highlightFeature(acceptor, ((Add)object).getIsa(), ModelFragmentPackage.Literals.ISA_BLOCK__CHUNK, 
                                  HighlightingConfiguration.ID_ID);
                              }
                              String _name_1 = ((Add)object).getIsa().getName();
                              boolean _tripleNotEquals_7 = (_name_1 != null);
                              if (_tripleNotEquals_7) {
                                this.highlightFeature(acceptor, ((Add)object).getIsa(), ModelFragmentPackage.Literals.ISA_BLOCK__NAME, 
                                  HighlightingConfiguration.VARIABLE_ID);
                              }
                            }
                          } else {
                            if ((object instanceof Modify)) {
                              this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.SIMPLE_SLOT_BLOCK__NAME, 
                                HighlightingConfiguration.BUFFER_ID);
                            } else {
                              if ((object instanceof Remove)) {
                                this.highlightFeature(acceptor, object, ModelFragmentPackage.Literals.SIMPLE_SLOT_BLOCK__NAME, 
                                  HighlightingConfiguration.BUFFER_ID);
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
    return false;
  }
}
