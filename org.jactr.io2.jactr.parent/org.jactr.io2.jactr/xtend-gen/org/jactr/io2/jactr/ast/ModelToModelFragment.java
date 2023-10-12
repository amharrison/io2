package org.jactr.io2.jactr.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import javax.inject.Inject;
import org.eclipse.collections.api.block.procedure.Procedure;
import org.eclipse.collections.api.multimap.MutableMultimap;
import org.eclipse.collections.api.multimap.list.MutableListMultimap;
import org.eclipse.collections.impl.factory.Multimaps;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.jactr.core.buffer.IActivationBuffer;
import org.jactr.core.chunk.IChunk;
import org.jactr.core.chunktype.IChunkType;
import org.jactr.core.extensions.IExtension;
import org.jactr.core.model.IModel;
import org.jactr.core.module.IModule;
import org.jactr.core.production.IProduction;
import org.jactr.core.production.action.AddAction;
import org.jactr.core.production.action.IAction;
import org.jactr.core.production.action.ModifyAction;
import org.jactr.core.production.action.OutputAction;
import org.jactr.core.production.action.ProxyAction;
import org.jactr.core.production.action.RemoveAction;
import org.jactr.core.production.condition.ChunkCondition;
import org.jactr.core.production.condition.ChunkTypeCondition;
import org.jactr.core.production.condition.ICondition;
import org.jactr.core.production.condition.ProxyCondition;
import org.jactr.core.production.condition.QueryCondition;
import org.jactr.core.production.condition.VariableCondition;
import org.jactr.core.slot.IConditionalSlot;
import org.jactr.core.slot.ILogicalSlot;
import org.jactr.core.slot.ISlot;
import org.jactr.core.utils.parameter.IParameterized;
import org.jactr.io2.jactr.modelFragment.Add;
import org.jactr.io2.jactr.modelFragment.BooleanOperation;
import org.jactr.io2.jactr.modelFragment.Buffer;
import org.jactr.io2.jactr.modelFragment.Buffers;
import org.jactr.io2.jactr.modelFragment.ChunkDef;
import org.jactr.io2.jactr.modelFragment.ChunkType;
import org.jactr.io2.jactr.modelFragment.Chunks;
import org.jactr.io2.jactr.modelFragment.ComplexSlot;
import org.jactr.io2.jactr.modelFragment.Condition;
import org.jactr.io2.jactr.modelFragment.ConditionalSlot;
import org.jactr.io2.jactr.modelFragment.Element;
import org.jactr.io2.jactr.modelFragment.IsaBlock;
import org.jactr.io2.jactr.modelFragment.Match;
import org.jactr.io2.jactr.modelFragment.ModelExtension;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.modelFragment.ModelFragmentFactory;
import org.jactr.io2.jactr.modelFragment.ModelModule;
import org.jactr.io2.jactr.modelFragment.Modify;
import org.jactr.io2.jactr.modelFragment.Output;
import org.jactr.io2.jactr.modelFragment.PackageDeclaration;
import org.jactr.io2.jactr.modelFragment.Parameter;
import org.jactr.io2.jactr.modelFragment.ParametersBlock;
import org.jactr.io2.jactr.modelFragment.Production;
import org.jactr.io2.jactr.modelFragment.ProductionAction;
import org.jactr.io2.jactr.modelFragment.ProductionCondition;
import org.jactr.io2.jactr.modelFragment.Proxy;
import org.jactr.io2.jactr.modelFragment.Query;
import org.jactr.io2.jactr.modelFragment.Remove;
import org.jactr.io2.jactr.modelFragment.Script;
import org.jactr.io2.jactr.modelFragment.SimpleSlot;
import org.jactr.io2.jactr.modelFragment.Value;
import org.jactr.scripting.action.ScriptableAction;
import org.jactr.scripting.condition.ScriptableCondition;

/**
 * converts an IModel to its meta ModelFragment
 */
@SuppressWarnings("all")
public class ModelToModelFragment {
  @Inject
  private ResourceSet _resourceSet;
  
