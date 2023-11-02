package org.jactr.io2.jactr.builder;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.jactr.core.buffer.IActivationBuffer;
import org.jactr.core.chunk.IChunk;
import org.jactr.core.chunktype.IChunkType;
import org.jactr.core.chunktype.ISubsymbolicChunkType;
import org.jactr.core.extensions.IExtension;
import org.jactr.core.model.IModel;
import org.jactr.core.model.basic.BasicModel;
import org.jactr.core.module.IModule;
import org.jactr.core.production.IProduction;
import org.jactr.core.production.action.AddAction;
import org.jactr.core.production.action.DefaultAction;
import org.jactr.core.production.action.IAction;
import org.jactr.core.production.action.ModifyAction;
import org.jactr.core.production.action.OutputAction;
import org.jactr.core.production.action.ProxyAction;
import org.jactr.core.production.action.RemoveAction;
import org.jactr.core.production.condition.AbstractSlotCondition;
import org.jactr.core.production.condition.ChunkCondition;
import org.jactr.core.production.condition.ChunkTypeCondition;
import org.jactr.core.production.condition.ICondition;
import org.jactr.core.production.condition.ProxyCondition;
import org.jactr.core.production.condition.QueryCondition;
import org.jactr.core.production.condition.VariableCondition;
import org.jactr.core.slot.BasicSlot;
import org.jactr.core.slot.DefaultConditionalSlot;
import org.jactr.core.slot.DefaultLogicalSlot;
import org.jactr.core.slot.DefaultMutableSlot;
import org.jactr.core.slot.IConditionalSlot;
import org.jactr.core.slot.ILogicalSlot;
import org.jactr.core.slot.ISlot;
import org.jactr.core.utils.parameter.IParameterized;
import org.jactr.fluent.FluentChunk;
import org.jactr.fluent.FluentChunkType;
import org.jactr.fluent.FluentProduction;
import org.jactr.io2.jactr.modelFragment.Add;
import org.jactr.io2.jactr.modelFragment.AndSlot;
import org.jactr.io2.jactr.modelFragment.Buffer;
import org.jactr.io2.jactr.modelFragment.ChunkDef;
import org.jactr.io2.jactr.modelFragment.ChunkType;
import org.jactr.io2.jactr.modelFragment.Chunks;
import org.jactr.io2.jactr.modelFragment.ComplexSlot;
import org.jactr.io2.jactr.modelFragment.Condition;
import org.jactr.io2.jactr.modelFragment.ConditionalSlot;
import org.jactr.io2.jactr.modelFragment.IsaBlock;
import org.jactr.io2.jactr.modelFragment.Match;
import org.jactr.io2.jactr.modelFragment.ModelExtension;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.modelFragment.ModelModule;
import org.jactr.io2.jactr.modelFragment.Modify;
import org.jactr.io2.jactr.modelFragment.NotSlot;
import org.jactr.io2.jactr.modelFragment.OrSlot;
import org.jactr.io2.jactr.modelFragment.Output;
import org.jactr.io2.jactr.modelFragment.Parameter;
import org.jactr.io2.jactr.modelFragment.Parameters;
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
import org.jactr.io2.jactr.validation.ModelFragmentUtil;
import org.jactr.scripting.IScriptableFactory;
import org.jactr.scripting.ScriptingManager;
import org.jactr.scripting.action.IActionScript;
import org.jactr.scripting.action.ScriptableAction;
import org.jactr.scripting.condition.IConditionScript;
import org.jactr.scripting.condition.ScriptableCondition;

@SuppressWarnings("all")
public class Builder {
  @Inject
  @Extension
  private ModelFragmentUtil _modelFragmentUtil;

  private BuilderContext _context = new BuilderContext();

  protected void assignParameters(final IParameterized parameterized, final ParametersBlock parameters) {
    if ((parameters != null)) {
      EList<Parameter> _parameter = parameters.getParameter();
      for (final Parameter parameter : _parameter) {
        {
          String strValue = "";
          boolean _isBoolean = this._modelFragmentUtil.isBoolean(parameter.getValue());
          if (_isBoolean) {
            strValue = parameter.getValue().getBoolean().toString();
          } else {
            boolean _isString = this._modelFragmentUtil.isString(parameter.getValue());
            if (_isString) {
              strValue = parameter.getValue().getString().toString();
            } else {
              boolean _isNumber = this._modelFragmentUtil.isNumber(parameter.getValue());
              if (_isNumber) {
                strValue = parameter.getValue().getNumber().toString();
              } else {
                strValue = parameter.getValue().getName().toString();
              }
            }
          }
          parameterized.setParameter(parameter.getName(), strValue);
        }
      }
    }
  }

