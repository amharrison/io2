/**
 * generated by Xtext 2.25.0
 */
package org.jactr.io2.jactr.modelFragment;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Query</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.Query#getName <em>Name</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.Query#getSlots <em>Slots</em>}</li>
 * </ul>
 *
 * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getQuery()
 * @model
 * @generated
 */
public interface Query extends ProductionCondition
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' reference.
   * @see #setName(Buffer)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getQuery_Name()
   * @model
   * @generated
   */
  Buffer getName();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.Query#getName <em>Name</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' reference.
   * @see #getName()
   * @generated
   */
  void setName(Buffer value);

  /**
   * Returns the value of the '<em><b>Slots</b></em>' containment reference list.
   * The list contents are of type {@link org.jactr.io2.jactr.modelFragment.ComplexSlot}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Slots</em>' containment reference list.
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getQuery_Slots()
   * @model containment="true"
   * @generated
   */
  EList<ComplexSlot> getSlots();

} // Query