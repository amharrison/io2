/**
 * generated by Xtext 2.25.0
 */
package org.jactr.io2.jactr.modelFragment;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Match</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.Match#getName <em>Name</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.Match#getIsa <em>Isa</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.Match#getSlots <em>Slots</em>}</li>
 * </ul>
 *
 * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getMatch()
 * @model
 * @generated
 */
public interface Match extends ProductionCondition
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' reference.
   * @see #setName(Buffer)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getMatch_Name()
   * @model
   * @generated
   */
  Buffer getName();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.Match#getName <em>Name</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' reference.
   * @see #getName()
   * @generated
   */
  void setName(Buffer value);

  /**
   * Returns the value of the '<em><b>Isa</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Isa</em>' containment reference.
   * @see #setIsa(IsaBlock)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getMatch_Isa()
   * @model containment="true"
   * @generated
   */
  IsaBlock getIsa();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.Match#getIsa <em>Isa</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Isa</em>' containment reference.
   * @see #getIsa()
   * @generated
   */
  void setIsa(IsaBlock value);

  /**
   * Returns the value of the '<em><b>Slots</b></em>' containment reference list.
   * The list contents are of type {@link org.jactr.io2.jactr.modelFragment.ComplexSlot}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Slots</em>' containment reference list.
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getMatch_Slots()
   * @model containment="true"
   * @generated
   */
  EList<ComplexSlot> getSlots();

} // Match