  public IModel build(final ModelFragment fragment) {
    try {
      IModel _xblockexpression = null;
      {
        this.stage1Modules(fragment);
        this.assignParameters(this._context.model, fragment.getPackage().getParameters());
        final Map<String, EObject> chunkTypeSymbols = this._modelFragmentUtil.resolveSymbolTable(fragment.eResource(), this._modelFragmentUtil.chunkTypeSymbolTable(fragment));
        final Map<String, EObject> chunkSymbols = this._modelFragmentUtil.resolveSymbolTable(fragment.eResource(), this._modelFragmentUtil.chunkSymbolTable(fragment));
        final Map<String, EObject> productionSymbols = this._modelFragmentUtil.resolveSymbolTable(fragment.eResource(), this._modelFragmentUtil.productionSymbolTable(fragment));
        final Map<String, EObject> bufferSymbols = this._modelFragmentUtil.resolveSymbolTable(fragment.eResource(), this._modelFragmentUtil.bufferSymbolTable(fragment));
        final TreeSet<String> chunksToIgnore = this.findBufferContents(bufferSymbols);
        this.stage2ChunkTypes(fragment, chunkTypeSymbols);
        this.stage3BootstrapChunk(fragment, chunkTypeSymbols, chunkSymbols);
        this.stage4ChunkTypeEncoding(fragment, chunkTypeSymbols, chunkSymbols);
        this.stage5ResolveChunks(fragment, chunkSymbols, chunksToIgnore);
        this.stage6DeclarativeParameterization(fragment, chunkTypeSymbols, chunkSymbols);
        this.stage7Buffers(fragment, bufferSymbols);
        this.stage8ProductionEncoding(fragment, productionSymbols);
        this.stage9Parameters(fragment, chunkTypeSymbols, chunkSymbols, productionSymbols, bufferSymbols);
        this.stage10Extensions(fragment);
        this._context.model.initialize();
        _xblockexpression = this._context.model;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  protected TreeSet<String> findBufferContents(final Map<String, EObject> bufferSymbols) {
    TreeSet<String> _xblockexpression = null;
    {
      final TreeSet<String> rtn = new TreeSet<String>();
      final Function1<EObject, Buffer> _function = (EObject o) -> {
        return ((Buffer) o);
      };
      final Function1<Buffer, Boolean> _function_1 = (Buffer b) -> {
        int _size = b.getSource().size();
        return Boolean.valueOf((_size > 0));
      };
      final Consumer<Buffer> _function_2 = (Buffer b) -> {
        final Consumer<ChunkDef> _function_3 = (ChunkDef s) -> {
          rtn.add(s.getName());
        };
        b.getSource().forEach(_function_3);
      };
      IterableExtensions.<Buffer>filter(IterableExtensions.<EObject, Buffer>map(bufferSymbols.values(), _function), _function_1).forEach(_function_2);
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }

  protected void stage7Buffers(final ModelFragment fragment, final Map<String, EObject> bufferSymbols) {
    final Function1<EObject, Buffer> _function = (EObject o) -> {
      return ((Buffer) o);
    };
    final Function1<Buffer, Boolean> _function_1 = (Buffer b) -> {
      int _size = b.getSource().size();
      return Boolean.valueOf((_size > 0));
    };
    final Consumer<Buffer> _function_2 = (Buffer b) -> {
      final IActivationBuffer buffer = this._context.model.getActivationBuffer(b.getName());
      final Consumer<ChunkDef> _function_3 = (ChunkDef source) -> {
        final IChunk src = this._context.getChunk(source.getName());
        if ((src == null)) {
          String _name = source.getName();
          String _plus = ("Source chunk (" + _name);
          String _plus_1 = (_plus + ") for ");
          String _name_1 = b.getName();
          String _plus_2 = (_plus_1 + _name_1);
          String _plus_3 = (_plus_2 + " was not found");
          throw new RuntimeException(_plus_3);
        }
        buffer.addSourceChunk(src);
      };
      b.getSource().forEach(_function_3);
    };
    IterableExtensions.<Buffer>filter(IterableExtensions.<EObject, Buffer>map(bufferSymbols.values(), _function), _function_1).forEach(_function_2);
    final Function1<EObject, Buffer> _function_3 = (EObject o) -> {
      return ((Buffer) o);
    };
    final Consumer<Buffer> _function_4 = (Buffer b) -> {
      final IActivationBuffer buffer = this._context.model.getActivationBuffer(b.getName());
      if ((buffer instanceof IParameterized)) {
        this.assignParameters(((IParameterized)buffer), b.getParameters());
      }
    };
    IterableExtensions.<EObject, Buffer>map(bufferSymbols.values(), _function_3).forEach(_function_4);
  }

  protected void stage10Extensions(final ModelFragment fragment) {
    final Consumer<ModelExtension> _function = (ModelExtension ext) -> {
      this.instantiateExtension(ext);
    };
    fragment.getPackage().getExtensions().forEach(_function);
  }

  protected void instantiateExtension(final ModelExtension ext) {
    final String className = ext.getExtensionClass();
    try {
      Class<?> _loadClass = IModel.class.getClassLoader().loadClass(className);
      Class<? extends IExtension> clazz = ((Class<? extends IExtension>) _loadClass);
      IExtension instance = clazz.newInstance();
      if ((instance instanceof IParameterized)) {
        this.assignParameters(instance, ext.getParameters());
      }
      this._context.model.install(instance);
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        final Exception e = (Exception)_t;
        throw new RuntimeException(("Failed to instantiate " + className), e);
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }

  /**
   * instantiate the modules and install
   */
  protected void stage1Modules(final ModelFragment fragment) {
    try {
      String _name = fragment.getPackage().getName();
      BasicModel _basicModel = new BasicModel(_name);
      this._context.model = _basicModel;
      EList<ModelModule> _modules = fragment.getPackage().getModules();
      for (final ModelModule module : _modules) {
        {
          Object _newInstance = IModel.class.getClassLoader().loadClass(module.getModuleClass()).newInstance();
          IModule actualModule = ((IModule) _newInstance);
          if ((actualModule instanceof IParameterized)) {
            this.assignParameters(((IParameterized)actualModule), module.getParameters());
          }
          this._context.model.install(actualModule);
        }
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  protected void stage2ChunkTypes(final ModelFragment fragment, final Map<String, EObject> chunkTypeSymbols) {
    final Function1<EObject, ChunkType> _function = (EObject n) -> {
      return ((ChunkType) n);
    };
    final Consumer<ChunkType> _function_1 = (ChunkType chunkType) -> {
      this.stage2ProvisionChunkType(chunkType, chunkTypeSymbols);
    };
    IterableExtensions.<EObject, ChunkType>map(chunkTypeSymbols.values(), _function).forEach(_function_1);
  }

  /**
   * provisionally create the chunktype, but leave its slot values unset, stash
   * in context for later processing (stage4)
   */
  protected IChunkType stage2ProvisionChunkType(final ChunkType chunkType, final Map<String, EObject> chunkTypeSymbols) {
    IChunkType ct = this._context.getChunkType(chunkType.getName());
    if ((ct != null)) {
      return ct;
    }
    final ChunkType chunkTypeToBuild = chunkType;
    FluentChunkType fct = FluentChunkType.from(this._context.model).named(chunkTypeToBuild.getName());
    ChunkType _superType = chunkTypeToBuild.getSuperType();
    boolean _tripleNotEquals = (_superType != null);
    if (_tripleNotEquals) {
      IChunkType parent = this._context.getChunkType(chunkTypeToBuild.getSuperType().getName());
      if ((parent == null)) {
        parent = this.stage2ProvisionChunkType(chunkTypeToBuild.getSuperType(), chunkTypeSymbols);
      }
      fct = fct.childOf(parent);
    }
    final FluentChunkType tmpfct = fct;
    final BiConsumer<String, Object> _function = (String s, Object v) -> {
      tmpfct.slot(s, v);
    };
    this.resolveSlots(
      EcoreUtil2.<SimpleSlot>getAllContentsOfType(chunkType, SimpleSlot.class), _function);
    ct = fct.build();
    this._context.add(ct);
    return ct;
  }

  /**
   * encode chunks of type chunk. this is done as a bootstrap since many default slot
   * values are these chunks
   */
  protected Map<String, EObject> stage3BootstrapChunk(final ModelFragment fragment, final Map<String, EObject> chunkTypeSymbols, final Map<String, EObject> chunkSymbols) {
    Map<String, EObject> _xblockexpression = null;
    {
      EObject _get = chunkTypeSymbols.get("chunk");
      final ChunkType chunkType = ((ChunkType) _get);
      if ((chunkType != null)) {
        this.stage4ResolveChunkType(chunkType, chunkSymbols);
      }
      _xblockexpression = chunkSymbols;
    }
    return _xblockexpression;
  }

  /**
   * iteratively try fully resolving chunktypes, for each fully resolved chunktype, provisionally create
   * the chunks
   */
  protected void stage4ChunkTypeEncoding(final ModelFragment fragment, final Map<String, EObject> chunkTypeSymbols, final Map<String, EObject> chunkSymbols) {
    final Function1<EObject, ChunkType> _function = (EObject node) -> {
      return ((ChunkType) node);
    };
    final Iterable<ChunkType> allChunkTypes = IterableExtensions.<EObject, ChunkType>map(chunkTypeSymbols.values(), _function);
    boolean noCyclesDetected = true;
    boolean lastCycleNoChanges = false;
    while ((noCyclesDetected && (IterableExtensions.size(allChunkTypes) > 0))) {
      {
        final int sizeAtStart = IterableExtensions.size(allChunkTypes);
        final Iterator<ChunkType> itr = allChunkTypes.iterator();
        while (itr.hasNext()) {
          boolean _stage4ResolveChunkType = this.stage4ResolveChunkType(itr.next(), chunkSymbols);
          if (_stage4ResolveChunkType) {
            itr.remove();
          }
        }
        final int sizeAtEnd = IterableExtensions.size(allChunkTypes);
        if ((sizeAtEnd == sizeAtStart)) {
          if (lastCycleNoChanges) {
            noCyclesDetected = false;
          }
          lastCycleNoChanges = true;
        }
      }
    }
    if ((!noCyclesDetected)) {
      final Function1<ChunkType, String> _function_1 = (ChunkType n) -> {
        return n.getName();
      };
      final Iterable<String> dependentTypes = IterableExtensions.<ChunkType, String>map(allChunkTypes, _function_1);
      throw new RuntimeException(("Suspected cyclic build dependency found in types " + dependentTypes));
    }
  }

  /**
   * return the resolved value or the value object itself for unresolved
   */
  protected Object resolveSlotValue(final Value value) {
    boolean _isString = this._modelFragmentUtil.isString(value);
    if (_isString) {
      return value.getString();
    }
    boolean _isNumber = this._modelFragmentUtil.isNumber(value);
    if (_isNumber) {
      return value.getNumber();
    }
    boolean _isNull = this._modelFragmentUtil.isNull(value);
    if (_isNull) {
      return null;
    }
    boolean _isBoolean = this._modelFragmentUtil.isBoolean(value);
    if (_isBoolean) {
      return value.getBoolean();
    }
    boolean _isVariable = this._modelFragmentUtil.isVariable(value);
    if (_isVariable) {
      return value.getName();
    }
    final Object resolvedByContext = this._context.resolve(value.getName());
    if ((resolvedByContext != null)) {
      return resolvedByContext;
    }
    return value;
  }

  /**
   * return true if all slots are resolved, for each resolved, call the consumer
   */
  protected boolean resolveSlots(final List<SimpleSlot> slots, final BiConsumer<String, Object> slotAcceptor) {
    final Iterator<SimpleSlot> itr = slots.iterator();
    while (itr.hasNext()) {
      {
        final SimpleSlot ss = itr.next();
        final Object resolvedValue = this.resolveSlotValue(ss.getValue());
        Value _value = ss.getValue();
        boolean _tripleNotEquals = (resolvedValue != _value);
        if (_tripleNotEquals) {
          itr.remove();
          slotAcceptor.accept(ss.getName(), resolvedValue);
        }
      }
    }
    int _size = slots.size();
    return (_size == 0);
  }

  /**
   * try to fully resolve the slot values of this chunktype. if successful,
   * provisionally encode the chunks
   */
  protected boolean stage4ResolveChunkType(final ChunkType chunkType, final Map<String, EObject> chunkSymbols) {
    final IChunkType ct = this._context.getChunkType(chunkType.getName());
    boolean _isEncoded = ct.isEncoded();
    if (_isEncoded) {
      return true;
    }
    final FluentChunkType fct = FluentChunkType.from(ct);
    ChunkType slotContainer = chunkType;
    boolean slotsResolved = true;
    final TreeSet<String> resolvedSlots = new TreeSet<String>();
    while ((slotContainer != null)) {
      {
        slotsResolved = (slotsResolved && this.resolveSlots(
          IterableExtensions.<SimpleSlot>toList(IterableExtensions.<SimpleSlot>filter(EcoreUtil2.<SimpleSlot>getAllContentsOfType(slotContainer, SimpleSlot.class), ((Function1<SimpleSlot, Boolean>) (SimpleSlot s) -> {
            boolean _contains = resolvedSlots.contains(s.getName());
            return Boolean.valueOf((!_contains));
          }))), 
          ((BiConsumer<String, Object>) (String s, Object v) -> {
            fct.slot(s, v);
            resolvedSlots.add(s);
          })));
        slotContainer = slotContainer.getSuperType();
      }
    }
    if (slotsResolved) {
      fct.build();
      this._context.encodeChunkType(chunkType.getName());
      this.stage4ProvisionChunks(chunkType, chunkSymbols);
      return true;
    }
    return false;
  }

  /**
   * provisionally create the chunks of this type
   */
  protected void stage4ProvisionChunks(final ChunkType chunkType, final Map<String, EObject> chunkSymbols) {
    final Function1<EObject, ChunkDef> _function = (EObject ed) -> {
      return ((ChunkDef) ed);
    };
    final Iterable<ChunkDef> allChunks = IterableExtensions.<EObject, ChunkDef>map(chunkSymbols.values(), _function);
    final Consumer<ChunkDef> _function_1 = (ChunkDef chunk) -> {
      EObject _eContainer = chunk.eContainer();
      String _name = ((Chunks) _eContainer).getType().getName();
      String _name_1 = chunkType.getName();
      boolean _equals = Objects.equal(_name, _name_1);
      if (_equals) {
        final IChunk provisionalChunk = FluentChunk.from(this._context.getChunkType(chunkType.getName())).named(chunk.getName()).build();
        this._context.add(provisionalChunk);
      }
    };
    allChunks.forEach(_function_1);
  }

  protected void stage5ResolveChunkSlots(final ChunkDef chunk, final Set<String> chunksToNotEncode) {
    IChunk c = this._context.getChunk(chunk.getName());
    boolean _isEncoded = c.isEncoded();
    if (_isEncoded) {
      return;
    }
    final FluentChunk fc = FluentChunk.from(c);
    final ArrayList<String> resolved = Lists.<String>newArrayList();
    final BiConsumer<String, Object> _function = (String s, Object v) -> {
      fc.slot(s, v);
      resolved.add(s);
    };
    boolean slotsResolved = this.resolveSlots(
      EcoreUtil2.<SimpleSlot>getAllContentsOfType(chunk, SimpleSlot.class), _function);
    if (slotsResolved) {
      c = fc.build();
      boolean _contains = chunksToNotEncode.contains(chunk.getName());
      boolean _not = (!_contains);
      if (_not) {
        this._context.encodeChunk(chunk.getName());
      }
    } else {
      String _name = chunk.getName();
      String _plus = ("Could not fully resolve chunk " + _name);
      String _plus_1 = (_plus + " at build time? Resolved :");
      String _plus_2 = (_plus_1 + resolved);
      throw new RuntimeException(_plus_2);
    }
  }

  protected void stage5ResolveChunks(final ModelFragment fragment, final Map<String, EObject> chunkSymbols, final Set<String> chunksToNotEncode) {
    final Function1<EObject, ChunkDef> _function = (EObject ed) -> {
      return ((ChunkDef) ed);
    };
    final Iterable<ChunkDef> allChunks = IterableExtensions.<EObject, ChunkDef>map(chunkSymbols.values(), _function);
    final Consumer<ChunkDef> _function_1 = (ChunkDef chunk) -> {
      this.stage5ResolveChunkSlots(chunk, chunksToNotEncode);
    };
    allChunks.forEach(_function_1);
  }

  protected void stage6DeclarativeParameterization(final ModelFragment fragment, final Map<String, EObject> chunkTypeSymbols, final Map<String, EObject> chunkSymbols) {
    final Function1<EObject, ChunkType> _function = (EObject node) -> {
      return ((ChunkType) node);
    };
    Iterable<ChunkType> _map = IterableExtensions.<EObject, ChunkType>map(chunkTypeSymbols.values(), _function);
    for (final ChunkType actualChunkType : _map) {
      {
        final IChunkType chunkType = this._context.getChunkType(actualChunkType.getName());
        if ((chunkType == null)) {
          String _name = actualChunkType.getName();
          String _plus = ("Missing chunktype for " + _name);
          String _plus_1 = (_plus + ". Potential override incompatibility.");
          throw new RuntimeException(_plus_1);
        }
        this.assignParameters(chunkType.getSubsymbolicChunkType(), actualChunkType.getParameters());
      }
    }
    final Function1<EObject, ChunkDef> _function_1 = (EObject node) -> {
      return ((ChunkDef) node);
    };
    Iterable<ChunkDef> _map_1 = IterableExtensions.<EObject, ChunkDef>map(chunkSymbols.values(), _function_1);
    for (final ChunkDef actualChunk : _map_1) {
      {
        final IChunk chunk = this._context.getChunk(actualChunk.getName());
        if ((chunk == null)) {
          String _name = actualChunk.getName();
          String _plus = ("Missing chunk for " + _name);
          String _plus_1 = (_plus + ". Potentially merged. Known :");
          Set<String> _knownChunks = this._context.knownChunks();
          String _plus_2 = (_plus_1 + _knownChunks);
          throw new RuntimeException(_plus_2);
        }
        this.assignParameters(chunk.getSubsymbolicChunk(), actualChunk.getParameters());
      }
    }
  }

  protected void stage8ProductionEncoding(final ModelFragment fragment, final Map<String, EObject> productionSymbols) {
    final Function<EObject, Production> _function = (EObject ed) -> {
      return ((Production) ed);
    };
    final Stream<Production> allProductions = productionSymbols.values().stream().<Production>map(_function);
    final Consumer<Production> _function_1 = (Production p) -> {
      this.stage8EncodeProduction(p, productionSymbols);
    };
    allProductions.forEach(_function_1);
  }

  protected void stage8EncodeProduction(final Production production, final Map<String, EObject> productionSymbols) {
    final Production productionToBuild = production;
    final FluentProduction fp = FluentProduction.from(this._context.model).named(productionToBuild.getName());
    EList<ProductionCondition> _conditions = productionToBuild.getConditions();
    for (final ProductionCondition condition : _conditions) {
      final Consumer<ICondition> _function = (ICondition c) -> {
        if ((c != null)) {
          fp.condition(c);
        } else {
          throw new IllegalStateException(("got null condition for " + condition));
        }
      };
      this.buildCondition(condition).forEach(_function);
    }
    EList<ProductionAction> _actions = productionToBuild.getActions();
    for (final ProductionAction action : _actions) {
      {
        DefaultAction _buildAction = this.buildAction(action);
        final IAction a = ((IAction) _buildAction);
        if ((a != null)) {
          fp.action(a);
        } else {
          throw new IllegalStateException(("got null action for " + action));
        }
      }
    }
    final IProduction p = fp.encode();
    this.assignParameters(p.getSubsymbolicProduction(), productionToBuild.getParameters());
  }

  protected Comparable<?> resolveIsa(final IsaBlock isa) {
    try {
      String _xtrycatchfinallyexpression = null;
      try {
        String _xblockexpression = null;
        {
          if ((isa == null)) {
            return null;
          }
          ChunkType _type = isa.getType();
          boolean _tripleNotEquals = (_type != null);
          if (_tripleNotEquals) {
            return this._context.getChunkType(isa.getType().getName());
          }
          ChunkDef _chunk = isa.getChunk();
          boolean _tripleNotEquals_1 = (_chunk != null);
          if (_tripleNotEquals_1) {
            return this._context.getChunk(isa.getChunk().getName());
          }
          _xblockexpression = isa.getName();
        }
        _xtrycatchfinallyexpression = _xblockexpression;
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          ChunkType _type = isa.getType();
          String _plus = ("NULL !!! " + _type);
          String _plus_1 = (_plus + " ");
          ChunkDef _chunk = isa.getChunk();
          String _plus_2 = (_plus_1 + _chunk);
          String _plus_3 = (_plus_2 + " ");
          String _name = isa.getName();
          String _plus_4 = (_plus_3 + _name);
          System.err.println(_plus_4);
          throw e;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      return _xtrycatchfinallyexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  protected Set<ICondition> _buildCondition(final Match match) {
    Set<ICondition> _xblockexpression = null;
    {
      AbstractSlotCondition pattern = ((AbstractSlotCondition) null);
      final Comparable<?> isa = this.resolveIsa(match.getIsa());
      if (((this._context.model.getActivationBuffer(match.getName().getName()) == null) || (isa == null))) {
        String _name = match.getName().getName();
        String _plus = ("Could not find buffer named " + _name);
        String _plus_1 = (_plus + 
          ", was its module installed and import defined?");
        throw new IllegalStateException(_plus_1);
      }
      if ((isa instanceof IChunkType)) {
        String _name_1 = match.getName().getName();
        List<ISlot> _emptyList = Collections.<ISlot>emptyList();
        ChunkTypeCondition _chunkTypeCondition = new ChunkTypeCondition(_name_1, ((IChunkType)isa), _emptyList);
        pattern = _chunkTypeCondition;
      } else {
        if ((isa instanceof IChunk)) {
          String _name_2 = match.getName().getName();
          List<ISlot> _emptyList_1 = Collections.<ISlot>emptyList();
          ChunkCondition _chunkCondition = new ChunkCondition(_name_2, ((IChunk)isa), _emptyList_1);
          pattern = _chunkCondition;
        } else {
          if ((isa instanceof String)) {
            String _name_3 = match.getName().getName();
            VariableCondition _variableCondition = new VariableCondition(_name_3, ((String)isa));
            pattern = _variableCondition;
          }
        }
      }
      EList<ComplexSlot> _slots = match.getSlots();
      for (final ComplexSlot slot : _slots) {
        {
          BasicSlot _buildSlot = this.buildSlot(slot);
          final ISlot actualSlot = ((ISlot) _buildSlot);
          pattern.addSlot(actualSlot);
        }
      }
      _xblockexpression = Collections.<ICondition>singleton(((ICondition) pattern));
    }
    return _xblockexpression;
  }

  protected Set<ICondition> _buildCondition(final Query query) {
    Set<ICondition> _xblockexpression = null;
    {
      String _name = query.getName().getName();
      final QueryCondition action = new QueryCondition(_name);
      EList<ComplexSlot> _slots = query.getSlots();
      for (final ComplexSlot slot : _slots) {
        BasicSlot _buildSlot = this.buildSlot(slot);
        action.addSlot(((ISlot) _buildSlot));
      }
      _xblockexpression = Collections.<ICondition>singleton(((ICondition) action));
    }
    return _xblockexpression;
  }

  protected Set<ICondition> _buildCondition(final Proxy proxy) {
    try {
      Set<ICondition> _xblockexpression = null;
      {
        String _name = proxy.getName();
        final ProxyCondition pc = new ProxyCondition(_name);
        EList<SimpleSlot> _slots = proxy.getSlots();
        for (final SimpleSlot slot : _slots) {
          BasicSlot _buildSlot = this.buildSlot(slot);
          pc.addSlot(((ISlot) _buildSlot));
        }
        _xblockexpression = Collections.<ICondition>singleton(((ICondition) pc));
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  protected Set<ICondition> _buildCondition(final Script script) {
    try {
      Set<ICondition> _xblockexpression = null;
      {
        final IScriptableFactory sm = ScriptingManager.getFactory(script.getLanguage());
        if ((sm == null)) {
          String _language = script.getLanguage();
          String _plus = ("Could not find script support for " + _language);
          throw new IllegalArgumentException(_plus);
        }
        IConditionScript _createConditionScript = sm.createConditionScript(script.getScript());
        ScriptableCondition _scriptableCondition = new ScriptableCondition(_createConditionScript);
        _xblockexpression = Collections.<ICondition>singleton(((ICondition) _scriptableCondition));
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  protected DefaultAction _buildAction(final Proxy proxy) {
    try {
      ProxyAction _xblockexpression = null;
      {
        String _name = proxy.getName();
        final ProxyAction pc = new ProxyAction(_name);
        EList<SimpleSlot> _slots = proxy.getSlots();
        for (final SimpleSlot slot : _slots) {
          BasicSlot _buildSlot = this.buildSlot(slot);
          pc.addSlot(((ISlot) _buildSlot));
        }
        _xblockexpression = pc;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  protected DefaultAction _buildAction(final Script script) {
    try {
      ScriptableAction _xblockexpression = null;
      {
        final IScriptableFactory sm = ScriptingManager.getFactory(script.getLanguage());
        if ((sm == null)) {
          String _language = script.getLanguage();
          String _plus = ("Could not find script support for " + _language);
          throw new IllegalArgumentException(_plus);
        }
        IActionScript _createActionScript = sm.createActionScript(script.getScript());
        _xblockexpression = new ScriptableAction(_createActionScript);
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }

  protected DefaultAction _buildAction(final Add add) {
    AddAction _xblockexpression = null;
    {
      String _name = add.getName().getName();
      Comparable<?> _resolveIsa = this.resolveIsa(add.getIsa());
      final AddAction action = new AddAction(_name, _resolveIsa);
      EList<ConditionalSlot> _slots = add.getSlots();
      for (final ConditionalSlot slot : _slots) {
        BasicSlot _buildSlot = this.buildSlot(slot);
        action.addSlot(((ISlot) _buildSlot));
      }
      _xblockexpression = action;
    }
    return _xblockexpression;
  }

  protected DefaultAction _buildAction(final Modify modify) {
    ModifyAction _xblockexpression = null;
    {
      String _name = modify.getName().getName();
      final ModifyAction action = new ModifyAction(_name);
      EList<SimpleSlot> _slots = modify.getSlots();
      for (final SimpleSlot slot : _slots) {
        BasicSlot _buildSlot = this.buildSlot(slot);
        action.addSlot(((ISlot) _buildSlot));
      }
      _xblockexpression = action;
    }
    return _xblockexpression;
  }

  protected DefaultAction _buildAction(final Remove remove) {
    RemoveAction _xblockexpression = null;
    {
      String _name = remove.getName().getName();
      final RemoveAction action = new RemoveAction(_name);
      EList<SimpleSlot> _slots = remove.getSlots();
      for (final SimpleSlot slot : _slots) {
        BasicSlot _buildSlot = this.buildSlot(slot);
        action.addSlot(((ISlot) _buildSlot));
      }
      _xblockexpression = action;
    }
    return _xblockexpression;
  }

  protected DefaultAction _buildAction(final Output output) {
    String _string = output.getString();
    return new OutputAction(_string);
  }

  protected BasicSlot _buildSlot(final OrSlot orSlot) {
    DefaultLogicalSlot _xblockexpression = null;
    {
      List<ISlot> _emptyList = Collections.<ISlot>emptyList();
      final DefaultLogicalSlot logic = new DefaultLogicalSlot(ILogicalSlot.OR, _emptyList);
      EList<ComplexSlot> _slots = orSlot.getSlots();
      for (final ComplexSlot slot : _slots) {
        Object _buildSlot = this.buildSlot(slot);
        logic.addSlot(((ISlot) _buildSlot));
      }
      _xblockexpression = logic;
    }
    return _xblockexpression;
  }

  protected BasicSlot _buildSlot(final AndSlot andSlot) {
    DefaultLogicalSlot _xblockexpression = null;
    {
      List<ISlot> _emptyList = Collections.<ISlot>emptyList();
      final DefaultLogicalSlot logic = new DefaultLogicalSlot(ILogicalSlot.AND, _emptyList);
      EList<ComplexSlot> _slots = andSlot.getSlots();
      for (final ComplexSlot slot : _slots) {
        Object _buildSlot = this.buildSlot(slot);
        logic.addSlot(((ISlot) _buildSlot));
      }
      _xblockexpression = logic;
    }
    return _xblockexpression;
  }

  protected BasicSlot _buildSlot(final NotSlot notSlot) {
    DefaultLogicalSlot _xblockexpression = null;
    {
      List<ISlot> _emptyList = Collections.<ISlot>emptyList();
      final DefaultLogicalSlot logic = new DefaultLogicalSlot(ILogicalSlot.NOT, _emptyList);
      EList<ComplexSlot> _slots = notSlot.getSlots();
      for (final ComplexSlot slot : _slots) {
        Object _buildSlot = this.buildSlot(slot);
        logic.addSlot(((ISlot) _buildSlot));
      }
      _xblockexpression = logic;
    }
    return _xblockexpression;
  }

  protected BasicSlot _buildSlot(final ConditionalSlot conditionalSlot) {
    DefaultConditionalSlot _xblockexpression = null;
    {
      int cond = IConditionalSlot.EQUALS;
      Condition _condition = conditionalSlot.getCondition();
      if (_condition != null) {
        switch (_condition) {
          case EQUALS:
            cond = IConditionalSlot.EQUALS;
            break;
          case LT:
            cond = IConditionalSlot.LESS_THAN;
            break;
          case LTE:
            cond = IConditionalSlot.LESS_THAN_EQUALS;
            break;
          case GT:
            cond = IConditionalSlot.GREATER_THAN;
            break;
          case GTE:
            cond = IConditionalSlot.GREATER_THAN_EQUALS;
            break;
          case NOT:
            cond = IConditionalSlot.NOT_EQUALS;
            break;
          default:
            break;
        }
      }
      String _name = conditionalSlot.getName();
      Object _resolveSlotValue = this.resolveSlotValue(conditionalSlot.getValue());
      _xblockexpression = new DefaultConditionalSlot(_name, cond, _resolveSlotValue);
    }
    return _xblockexpression;
  }

  protected BasicSlot _buildSlot(final SimpleSlot simpleSlot) {
    String _name = simpleSlot.getName();
    Object _resolveSlotValue = this.resolveSlotValue(simpleSlot.getValue());
    return new DefaultMutableSlot(_name, _resolveSlotValue);
  }

  protected void stage9Parameters(final ModelFragment fragment, final Map<String, EObject> chunkTypeSymbols, final Map<String, EObject> chunkSymbols, final Map<String, EObject> productionSymbols, final Map<String, EObject> bufferSymbols) {
    final Consumer<Parameters> _function = (Parameters parameters) -> {
      final Consumer<String> _function_1 = (String id) -> {
        try {
          IParameterized parameterized = ((IParameterized) null);
          EObject obj = chunkTypeSymbols.get(id);
          if (((obj != null) && (parameterized == null))) {
            ISubsymbolicChunkType _subsymbolicChunkType = this._context.getChunkType(id).getSubsymbolicChunkType();
            parameterized = ((IParameterized) _subsymbolicChunkType);
          } else {
            obj = chunkSymbols.get(id);
          }
          if (((obj != null) && (parameterized == null))) {
            parameterized = this._context.getChunk(id).getSubsymbolicChunk();
          } else {
            obj = productionSymbols.get(id);
          }
          if (((obj != null) && (parameterized == null))) {
            parameterized = this._context.model.getProceduralModule().getProduction(id).get().getSubsymbolicProduction();
          } else {
            obj = bufferSymbols.get(id);
          }
          if (((obj != null) && (parameterized == null))) {
            IActivationBuffer _activationBuffer = this._context.model.getActivationBuffer(id);
            parameterized = ((IParameterized) _activationBuffer);
          }
          this.assignParameters(parameterized, parameters.getParameters());
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      parameters.getElements().forEach(_function_1);
    };
    EcoreUtil2.<Parameters>getAllContentsOfType(fragment, Parameters.class).forEach(_function);
  }

  protected Set<ICondition> buildCondition(final ProductionCondition match) {
    if (match instanceof Match) {
      return _buildCondition((Match)match);
    } else if (match instanceof Proxy) {
      return _buildCondition((Proxy)match);
    } else if (match instanceof Query) {
      return _buildCondition((Query)match);
    } else if (match instanceof Script) {
      return _buildCondition((Script)match);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(match).toString());
    }
  }

  protected DefaultAction buildAction(final ProductionAction add) {
    if (add instanceof Add) {
      return _buildAction((Add)add);
    } else if (add instanceof Modify) {
      return _buildAction((Modify)add);
    } else if (add instanceof Output) {
      return _buildAction((Output)add);
    } else if (add instanceof Proxy) {
      return _buildAction((Proxy)add);
    } else if (add instanceof Remove) {
      return _buildAction((Remove)add);
    } else if (add instanceof Script) {
      return _buildAction((Script)add);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(add).toString());
    }
  }

  protected BasicSlot buildSlot(final EObject andSlot) {
    if (andSlot instanceof AndSlot) {
      return _buildSlot((AndSlot)andSlot);
    } else if (andSlot instanceof NotSlot) {
      return _buildSlot((NotSlot)andSlot);
    } else if (andSlot instanceof OrSlot) {
      return _buildSlot((OrSlot)andSlot);
    } else if (andSlot instanceof ConditionalSlot) {
      return _buildSlot((ConditionalSlot)andSlot);
    } else if (andSlot instanceof SimpleSlot) {
      return _buildSlot((SimpleSlot)andSlot);
    } else {
      throw new IllegalArgumentException("Unhandled parameter types: " +
        Arrays.<Object>asList(andSlot).toString());
    }
  }
}
