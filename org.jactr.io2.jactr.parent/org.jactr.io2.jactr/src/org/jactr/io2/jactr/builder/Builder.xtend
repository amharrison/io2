package org.jactr.io2.jactr.builder

import com.google.common.collect.Lists
import com.google.inject.Inject
import java.util.Collections
import java.util.List
import java.util.Map
import java.util.Set
import java.util.TreeSet
import java.util.function.BiConsumer
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2
import org.jactr.core.chunk.IChunk
import org.jactr.core.chunktype.IChunkType
import org.jactr.core.extensions.IExtension
import org.jactr.core.model.IModel
import org.jactr.core.model.basic.BasicModel
import org.jactr.core.module.IModule
import org.jactr.core.production.action.AddAction
import org.jactr.core.production.action.IAction
import org.jactr.core.production.action.ModifyAction
import org.jactr.core.production.action.OutputAction
import org.jactr.core.production.action.ProxyAction
import org.jactr.core.production.action.RemoveAction
import org.jactr.core.production.condition.AbstractSlotCondition
import org.jactr.core.production.condition.ChunkCondition
import org.jactr.core.production.condition.ChunkTypeCondition
import org.jactr.core.production.condition.ICondition
import org.jactr.core.production.condition.ProxyCondition
import org.jactr.core.production.condition.QueryCondition
import org.jactr.core.production.condition.VariableCondition
import org.jactr.core.slot.DefaultConditionalSlot
import org.jactr.core.slot.DefaultLogicalSlot
import org.jactr.core.slot.DefaultMutableSlot
import org.jactr.core.slot.IConditionalSlot
import org.jactr.core.slot.ILogicalSlot
import org.jactr.core.slot.ISlot
import org.jactr.core.utils.parameter.IParameterized
import org.jactr.fluent.FluentChunk
import org.jactr.fluent.FluentChunkType
import org.jactr.fluent.FluentProduction
import org.jactr.io2.jactr.modelFragment.Add
import org.jactr.io2.jactr.modelFragment.AndSlot
import org.jactr.io2.jactr.modelFragment.Buffer
import org.jactr.io2.jactr.modelFragment.ChunkDef
import org.jactr.io2.jactr.modelFragment.ChunkType
import org.jactr.io2.jactr.modelFragment.Chunks
import org.jactr.io2.jactr.modelFragment.ConditionalSlot
import org.jactr.io2.jactr.modelFragment.IsaBlock
import org.jactr.io2.jactr.modelFragment.Match
import org.jactr.io2.jactr.modelFragment.ModelExtension
import org.jactr.io2.jactr.modelFragment.ModelFragment
import org.jactr.io2.jactr.modelFragment.Modify
import org.jactr.io2.jactr.modelFragment.NotSlot
import org.jactr.io2.jactr.modelFragment.OrSlot
import org.jactr.io2.jactr.modelFragment.Output
import org.jactr.io2.jactr.modelFragment.Parameters
import org.jactr.io2.jactr.modelFragment.ParametersBlock
import org.jactr.io2.jactr.modelFragment.Production
import org.jactr.io2.jactr.modelFragment.ProductionAction
import org.jactr.io2.jactr.modelFragment.ProductionCondition
import org.jactr.io2.jactr.modelFragment.Proxy
import org.jactr.io2.jactr.modelFragment.Query
import org.jactr.io2.jactr.modelFragment.Remove
import org.jactr.io2.jactr.modelFragment.Script
import org.jactr.io2.jactr.modelFragment.SimpleSlot
import org.jactr.io2.jactr.modelFragment.Value
import org.jactr.io2.jactr.validation.ModelFragmentUtil
import org.jactr.scripting.ScriptingManager
import org.jactr.scripting.action.ScriptableAction
import org.jactr.scripting.condition.ScriptableCondition

//import org.slf4j.Logger
//import org.slf4j.LoggerFactory

class Builder {

//  static transient Logger LOGGER = LoggerFactory.getLogger(Builder)

  @Inject extension ModelFragmentUtil

  BuilderContext _context = new BuilderContext()

