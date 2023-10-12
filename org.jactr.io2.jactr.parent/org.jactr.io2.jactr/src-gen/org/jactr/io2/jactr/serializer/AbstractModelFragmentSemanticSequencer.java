/*
 * generated by Xtext 2.25.0
 */
package org.jactr.io2.jactr.serializer;

import com.google.inject.Inject;
import java.util.Set;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.xtext.Action;
import org.eclipse.xtext.Parameter;
import org.eclipse.xtext.ParserRule;
import org.eclipse.xtext.serializer.ISerializationContext;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;
import org.jactr.io2.jactr.modelFragment.Add;
import org.jactr.io2.jactr.modelFragment.AndSlot;
import org.jactr.io2.jactr.modelFragment.Buffer;
import org.jactr.io2.jactr.modelFragment.Buffers;
import org.jactr.io2.jactr.modelFragment.ChunkDef;
import org.jactr.io2.jactr.modelFragment.ChunkType;
import org.jactr.io2.jactr.modelFragment.Chunks;
import org.jactr.io2.jactr.modelFragment.ConditionalSlot;
import org.jactr.io2.jactr.modelFragment.Import;
import org.jactr.io2.jactr.modelFragment.IsaBlock;
import org.jactr.io2.jactr.modelFragment.Match;
import org.jactr.io2.jactr.modelFragment.ModelExtension;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.modelFragment.ModelFragmentPackage;
import org.jactr.io2.jactr.modelFragment.ModelModule;
import org.jactr.io2.jactr.modelFragment.Modify;
import org.jactr.io2.jactr.modelFragment.NotSlot;
import org.jactr.io2.jactr.modelFragment.OrSlot;
import org.jactr.io2.jactr.modelFragment.Output;
import org.jactr.io2.jactr.modelFragment.PackageDeclaration;
import org.jactr.io2.jactr.modelFragment.Parameters;
import org.jactr.io2.jactr.modelFragment.ParametersBlock;
import org.jactr.io2.jactr.modelFragment.Production;
import org.jactr.io2.jactr.modelFragment.Proxy;
import org.jactr.io2.jactr.modelFragment.Query;
import org.jactr.io2.jactr.modelFragment.Remove;
import org.jactr.io2.jactr.modelFragment.Script;
import org.jactr.io2.jactr.modelFragment.SimpleSlot;
import org.jactr.io2.jactr.modelFragment.Value;
import org.jactr.io2.jactr.services.ModelFragmentGrammarAccess;

