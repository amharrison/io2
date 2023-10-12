package org.jactr.io2.jactr.naming

import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider
import org.eclipse.xtext.naming.QualifiedName
import org.jactr.io2.jactr.modelFragment.ConditionalSlot
import org.jactr.io2.jactr.modelFragment.Parameter
import org.jactr.io2.jactr.modelFragment.SimpleSlot

class QualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {
  
  /**
   * masks the slot and parameter IDs from the global namespace
   */
  override computeFullyQualifiedNameFromNameAttribute(EObject obj){
    if(obj instanceof SimpleSlot) return null as QualifiedName
    if(obj instanceof ConditionalSlot) return null as QualifiedName
    if(obj instanceof Parameter) return null as QualifiedName
//    if(obj instanceof Value)
//     if(obj.isVariable)
//      return QualifiedName.create(obj.id)
      
    return super.computeFullyQualifiedNameFromNameAttribute(obj)
  }
  
}