  protected def assignParameters(IParameterized parameterized, ParametersBlock parameters) {
    if (parameters !== null)
      for (parameter : parameters.parameter) {
        var strValue = ""
        if (parameter.value.isBoolean)
          strValue = parameter.value.boolean.toString
        else if (parameter.value.isString)
          strValue = parameter.value.string.toString
        else if (parameter.value.isNumber)
          strValue = parameter.value.number.toString
        else
          strValue = parameter.value.name.toString
        parameterized.setParameter(parameter.name, strValue)
      }
  }

  def build(ModelFragment fragment) {

    fragment.stage1Modules

    assignParameters(_context.model, fragment.package.parameters)

    val chunkTypeSymbols = resolveSymbolTable(fragment.eResource, fragment.chunkTypeSymbolTable)
    val chunkSymbols = resolveSymbolTable(fragment.eResource, fragment.chunkSymbolTable)
    val productionSymbols = resolveSymbolTable(fragment.eResource, fragment.productionSymbolTable);
    val bufferSymbols = resolveSymbolTable(fragment.eResource, fragment.bufferSymbolTable);

    val chunksToIgnore = findBufferContents(bufferSymbols)

    fragment.stage2ChunkTypes(chunkTypeSymbols)

    fragment.stage3BootstrapChunk(chunkTypeSymbols, chunkSymbols)

    fragment.stage4ChunkTypeEncoding(chunkTypeSymbols, chunkSymbols)

    fragment.stage5ResolveChunks(chunkSymbols, chunksToIgnore)

    fragment.stage6DeclarativeParameterization(chunkTypeSymbols, chunkSymbols)

    /*
     * buffer contents & parameters must be done before productions so that
     * StrictHarvestingEnabled is handled correctly.
     */
    fragment.stage7Buffers(bufferSymbols)

    fragment.stage8ProductionEncoding(productionSymbols)

    fragment.stage9Parameters(chunkTypeSymbols, chunkSymbols, productionSymbols, bufferSymbols)

    // when do we handle the extensions?
    fragment.stage10Extensions()

    // where do we call model.initialize?
    _context.model.initialize
    _context.model
  }

  protected def findBufferContents(Map<String, EObject> bufferSymbols) {
    val rtn = new TreeSet
    bufferSymbols.values.map[o|o as Buffer].filter[b|b.source.size > 0].forEach [ b |
      b.source.forEach[s|rtn.add(s.name)]
    ]
    rtn
  }

  protected def void stage7Buffers(ModelFragment fragment, Map<String, EObject> bufferSymbols) {
    bufferSymbols.values.map[o|o as Buffer].filter [ b |
      b.source.size > 0
    ].forEach [ b |
      val buffer = _context.model.getActivationBuffer(b.name)
      b.source.forEach [ source |
        val src = _context.getChunk(source.name)
        if (src === null)
          throw new RuntimeException("Source chunk (" + source.name + ") for " + b.name + " was not found");
        buffer.addSourceChunk(src)
      ]
    ]

    bufferSymbols.values.map[o|o as Buffer].forEach [ b |
      val buffer = _context.model.getActivationBuffer(b.name)
      if (buffer instanceof IParameterized)
        assignParameters(buffer, b.parameters)
    ]
  }

  protected def void stage10Extensions(ModelFragment fragment) {
    fragment.package.extensions.forEach[ext|instantiateExtension(ext)]
  }

  protected def void instantiateExtension(ModelExtension ext) {
    val className = ext.extensionClass

    try {
      var clazz = IModel.classLoader.loadClass(className) as Class<? extends IExtension>;
      var instance = clazz.newInstance()
      if (instance instanceof IParameterized)
        assignParameters(instance, ext.parameters)

      _context.model.install(instance);
    } catch (Exception e) {
      throw new RuntimeException("Failed to instantiate " + className, e);
    }
  }

  /**
   * instantiate the modules and install
   */
  protected def stage1Modules(ModelFragment fragment) {
    _context.model = new BasicModel(fragment.package.name)
    for (module : fragment.package.modules) {
      var actualModule = IModel.classLoader.loadClass(module.moduleClass).newInstance as IModule
      if (actualModule instanceof IParameterized)
        assignParameters(actualModule, module.parameters)

      _context.model.install(actualModule)
    }
  }

