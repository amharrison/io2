/**
 * generated by Xtext 2.33.0.M1
 */
package org.jactr.io2.jactr.modelFragment.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.jactr.io2.jactr.modelFragment.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFragmentFactoryImpl extends EFactoryImpl implements ModelFragmentFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ModelFragmentFactory init()
  {
    try
    {
      ModelFragmentFactory theModelFragmentFactory = (ModelFragmentFactory)EPackage.Registry.INSTANCE.getEFactory(ModelFragmentPackage.eNS_URI);
      if (theModelFragmentFactory != null)
      {
        return theModelFragmentFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ModelFragmentFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelFragmentFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case ModelFragmentPackage.MODEL_FRAGMENT: return createModelFragment();
      case ModelFragmentPackage.PACKAGE_DECLARATION: return createPackageDeclaration();
      case ModelFragmentPackage.IMPORT: return createImport();
      case ModelFragmentPackage.MODEL_MODULE: return createModelModule();
      case ModelFragmentPackage.MODEL_EXTENSION: return createModelExtension();
      case ModelFragmentPackage.ELEMENT: return createElement();
      case ModelFragmentPackage.PARAMETERS: return createParameters();
      case ModelFragmentPackage.CHUNKS: return createChunks();
      case ModelFragmentPackage.CHUNK_DEF: return createChunkDef();
      case ModelFragmentPackage.CHUNK_TYPE: return createChunkType();
      case ModelFragmentPackage.OVERRIDABLE: return createOverridable();
      case ModelFragmentPackage.BUFFERS: return createBuffers();
      case ModelFragmentPackage.BUFFER: return createBuffer();
      case ModelFragmentPackage.PRODUCTION: return createProduction();
      case ModelFragmentPackage.PRODUCTION_CONDITION: return createProductionCondition();
      case ModelFragmentPackage.MATCH: return createMatch();
      case ModelFragmentPackage.QUERY: return createQuery();
      case ModelFragmentPackage.PROXY: return createProxy();
      case ModelFragmentPackage.SCRIPT: return createScript();
      case ModelFragmentPackage.PRODUCTION_ACTION: return createProductionAction();
      case ModelFragmentPackage.ADD: return createAdd();
      case ModelFragmentPackage.REMOVE: return createRemove();
      case ModelFragmentPackage.MODIFY: return createModify();
      case ModelFragmentPackage.OUTPUT: return createOutput();
      case ModelFragmentPackage.SIMPLE_SLOT_BLOCK: return createSimpleSlotBlock();
      case ModelFragmentPackage.ISA_BLOCK: return createIsaBlock();
      case ModelFragmentPackage.SIMPLE_SLOT: return createSimpleSlot();
      case ModelFragmentPackage.COMPLEX_SLOT: return createComplexSlot();
      case ModelFragmentPackage.BOOLEAN_OPERATION: return createBooleanOperation();
      case ModelFragmentPackage.CONDITIONAL_SLOT: return createConditionalSlot();
      case ModelFragmentPackage.OR_SLOT: return createOrSlot();
      case ModelFragmentPackage.AND_SLOT: return createAndSlot();
      case ModelFragmentPackage.NOT_SLOT: return createNotSlot();
      case ModelFragmentPackage.PARAMETER: return createParameter();
      case ModelFragmentPackage.PARAMETERS_BLOCK: return createParametersBlock();
      case ModelFragmentPackage.VALUE: return createValue();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ModelFragmentPackage.CONDITION:
        return createConditionFromString(eDataType, initialValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue)
  {
    switch (eDataType.getClassifierID())
    {
      case ModelFragmentPackage.CONDITION:
        return convertConditionToString(eDataType, instanceValue);
      default:
        throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ModelFragment createModelFragment()
  {
    ModelFragmentImpl modelFragment = new ModelFragmentImpl();
    return modelFragment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public PackageDeclaration createPackageDeclaration()
  {
    PackageDeclarationImpl packageDeclaration = new PackageDeclarationImpl();
    return packageDeclaration;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Import createImport()
  {
    ImportImpl import_ = new ImportImpl();
    return import_;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ModelModule createModelModule()
  {
    ModelModuleImpl modelModule = new ModelModuleImpl();
    return modelModule;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ModelExtension createModelExtension()
  {
    ModelExtensionImpl modelExtension = new ModelExtensionImpl();
    return modelExtension;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Element createElement()
  {
    ElementImpl element = new ElementImpl();
    return element;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Parameters createParameters()
  {
    ParametersImpl parameters = new ParametersImpl();
    return parameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Chunks createChunks()
  {
    ChunksImpl chunks = new ChunksImpl();
    return chunks;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ChunkDef createChunkDef()
  {
    ChunkDefImpl chunkDef = new ChunkDefImpl();
    return chunkDef;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ChunkType createChunkType()
  {
    ChunkTypeImpl chunkType = new ChunkTypeImpl();
    return chunkType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Overridable createOverridable()
  {
    OverridableImpl overridable = new OverridableImpl();
    return overridable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Buffers createBuffers()
  {
    BuffersImpl buffers = new BuffersImpl();
    return buffers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Buffer createBuffer()
  {
    BufferImpl buffer = new BufferImpl();
    return buffer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Production createProduction()
  {
    ProductionImpl production = new ProductionImpl();
    return production;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ProductionCondition createProductionCondition()
  {
    ProductionConditionImpl productionCondition = new ProductionConditionImpl();
    return productionCondition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Match createMatch()
  {
    MatchImpl match = new MatchImpl();
    return match;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Query createQuery()
  {
    QueryImpl query = new QueryImpl();
    return query;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Proxy createProxy()
  {
    ProxyImpl proxy = new ProxyImpl();
    return proxy;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Script createScript()
  {
    ScriptImpl script = new ScriptImpl();
    return script;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ProductionAction createProductionAction()
  {
    ProductionActionImpl productionAction = new ProductionActionImpl();
    return productionAction;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Add createAdd()
  {
    AddImpl add = new AddImpl();
    return add;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Remove createRemove()
  {
    RemoveImpl remove = new RemoveImpl();
    return remove;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Modify createModify()
  {
    ModifyImpl modify = new ModifyImpl();
    return modify;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Output createOutput()
  {
    OutputImpl output = new OutputImpl();
    return output;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SimpleSlotBlock createSimpleSlotBlock()
  {
    SimpleSlotBlockImpl simpleSlotBlock = new SimpleSlotBlockImpl();
    return simpleSlotBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public IsaBlock createIsaBlock()
  {
    IsaBlockImpl isaBlock = new IsaBlockImpl();
    return isaBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public SimpleSlot createSimpleSlot()
  {
    SimpleSlotImpl simpleSlot = new SimpleSlotImpl();
    return simpleSlot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ComplexSlot createComplexSlot()
  {
    ComplexSlotImpl complexSlot = new ComplexSlotImpl();
    return complexSlot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public BooleanOperation createBooleanOperation()
  {
    BooleanOperationImpl booleanOperation = new BooleanOperationImpl();
    return booleanOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ConditionalSlot createConditionalSlot()
  {
    ConditionalSlotImpl conditionalSlot = new ConditionalSlotImpl();
    return conditionalSlot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public OrSlot createOrSlot()
  {
    OrSlotImpl orSlot = new OrSlotImpl();
    return orSlot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public AndSlot createAndSlot()
  {
    AndSlotImpl andSlot = new AndSlotImpl();
    return andSlot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotSlot createNotSlot()
  {
    NotSlotImpl notSlot = new NotSlotImpl();
    return notSlot;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Parameter createParameter()
  {
    ParameterImpl parameter = new ParameterImpl();
    return parameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ParametersBlock createParametersBlock()
  {
    ParametersBlockImpl parametersBlock = new ParametersBlockImpl();
    return parametersBlock;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Value createValue()
  {
    ValueImpl value = new ValueImpl();
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Condition createConditionFromString(EDataType eDataType, String initialValue)
  {
    Condition result = Condition.get(initialValue);
    if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertConditionToString(EDataType eDataType, Object instanceValue)
  {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ModelFragmentPackage getModelFragmentPackage()
  {
    return (ModelFragmentPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ModelFragmentPackage getPackage()
  {
    return ModelFragmentPackage.eINSTANCE;
  }

} //ModelFragmentFactoryImpl
