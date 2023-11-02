/**
 * generated by Xtext 2.33.0.M1
 */
package org.jactr.io2.jactr.modelFragment.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import org.jactr.io2.jactr.modelFragment.Buffer;
import org.jactr.io2.jactr.modelFragment.ChunkDef;
import org.jactr.io2.jactr.modelFragment.ModelFragmentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Buffer</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.impl.BufferImpl#getSource <em>Source</em>}</li>
 * </ul>
 *
 * @generated
 */
public class BufferImpl extends OverridableImpl implements Buffer
{
  /**
   * The cached value of the '{@link #getSource() <em>Source</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected EList<ChunkDef> source;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BufferImpl()
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
    return ModelFragmentPackage.Literals.BUFFER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EList<ChunkDef> getSource()
  {
    if (source == null)
    {
      source = new EObjectResolvingEList<ChunkDef>(ChunkDef.class, this, ModelFragmentPackage.BUFFER__SOURCE);
    }
    return source;
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
      case ModelFragmentPackage.BUFFER__SOURCE:
        return getSource();
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
      case ModelFragmentPackage.BUFFER__SOURCE:
        getSource().clear();
        getSource().addAll((Collection<? extends ChunkDef>)newValue);
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
      case ModelFragmentPackage.BUFFER__SOURCE:
        getSource().clear();
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
      case ModelFragmentPackage.BUFFER__SOURCE:
        return source != null && !source.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //BufferImpl