  protected def stage2ChunkTypes(ModelFragment fragment, Map<String, EObject> chunkTypeSymbols) {

    chunkTypeSymbols.values.map[n|n as ChunkType].forEach([ chunkType |
      chunkType.stage2ProvisionChunkType(chunkTypeSymbols)
    ])
  }

  /**
   * provisionally create the chunktype, but leave its slot values unset, stash
   * in context for later processing (stage4)
   */
  protected def IChunkType stage2ProvisionChunkType(ChunkType chunkType, Map<String, EObject> chunkTypeSymbols) {
    var ct = _context.getChunkType(chunkType.name)
    if(ct !== null) return ct // already provisioned
    val chunkTypeToBuild = chunkType

    var fct = FluentChunkType.from(_context.model).named(chunkTypeToBuild.name)
    if (chunkTypeToBuild.superType !== null) {
      var parent = _context.getChunkType(chunkTypeToBuild.superType.name);
      if (parent === null)
        parent = chunkTypeToBuild.superType.stage2ProvisionChunkType(chunkTypeSymbols)

      fct = fct.childOf(parent)
    }

    val tmpfct = fct;
    // quick provisional slots, the actual slots will be reset again
    // after we've provisioned the default slot values.
    resolveSlots(
      EcoreUtil2.getAllContentsOfType(chunkType, SimpleSlot),
      [s, v|tmpfct.slot(s, v)]
    )

    ct = fct.build
    _context.add(ct)

    return ct
  }

  /**
   * encode chunks of type chunk. this is done as a bootstrap since many default slot
   * values are these chunks
   */
  protected def stage3BootstrapChunk(ModelFragment fragment, Map<String, EObject> chunkTypeSymbols,
    Map<String, EObject> chunkSymbols) {

    val chunkType = chunkTypeSymbols.get("chunk") as ChunkType
    if (chunkType !== null)
      stage4ResolveChunkType(chunkType, chunkSymbols)

    chunkSymbols
  }

  /**
   * iteratively try fully resolving chunktypes, for each fully resolved chunktype, provisionally create
   * the chunks
   */
  protected def stage4ChunkTypeEncoding(ModelFragment fragment, Map<String, EObject> chunkTypeSymbols,
    Map<String, EObject> chunkSymbols) {

    val allChunkTypes = chunkTypeSymbols.values.map[node|node as ChunkType]
    var noCyclesDetected = true
    var lastCycleNoChanges = false

    while (noCyclesDetected && allChunkTypes.size > 0) {
      val sizeAtStart = allChunkTypes.size

      val itr = allChunkTypes.iterator
      while (itr.hasNext)
        if (stage4ResolveChunkType(itr.next, chunkSymbols))
          itr.remove

      /*
       * have we encoded anyone? two cycles of no changes, we assume
       * cyclic dependency
       */
      val sizeAtEnd = allChunkTypes.size
      if (sizeAtEnd == sizeAtStart) {
        if (lastCycleNoChanges)
          noCyclesDetected = false

        lastCycleNoChanges = true
      }
    }

    // provide more information please
    if (!noCyclesDetected) {
      val dependentTypes = allChunkTypes.map[n|n.name]
      throw new RuntimeException("Suspected cyclic build dependency found in types " + dependentTypes);
    }
  }

  /**
   * return the resolved value or the value object itself for unresolved
   */
  protected def resolveSlotValue(Value value) {
    if (value.isString)
      return value.string
    if (value.isNumber)
      return value.number
    if (value.isNull)
      return null
    if (value.isBoolean)
      return value.boolean
    if (value.isVariable)
      return value.name
    /*
     * now we have an id that we need to resolve
     */
    val resolvedByContext = _context.resolve(value.name)
    if (resolvedByContext !== null)
      return resolvedByContext

//    LOGGER.debug(String.format("Unresolved slot value %s", value))
    return value
  }

