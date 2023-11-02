/**
 * generated by Xtext 2.33.0.M1
 */
package org.jactr.io2.jactr.modelFragment;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Chunk Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.ChunkType#getSuperType <em>Super Type</em>}</li>
 *   <li>{@link org.jactr.io2.jactr.modelFragment.ChunkType#getSlots <em>Slots</em>}</li>
 * </ul>
 *
 * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getChunkType()
 * @model
 * @generated
 */
public interface ChunkType extends Element, Overridable
{
  /**
   * Returns the value of the '<em><b>Super Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Super Type</em>' reference.
   * @see #setSuperType(ChunkType)
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getChunkType_SuperType()
   * @model
   * @generated
   */
  ChunkType getSuperType();

  /**
   * Sets the value of the '{@link org.jactr.io2.jactr.modelFragment.ChunkType#getSuperType <em>Super Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Super Type</em>' reference.
   * @see #getSuperType()
   * @generated
   */
  void setSuperType(ChunkType value);

  /**
   * Returns the value of the '<em><b>Slots</b></em>' containment reference list.
   * The list contents are of type {@link org.jactr.io2.jactr.modelFragment.SimpleSlot}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Slots</em>' containment reference list.
   * @see org.jactr.io2.jactr.modelFragment.ModelFragmentPackage#getChunkType_Slots()
   * @model containment="true"
   * @generated
   */
  EList<SimpleSlot> getSlots();

} // ChunkType
