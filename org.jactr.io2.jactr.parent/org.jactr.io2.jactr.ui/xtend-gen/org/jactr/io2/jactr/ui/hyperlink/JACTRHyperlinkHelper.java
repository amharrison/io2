package org.jactr.io2.jactr.ui.hyperlink;

import com.google.common.collect.LinkedListMultimap;
import com.google.inject.Inject;
import java.util.List;
import org.eclipse.core.runtime.ILog;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.xtext.ui.TypeAwareHyperlinkHelper;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.ILeafNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkAcceptor;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.jactr.io2.jactr.modelFragment.Import;
import org.jactr.io2.jactr.modelFragment.Parameters;
import org.jactr.io2.jactr.modelFragment.Value;
import org.jactr.io2.jactr.ui.internal.JactrActivator;
import org.jactr.io2.jactr.validation.ModelFragmentUtil;

@SuppressWarnings("all")
public class JACTRHyperlinkHelper extends TypeAwareHyperlinkHelper {
  @Inject
  @Extension
  private ModelFragmentUtil _modelFragmentUtil;

  @Override
  public void createHyperlinksByOffset(final XtextResource resource, final int offset, final IHyperlinkAcceptor acceptor) {
    final EObject astNode = this.getEObjectAtOffsetHelper().resolveElementAt(resource, offset);
    if ((astNode instanceof Value)) {
      boolean _isID = false;
      if (((Value)astNode)!=null) {
        _isID=this._modelFragmentUtil.isID(((Value)astNode));
      }
      if (_isID) {
        final ICompositeNode parseNode = NodeModelUtils.findActualNodeFor(astNode);
        final EObject symbol = this.getSymbol(astNode, ((Value)astNode).getName());
        if ((symbol != null)) {
          this.createHyperlinksTo(resource, parseNode, symbol, acceptor);
        }
      }
    } else {
      if ((astNode instanceof Import)) {
        String importSource = ((Import)astNode).getImportedNamespace();
        int _length = importSource.length();
        int _minus = (_length - 2);
        importSource = importSource.substring(0, _minus);
        final ICompositeNode parseNode_1 = NodeModelUtils.findActualNodeFor(astNode);
        final LinkedListMultimap<String, IEObjectDescription> table = this._modelFragmentUtil.packageSymbolTable(astNode);
        final List<IEObjectDescription> matches = table.get(importSource);
        final IEObjectDescription descriptor = matches.getLast();
        if ((descriptor != null)) {
          final EObject target = EcoreUtil.resolve(descriptor.getEObjectOrProxy(), resource.getResourceSet());
          this.createHyperlinksTo(resource, parseNode_1, target, acceptor);
        }
      } else {
        if ((astNode instanceof Parameters)) {
          final ICompositeNode parseNode_2 = NodeModelUtils.findActualNodeFor(astNode);
          final ILeafNode leaf = NodeModelUtils.findLeafNodeAtOffset(parseNode_2, offset);
          final EObject symbol_1 = this.getSymbol(astNode, leaf.getText());
          if ((symbol_1 != null)) {
            this.createHyperlinksTo(resource, leaf, symbol_1, acceptor);
          }
        } else {
          super.createHyperlinksByOffset(resource, offset, acceptor);
        }
      }
    }
  }

  public Object getSymbol2(final EObject astNode, final String name) {
    return null;
  }

  public EObject getSymbol(final EObject astNode, final String name) {
    try {
      EObject descriptor = this._modelFragmentUtil.resolveSymbolTableAny(astNode.eResource(), this._modelFragmentUtil.chunkSymbolTable(astNode)).get(name);
      if ((descriptor == null)) {
        descriptor = this._modelFragmentUtil.resolveSymbolTableAny(astNode.eResource(), this._modelFragmentUtil.chunkTypeSymbolTable(astNode)).get(name);
      }
      if ((descriptor == null)) {
        descriptor = this._modelFragmentUtil.resolveSymbolTableAny(astNode.eResource(), this._modelFragmentUtil.bufferSymbolTable(astNode)).get(name);
      }
      if ((descriptor == null)) {
        descriptor = this._modelFragmentUtil.resolveSymbolTableAny(astNode.eResource(), this._modelFragmentUtil.productionSymbolTable(astNode)).get(name);
      }
      return descriptor;
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        ILog _log = JactrActivator.getInstance().getLog();
        Status _status = new Status(IStatus.ERROR, JactrActivator.PLUGIN_ID, ("Failed to find descriptor for " + name), e);
        _log.log(_status);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return null;
  }
}
