/**
 * generated by Xtext 2.16.0
 */
package org.jactr.io2.jactr.ui.quickfix;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.ui.editor.model.edit.IModification;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.jactr.io2.jactr.modelFragment.ChunkDef;
import org.jactr.io2.jactr.modelFragment.ChunkType;
import org.jactr.io2.jactr.modelFragment.Chunks;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.modelFragment.ModelFragmentFactory;
import org.jactr.io2.jactr.modelFragment.Overridable;
import org.jactr.io2.jactr.validation.ModelFragmentUtil;
import org.jactr.io2.jactr.validation.ModelFragmentValidator;

/**
 * Custom quickfixes.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
@SuppressWarnings("all")
public class ModelFragmentQuickfixProvider extends DefaultQuickfixProvider {
  @Inject
  @Extension
  private ModelFragmentUtil _modelFragmentUtil;

  @Fix(ModelFragmentValidator.BAD_SLOT_NAME)
  public void replaceSlot(final Issue issue, final IssueResolutionAcceptor acceptor) {
    String[] _data = issue.getData();
    boolean _tripleNotEquals = (_data != null);
    if (_tripleNotEquals) {
      String[] _data_1 = null;
      if (issue!=null) {
        _data_1=issue.getData();
      }
      for (final String slotName : _data_1) {
        final IModification _function = (IModificationContext context) -> {
          context.getXtextDocument().replace((issue.getOffset()).intValue(), (issue.getLength()).intValue(), slotName);
        };
        acceptor.accept(issue, slotName, ("Replace with " + slotName), "basic/slot.gif", _function);
      }
    }
  }

  @Fix(ModelFragmentValidator.BAD_VARIABLE)
  public void replaceVariableOrSlot(final Issue issue, final IssueResolutionAcceptor acceptor) {
    String[] _data = issue.getData();
    boolean _tripleNotEquals = (_data != null);
    if (_tripleNotEquals) {
      String[] _data_1 = null;
      if (issue!=null) {
        _data_1=issue.getData();
      }
      for (final String variable : _data_1) {
        final IModification _function = (IModificationContext context) -> {
          context.getXtextDocument().replace((issue.getOffset()).intValue(), (issue.getLength()).intValue(), variable);
        };
        acceptor.accept(issue, variable, ("Replace with " + variable), "basic/prop_ps.gif", _function);
      }
    }
  }

  @Fix(ModelFragmentValidator.REDEFINED)
  public void insertOverride(final Issue issue, final IssueResolutionAcceptor acceptor) {
    final ISemanticModification _function = (EObject element, IModificationContext context) -> {
      if ((element instanceof Overridable)) {
        ((Overridable)element).setOverride(true);
      }
    };
    acceptor.accept(issue, "@Override", "Insert @Override", null, _function);
  }

  @Fix(ModelFragmentValidator.UNKNOWN_CHUNK)
  public void insertChunkDefSemantic(final Issue issue, final IssueResolutionAcceptor acceptor) {
    String[] _data = issue.getData();
    boolean _tripleNotEquals = (_data != null);
    if (_tripleNotEquals) {
      String[] _data_1 = null;
      if (issue!=null) {
        _data_1=issue.getData();
      }
      for (final String chunkName : _data_1) {
        final ISemanticModification _function = (EObject element, IModificationContext context) -> {
          try {
            final ChunkDef chunkDef = ModelFragmentFactory.eINSTANCE.createChunkDef();
            chunkDef.setName(chunkName);
            EObject _rootContainer = EcoreUtil.getRootContainer(element);
            final ModelFragment root = ((ModelFragment) _rootContainer);
            final IEObjectDescription chunkTypeDesc = this._modelFragmentUtil.chunkTypeSymbolTable(root).get("chunk").getLast();
            EObject _resolve = EcoreUtil.resolve(chunkTypeDesc.getEObjectOrProxy(), root.eResource().getResourceSet());
            final ChunkType chunkType = ((ChunkType) _resolve);
            boolean foundExisting = false;
            final Function1<Chunks, Boolean> _function_1 = (Chunks chunks) -> {
              ChunkType _type = chunks.getType();
              return Boolean.valueOf(Objects.equal(_type, chunkType));
            };
            Chunks chunks = IterableExtensions.<Chunks>findFirst(EcoreUtil2.<Chunks>getAllContentsOfType(root, Chunks.class), _function_1);
            if ((chunks == null)) {
              chunks = ModelFragmentFactory.eINSTANCE.createChunks();
              chunks.setType(chunkType);
            } else {
              foundExisting = true;
            }
            chunks.getChunks().add(chunkDef);
            if ((!foundExisting)) {
              root.getPackage().getElements().add(0, chunks);
            }
          } catch (final Throwable _t) {
            if (_t instanceof Exception) {
              final Exception e = (Exception)_t;
              System.err.println(e);
            } else {
              throw Exceptions.sneakyThrow(_t);
            }
          }
        };
        acceptor.accept(issue, ("Create " + chunkName), ("Create chunk named " + chunkName), "basic/chunk.gif", _function);
      }
    }
  }
}
