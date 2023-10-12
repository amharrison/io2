package org.jactr.io2.jactr.naming;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.DefaultDeclarativeQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.jactr.io2.jactr.modelFragment.ConditionalSlot;
import org.jactr.io2.jactr.modelFragment.Parameter;
import org.jactr.io2.jactr.modelFragment.SimpleSlot;

@SuppressWarnings("all")
public class QualifiedNameProvider extends DefaultDeclarativeQualifiedNameProvider {
  /**
   * masks the slot and parameter IDs from the global namespace
   */
  @Override
  public QualifiedName computeFullyQualifiedNameFromNameAttribute(final EObject obj) {
    if ((obj instanceof SimpleSlot)) {
      return ((QualifiedName) null);
    }
    if ((obj instanceof ConditionalSlot)) {
      return ((QualifiedName) null);
    }
    if ((obj instanceof Parameter)) {
      return ((QualifiedName) null);
    }
    return super.computeFullyQualifiedNameFromNameAttribute(obj);
  }
}