  /**
   * return true if all slots are resolved, for each resolved, call the consumer
   */
  protected def resolveSlots(List<SimpleSlot> slots, BiConsumer<String, Object> slotAcceptor) {
    val itr = slots.iterator
    while (itr.hasNext) {
      val ss = itr.next
      val resolvedValue = resolveSlotValue(ss.value)
      if (resolvedValue !== ss.value) {
        itr.remove
        slotAcceptor.accept(ss.name, resolvedValue)
      }
    }

    return slots.size == 0
  }

  /**
   * try to fully resolve the slot values of this chunktype. if successful, 
   * provisionally encode the chunks
   */
  protected def stage4ResolveChunkType(ChunkType chunkType, Map<String, EObject> chunkSymbols) {

    val ct = _context.getChunkType(chunkType.name)

    if (ct.isEncoded) {

      return true // already completed stage4
    }
    val fct = FluentChunkType.from(ct)

    var slotContainer = chunkType
    var slotsResolved = true
    val resolvedSlots = new TreeSet()
    /*
     * make sure we add our default slots and the parent's too, but only
     * those that haven;t already been defined
     */
    while (slotContainer !== null) {

      slotsResolved = slotsResolved && resolveSlots(
        EcoreUtil2.getAllContentsOfType(slotContainer, SimpleSlot).filter[s|!resolvedSlots.contains(s.name)].toList,
        [ s, v |
          fct.slot(s, v)
          resolvedSlots.add(s)
        ]
      )
      slotContainer = slotContainer.superType
    }

    if (slotsResolved) {

      fct.build()

      _context.encodeChunkType(chunkType.name)

//      LOGGER.debug(String.format("Resolved %s", chunkType.name))

      stage4ProvisionChunks(chunkType, chunkSymbols)
      return true
    }

    return false
  }

  /**
   * provisionally create the chunks of this type
   */
  protected def stage4ProvisionChunks(ChunkType chunkType, Map<String, EObject> chunkSymbols) {

    val allChunks = chunkSymbols.values.map[ed|ed as ChunkDef]

    allChunks.forEach [ chunk |
      // we test the name not chunktype itself since the type may have been overridden
      if ((chunk.eContainer as Chunks).type.name == chunkType.name) {
        val provisionalChunk = FluentChunk.from(_context.getChunkType(chunkType.name)).named(chunk.name).build
        _context.add(provisionalChunk)
//        LOGGER.debug(String.format("Provisioned %s", chunk.name))
      }
    ]
  }

  protected def stage5ResolveChunkSlots(ChunkDef chunk, Set<String> chunksToNotEncode) {
    var c = _context.getChunk(chunk.name);
    if(c.isEncoded) return
    val fc = FluentChunk.from(c)
    val resolved = Lists.newArrayList
    var slotsResolved = resolveSlots(
      EcoreUtil2.getAllContentsOfType(chunk, SimpleSlot),
      [ s, v |
        fc.slot(s, v)
        resolved.add(s)
      ]
    )

    if (slotsResolved) {
      c = fc.build
      /*
       * this chunk might not be encoded because its a buffer content
       */
      if (!chunksToNotEncode.contains(chunk.name)) {
        _context.encodeChunk(chunk.name)
//        LOGGER.debug(String.format("Resolved %s", chunk.name))
      }

// parameters are done once all the chunks have been resolved, this way we
// can be sure associative links are processed correctly
    } else {
      throw new RuntimeException(
        "Could not fully resolve chunk " + chunk.name + " at build time? Resolved :" + resolved)
    }
  }

  protected def stage5ResolveChunks(ModelFragment fragment, Map<String, EObject> chunkSymbols,
    Set<String> chunksToNotEncode) {
    val allChunks = chunkSymbols.values.map[ed|ed as ChunkDef]
    allChunks.forEach[chunk|chunk.stage5ResolveChunkSlots(chunksToNotEncode)]
  }

