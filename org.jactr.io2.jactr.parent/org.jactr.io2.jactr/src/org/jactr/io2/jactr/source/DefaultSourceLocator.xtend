package org.jactr.io2.jactr.source

import com.google.inject.Inject
import java.util.TreeMap
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.jactr.io2.jactr.modelFragment.Production
import org.jactr.io2.jactr.validation.ModelFragmentUtil

class DefaultSourceLocator implements ISourceLocator {

  @Inject ModelFragmentUtil _util

  override locateSourceElements(EObject root) {
    var symbolTable = _util.resolveSymbolTable(root.eResource, _util.productionSymbolTable(root))
    val rtn = new TreeMap<String, SourceLocation>()
    
    if(symbolTable.size()==0)
    {
      //try the explicit nodes incase we get nothing from the symbol table
      // this is a known bug of unknown cause. 
      
      symbolTable = EcoreUtil2.getAllContentsOfType(root, Production).toMap([v| 
        (v as Production).name
      ])
      
    }

    symbolTable.values.stream.map([node|node as Production]).forEach([overridable | 
      
      val node = NodeModelUtils.getNode(overridable)
      val loc = new SourceLocation(node.startLine, overridable.eResource.URI)
      rtn.put(overridable.name, loc)
    ])

    rtn
  }

}