  public ModelFragment convert(final IModel model) {
    try {
      ModelFragment _xblockexpression = null;
      {
        final ModelFragment modelFragment = ModelFragmentFactory.eINSTANCE.createModelFragment();
        modelFragment.setPackage(ModelFragmentFactory.eINSTANCE.createPackageDeclaration());
        PackageDeclaration _package = modelFragment.getPackage();
        _package.setName(model.getName());
        Iterable<ModelModule> _modules = this.modules(model);
        for (final ModelModule module : _modules) {
          modelFragment.getPackage().getModules().add(module);
        }
        Iterable<ModelExtension> _extensions = this.extensions(model);
        for (final ModelExtension ext : _extensions) {
          modelFragment.getPackage().getExtensions().add(ext);
        }
        final MutableListMultimap<String, EObject> variables = Multimaps.mutable.list.<String, EObject>empty();
        ArrayList<Element> _chunkTypes = this.chunkTypes(model, variables);
        for (final Element chunkType : _chunkTypes) {
          modelFragment.getPackage().getElements().add(chunkType);
        }
        PackageDeclaration _package_1 = modelFragment.getPackage();
        _package_1.setBuffers(this.buffers(model, variables));
        Collection<IProduction> _get = model.getProceduralModule().getProductions().get();
        for (final IProduction production : _get) {
          modelFragment.getPackage().getElements().add(this.toProduction(production, variables));
        }
        PackageDeclaration _package_2 = modelFragment.getPackage();
        _package_2.setParameters(this.parameters(model));
        _xblockexpression = modelFragment;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * ASTs should be associated with a resource, so we create and reuse a dummy one.
   */
  public ModelFragment createFakeFragment() {
    ModelFragment _xblockexpression = null;
    {
      final ModelFragment modelFragment = ModelFragmentFactory.eINSTANCE.createModelFragment();
      modelFragment.setPackage(ModelFragmentFactory.eINSTANCE.createPackageDeclaration());
      PackageDeclaration _package = modelFragment.getPackage();
      _package.setName("dummy");
      PackageDeclaration _package_1 = modelFragment.getPackage();
      _package_1.setBuffers(ModelFragmentFactory.eINSTANCE.createBuffers());
      final URI uri = URI.createURI("dummy:/inmemory.jactr");
      final Resource oldResource = this._resourceSet.getResource(uri, false);
      Resource resource = oldResource;
      if ((resource == null)) {
        resource = this._resourceSet.createResource(uri);
      }
      resource.getContents().clear();
      resource.getContents().add(modelFragment);
      _xblockexpression = modelFragment;
    }
    return _xblockexpression;
  }
  
  /**
   * sparse conversion of chunktype to ChunkType. It is sparse in that
   * the parent tree is no rendered. But all the derived slots are.
   */
  public Object convert(final IChunkType chunkType, final boolean includeChunks) {
    return null;
  }
  
  public ChunkDef convert(final IChunk chunk) {
    ChunkDef _xblockexpression = null;
    {
      final ChunkDef def = this.chunk(chunk);
      final ModelFragment fakeFragment = this.createFakeFragment();
      final Chunks chunks = ModelFragmentFactory.eINSTANCE.createChunks();
      chunks.setType(ModelFragmentFactory.eINSTANCE.createChunkType());
      ChunkType _type = chunks.getType();
      _type.setName("dummy-chunks");
      chunks.getChunks().add(def);
      fakeFragment.getPackage().getElements().add(chunks);
      _xblockexpression = def;
    }
    return _xblockexpression;
  }
  
  public Production convert(final IProduction production) {
    Production _xblockexpression = null;
    {
      final MutableListMultimap<String, EObject> variables = Multimaps.mutable.list.<String, EObject>empty();
      final ModelFragment fakeFragment = this.createFakeFragment();
      final Production productionDef = this.toProduction(production, variables);
      final Procedure<EObject> _function = (EObject node) -> {
        if ((node instanceof Production)) {
          fakeFragment.getPackage().getElements().add(((Element)node));
        }
        if ((node instanceof ChunkDef)) {
          final Chunks chunks = ModelFragmentFactory.eINSTANCE.createChunks();
          chunks.setType(ModelFragmentFactory.eINSTANCE.createChunkType());
          ChunkType _type = chunks.getType();
          _type.setName("dummy-chunks");
          chunks.getChunks().add(((ChunkDef)node));
          fakeFragment.getPackage().getElements().add(chunks);
          fakeFragment.getPackage().getElements().add(chunks.getType());
        }
        if ((node instanceof ChunkType)) {
          fakeFragment.getPackage().getElements().add(((Element)node));
        }
        if ((node instanceof Buffer)) {
          fakeFragment.getPackage().getBuffers().getBuffers().add(((Buffer)node));
        }
      };
      variables.valuesView().forEach(_function);
      fakeFragment.getPackage().getElements().add(productionDef);
      _xblockexpression = productionDef;
    }
    return _xblockexpression;
  }
  
  public Object convert(final IActivationBuffer buffer) {
    return null;
  }
  
  protected Iterable<ModelModule> modules(final IModel model) {
    final Function1<IModule, ModelModule> _function = (IModule module) -> {
      ModelModule _xblockexpression = null;
      {
        final ModelModule m = ModelFragmentFactory.eINSTANCE.createModelModule();
        m.setModuleClass(module.getClass().getName());
        if ((module instanceof IParameterized)) {
          m.setParameters(this.parameters(((IParameterized)module)));
        }
        _xblockexpression = m;
      }
      return _xblockexpression;
    };
    return IterableExtensions.<IModule, ModelModule>map(model.getModules(), _function);
  }
  
  protected Iterable<ModelExtension> extensions(final IModel model) {
    final Function1<IExtension, ModelExtension> _function = (IExtension module) -> {
      ModelExtension _xblockexpression = null;
      {
        final ModelExtension m = ModelFragmentFactory.eINSTANCE.createModelExtension();
        m.setExtensionClass(module.getClass().getName());
        if ((module instanceof IParameterized)) {
          m.setParameters(this.parameters(module));
        }
        _xblockexpression = m;
      }
      return _xblockexpression;
    };
    return IterableExtensions.<IExtension, ModelExtension>map(model.getExtensions(), _function);
  }
  
  protected Buffers buffers(final IModel model, final MutableMultimap<String, EObject> variables) {
    Buffers _xblockexpression = null;
    {
      final Buffers buffers = ModelFragmentFactory.eINSTANCE.createBuffers();
      final Function1<IActivationBuffer, Buffer> _function = (IActivationBuffer b) -> {
        return this.toBuffer(b);
      };
      final Consumer<Buffer> _function_1 = (Buffer b) -> {
        buffers.getBuffers().add(b);
        variables.put(b.getName(), b);
      };
      IterableExtensions.<IActivationBuffer, Buffer>map(model.getActivationBuffers(), _function).forEach(_function_1);
      _xblockexpression = buffers;
    }
    return _xblockexpression;
  }
  
  protected Buffer toBuffer(final IActivationBuffer buffer) {
    Buffer _xblockexpression = null;
    {
      final Buffer buff = ModelFragmentFactory.eINSTANCE.createBuffer();
      buff.setName(buffer.getName());
      if ((buffer instanceof IParameterized)) {
        buff.setParameters(this.parameters(((IParameterized)buffer)));
      }
      _xblockexpression = buff;
    }
    return _xblockexpression;
  }
  
  protected Production toProduction(final IProduction production, final MutableMultimap<String, EObject> variables) {
    Production _xblockexpression = null;
    {
      final Production p = ModelFragmentFactory.eINSTANCE.createProduction();
      p.setName(production.getSymbolicProduction().getName());
      Collection<ICondition> _conditions = production.getSymbolicProduction().getConditions();
      for (final ICondition condition : _conditions) {
        ProductionCondition _condition = this.toCondition(condition, variables);
        p.getConditions().add(((ProductionCondition) _condition));
      }
      Collection<IAction> _actions = production.getSymbolicProduction().getActions();
      for (final IAction action : _actions) {
        ProductionAction _action = this.toAction(action, variables);
        p.getActions().add(((ProductionAction) _action));
      }
      p.setParameters(this.parameters(production.getSubsymbolicProduction()));
      _xblockexpression = p;
    }
    return _xblockexpression;
  }
  
  protected ProductionCondition _toCondition(final ChunkCondition condition, final MutableMultimap<String, EObject> variables) {
    Match _xblockexpression = null;
    {
      final Match ast = ModelFragmentFactory.eINSTANCE.createMatch();
      final Function1<EObject, Boolean> _function = (EObject n) -> {
        return Boolean.valueOf((n instanceof Buffer));
      };
      EObject _findFirst = IterableExtensions.<EObject>findFirst(variables.get(condition.getBufferName()), _function);
      ast.setName(((Buffer) _findFirst));
      ast.setIsa(ModelFragmentFactory.eINSTANCE.createIsaBlock());
      IsaBlock _isa = ast.getIsa();
      final Function1<EObject, Boolean> _function_1 = (EObject n) -> {
        return Boolean.valueOf((n instanceof ChunkDef));
      };
      EObject _findFirst_1 = IterableExtensions.<EObject>findFirst(variables.get(condition.getChunk().getSymbolicChunk().getName()), _function_1);
      _isa.setChunk(((ChunkDef) _findFirst_1));
      Buffer _name = ast.getName();
      boolean _tripleEquals = (_name == null);
      if (_tripleEquals) {
        ast.setName(ModelFragmentFactory.eINSTANCE.createBuffer());
        Buffer _name_1 = ast.getName();
        _name_1.setName(condition.getBufferName());
        variables.put(ast.getName().getName(), ast.getName());
      }
      ChunkDef _chunk = ast.getIsa().getChunk();
      boolean _tripleEquals_1 = (_chunk == null);
      if (_tripleEquals_1) {
        IsaBlock _isa_1 = ast.getIsa();
        _isa_1.setChunk(ModelFragmentFactory.eINSTANCE.createChunkDef());
        ChunkDef _chunk_1 = ast.getIsa().getChunk();
        _chunk_1.setName(condition.getChunk().getSymbolicChunk().getName());
        variables.put(ast.getIsa().getChunk().getName(), ast.getIsa().getChunk());
      }
      Collection<? extends ISlot> _slots = condition.getSlots();
      for (final ISlot slot : _slots) {
        EObject _slot = this.slot(slot, false);
        ast.getSlots().add(((ComplexSlot) _slot));
      }
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionCondition _toCondition(final ChunkTypeCondition condition, final MutableMultimap<String, EObject> variables) {
    Match _xblockexpression = null;
    {
      final Match ast = ModelFragmentFactory.eINSTANCE.createMatch();
      final Function1<EObject, Boolean> _function = (EObject n) -> {
        return Boolean.valueOf((n instanceof Buffer));
      };
      EObject _findFirst = IterableExtensions.<EObject>findFirst(variables.get(condition.getBufferName()), _function);
      ast.setName(((Buffer) _findFirst));
      ast.setIsa(ModelFragmentFactory.eINSTANCE.createIsaBlock());
      IsaBlock _isa = ast.getIsa();
      final Function1<EObject, Boolean> _function_1 = (EObject n) -> {
        return Boolean.valueOf((n instanceof ChunkType));
      };
      EObject _findFirst_1 = IterableExtensions.<EObject>findFirst(variables.get(condition.getChunkType().getSymbolicChunkType().getName()), _function_1);
      _isa.setType(((ChunkType) _findFirst_1));
      Buffer _name = ast.getName();
      boolean _tripleEquals = (_name == null);
      if (_tripleEquals) {
        ast.setName(ModelFragmentFactory.eINSTANCE.createBuffer());
        Buffer _name_1 = ast.getName();
        _name_1.setName(condition.getBufferName());
        variables.put(ast.getName().getName(), ast.getName());
      }
      ChunkType _type = ast.getIsa().getType();
      boolean _tripleEquals_1 = (_type == null);
      if (_tripleEquals_1) {
        IsaBlock _isa_1 = ast.getIsa();
        _isa_1.setType(ModelFragmentFactory.eINSTANCE.createChunkType());
        ChunkType _type_1 = ast.getIsa().getType();
        _type_1.setName(condition.getChunkType().getSymbolicChunkType().getName());
        variables.put(ast.getIsa().getType().getName(), ast.getIsa().getType());
      }
      Collection<? extends ISlot> _slots = condition.getSlots();
      for (final ISlot slot : _slots) {
        EObject _slot = this.slot(slot, false);
        ast.getSlots().add(((ComplexSlot) _slot));
      }
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionCondition _toCondition(final VariableCondition condition, final MutableMultimap<String, EObject> variables) {
    Match _xblockexpression = null;
    {
      final Match ast = ModelFragmentFactory.eINSTANCE.createMatch();
      final Function1<EObject, Boolean> _function = (EObject n) -> {
        return Boolean.valueOf((n instanceof Buffer));
      };
      EObject _findFirst = IterableExtensions.<EObject>findFirst(variables.get(condition.getBufferName()), _function);
      ast.setName(((Buffer) _findFirst));
      ast.setIsa(ModelFragmentFactory.eINSTANCE.createIsaBlock());
      IsaBlock _isa = ast.getIsa();
      _isa.setName(condition.getVariableName());
      Buffer _name = ast.getName();
      boolean _tripleEquals = (_name == null);
      if (_tripleEquals) {
        ast.setName(ModelFragmentFactory.eINSTANCE.createBuffer());
        Buffer _name_1 = ast.getName();
        _name_1.setName(condition.getBufferName());
        variables.put(ast.getName().getName(), ast.getName());
      }
      Collection<? extends ISlot> _slots = condition.getSlots();
      for (final ISlot slot : _slots) {
        EObject _slot = this.slot(slot, false);
        ast.getSlots().add(((ComplexSlot) _slot));
      }
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionCondition _toCondition(final QueryCondition condition, final MutableMultimap<String, EObject> variables) {
    Query _xblockexpression = null;
    {
      final Query ast = ModelFragmentFactory.eINSTANCE.createQuery();
      final Function1<EObject, Boolean> _function = (EObject n) -> {
        return Boolean.valueOf((n instanceof Buffer));
      };
      EObject _findFirst = IterableExtensions.<EObject>findFirst(variables.get(condition.getBufferName()), _function);
      ast.setName(((Buffer) _findFirst));
      Buffer _name = ast.getName();
      boolean _tripleEquals = (_name == null);
      if (_tripleEquals) {
        ast.setName(ModelFragmentFactory.eINSTANCE.createBuffer());
        Buffer _name_1 = ast.getName();
        _name_1.setName(condition.getBufferName());
        variables.put(ast.getName().getName(), ast.getName());
      }
      Collection<? extends ISlot> _slots = condition.getSlots();
      for (final ISlot slot : _slots) {
        EObject _slot = this.slot(slot, false);
        ast.getSlots().add(((ComplexSlot) _slot));
      }
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionCondition _toCondition(final ProxyCondition condition, final MutableMultimap<String, EObject> variables) {
    Proxy _xblockexpression = null;
    {
      final Proxy ast = ModelFragmentFactory.eINSTANCE.createProxy();
      ast.setName(condition.getDelegateClassName());
      Collection<? extends ISlot> _slots = condition.getSlots();
      for (final ISlot slot : _slots) {
        EObject _slot = this.slot(slot, true);
        ast.getSlots().add(((SimpleSlot) _slot));
      }
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionCondition _toCondition(final ScriptableCondition condition, final MutableMultimap<String, EObject> variables) {
    Script _xblockexpression = null;
    {
      final Script ast = ModelFragmentFactory.eINSTANCE.createScript();
      ast.setLanguage(condition.getFactory().getLanguageName());
      ast.setScript(condition.getScript());
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionAction _toAction(final AddAction action, final MutableMultimap<String, EObject> variables) {
    Add _xblockexpression = null;
    {
      final Add ast = ModelFragmentFactory.eINSTANCE.createAdd();
      final Function1<EObject, Boolean> _function = (EObject n) -> {
        return Boolean.valueOf((n instanceof Buffer));
      };
      EObject _findFirst = IterableExtensions.<EObject>findFirst(variables.get(action.getBufferName()), _function);
      ast.setName(((Buffer) _findFirst));
      final Object ref = action.getReferant();
      ast.setIsa(ModelFragmentFactory.eINSTANCE.createIsaBlock());
      Buffer _name = ast.getName();
      boolean _tripleEquals = (_name == null);
      if (_tripleEquals) {
        ast.setName(ModelFragmentFactory.eINSTANCE.createBuffer());
        Buffer _name_1 = ast.getName();
        _name_1.setName(action.getBufferName());
        variables.put(ast.getName().getName(), ast.getName());
      }
      if ((ref instanceof IChunkType)) {
        IsaBlock _isa = ast.getIsa();
        final Function1<EObject, Boolean> _function_1 = (EObject n) -> {
          return Boolean.valueOf((n instanceof ChunkType));
        };
        EObject _findFirst_1 = IterableExtensions.<EObject>findFirst(variables.get(((IChunkType)ref).getSymbolicChunkType().getName()), _function_1);
        _isa.setType(((ChunkType) _findFirst_1));
        ChunkType _type = ast.getIsa().getType();
        boolean _tripleEquals_1 = (_type == null);
        if (_tripleEquals_1) {
          IsaBlock _isa_1 = ast.getIsa();
          _isa_1.setType(ModelFragmentFactory.eINSTANCE.createChunkType());
          ChunkType _type_1 = ast.getIsa().getType();
          _type_1.setName(((IChunkType)ref).getSymbolicChunkType().getName());
          variables.put(ast.getIsa().getType().getName(), ast.getIsa().getType());
        }
      } else {
        if ((ref instanceof IChunk)) {
          IsaBlock _isa_2 = ast.getIsa();
          final Function1<EObject, Boolean> _function_2 = (EObject n) -> {
            return Boolean.valueOf((n instanceof ChunkDef));
          };
          EObject _findFirst_2 = IterableExtensions.<EObject>findFirst(variables.get(((IChunk)ref).getSymbolicChunk().getName()), _function_2);
          _isa_2.setChunk(((ChunkDef) _findFirst_2));
          ChunkDef _chunk = ast.getIsa().getChunk();
          boolean _tripleEquals_2 = (_chunk == null);
          if (_tripleEquals_2) {
            IsaBlock _isa_3 = ast.getIsa();
            _isa_3.setChunk(ModelFragmentFactory.eINSTANCE.createChunkDef());
            ChunkDef _chunk_1 = ast.getIsa().getChunk();
            _chunk_1.setName(((IChunk)ref).getSymbolicChunk().getName());
            variables.put(ast.getIsa().getChunk().getName(), ast.getIsa().getChunk());
          }
        } else {
          if ((ref != null)) {
            IsaBlock _isa_4 = ast.getIsa();
            _isa_4.setName(ref.toString());
          } else {
            ast.setIsa(null);
          }
        }
      }
      Collection<? extends ISlot> _slots = action.getSlots();
      for (final ISlot slot : _slots) {
        EObject _slot = this.slot(slot, false);
        ast.getSlots().add(((ConditionalSlot) _slot));
      }
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionAction _toAction(final ModifyAction action, final MutableMultimap<String, EObject> variables) {
    Modify _xblockexpression = null;
    {
      final Modify ast = ModelFragmentFactory.eINSTANCE.createModify();
      final Function1<EObject, Boolean> _function = (EObject n) -> {
        return Boolean.valueOf((n instanceof Buffer));
      };
      EObject _findFirst = IterableExtensions.<EObject>findFirst(variables.get(action.getBufferName()), _function);
      ast.setName(((Buffer) _findFirst));
      Buffer _name = ast.getName();
      boolean _tripleEquals = (_name == null);
      if (_tripleEquals) {
        ast.setName(ModelFragmentFactory.eINSTANCE.createBuffer());
        Buffer _name_1 = ast.getName();
        _name_1.setName(action.getBufferName());
        variables.put(ast.getName().getName(), ast.getName());
      }
      Collection<? extends ISlot> _slots = action.getSlots();
      for (final ISlot slot : _slots) {
        EObject _slot = this.slot(slot, true);
        ast.getSlots().add(((SimpleSlot) _slot));
      }
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionAction _toAction(final RemoveAction action, final MutableMultimap<String, EObject> variables) {
    Remove _xblockexpression = null;
    {
      final Remove ast = ModelFragmentFactory.eINSTANCE.createRemove();
      final Function1<EObject, Boolean> _function = (EObject n) -> {
        return Boolean.valueOf((n instanceof Buffer));
      };
      EObject _findFirst = IterableExtensions.<EObject>findFirst(variables.get(action.getBufferName()), _function);
      ast.setName(((Buffer) _findFirst));
      Buffer _name = ast.getName();
      boolean _tripleEquals = (_name == null);
      if (_tripleEquals) {
        ast.setName(ModelFragmentFactory.eINSTANCE.createBuffer());
        Buffer _name_1 = ast.getName();
        _name_1.setName(action.getBufferName());
        variables.put(ast.getName().getName(), ast.getName());
      }
      Collection<? extends ISlot> _slots = action.getSlots();
      for (final ISlot slot : _slots) {
        EObject _slot = this.slot(slot, true);
        ast.getSlots().add(((SimpleSlot) _slot));
      }
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionAction _toAction(final OutputAction action, final MutableMultimap<String, EObject> variables) {
    Output _xblockexpression = null;
    {
      final Output ast = ModelFragmentFactory.eINSTANCE.createOutput();
      ast.setString(action.getText());
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionAction _toAction(final ProxyAction action, final MutableMultimap<String, EObject> variables) {
    Proxy _xblockexpression = null;
    {
      final Proxy ast = ModelFragmentFactory.eINSTANCE.createProxy();
      ast.setName(action.getDelegateClassName());
      Collection<? extends ISlot> _slots = action.getSlots();
      for (final ISlot slot : _slots) {
        EObject _slot = this.slot(slot, true);
        ast.getSlots().add(((SimpleSlot) _slot));
      }
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionAction _toAction(final ScriptableAction action, final MutableMultimap<String, EObject> variables) {
    Script _xblockexpression = null;
    {
      final Script ast = ModelFragmentFactory.eINSTANCE.createScript();
      ast.setLanguage(action.getFactory().getLanguageName());
      ast.setScript(action.getScript());
      _xblockexpression = ast;
    }
    return _xblockexpression;
  }
  
  protected ProductionAction _toAction(final IAction action, final MutableMultimap<String, EObject> variables) {
    String _name = action.getClass().getName();
    String _plus = ("Unknown action type " + _name);
    throw new IllegalStateException(_plus);
  }
  
  protected ParametersBlock parameters(final IParameterized parameterized) {
    ParametersBlock _xblockexpression = null;
    {
      final ParametersBlock parameters = ModelFragmentFactory.eINSTANCE.createParametersBlock();
      Collection<String> _setableParameters = parameterized.getSetableParameters();
      for (final String key : _setableParameters) {
        {
          final Parameter parameter = ModelFragmentFactory.eINSTANCE.createParameter();
          parameter.setName(key);
          parameter.setValue(ModelFragmentFactory.eINSTANCE.createValue());
          Value _value = parameter.getValue();
          _value.setString(parameterized.getParameter(key));
          String _string = parameter.getValue().getString();
          boolean _tripleNotEquals = (_string != null);
          if (_tripleNotEquals) {
            parameters.getParameter().add(parameter);
          }
        }
      }
      _xblockexpression = parameters;
    }
    return _xblockexpression;
  }
  
  /**
   * recursively visit the chunktypes, rendering the
   * roots first, and
   */
  protected ArrayList<Element> chunkTypes(final IModel model, final MutableMultimap<String, EObject> variables) {
    try {
      ArrayList<Element> _xblockexpression = null;
      {
        final Collection<IChunkType> chunkTypes = model.getDeclarativeModule().getChunkTypes().get();
        final HashMap<String, ChunkType> visited = new HashMap<String, ChunkType>();
        final ArrayList<Element> rtn = new ArrayList<Element>();
        for (final IChunkType chunkType : chunkTypes) {
          {
            final ChunkType chunkTypeNode = this.chunkType(chunkType, chunkTypes, visited);
            rtn.add(chunkTypeNode);
            variables.put(chunkTypeNode.getName(), chunkTypeNode);
            final Collection<IChunk> allChunks = chunkType.getSymbolicChunkType().getChunks();
            int _size = allChunks.size();
            boolean _greaterThan = (_size > 0);
            if (_greaterThan) {
              Chunks chunksWrapper = null;
              final Function1<IChunk, Boolean> _function = (IChunk c) -> {
                return Boolean.valueOf(c.isAStrict(chunkType));
              };
              Iterable<IChunk> _filter = IterableExtensions.<IChunk>filter(allChunks, _function);
              for (final IChunk chunk : _filter) {
                {
                  if ((chunksWrapper == null)) {
                    chunksWrapper = ModelFragmentFactory.eINSTANCE.createChunks();
                    chunksWrapper.setType(chunkTypeNode);
                    rtn.add(chunksWrapper);
                  }
                  final ChunkDef def = this.chunk(chunk);
                  variables.put(def.getName(), def);
                  chunksWrapper.getChunks().add(def);
                }
              }
            }
          }
        }
        _xblockexpression = rtn;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected ChunkDef chunk(final IChunk chunk) {
    ChunkDef _xblockexpression = null;
    {
      final ChunkDef def = ModelFragmentFactory.eINSTANCE.createChunkDef();
      def.setName(chunk.getSymbolicChunk().getName());
      Collection<? extends ISlot> _slots = chunk.getSymbolicChunk().getSlots();
      for (final ISlot slot : _slots) {
        EObject _slot = this.slot(slot, true);
        def.getSlots().add(((SimpleSlot) _slot));
      }
      def.setParameters(this.parameters(chunk.getSubsymbolicChunk()));
      _xblockexpression = def;
    }
    return _xblockexpression;
  }
  
  protected ChunkType chunkType(final IChunkType toCreate, final Collection<IChunkType> all, final Map<String, ChunkType> visited) {
    ChunkType _xblockexpression = null;
    {
      final String name = toCreate.getSymbolicChunkType().getName();
      boolean _containsKey = visited.containsKey(name);
      if (_containsKey) {
        return visited.get(name);
      }
      final ChunkType chunkType = ModelFragmentFactory.eINSTANCE.createChunkType();
      chunkType.setName(name);
      IChunkType _parent = toCreate.getSymbolicChunkType().getParent();
      boolean _tripleNotEquals = (_parent != null);
      if (_tripleNotEquals) {
        chunkType.setSuperType(this.chunkType(toCreate.getSymbolicChunkType().getParent(), all, visited));
      }
      Collection<? extends ISlot> _slots = toCreate.getSymbolicChunkType().getSlots();
      for (final ISlot slot : _slots) {
        EObject _slot = this.slot(slot, true);
        chunkType.getSlots().add(((SimpleSlot) _slot));
      }
      chunkType.setParameters(this.parameters(toCreate.getSubsymbolicChunkType()));
      visited.put(name, chunkType);
      _xblockexpression = chunkType;
    }
    return _xblockexpression;
  }
  
  protected EObject slot(final ISlot slot, final boolean forceSimple) {
    EObject _xifexpression = null;
    if ((slot instanceof ILogicalSlot)) {
      BooleanOperation _xblockexpression = null;
      {
        BooleanOperation s = ((BooleanOperation) null);
        int _operator = ((ILogicalSlot)slot).getOperator();
        switch (_operator) {
          case ILogicalSlot.AND:
            s = ModelFragmentFactory.eINSTANCE.createAndSlot();
            break;
          case ILogicalSlot.OR:
            s = ModelFragmentFactory.eINSTANCE.createOrSlot();
            break;
          case ILogicalSlot.NOT:
            s = ModelFragmentFactory.eINSTANCE.createNotSlot();
            break;
        }
        Collection<? extends ISlot> _slots = ((ILogicalSlot)slot).getSlots();
        for (final ISlot child : _slots) {
          Object _slot = this.slot(child, forceSimple);
          s.getSlots().add(((ComplexSlot) _slot));
        }
        _xblockexpression = s;
      }
      _xifexpression = _xblockexpression;
    } else {
      EObject _xifexpression_1 = null;
      if ((slot instanceof IConditionalSlot)) {
        EObject _xifexpression_2 = null;
        if (forceSimple) {
          SimpleSlot _xblockexpression_1 = null;
          {
            final SimpleSlot s = ModelFragmentFactory.eINSTANCE.createSimpleSlot();
            s.setName(((IConditionalSlot)slot).getName());
            s.setValue(this.toSlotValue(((IConditionalSlot)slot).getValue()));
            _xblockexpression_1 = s;
          }
          _xifexpression_2 = _xblockexpression_1;
        } else {
          ConditionalSlot _xblockexpression_2 = null;
          {
            final ConditionalSlot s = ModelFragmentFactory.eINSTANCE.createConditionalSlot();
            s.setName(((IConditionalSlot)slot).getName());
            s.setValue(this.toSlotValue(((IConditionalSlot)slot).getValue()));
            int _condition = ((IConditionalSlot)slot).getCondition();
            switch (_condition) {
              case IConditionalSlot.EQUALS:
                s.setCondition(Condition.EQUALS);
                break;
              case IConditionalSlot.LESS_THAN:
                s.setCondition(Condition.LT);
                break;
              case IConditionalSlot.LESS_THAN_EQUALS:
                s.setCondition(Condition.LTE);
                break;
              case IConditionalSlot.GREATER_THAN:
                s.setCondition(Condition.GT);
                break;
              case IConditionalSlot.GREATER_THAN_EQUALS:
                s.setCondition(Condition.GTE);
                break;
              case IConditionalSlot.NOT_EQUALS:
                s.setCondition(Condition.NOT);
                break;
            }
            _xblockexpression_2 = s;
          }
          _xifexpression_2 = _xblockexpression_2;
        }
        _xifexpression_1 = _xifexpression_2;
      } else {
        SimpleSlot _xblockexpression_3 = null;
        {
          final SimpleSlot s = ModelFragmentFactory.eINSTANCE.createSimpleSlot();
          s.setName(slot.getName());
          s.setValue(this.toSlotValue(slot.getValue()));
          _xblockexpression_3 = s;
        }
        _xifexpression_1 = _xblockexpression_3;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  protected Value toSlotValue(final Object object) {
    Value _xblockexpression = null;
    {
      Value rtn = ModelFragmentFactory.eINSTANCE.createValue();
      if ((object == null)) {
        rtn.setName("null");
      } else {
        if ((object instanceof String)) {
          boolean _startsWith = ((String)object).startsWith("=");
          if (_startsWith) {
            rtn.setName(((String)object));
          } else {
            rtn.setString(((String)object));
          }
        } else {
          if ((object instanceof Boolean)) {
            rtn.setBoolean(((Boolean)object));
          } else {
            if ((object instanceof Number)) {
              rtn.setNumber(Double.valueOf(((Number)object).doubleValue()));
            } else {
              rtn.setName(object.toString());
            }
          }
        }
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected ProductionCondition toCondition(final ICondition condition, final MutableMultimap<String, EObject> variables) {
    if (condition instanceof ChunkCondition) {
      return _toCondition((ChunkCondition)condition, variables);
    } else if (condition instanceof ChunkTypeCondition) {
      return _toCondition((ChunkTypeCondition)condition, variables);
    } else if (condition instanceof QueryCondition) {
      return _toCondition((QueryCondition)condition, variables);
    } else if (condition instanceof VariableCondition) {
      return _toCondition((VariableCondition)condition, variables);
    } else if (condition instanceof ProxyCondition) {
      return _toCondition((ProxyCondition)condition, variables);
    } else if (condition instanceof ScriptableCondition) {
      return _toCondition((ScriptableCondition)condition, variables);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(condition, variables).toString());
    }
  }
  
  protected ProductionAction toAction(final IAction action, final MutableMultimap<String, EObject> variables) {
    if (action instanceof ProxyAction) {
      return _toAction((ProxyAction)action, variables);
    } else if (action instanceof RemoveAction) {
      return _toAction((RemoveAction)action, variables);
    } else if (action instanceof AddAction) {
      return _toAction((AddAction)action, variables);
    } else if (action instanceof ModifyAction) {
      return _toAction((ModifyAction)action, variables);
    } else if (action instanceof OutputAction) {
      return _toAction((OutputAction)action, variables);
    } else if (action instanceof ScriptableAction) {
      return _toAction((ScriptableAction)action, variables);
    } else if (action != null) {
      return _toAction(action, variables);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(action, variables).toString());
    }
  }
}
