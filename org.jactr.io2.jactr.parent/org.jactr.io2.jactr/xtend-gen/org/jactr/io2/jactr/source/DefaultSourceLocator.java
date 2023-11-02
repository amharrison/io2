package org.jactr.io2.jactr.source;

import com.google.inject.Inject;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Function;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.jactr.io2.jactr.modelFragment.Production;
import org.jactr.io2.jactr.validation.ModelFragmentUtil;

@SuppressWarnings("all")
public class DefaultSourceLocator implements ISourceLocator {
  @Inject
  private ModelFragmentUtil _util;

  @Override
  public Map<String, ISourceLocator.SourceLocation> locateSourceElements(final EObject root) {
    TreeMap<String, ISourceLocator.SourceLocation> _xblockexpression = null;
    {
      Map<String, EObject> symbolTable = this._util.resolveSymbolTable(root.eResource(), this._util.productionSymbolTable(root));
      final TreeMap<String, ISourceLocator.SourceLocation> rtn = new TreeMap<String, ISourceLocator.SourceLocation>();
      int _size = symbolTable.size();
      boolean _equals = (_size == 0);
      if (_equals) {
        final Function1<EObject, String> _function = (EObject v) -> {
          return ((Production) v).getName();
        };
        symbolTable = IterableExtensions.<String, EObject>toMap(EcoreUtil2.<Production>getAllContentsOfType(root, Production.class), _function);
      }
      final Function<EObject, Production> _function_1 = (EObject node) -> {
        return ((Production) node);
      };
      final Consumer<Production> _function_2 = (Production overridable) -> {
        final ICompositeNode node = NodeModelUtils.getNode(overridable);
        int _startLine = node.getStartLine();
        URI _uRI = overridable.eResource().getURI();
        final ISourceLocator.SourceLocation loc = new ISourceLocator.SourceLocation(_startLine, _uRI);
        rtn.put(overridable.getName(), loc);
      };
      symbolTable.values().stream().<Production>map(_function_1).forEach(_function_2);
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
}
