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

import org.jactr.io2.jactr.modelFragment.ChunkType;
import org.jactr.io2.jactr.modelFragment.ModelFragmentPackage;
import org.jactr.io2.jactr.modelFragment.Overridable;
import org.jactr.io2.jactr.modelFragment.ParametersBlock;
import org.jactr.io2.jactr.modelFragment.SimpleSlot;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Chunk Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.impl.ChunkTypeImpl#isOverride <em>Override</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.impl.ChunkTypeImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.impl.ChunkTypeImpl#getParameters <em>Parameters</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.impl.ChunkTypeImpl#getSuperType <em>Super Type</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.impl.ChunkTypeImpl#getSlots <em>Slots</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ChunkTypeImpl extends ElementImpl implements ChunkType
{
  /**
   * The default value of the '{@link #isOverride() <em>Override</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOverride()
   * @generated
   * @ordered
   */
  protected static final boolean OVERRIDE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOverride() <em>Override</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isOverride()
   * @generated
   * @ordered
   */
  protected boolean override = OVERRIDE_EDEFAULT;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected ParametersBlock parameters;

  /**
   * The cached value of the '{@link #getSuperType() <em>Super Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSuperType()
   * @generated
   * @ordered
   */
  protected ChunkType superType;

  /**
   * The cached value of the '{@link #getSlots() <em>Slots</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSlots()
   * @generated
   * @ordered
   */
  protected EList<SimpleSlot> slots;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ChunkTypeImpl()
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
    return ModelFragmentPackage.Literals.CHUNK_TYPE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean isOverride()
  {
    return override;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setOverride(boolean newOverride)
  {
    boolean oldOverride = override;
    override = newOverride;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelFragmentPackage.CHUNK_TYPE__OVERRIDE, oldOverride, override));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelFragmentPackage.CHUNK_TYPE__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ParametersBlock getParameters()
  {
    return parameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetParameters(ParametersBlock newParameters, NotificationChain msgs)
  {
    ParametersBlock oldParameters = parameters;
    parameters = newParameters;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelFragmentPackage.CHUNK_TYPE__PARAMETERS, oldParameters, newParameters);
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
  public void setParameters(ParametersBlock newParameters)
  {
    if (newParameters != parameters)
    {
      NotificationChain msgs = null;
      if (parameters != null)
        msgs = ((InternalEObject)parameters).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelFragmentPackage.CHUNK_TYPE__PARAMETERS, null, msgs);
      if (newParameters != null)
        msgs = ((InternalEObject)newParameters).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelFragmentPackage.CHUNK_TYPE__PARAMETERS, null, msgs);
      msgs = basicSetParameters(newParameters, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelFragmentPackage.CHUNK_TYPE__PARAMETERS, newParameters, newParameters));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ChunkType getSuperType()
  {
    if (superType != null && superType.eIsProxy())
    {
      InternalEObject oldSuperType = (InternalEObject)superType;
      superType = (ChunkType)eResolveProxy(oldSuperType);
      if (superType != oldSuperType)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelFragmentPackage.CHUNK_TYPE__SUPER_TYPE, oldSuperType, superType));
      }
    }
    return superType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ChunkType basicGetSuperType()
  {
    return superType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void setSuperType(ChunkType newSuperType)
  {
    ChunkType oldSuperType = superType;
    superType = newSuperType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ModelFragmentPackage.CHUNK_TYPE__SUPER_TYPE, oldSuperType, superType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<SimpleSlot> getSlots()
  {
    if (slots == null)
    {
      slots = new EObjectContainmentEList<SimpleSlot>(SimpleSlot.class, this, ModelFragmentPackage.CHUNK_TYPE__SLOTS);
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
      case ModelFragmentPackage.CHUNK_TYPE__PARAMETERS:
        return basicSetParameters(null, msgs);
      case ModelFragmentPackage.CHUNK_TYPE__SLOTS:
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
      case ModelFragmentPackage.CHUNK_TYPE__OVERRIDE:
        return isOverride();
      case ModelFragmentPackage.CHUNK_TYPE__NAME:
        return getName();
      case ModelFragmentPackage.CHUNK_TYPE__PARAMETERS:
        return getParameters();
      case ModelFragmentPackage.CHUNK_TYPE__SUPER_TYPE:
        if (resolve) return getSuperType();
        return basicGetSuperType();
      case ModelFragmentPackage.CHUNK_TYPE__SLOTS:
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
      case ModelFragmentPackage.CHUNK_TYPE__OVERRIDE:
        setOverride((Boolean)newValue);
        return;
      case ModelFragmentPackage.CHUNK_TYPE__NAME:
        setName((String)newValue);
        return;
      case ModelFragmentPackage.CHUNK_TYPE__PARAMETERS:
        setParameters((ParametersBlock)newValue);
        return;
      case ModelFragmentPackage.CHUNK_TYPE__SUPER_TYPE:
        setSuperType((ChunkType)newValue);
        return;
      case ModelFragmentPackage.CHUNK_TYPE__SLOTS:
        getSlots().clear();
        getSlots().addAll((Collection<? extends SimpleSlot>)newValue);
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
      case ModelFragmentPackage.CHUNK_TYPE__OVERRIDE:
        setOverride(OVERRIDE_EDEFAULT);
        return;
      case ModelFragmentPackage.CHUNK_TYPE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ModelFragmentPackage.CHUNK_TYPE__PARAMETERS:
        setParameters((ParametersBlock)null);
        return;
      case ModelFragmentPackage.CHUNK_TYPE__SUPER_TYPE:
        setSuperType((ChunkType)null);
        return;
      case ModelFragmentPackage.CHUNK_TYPE__SLOTS:
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
      case ModelFragmentPackage.CHUNK_TYPE__OVERRIDE:
        return override != OVERRIDE_EDEFAULT;
      case ModelFragmentPackage.CHUNK_TYPE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ModelFragmentPackage.CHUNK_TYPE__PARAMETERS:
        return parameters != null;
      case ModelFragmentPackage.CHUNK_TYPE__SUPER_TYPE:
        return superType != null;
      case ModelFragmentPackage.CHUNK_TYPE__SLOTS:
        return slots != null && !slots.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass)
  {
    if (baseClass == Overridable.class)
    {
      switch (derivedFeatureID)
      {
        case ModelFragmentPackage.CHUNK_TYPE__OVERRIDE: return ModelFragmentPackage.OVERRIDABLE__OVERRIDE;
        case ModelFragmentPackage.CHUNK_TYPE__NAME: return ModelFragmentPackage.OVERRIDABLE__NAME;
        case ModelFragmentPackage.CHUNK_TYPE__PARAMETERS: return ModelFragmentPackage.OVERRIDABLE__PARAMETERS;
        default: return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass)
  {
    if (baseClass == Overridable.class)
    {
      switch (baseFeatureID)
      {
        case ModelFragmentPackage.OVERRIDABLE__OVERRIDE: return ModelFragmentPackage.CHUNK_TYPE__OVERRIDE;
        case ModelFragmentPackage.OVERRIDABLE__NAME: return ModelFragmentPackage.CHUNK_TYPE__NAME;
        case ModelFragmentPackage.OVERRIDABLE__PARAMETERS: return ModelFragmentPackage.CHUNK_TYPE__PARAMETERS;
        default: return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (override: ");
    result.append(override);
    result.append(", name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //ChunkTypeImpl
