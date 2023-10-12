/**
 * generated by Xtext 2.25.0
 */
package org.jactr.io2.jactr.modelFragment;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Model Module</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.ModelModule#getModuleClass <em>Module Class</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.ModelModule#getParameters <em>Parameters</em>}</li>
 * </ul>
 *
 * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getModelModule()
 * @model
 * @generated
 */
public interface ModelModule extends EObject
{
  /**
   * Returns the value of the '<em><b>Module Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Module Class</em>' attribute.
   * @see #setModuleClass(String)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getModelModule_ModuleClass()
   * @model
   * @generated
   */
  String getModuleClass();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.ModelModule#getModuleClass <em>Module Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Module Class</em>' attribute.
   * @see #getModuleClass()
   * @generated
   */
  void setModuleClass(String value);

  /**
   * Returns the value of the '<em><b>Parameters</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameters</em>' containment reference.
   * @see #setParameters(ParametersBlock)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getModelModule_Parameters()
   * @model containment="true"
   * @generated
   */
  ParametersBlock getParameters();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.ModelModule#getParameters <em>Parameters</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Parameters</em>' containment reference.
   * @see #getParameters()
   * @generated
   */
  void setParameters(ParametersBlock value);

} // ModelModule