@SuppressWarnings("all")
public abstract class AbstractModelFragmentSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private ModelFragmentGrammarAccess grammarAccess;
	
	@Override
	public void sequence(ISerializationContext context, EObject semanticObject) {
		EPackage epackage = semanticObject.eClass().getEPackage();
		ParserRule rule = context.getParserRule();
		Action action = context.getAssignedAction();
		Set<Parameter> parameters = context.getEnabledBooleanParameters();
		if (epackage == ModelFragmentPackage.eINSTANCE)
			switch (semanticObject.eClass().getClassifierID()) {
			case ModelFragmentPackage.ADD:
				sequence_Add(context, (Add) semanticObject); 
				return; 
			case ModelFragmentPackage.AND_SLOT:
				sequence_AndSlot(context, (AndSlot) semanticObject); 
				return; 
			case ModelFragmentPackage.BUFFER:
				sequence_Buffer_Overridable(context, (Buffer) semanticObject); 
				return; 
			case ModelFragmentPackage.BUFFERS:
				sequence_Buffers(context, (Buffers) semanticObject); 
				return; 
			case ModelFragmentPackage.CHUNK_DEF:
				sequence_ChunkDef_Overridable(context, (ChunkDef) semanticObject); 
				return; 
			case ModelFragmentPackage.CHUNK_TYPE:
				sequence_ChunkType_Overridable(context, (ChunkType) semanticObject); 
				return; 
			case ModelFragmentPackage.CHUNKS:
				sequence_Chunks(context, (Chunks) semanticObject); 
				return; 
			case ModelFragmentPackage.CONDITIONAL_SLOT:
				sequence_ConditionalSlot(context, (ConditionalSlot) semanticObject); 
				return; 
			case ModelFragmentPackage.IMPORT:
				sequence_Import(context, (Import) semanticObject); 
				return; 
			case ModelFragmentPackage.ISA_BLOCK:
				sequence_IsaBlock(context, (IsaBlock) semanticObject); 
				return; 
			case ModelFragmentPackage.MATCH:
				sequence_Match(context, (Match) semanticObject); 
				return; 
			case ModelFragmentPackage.MODEL_EXTENSION:
				sequence_ModelExtension(context, (ModelExtension) semanticObject); 
				return; 
			case ModelFragmentPackage.MODEL_FRAGMENT:
				sequence_ModelFragment(context, (ModelFragment) semanticObject); 
				return; 
			case ModelFragmentPackage.MODEL_MODULE:
				sequence_ModelModule(context, (ModelModule) semanticObject); 
				return; 
			case ModelFragmentPackage.MODIFY:
				sequence_SimpleSlotBlock(context, (Modify) semanticObject); 
				return; 
			case ModelFragmentPackage.NOT_SLOT:
				sequence_NotSlot(context, (NotSlot) semanticObject); 
				return; 
			case ModelFragmentPackage.OR_SLOT:
				sequence_OrSlot(context, (OrSlot) semanticObject); 
				return; 
			case ModelFragmentPackage.OUTPUT:
				sequence_Output(context, (Output) semanticObject); 
				return; 
			case ModelFragmentPackage.PACKAGE_DECLARATION:
				sequence_PackageDeclaration(context, (PackageDeclaration) semanticObject); 
				return; 
			case ModelFragmentPackage.PARAMETER:
				sequence_Parameter(context, (org.jactr.io2.jactr.modelFragment.Parameter) semanticObject); 
				return; 
			case ModelFragmentPackage.PARAMETERS:
				sequence_Parameters(context, (Parameters) semanticObject); 
				return; 
			case ModelFragmentPackage.PARAMETERS_BLOCK:
				sequence_ParametersBlock(context, (ParametersBlock) semanticObject); 
				return; 
			case ModelFragmentPackage.PRODUCTION:
				sequence_Overridable_Production(context, (Production) semanticObject); 
				return; 
			case ModelFragmentPackage.PROXY:
				sequence_Proxy(context, (Proxy) semanticObject); 
				return; 
			case ModelFragmentPackage.QUERY:
				sequence_Query(context, (Query) semanticObject); 
				return; 
			case ModelFragmentPackage.REMOVE:
				sequence_SimpleSlotBlock(context, (Remove) semanticObject); 
				return; 
			case ModelFragmentPackage.SCRIPT:
				sequence_Script(context, (Script) semanticObject); 
				return; 
			case ModelFragmentPackage.SIMPLE_SLOT:
				sequence_SimpleSlot(context, (SimpleSlot) semanticObject); 
				return; 
			case ModelFragmentPackage.VALUE:
				sequence_Value(context, (Value) semanticObject); 
				return; 
			}
		if (errorAcceptor != null)
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Contexts:
	 *     ProductionAction returns Add
	 *     Add returns Add
	 *
	 * Constraint:
	 *     (name=[Buffer|ID] isa=IsaBlock? slots+=ConditionalSlot*)
	 */
	protected void sequence_Add(ISerializationContext context, Add semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ComplexSlot returns AndSlot
	 *     BooleanOperation returns AndSlot
	 *     AndSlot returns AndSlot
	 *
	 * Constraint:
	 *     slots+=ComplexSlot*
	 */
	protected void sequence_AndSlot(ISerializationContext context, AndSlot semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Buffer returns Buffer
	 *
	 * Constraint:
	 *     (override?=OVERRIDE? name=ID source+=[ChunkDef|QualifiedName]* parameters=ParametersBlock?)
	 */
	protected void sequence_Buffer_Overridable(ISerializationContext context, Buffer semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Buffers returns Buffers
	 *
	 * Constraint:
	 *     buffers+=Buffer*
	 */
	protected void sequence_Buffers(ISerializationContext context, Buffers semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ChunkDef returns ChunkDef
	 *
	 * Constraint:
	 *     (override?=OVERRIDE? name=ID (slots+=SimpleSlot slots+=SimpleSlot*)? parameters=ParametersBlock?)
	 */
	protected void sequence_ChunkDef_Overridable(ISerializationContext context, ChunkDef semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Element returns ChunkType
	 *     ChunkType returns ChunkType
	 *
	 * Constraint:
	 *     (override?=OVERRIDE? name=ID superType=[ChunkType|QualifiedName]? slots+=SimpleSlot* parameters=ParametersBlock?)
	 */
	protected void sequence_ChunkType_Overridable(ISerializationContext context, ChunkType semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Element returns Chunks
	 *     Chunks returns Chunks
	 *
	 * Constraint:
	 *     (type=[ChunkType|QualifiedName] chunks+=ChunkDef chunks+=ChunkDef*)
	 */
	protected void sequence_Chunks(ISerializationContext context, Chunks semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ComplexSlot returns ConditionalSlot
	 *     ConditionalSlot returns ConditionalSlot
	 *
	 * Constraint:
	 *     ((name=ID | name=VARIABLE) condition=Condition value=Value)
	 */
	protected void sequence_ConditionalSlot(ISerializationContext context, ConditionalSlot semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Import returns Import
	 *
	 * Constraint:
	 *     importedNamespace=QualifiedNameWithWildcard
	 */
	protected void sequence_Import(ISerializationContext context, Import semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ModelFragmentPackage.Literals.IMPORT__IMPORTED_NAMESPACE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModelFragmentPackage.Literals.IMPORT__IMPORTED_NAMESPACE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getImportAccess().getImportedNamespaceQualifiedNameWithWildcardParserRuleCall_1_0(), semanticObject.getImportedNamespace());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     IsaBlock returns IsaBlock
	 *
	 * Constraint:
	 *     (type=[ChunkType|QualifiedName] | chunk=[ChunkDef|QualifiedName] | name=VARIABLE)
	 */
	protected void sequence_IsaBlock(ISerializationContext context, IsaBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ProductionCondition returns Match
	 *     Match returns Match
	 *
	 * Constraint:
	 *     (name=[Buffer|ID] isa=IsaBlock slots+=ComplexSlot*)
	 */
	protected void sequence_Match(ISerializationContext context, Match semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ModelExtension returns ModelExtension
	 *
	 * Constraint:
	 *     (extensionClass=STRING parameters=ParametersBlock?)
	 */
	protected void sequence_ModelExtension(ISerializationContext context, ModelExtension semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ModelFragment returns ModelFragment
	 *
	 * Constraint:
	 *     package=PackageDeclaration
	 */
	protected void sequence_ModelFragment(ISerializationContext context, ModelFragment semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ModelFragmentPackage.Literals.MODEL_FRAGMENT__PACKAGE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModelFragmentPackage.Literals.MODEL_FRAGMENT__PACKAGE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getModelFragmentAccess().getPackagePackageDeclarationParserRuleCall_0(), semanticObject.getPackage());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ModelModule returns ModelModule
	 *
	 * Constraint:
	 *     (moduleClass=STRING parameters=ParametersBlock?)
	 */
	protected void sequence_ModelModule(ISerializationContext context, ModelModule semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ComplexSlot returns NotSlot
	 *     BooleanOperation returns NotSlot
	 *     NotSlot returns NotSlot
	 *
	 * Constraint:
	 *     slots+=ComplexSlot*
	 */
	protected void sequence_NotSlot(ISerializationContext context, NotSlot semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ComplexSlot returns OrSlot
	 *     BooleanOperation returns OrSlot
	 *     OrSlot returns OrSlot
	 *
	 * Constraint:
	 *     slots+=ComplexSlot*
	 */
	protected void sequence_OrSlot(ISerializationContext context, OrSlot semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ProductionAction returns Output
	 *     Output returns Output
	 *
	 * Constraint:
	 *     string=STRING
	 */
	protected void sequence_Output(ISerializationContext context, Output semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ModelFragmentPackage.Literals.OUTPUT__STRING) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModelFragmentPackage.Literals.OUTPUT__STRING));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getOutputAccess().getStringSTRINGTerminalRuleCall_2_0(), semanticObject.getString());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Element returns Production
	 *     Production returns Production
	 *
	 * Constraint:
	 *     (override?=OVERRIDE? name=ID conditions+=ProductionCondition+ actions+=ProductionAction+ parameters=ParametersBlock?)
	 */
	protected void sequence_Overridable_Production(ISerializationContext context, Production semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     PackageDeclaration returns PackageDeclaration
	 *
	 * Constraint:
	 *     (
	 *         name=QualifiedName 
	 *         imports+=Import* 
	 *         modules+=ModelModule* 
	 *         extensions+=ModelExtension* 
	 *         buffers=Buffers? 
	 *         elements+=Element* 
	 *         parameters=ParametersBlock?
	 *     )
	 */
	protected void sequence_PackageDeclaration(ISerializationContext context, PackageDeclaration semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Parameter returns Parameter
	 *
	 * Constraint:
	 *     (name=STRING value=Value)
	 */
	protected void sequence_Parameter(ISerializationContext context, org.jactr.io2.jactr.modelFragment.Parameter semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ModelFragmentPackage.Literals.PARAMETER__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModelFragmentPackage.Literals.PARAMETER__NAME));
			if (transientValues.isValueTransient(semanticObject, ModelFragmentPackage.Literals.PARAMETER__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModelFragmentPackage.Literals.PARAMETER__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getParameterAccess().getNameSTRINGTerminalRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getParameterAccess().getValueValueParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ParametersBlock returns ParametersBlock
	 *
	 * Constraint:
	 *     parameter+=Parameter*
	 */
	protected void sequence_ParametersBlock(ISerializationContext context, ParametersBlock semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     Element returns Parameters
	 *     Parameters returns Parameters
	 *
	 * Constraint:
	 *     (elements+=ID elements+=ID* parameters=ParametersBlock)
	 */
	protected void sequence_Parameters(ISerializationContext context, Parameters semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ProductionCondition returns Proxy
	 *     Proxy returns Proxy
	 *     ProductionAction returns Proxy
	 *
	 * Constraint:
	 *     (name=STRING slots+=SimpleSlot*)
	 */
	protected void sequence_Proxy(ISerializationContext context, Proxy semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ProductionCondition returns Query
	 *     Query returns Query
	 *
	 * Constraint:
	 *     (name=[Buffer|ID] slots+=ComplexSlot*)
	 */
	protected void sequence_Query(ISerializationContext context, Query semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ProductionCondition returns Script
	 *     Script returns Script
	 *     ProductionAction returns Script
	 *
	 * Constraint:
	 *     (language=STRING script=CODE)
	 */
	protected void sequence_Script(ISerializationContext context, Script semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ModelFragmentPackage.Literals.SCRIPT__LANGUAGE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModelFragmentPackage.Literals.SCRIPT__LANGUAGE));
			if (transientValues.isValueTransient(semanticObject, ModelFragmentPackage.Literals.SCRIPT__SCRIPT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModelFragmentPackage.Literals.SCRIPT__SCRIPT));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getScriptAccess().getLanguageSTRINGTerminalRuleCall_2_0(), semanticObject.getLanguage());
		feeder.accept(grammarAccess.getScriptAccess().getScriptCODETerminalRuleCall_4_0(), semanticObject.getScript());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     ProductionAction returns Modify
	 *     Modify returns Modify
	 *
	 * Constraint:
	 *     (name=[Buffer|ID] slots+=SimpleSlot*)
	 */
	protected void sequence_SimpleSlotBlock(ISerializationContext context, Modify semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     ProductionAction returns Remove
	 *     Remove returns Remove
	 *
	 * Constraint:
	 *     (name=[Buffer|ID] slots+=SimpleSlot*)
	 */
	protected void sequence_SimpleSlotBlock(ISerializationContext context, Remove semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Contexts:
	 *     SimpleSlot returns SimpleSlot
	 *
	 * Constraint:
	 *     (name=ID value=Value)
	 */
	protected void sequence_SimpleSlot(ISerializationContext context, SimpleSlot semanticObject) {
		if (errorAcceptor != null) {
			if (transientValues.isValueTransient(semanticObject, ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModelFragmentPackage.Literals.SIMPLE_SLOT__NAME));
			if (transientValues.isValueTransient(semanticObject, ModelFragmentPackage.Literals.SIMPLE_SLOT__VALUE) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, ModelFragmentPackage.Literals.SIMPLE_SLOT__VALUE));
		}
		SequenceFeeder feeder = createSequencerFeeder(context, semanticObject);
		feeder.accept(grammarAccess.getSimpleSlotAccess().getNameIDTerminalRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getSimpleSlotAccess().getValueValueParserRuleCall_2_0(), semanticObject.getValue());
		feeder.finish();
	}
	
	
	/**
	 * Contexts:
	 *     Value returns Value
	 *
	 * Constraint:
	 *     (
	 *         name=VARIABLE | 
	 *         name=ID | 
	 *         name=NULL | 
	 *         string=STRING | 
	 *         number=NUMERIC | 
	 *         boolean=BOOLEAN
	 *     )
	 */
	protected void sequence_Value(ISerializationContext context, Value semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
}