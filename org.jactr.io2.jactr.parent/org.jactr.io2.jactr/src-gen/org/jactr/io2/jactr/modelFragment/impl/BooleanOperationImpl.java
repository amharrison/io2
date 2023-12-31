/**
 * generated by Xtext 2.33.0.M1
 */
package org.jactr.io2.jactr.modelFragment.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.jactr.io2.jactr.modelFragment.BooleanOperation;
import org.jactr.io2.jactr.modelFragment.ComplexSlot;
import org.jactr.io2.jactr.modelFragment.ModelFragmentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.impl.BooleanOperationImpl#getSlots <em>Slots</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BooleanOperationImpl extends ComplexSlotImpl implements BooleanOperation
{
  /**
   * The cached value of the '{@link #getSlots() <em>Slots</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSlots()
   * @generated
   * @ordered
   */
  protected EList<ComplexSlot> slots;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BooleanOperationImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ModelFragmentPackage.Literals.BOOLEAN_OPERATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<ComplexSlot> getSlots()
  {
    if (slots == null)
    {
      slots = new EObjectContainmentEList<ComplexSlot>(ComplexSlot.class, this, ModelFragmentPackage.BOOLEAN_OPERATION__SLOTS);
    }
    return slots;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ModelFragmentPackage.BOOLEAN_OPERATION__SLOTS:
        return ((InternalEList<?>)getSlots()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ModelFragmentPackage.BOOLEAN_OPERATION__SLOTS:
        return getSlots();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ModelFragmentPackage.BOOLEAN_OPERATION__SLOTS:
        getSlots().clear();
        getSlots().addAll((Collection<? extends ComplexSlot>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ModelFragmentPackage.BOOLEAN_OPERATION__SLOTS:
        getSlots().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ModelFragmentPackage.BOOLEAN_OPERATION__SLOTS:
        return slots != null && !slots.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //BooleanOperationImpl
