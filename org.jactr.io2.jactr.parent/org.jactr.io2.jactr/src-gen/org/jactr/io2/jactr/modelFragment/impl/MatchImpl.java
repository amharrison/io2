/**
 * generated by Xtext 2.33.0.M1
 */
package org.jactr.io2.jactr.modelFragment.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.jactr.io2.jactr.modelFragment.Buffer;
import org.jactr.io2.jactr.modelFragment.ComplexSlot;
import org.jactr.io2.jactr.modelFragment.IsaBlock;
import org.jactr.io2.jactr.modelFragment.Match;
import org.jactr.io2.jactr.modelFragment.ModelFragmentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Match</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.impl.MatchImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.impl.MatchImpl#getIsa <em>Isa</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.impl.MatchImpl#getSlots <em>Slots</em>}</li>
 * </ul>
 *
 * @generated
 */
public class MatchImpl extends ProductionConditionImpl implements Match
{
  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected Buffer name;

  /**
   * The cached value of the '{@link #getIsa() <em>Isa</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIsa()
   * @generated
   * @ordered
   */
  protected IsaBlock isa;

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
  protected MatchImpl()
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
    return ModelFragmentPackage.Literals.MATCH;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Buffer getName()
  {
    if (name != null && name.eIsProxy())
    {
      InternalEObject oldName = (InternalEObject)name;
      name = (Buffer)eResolveProxy(oldName);
      if (name != oldName)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelFragmentPackage.MATCH__NAME, oldName, name));
      }
    }
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Buffer basicGetName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setName(Buffer newName)
  {
    Buffer oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelFragmentPackage.MATCH__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public IsaBlock getIsa()
  {
    return isa;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIsa(IsaBlock newIsa, NotificationChain msgs)
  {
    IsaBlock oldIsa = isa;
    isa = newIsa;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelFragmentPackage.MATCH__ISA, oldIsa, newIsa);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setIsa(IsaBlock newIsa)
  {
    if (newIsa != isa)
    {
      NotificationChain msgs = null;
      if (isa != null)
        msgs = ((InternalEObject)isa).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelFragmentPackage.MATCH__ISA, null, msgs);
      if (newIsa != null)
        msgs = ((InternalEObject)newIsa).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelFragmentPackage.MATCH__ISA, null, msgs);
      msgs = basicSetIsa(newIsa, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelFragmentPackage.MATCH__ISA, newIsa, newIsa));
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
      slots = new EObjectContainmentEList<ComplexSlot>(ComplexSlot.class, this, ModelFragmentPackage.MATCH__SLOTS);
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
      case ModelFragmentPackage.MATCH__ISA:
        return basicSetIsa(null, msgs);
      case ModelFragmentPackage.MATCH__SLOTS:
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
      case ModelFragmentPackage.MATCH__NAME:
        if (resolve) return getName();
        return basicGetName();
      case ModelFragmentPackage.MATCH__ISA:
        return getIsa();
      case ModelFragmentPackage.MATCH__SLOTS:
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
      case ModelFragmentPackage.MATCH__NAME:
        setName((Buffer)newValue);
        return;
      case ModelFragmentPackage.MATCH__ISA:
        setIsa((IsaBlock)newValue);
        return;
      case ModelFragmentPackage.MATCH__SLOTS:
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
      case ModelFragmentPackage.MATCH__NAME:
        setName((Buffer)null);
        return;
      case ModelFragmentPackage.MATCH__ISA:
        setIsa((IsaBlock)null);
        return;
      case ModelFragmentPackage.MATCH__SLOTS:
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
      case ModelFragmentPackage.MATCH__NAME:
        return name != null;
      case ModelFragmentPackage.MATCH__ISA:
        return isa != null;
      case ModelFragmentPackage.MATCH__SLOTS:
        return slots != null && !slots.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //MatchImpl
