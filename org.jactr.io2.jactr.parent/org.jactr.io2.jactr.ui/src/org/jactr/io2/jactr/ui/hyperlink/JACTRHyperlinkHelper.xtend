package org.jactr.io2.jactr.ui.hyperlink

import javax.inject.Inject
import org.eclipse.core.runtime.IStatus
import org.eclipse.core.runtime.Status
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.common.types.xtext.ui.TypeAwareHyperlinkHelper
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkAcceptor
import org.jactr.io2.jactr.modelFragment.Import
import org.jactr.io2.jactr.modelFragment.Parameters
import org.jactr.io2.jactr.modelFragment.Value
import org.jactr.io2.jactr.ui.internal.JactrActivator
import org.jactr.io2.jactr.validation.ModelFragmentUtil

class JACTRHyperlinkHelper extends TypeAwareHyperlinkHelper {

  @Inject extension ModelFragmentUtil

  override createHyperlinksByOffset(XtextResource resource, int offset, IHyperlinkAcceptor acceptor) {
    val astNode = EObjectAtOffsetHelper.resolveElementAt(resource, offset)
    if (astNode instanceof Value) {
      if (astNode?.isID) {
        val parseNode = NodeModelUtils.findActualNodeFor(astNode)
        val symbol = getSymbol(astNode, astNode.name)
        if (symbol !== null)
          createHyperlinksTo(resource, parseNode, symbol, acceptor)
      }
    } else if (astNode instanceof Import) {
      var importSource = astNode.importedNamespace
      importSource = importSource.substring(0, importSource.length - 2)
      val parseNode = NodeModelUtils.findActualNodeFor(astNode)
      val table = packageSymbolTable(astNode)
      val matches = table.get(importSource)
      val descriptor = matches.last
      if (descriptor !== null) {
        val target = EcoreUtil.resolve(descriptor.EObjectOrProxy, resource.resourceSet)
        createHyperlinksTo(resource, parseNode, target, acceptor)
      }
    } else if (astNode instanceof Parameters) {
      val parseNode = NodeModelUtils.findActualNodeFor(astNode)
      val leaf = NodeModelUtils.findLeafNodeAtOffset(parseNode, offset)
      val symbol = getSymbol(astNode, leaf.text)
      if (symbol !== null)
        createHyperlinksTo(resource, leaf, symbol, acceptor)
    } else
      super.createHyperlinksByOffset(resource, offset, acceptor)
  }

  def getSymbol2(EObject astNode, String name) {
  }

  def getSymbol(EObject astNode, String name) {
    try {

      var descriptor = resolveSymbolTableAny(astNode.eResource, chunkSymbolTable(astNode)).get(name);
      if (descriptor === null)
        descriptor = resolveSymbolTableAny(astNode.eResource, chunkTypeSymbolTable(astNode)).get(name);
      if (descriptor === null)
        descriptor = resolveSymbolTableAny(astNode.eResource, bufferSymbolTable(astNode)).get(name);
      if (descriptor === null)
        descriptor = resolveSymbolTableAny(astNode.eResource, productionSymbolTable(astNode)).get(name);

      return descriptor
    } catch (Exception e) {
      // easily possible during an edit
      JactrActivator.instance.log.log(
        new Status(IStatus.ERROR, JactrActivator.PLUGIN_ID, "Failed to find descriptor for " + name, e));
    }
    return null
  }
}
