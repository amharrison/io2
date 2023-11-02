/**
 * generated by Xtext 2.33.0.M1
 */
package org.jactr.io2.jactr.modelFragment;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conditional Slot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.ConditionalSlot#getName <em>Name</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.ConditionalSlot#getCondition <em>Condition</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.ConditionalSlot#getValue <em>Value</em>}</li>
 * </ul>
 *
 * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getConditionalSlot()
 * @model
 * @generated
 */
public interface ConditionalSlot extends ComplexSlot
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getConditionalSlot_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.ConditionalSlot#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Condition</b></em>' attribute.
   * The literals are from the enumeration {@link org.jactr.io2.jactr.modelFragment.Condition}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Condition</em>' attribute.
   * @see org.jactr.io2.jactr.modelFragment.Condition
   * @see #setCondition(Condition)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getConditionalSlot_Condition()
   * @model
   * @generated
   */
  Condition getCondition();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.ConditionalSlot#getCondition <em>Condition</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Condition</em>' attribute.
   * @see org.jactr.io2.jactr.modelFragment.Condition
   * @see #getCondition()
   * @generated
   */
  void setCondition(Condition value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' containment reference.
   * @see #setValue(Value)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getConditionalSlot_Value()
   * @model containment="true"
   * @generated
   */
  Value getValue();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.ConditionalSlot#getValue <em>Value</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Value</em>' containment reference.
   * @see #getValue()
   * @generated
   */
  void setValue(Value value);

} // ConditionalSlot