  protected def stage6DeclarativeParameterization(ModelFragment fragment, Map<String, EObject> chunkTypeSymbols,
    Map<String, EObject> chunkSymbols) {

    /*
     * go back through chunktypes and chunks setting the parameters
     */
    for (actualChunkType : chunkTypeSymbols.values.map[node|node as ChunkType]) {
      val chunkType = _context.getChunkType(actualChunkType.name)
      if (chunkType === null)
        throw new RuntimeException(
          "Missing chunktype for " + actualChunkType.name + ". Potential override incompatibility.")

      assignParameters(chunkType.subsymbolicChunkType, actualChunkType.parameters);
    }

    for (actualChunk : chunkSymbols.values.map[node|node as ChunkDef]) {
      val chunk = _context.getChunk(actualChunk.name)
      if (chunk === null)
        throw new RuntimeException("Missing chunk for " + actualChunk.name + ". Potentially merged. Known :" +
          _context.knownChunks)

      assignParameters(chunk.subsymbolicChunk, actualChunk.parameters);
    }

  }

  protected def stage8ProductionEncoding(ModelFragment fragment, Map<String, EObject> productionSymbols) {
    val allProductions = productionSymbols.values.stream.map [ ed |
      ed as Production
    ]
    allProductions.forEach[p|p.stage8EncodeProduction(productionSymbols)]
  }

  protected def stage8EncodeProduction(Production production, Map<String, EObject> productionSymbols) {
    val productionToBuild = production

    val fp = FluentProduction.from(_context.model).named(productionToBuild.name)
    for (ProductionCondition condition : productionToBuild.conditions) {
      condition.buildCondition.forEach [ c |
        if (c !== null)
          fp.condition(c)
        else
          throw new IllegalStateException("got null condition for " + condition)
      ]
    }

    for (ProductionAction action : productionToBuild.actions) {
      val a = action.buildAction as IAction
      if (a !== null)
        fp.action(a)
      else
        throw new IllegalStateException("got null action for " + action)

    }

    val p = fp.encode

    assignParameters(p.subsymbolicProduction, productionToBuild.parameters)
  }

  protected def resolveIsa(IsaBlock isa) {
    try {
      if(isa===null) return null;
      
      if (isa.type !== null)
        return _context.getChunkType(isa.type.name)
      if (isa.chunk !== null)
        return _context.getChunk(isa.chunk.name)
      
      isa.name
    } catch (Exception e) {
      System.err.println("NULL !!! " + isa.type + " " + isa.chunk + " " + isa.name);
      throw e
    }
  }

  protected def dispatch buildCondition(Match match) {
    var pattern = null as AbstractSlotCondition
    val isa = resolveIsa(match.isa)

    if (_context.model.getActivationBuffer(match.name.name) === null || isa === null)
      throw new IllegalStateException("Could not find buffer named " + match.name.name +
        ", was its module installed and import defined?");

    if (isa instanceof IChunkType)
      pattern = new ChunkTypeCondition(match.name.name, isa, Collections.emptyList)
    else if (isa instanceof IChunk)
      pattern = new ChunkCondition(match.name.name, isa, Collections.emptyList)
    else if (isa instanceof String)
      pattern = new VariableCondition(match.name.name, isa)

    for (slot : match.slots) {
      val actualSlot = buildSlot(slot) as ISlot

      pattern.addSlot(actualSlot)
    }

    Collections.singleton(pattern as ICondition)
  }

  protected def dispatch buildCondition(Query query) {
    val action = new QueryCondition(query.name.name)
    for (slot : query.slots)
      action.addSlot(buildSlot(slot) as ISlot)
    Collections.singleton(action as ICondition)
  }

  protected def dispatch buildCondition(Proxy proxy) {
    val pc = new ProxyCondition(proxy.name)
    for (slot : proxy.slots)
      pc.addSlot(buildSlot(slot) as ISlot)
    Collections.singleton(pc as ICondition)
  }

  protected def dispatch buildCondition(Script script) {
    val sm = ScriptingManager.getFactory(script.language)
    if (sm === null)
      throw new IllegalArgumentException("Could not find script support for " + script.language)

    Collections.singleton(new ScriptableCondition(sm.createConditionScript(script.script)) as ICondition)
  }

  protected def dispatch buildAction(Proxy proxy) {
    val pc = new ProxyAction(proxy.name)
    for (slot : proxy.slots)
      pc.addSlot(buildSlot(slot) as ISlot)
    pc
  }

