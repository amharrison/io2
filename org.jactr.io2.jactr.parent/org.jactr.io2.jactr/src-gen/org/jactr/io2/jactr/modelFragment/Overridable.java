/**
 * generated by Xtext 2.25.0
 */
package org.jactr.io2.jactr.modelFragment;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Overridable</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.Overridable#isOverride <em>Override</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.Overridable#getName <em>Name</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.Overridable#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getOverridable()
 * @model
 * @generated
 */
public interface Overridable extends EObject
{
  /**
   * Returns the value of the '<em><b>Override</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Override</em>' attribute.
   * @see #setOverride(boolean)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getOverridable_Override()
   * @model
   * @generated
   */
  boolean isOverride();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.Overridable#isOverride <em>Override</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Override</em>' attribute.
   * @see #isOverride()
   * @generated
   */
  void setOverride(boolean value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getOverridable_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.Overridable#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference.
   * @see #setParameters(ParametersBlock)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getOverridable_Parameters()
   * @model containment="true"
   * @generated
   */
  ParametersBlock getParameters();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.Overridable#getParameters <em>Parameters</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameters</em>' containment reference.
   * @see #getParameters()
   * @generated
   */
  void setParameters(ParametersBlock value);

} // Overridable