  protected def dispatch buildAction(Script script) {
    val sm = ScriptingManager.getFactory(script.language)
    if (sm === null)
      throw new IllegalArgumentException("Could not find script support for " + script.language)

    new ScriptableAction(sm.createActionScript(script.script))
  }

  protected def dispatch buildAction(Add add) {
    val action = new AddAction(add.name.name, resolveIsa(add.isa))
    for (slot : add.slots)
      action.addSlot(buildSlot(slot) as ISlot)
    action
  }

  protected def dispatch buildAction(Modify modify) {
    val action = new ModifyAction(modify.name.name)
    for (slot : modify.slots)
      action.addSlot(buildSlot(slot) as ISlot)
    action
  }

  protected def dispatch buildAction(Remove remove) {
    val action = new RemoveAction(remove.name.name)
    for (slot : remove.slots)
      action.addSlot(buildSlot(slot) as ISlot)
    action
  }

  protected def dispatch buildAction(Output output) {
    new OutputAction(output.string)
  }

  protected def dispatch buildSlot(OrSlot orSlot) {
    val logic = new DefaultLogicalSlot(ILogicalSlot.OR, Collections.emptyList)
    for (slot : orSlot.slots)
      logic.addSlot(buildSlot(slot) as ISlot)
    logic
  }

  protected def dispatch buildSlot(AndSlot andSlot) {
    val logic = new DefaultLogicalSlot(ILogicalSlot.AND, Collections.emptyList)
    for (slot : andSlot.slots)
      logic.addSlot(buildSlot(slot) as ISlot)
    logic
  }

  protected def dispatch buildSlot(NotSlot notSlot) {
    val logic = new DefaultLogicalSlot(ILogicalSlot.NOT, Collections.emptyList)
    for (slot : notSlot.slots)
      logic.addSlot(buildSlot(slot) as ISlot)
    logic
  }

  protected def dispatch buildSlot(ConditionalSlot conditionalSlot) {
    var cond = IConditionalSlot.EQUALS
    switch (conditionalSlot.condition) {
      case EQUALS: cond = IConditionalSlot.EQUALS
      case LT: cond = IConditionalSlot.LESS_THAN
      case LTE: cond = IConditionalSlot.LESS_THAN_EQUALS
      case GT: cond = IConditionalSlot.GREATER_THAN
      case GTE: cond = IConditionalSlot.GREATER_THAN_EQUALS
      case NOT: cond = IConditionalSlot.NOT_EQUALS
    }

    new DefaultConditionalSlot(conditionalSlot.name, cond, resolveSlotValue(conditionalSlot.value))
  }

  protected def dispatch buildSlot(SimpleSlot simpleSlot) {
    new DefaultMutableSlot(simpleSlot.name, resolveSlotValue(simpleSlot.value))
  }

  protected def stage9Parameters(ModelFragment fragment, Map<String, EObject> chunkTypeSymbols,
    Map<String, EObject> chunkSymbols, Map<String, EObject> productionSymbols, Map<String, EObject> bufferSymbols) {
    EcoreUtil2.getAllContentsOfType(fragment, Parameters).forEach([ parameters |
      parameters.elements.forEach([ id |
        var parameterized = null as IParameterized

        var obj = chunkTypeSymbols.get(id)
        if (obj !== null && parameterized === null)
          parameterized = _context.getChunkType(id).getSubsymbolicChunkType() as IParameterized
        else
          obj = chunkSymbols.get(id)

        if (obj !== null && parameterized === null)
          parameterized = _context.getChunk(id).getSubsymbolicChunk()
        else
          obj = productionSymbols.get(id)

        if (obj !== null && parameterized === null)
          parameterized = _context.model.proceduralModule.getProduction(id).get().subsymbolicProduction
        else
          obj = bufferSymbols.get(id)

        if (obj !== null && parameterized === null)
          parameterized = _context.model.getActivationBuffer(id) as IParameterized

        assignParameters(parameterized, parameters.parameters)
      ])
    ])
  }
}
