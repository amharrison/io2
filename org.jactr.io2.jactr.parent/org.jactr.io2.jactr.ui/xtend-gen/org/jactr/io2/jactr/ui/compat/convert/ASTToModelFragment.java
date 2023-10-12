package org.jactr.io2.jactr.ui.compat.convert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.jactr.io.antlr3.builder.JACTRBuilder;
import org.jactr.io.antlr3.misc.ASTSupport;
import org.jactr.io2.jactr.modelFragment.Add;
import org.jactr.io2.jactr.modelFragment.AndSlot;
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
import org.jactr.io2.jactr.modelFragment.NotSlot;
import org.jactr.io2.jactr.modelFragment.OrSlot;
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

/**
 * converts old io CommonTree into io2.xtext.ModelFragment
 */
@SuppressWarnings("all")
public class ASTToModelFragment {
  public ModelFragment convert(final CommonTree modelTree) {
    final ModelFragment modelFragment = ModelFragmentFactory.eINSTANCE.createModelFragment();
    modelFragment.setPackage(ModelFragmentFactory.eINSTANCE.createPackageDeclaration());
    PackageDeclaration _package = modelFragment.getPackage();
    _package.setName(ASTSupport.getName(modelTree));
    ArrayList<ModelModule> _modules = this.modules(modelTree);
    for (final ModelModule module : _modules) {
      modelFragment.getPackage().getModules().add(module);
    }
    ArrayList<ModelExtension> _extensions = this.extensions(modelTree);
    for (final ModelExtension ext : _extensions) {
      modelFragment.getPackage().getExtensions().add(ext);
    }
    PackageDeclaration _package_1 = modelFragment.getPackage();
    _package_1.setBuffers(this.buffers(modelTree));
    ArrayList<Element> _chunkTypes = this.chunkTypes(modelTree);
    for (final Element chunkType : _chunkTypes) {
      modelFragment.getPackage().getElements().add(chunkType);
    }
    ArrayList<Production> _productions = this.productions(modelTree, modelFragment);
    for (final Production production : _productions) {
      modelFragment.getPackage().getElements().add(production);
    }
    return modelFragment;
  }
  
  protected String safeName(final String name) {
    String _xblockexpression = null;
    {
      String str = name;
      boolean _isDigit = Character.isDigit(name.charAt(0));
      if (_isDigit) {
        str = ("a" + str);
      }
      int _length = str.length();
      boolean _equals = (_length == 1);
      if (_equals) {
        str = (str + str);
      }
      if (("true".equals(str) || "false".equals(str))) {
        str = str.toUpperCase();
      }
      _xblockexpression = str.replace(".", "-");
    }
    return _xblockexpression;
  }
  
  protected ArrayList<Production> productions(final CommonTree modelTree, final ModelFragment modelFragment) {
    ArrayList<Production> _xblockexpression = null;
    {
      final ArrayList<Production> rtn = new ArrayList<Production>();
      Collection<CommonTree> _allDescendantsWithType = ASTSupport.getAllDescendantsWithType(modelTree, JACTRBuilder.PRODUCTION);
      for (final CommonTree productionAST : _allDescendantsWithType) {
        rtn.add(this.production(productionAST, modelFragment));
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected Production production(final CommonTree productionTree, final ModelFragment modelFragment) {
    Production _xblockexpression = null;
    {
      final Production production = ModelFragmentFactory.eINSTANCE.createProduction();
      production.setName(this.safeName(ASTSupport.getName(productionTree)));
      final Function1<Buffer, String> _function = (Buffer buffer) -> {
        return buffer.getName();
      };
      final Map<String, Buffer> buffers = IterableExtensions.<String, Buffer>toMap(EcoreUtil2.<Buffer>getAllContentsOfType(modelFragment.getPackage(), Buffer.class), _function);
      ArrayList<ProductionCondition> _conditions = this.conditions(productionTree, buffers);
      for (final ProductionCondition condition : _conditions) {
        production.getConditions().add(condition);
      }
      ArrayList<ProductionAction> _actions = this.actions(productionTree, buffers);
      for (final ProductionAction action : _actions) {
        production.getActions().add(action);
      }
      final CommonTree parametersAST = ASTSupport.getFirstDescendantWithType(productionTree, JACTRBuilder.PARAMETERS);
      if (((parametersAST != null) && (parametersAST.getChildCount() != 0))) {
        production.setParameters(this.parameters(parametersAST));
      }
      _xblockexpression = production;
    }
    return _xblockexpression;
  }
  
  protected ArrayList<ProductionCondition> conditions(final CommonTree productionTree, final Map<String, Buffer> buffers) {
    ArrayList<ProductionCondition> _xblockexpression = null;
    {
      final ArrayList<ProductionCondition> rtn = new ArrayList<ProductionCondition>();
      Tree _firstChildWithType = productionTree.getFirstChildWithType(JACTRBuilder.CONDITIONS);
      List _children = ((CommonTree) _firstChildWithType).getChildren();
      for (final Object conditionAST : _children) {
        if ((conditionAST instanceof CommonTree)) {
          int _type = ((CommonTree)conditionAST).getType();
          switch (_type) {
            case JACTRBuilder.MATCH_CONDITION:
              rtn.add(this.match(((CommonTree)conditionAST), buffers));
              break;
            case JACTRBuilder.QUERY_CONDITION:
              rtn.add(this.query(((CommonTree)conditionAST), buffers));
              break;
            case JACTRBuilder.PROXY_CONDITION:
              rtn.add(this.proxyCondition(((CommonTree)conditionAST)));
              break;
            case JACTRBuilder.SCRIPTABLE_CONDITION:
              rtn.add(this.scriptableCondition(((CommonTree)conditionAST)));
              break;
          }
        }
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected ArrayList<ProductionAction> actions(final CommonTree productionTree, final Map<String, Buffer> buffers) {
    ArrayList<ProductionAction> _xblockexpression = null;
    {
      final ArrayList<ProductionAction> rtn = new ArrayList<ProductionAction>();
      Tree _firstChildWithType = productionTree.getFirstChildWithType(JACTRBuilder.ACTIONS);
      List _children = ((CommonTree) _firstChildWithType).getChildren();
      for (final Object actionAST : _children) {
        if ((actionAST instanceof CommonTree)) {
          int _type = ((CommonTree)actionAST).getType();
          switch (_type) {
            case JACTRBuilder.ADD_ACTION:
              rtn.add(this.add(((CommonTree)actionAST), buffers));
              break;
            case JACTRBuilder.MODIFY_ACTION:
              rtn.add(this.modify(((CommonTree)actionAST), buffers));
              break;
            case JACTRBuilder.REMOVE_ACTION:
              rtn.add(this.remove(((CommonTree)actionAST), buffers));
              break;
            case JACTRBuilder.OUTPUT_ACTION:
              rtn.add(this.output(((CommonTree)actionAST)));
              break;
            case JACTRBuilder.PROXY_ACTION:
              rtn.add(this.proxyAction(((CommonTree)actionAST)));
              break;
            case JACTRBuilder.SCRIPTABLE_ACTION:
              rtn.add(this.scriptableAction(((CommonTree)actionAST)));
              break;
          }
        }
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected Add add(final CommonTree addAST, final Map<String, Buffer> buffers) {
    Add _xblockexpression = null;
    {
      final Add add = ModelFragmentFactory.eINSTANCE.createAdd();
      add.setName(buffers.get(ASTSupport.getName(addAST)));
      EObject _rootContainer = EcoreUtil.getRootContainer(add.getName());
      add.setIsa(this.isa(addAST, ((ModelFragment) _rootContainer)));
      ArrayList<ConditionalSlot> _conditionalSlots = this.conditionalSlots(addAST);
      for (final ConditionalSlot slot : _conditionalSlots) {
        add.getSlots().add(slot);
      }
      _xblockexpression = add;
    }
    return _xblockexpression;
  }
  
  protected IsaBlock isa(final CommonTree isaOwnerAST, final ModelFragment fragment) {
    IsaBlock _xblockexpression = null;
    {
      final IsaBlock isa = ModelFragmentFactory.eINSTANCE.createIsaBlock();
      final int type = isaOwnerAST.getChild(1).getType();
      final String txt = isaOwnerAST.getChild(1).getText();
      switch (type) {
        case JACTRBuilder.VARIABLE:
          isa.setName(txt);
          break;
        case JACTRBuilder.CHUNK_IDENTIFIER:
          final Function1<ChunkDef, Boolean> _function = (ChunkDef def) -> {
            return Boolean.valueOf(def.getName().equals(txt));
          };
          isa.setChunk(IterableExtensions.<ChunkDef>last(IterableExtensions.<ChunkDef>filter(EcoreUtil2.<ChunkDef>getAllContentsOfType(fragment, ChunkDef.class), _function)));
          break;
        case JACTRBuilder.CHUNK_TYPE_IDENTIFIER:
          final Function1<ChunkType, Boolean> _function_1 = (ChunkType def) -> {
            return Boolean.valueOf(def.getName().equals(txt));
          };
          isa.setType(IterableExtensions.<ChunkType>last(IterableExtensions.<ChunkType>filter(EcoreUtil2.<ChunkType>getAllContentsOfType(fragment, ChunkType.class), _function_1)));
          break;
      }
      _xblockexpression = isa;
    }
    return _xblockexpression;
  }
  
  protected Modify modify(final CommonTree modifyAST, final Map<String, Buffer> buffers) {
    Modify _xblockexpression = null;
    {
      final Modify modify = ModelFragmentFactory.eINSTANCE.createModify();
      modify.setName(buffers.get(ASTSupport.getName(modifyAST)));
      ArrayList<SimpleSlot> _simpleSlots = this.simpleSlots(modifyAST);
      for (final SimpleSlot slot : _simpleSlots) {
        modify.getSlots().add(slot);
      }
      _xblockexpression = modify;
    }
    return _xblockexpression;
  }
  
  protected Remove remove(final CommonTree removeAST, final Map<String, Buffer> buffers) {
    Remove _xblockexpression = null;
    {
      final Remove remove = ModelFragmentFactory.eINSTANCE.createRemove();
      remove.setName(buffers.get(ASTSupport.getName(removeAST)));
      ArrayList<SimpleSlot> _simpleSlots = this.simpleSlots(removeAST);
      for (final SimpleSlot slot : _simpleSlots) {
        remove.getSlots().add(slot);
      }
      _xblockexpression = remove;
    }
    return _xblockexpression;
  }
  
  protected Output output(final CommonTree outputAST) {
    Output _xblockexpression = null;
    {
      final Output output = ModelFragmentFactory.eINSTANCE.createOutput();
      output.setString(ASTSupport.getFirstDescendantWithType(outputAST, JACTRBuilder.STRING).getText());
      _xblockexpression = output;
    }
    return _xblockexpression;
  }
  
  protected Match match(final CommonTree matchAST, final Map<String, Buffer> buffers) {
    final Match match = ModelFragmentFactory.eINSTANCE.createMatch();
    match.setName(buffers.get(ASTSupport.getName(matchAST)));
    EObject _rootContainer = EcoreUtil.getRootContainer(match.getName());
    match.setIsa(this.isa(matchAST, ((ModelFragment) _rootContainer)));
    ArrayList<ComplexSlot> _booleanAndConditionals = this.booleanAndConditionals(ASTSupport.getFirstDescendantWithType(matchAST, JACTRBuilder.SLOTS));
    for (final ComplexSlot slot : _booleanAndConditionals) {
      match.getSlots().add(slot);
    }
    return match;
  }
  
  protected Query query(final CommonTree queryAST, final Map<String, Buffer> buffers) {
    final Query query = ModelFragmentFactory.eINSTANCE.createQuery();
    query.setName(buffers.get(ASTSupport.getName(queryAST)));
    ArrayList<ComplexSlot> _booleanAndConditionals = this.booleanAndConditionals(ASTSupport.getFirstDescendantWithType(queryAST, JACTRBuilder.SLOTS));
    for (final ComplexSlot slot : _booleanAndConditionals) {
      query.getSlots().add(slot);
    }
    return query;
  }
  
  protected Proxy proxyCondition(final CommonTree proxyAST) {
    Proxy _xblockexpression = null;
    {
      final Proxy proxy = ModelFragmentFactory.eINSTANCE.createProxy();
      proxy.setName(ASTSupport.getFirstDescendantWithType(proxyAST, JACTRBuilder.CLASS_SPEC).getText());
      ArrayList<SimpleSlot> _simpleSlots = this.simpleSlots(proxyAST);
      for (final SimpleSlot slot : _simpleSlots) {
        proxy.getSlots().add(slot);
      }
      _xblockexpression = proxy;
    }
    return _xblockexpression;
  }
  
  protected Proxy proxyAction(final CommonTree proxyAST) {
    Proxy _xblockexpression = null;
    {
      final Proxy proxy = ModelFragmentFactory.eINSTANCE.createProxy();
      proxy.setName(ASTSupport.getFirstDescendantWithType(proxyAST, JACTRBuilder.CLASS_SPEC).getText());
      ArrayList<SimpleSlot> _simpleSlots = this.simpleSlots(proxyAST);
      for (final SimpleSlot slot : _simpleSlots) {
        proxy.getSlots().add(slot);
      }
      _xblockexpression = proxy;
    }
    return _xblockexpression;
  }
  
  protected Script scriptableCondition(final CommonTree scriptAST) {
    Script _xblockexpression = null;
    {
      final Script script = ModelFragmentFactory.eINSTANCE.createScript();
      script.setLanguage(ASTSupport.getFirstDescendantWithType(scriptAST, JACTRBuilder.LANG).getText());
      script.setScript(ASTSupport.getFirstDescendantWithType(scriptAST, JACTRBuilder.SCRIPT).getText());
      _xblockexpression = script;
    }
    return _xblockexpression;
  }
  
  protected Script scriptableAction(final CommonTree scriptAST) {
    Script _xblockexpression = null;
    {
      final Script script = ModelFragmentFactory.eINSTANCE.createScript();
      script.setLanguage(ASTSupport.getFirstDescendantWithType(scriptAST, JACTRBuilder.LANG).getText());
      script.setScript(ASTSupport.getFirstDescendantWithType(scriptAST, JACTRBuilder.SCRIPT).getText());
      _xblockexpression = script;
    }
    return _xblockexpression;
  }
  
  protected ArrayList<ComplexSlot> booleanAndConditionals(final CommonTree slots) {
    ArrayList<ComplexSlot> _xblockexpression = null;
    {
      final ArrayList<ComplexSlot> rtn = new ArrayList<ComplexSlot>();
      if ((slots == null)) {
        return rtn;
      }
      List _children = slots.getChildren();
      for (final Object slotAST : _children) {
        if ((slotAST instanceof CommonTree)) {
          ComplexSlot _booleanOrConditional = this.booleanOrConditional(((CommonTree)slotAST));
          final ComplexSlot slot = ((ComplexSlot) _booleanOrConditional);
          rtn.add(slot);
        }
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected ComplexSlot booleanOrConditional(final CommonTree slotAST) {
    int _type = slotAST.getType();
    switch (_type) {
      case JACTRBuilder.SLOT:
        ConditionalSlot _conditionalSlot = this.conditionalSlot(slotAST);
        return ((ComplexSlot) _conditionalSlot);
      case JACTRBuilder.LOGIC:
        BooleanOperation _logicalSlot = this.logicalSlot(slotAST);
        return ((ComplexSlot) _logicalSlot);
    }
    return ((BooleanOperation) null);
  }
  
  protected BooleanOperation logicalSlot(final CommonTree slotAST) {
    NotSlot _xblockexpression = null;
    {
      final int operation = slotAST.getChild(0).getType();
      NotSlot _xifexpression = null;
      if ((operation == JACTRBuilder.AND)) {
        return this.and(slotAST);
      } else {
        NotSlot _xifexpression_1 = null;
        if ((operation == JACTRBuilder.OR)) {
          return this.or(slotAST);
        } else {
          _xifexpression_1 = this.not(slotAST);
        }
        _xifexpression = _xifexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  /**
   * logical slots have the operation as a child with the child slots
   */
  protected ArrayList<ComplexSlot> resolveSlotsForLogical(final CommonTree slotContainer) {
    ArrayList<ComplexSlot> _xblockexpression = null;
    {
      final ArrayList<ComplexSlot> rtn = new ArrayList<ComplexSlot>();
      List _children = slotContainer.getChildren();
      for (final Object child : _children) {
        if ((child instanceof CommonTree)) {
          if (((((CommonTree)child).getType() == JACTRBuilder.SLOT) || (((CommonTree)child).getType() == JACTRBuilder.LOGIC))) {
            final Object slot = this.booleanOrConditional(((CommonTree)child));
            rtn.add(((ComplexSlot) slot));
          }
        }
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected AndSlot and(final CommonTree slotAST) {
    AndSlot _xblockexpression = null;
    {
      final AndSlot rtn = ModelFragmentFactory.eINSTANCE.createAndSlot();
      ArrayList<ComplexSlot> _resolveSlotsForLogical = this.resolveSlotsForLogical(slotAST);
      for (final ComplexSlot slot : _resolveSlotsForLogical) {
        rtn.getSlots().add(slot);
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected OrSlot or(final CommonTree slotAST) {
    OrSlot _xblockexpression = null;
    {
      final OrSlot rtn = ModelFragmentFactory.eINSTANCE.createOrSlot();
      ArrayList<ComplexSlot> _resolveSlotsForLogical = this.resolveSlotsForLogical(slotAST);
      for (final ComplexSlot slot : _resolveSlotsForLogical) {
        rtn.getSlots().add(slot);
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected NotSlot not(final CommonTree slotAST) {
    NotSlot _xblockexpression = null;
    {
      final NotSlot rtn = ModelFragmentFactory.eINSTANCE.createNotSlot();
      ArrayList<ComplexSlot> _resolveSlotsForLogical = this.resolveSlotsForLogical(slotAST);
      for (final ComplexSlot slot : _resolveSlotsForLogical) {
        rtn.getSlots().add(slot);
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected ArrayList<ConditionalSlot> conditionalSlots(final CommonTree owner) {
    ArrayList<ConditionalSlot> _xblockexpression = null;
    {
      final ArrayList<ConditionalSlot> rtn = new ArrayList<ConditionalSlot>();
      Collection<CommonTree> _allDescendantsWithType = ASTSupport.getAllDescendantsWithType(owner, JACTRBuilder.SLOT);
      for (final CommonTree slotAST : _allDescendantsWithType) {
        rtn.add(this.conditionalSlot(slotAST));
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected ConditionalSlot conditionalSlot(final CommonTree slotAST) {
    ConditionalSlot _xblockexpression = null;
    {
      final ConditionalSlot conditional = ModelFragmentFactory.eINSTANCE.createConditionalSlot();
      conditional.setName(this.safeName(ASTSupport.getName(slotAST)));
      conditional.setCondition(this.asCondition(slotAST));
      Tree _child = slotAST.getChild(2);
      conditional.setValue(this.asValue(((CommonTree) _child)));
      _xblockexpression = conditional;
    }
    return _xblockexpression;
  }
  
  protected Value asValue(final CommonTree valueAST) {
    Value _xblockexpression = null;
    {
      final Value value = ModelFragmentFactory.eINSTANCE.createValue();
      final String valueString = valueAST.getText();
      int _type = valueAST.getType();
      boolean _equals = (_type == JACTRBuilder.NUMBER);
      if (_equals) {
        value.setNumber(Double.valueOf(Double.parseDouble(valueString)));
      } else {
        int _type_1 = valueAST.getType();
        boolean _equals_1 = (_type_1 == JACTRBuilder.IDENTIFIER);
        if (_equals_1) {
          value.setName(valueString);
        } else {
          int _type_2 = valueAST.getType();
          boolean _equals_2 = (_type_2 == JACTRBuilder.VARIABLE);
          if (_equals_2) {
            value.setName(valueString);
          } else {
            int _type_3 = valueAST.getType();
            boolean _equals_3 = (_type_3 == JACTRBuilder.STRING);
            if (_equals_3) {
              if (("true".equals(valueString) || "false".equals(valueString))) {
                value.setBoolean(Boolean.valueOf(Boolean.parseBoolean(valueString)));
              } else {
                value.setString(valueString);
              }
            }
          }
        }
      }
      _xblockexpression = value;
    }
    return _xblockexpression;
  }
  
  protected Condition asCondition(final CommonTree slotAST) {
    int _type = slotAST.getChild(1).getType();
    switch (_type) {
      case JACTRBuilder.EQUALS:
        return Condition.EQUALS;
      case JACTRBuilder.NOT:
        return Condition.NOT;
      case JACTRBuilder.LT:
        return Condition.LT;
      case JACTRBuilder.LTE:
        return Condition.LTE;
      case JACTRBuilder.GT:
        return Condition.GT;
      case JACTRBuilder.GTE:
        return Condition.GTE;
      default:
        int _type_1 = slotAST.getChild(1).getType();
        String _plus = ("Unknown condition type : " + Integer.valueOf(_type_1));
        throw new RuntimeException(_plus);
    }
  }
  
  protected ArrayList<Element> chunkTypes(final CommonTree modelTree) {
    ArrayList<Element> _xblockexpression = null;
    {
      final ArrayList<Element> rtn = new ArrayList<Element>();
      final HashMap<String, ChunkType> map = new HashMap<String, ChunkType>();
      final Collection<CommonTree> chunkTypeASTs = ASTSupport.getAllDescendantsWithType(modelTree, JACTRBuilder.CHUNK_TYPE);
      for (final CommonTree typeAST : chunkTypeASTs) {
        {
          final ChunkType chunkType = this.chunkType(ASTSupport.getName(typeAST), chunkTypeASTs, map);
          final Chunks chunks = ModelFragmentFactory.eINSTANCE.createChunks();
          chunks.setType(chunkType);
          ArrayList<ChunkDef> _chunks = this.chunks(typeAST);
          for (final ChunkDef chunk : _chunks) {
            chunks.getChunks().add(chunk);
          }
          rtn.add(chunkType);
          int _size = chunks.getChunks().size();
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            rtn.add(chunks);
          }
        }
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected ChunkType chunkType(final String chunkTypeName, final Collection<CommonTree> chunkTypeNodes, final Map<String, ChunkType> chunkTypes) {
    boolean _containsKey = chunkTypes.containsKey(chunkTypeName);
    if (_containsKey) {
      return chunkTypes.get(chunkTypeName);
    }
    final Function1<CommonTree, Boolean> _function = (CommonTree node) -> {
      return Boolean.valueOf(ASTSupport.getName(node).equals(chunkTypeName));
    };
    final CommonTree chunkTypeAST = IterableExtensions.<CommonTree>last(IterableExtensions.<CommonTree>filter(chunkTypeNodes, _function));
    final ChunkType chunkType = ModelFragmentFactory.eINSTANCE.createChunkType();
    final Tree parents = chunkTypeAST.getChild(1);
    String parentName = ((String) null);
    int _childCount = parents.getChildCount();
    boolean _greaterThan = (_childCount > 0);
    if (_greaterThan) {
      Tree _child = parents.getChild(0);
      parentName = ((CommonTree) _child).getText();
    }
    chunkType.setName(ASTSupport.getName(chunkTypeAST));
    if ((parentName != null)) {
      chunkType.setSuperType(this.chunkType(parentName, chunkTypeNodes, chunkTypes));
    }
    Tree _child_1 = chunkTypeAST.getChild(2);
    ArrayList<SimpleSlot> _simpleSlots = this.simpleSlots(((CommonTree) _child_1));
    for (final SimpleSlot slot : _simpleSlots) {
      chunkType.getSlots().add(slot);
    }
    final CommonTree parametersAST = ASTSupport.getFirstDescendantWithType(chunkTypeAST, JACTRBuilder.PARAMETERS);
    if (((parametersAST != null) && (parametersAST.getChildCount() != 0))) {
      chunkType.setParameters(this.parameters(parametersAST));
    }
    chunkTypes.put(chunkType.getName(), chunkType);
    return chunkType;
  }
  
  protected ArrayList<ChunkDef> chunks(final CommonTree chunkTypeTree) {
    ArrayList<ChunkDef> _xblockexpression = null;
    {
      final ArrayList<ChunkDef> rtn = new ArrayList<ChunkDef>();
      Collection<CommonTree> _allDescendantsWithType = ASTSupport.getAllDescendantsWithType(chunkTypeTree, JACTRBuilder.CHUNK);
      for (final CommonTree chunkAST : _allDescendantsWithType) {
        {
          final ChunkDef chunk = ModelFragmentFactory.eINSTANCE.createChunkDef();
          chunk.setName(this.safeName(ASTSupport.getName(chunkAST)));
          ArrayList<SimpleSlot> _simpleSlots = this.simpleSlots(chunkAST);
          for (final SimpleSlot slot : _simpleSlots) {
            chunk.getSlots().add(slot);
          }
          final CommonTree parametersAST = ASTSupport.getFirstDescendantWithType(chunkAST, JACTRBuilder.PARAMETERS);
          if (((parametersAST != null) && (parametersAST.getChildCount() != 0))) {
            chunk.setParameters(this.parameters(parametersAST));
          }
          rtn.add(chunk);
        }
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected ArrayList<SimpleSlot> simpleSlots(final CommonTree slotContainingTree) {
    ArrayList<SimpleSlot> _xblockexpression = null;
    {
      final ArrayList<SimpleSlot> rtn = new ArrayList<SimpleSlot>();
      Collection<CommonTree> _allDescendantsWithType = ASTSupport.getAllDescendantsWithType(slotContainingTree, JACTRBuilder.SLOT);
      for (final CommonTree slotAST : _allDescendantsWithType) {
        {
          final SimpleSlot slot = ModelFragmentFactory.eINSTANCE.createSimpleSlot();
          slot.setName(this.safeName(ASTSupport.getName(slotAST)));
          slot.setValue(ModelFragmentFactory.eINSTANCE.createValue());
          final CommonTree valueAST = ASTSupport.getLastDescendant(slotAST);
          final String valueString = valueAST.getText();
          int _type = valueAST.getType();
          boolean _equals = (_type == JACTRBuilder.NUMBER);
          if (_equals) {
            Value _value = slot.getValue();
            _value.setNumber(Double.valueOf(Double.parseDouble(valueString)));
          } else {
            int _type_1 = valueAST.getType();
            boolean _equals_1 = (_type_1 == JACTRBuilder.IDENTIFIER);
            if (_equals_1) {
              Value _value_1 = slot.getValue();
              _value_1.setName(this.safeName(valueString));
            } else {
              int _type_2 = valueAST.getType();
              boolean _equals_2 = (_type_2 == JACTRBuilder.VARIABLE);
              if (_equals_2) {
                Value _value_2 = slot.getValue();
                _value_2.setName(valueString);
              } else {
                int _type_3 = valueAST.getType();
                boolean _equals_3 = (_type_3 == JACTRBuilder.STRING);
                if (_equals_3) {
                  if (("true".equals(valueString) || "false".equals(valueString))) {
                    Value _value_3 = slot.getValue();
                    _value_3.setBoolean(Boolean.valueOf(Boolean.parseBoolean(valueString)));
                  } else {
                    Value _value_4 = slot.getValue();
                    _value_4.setString(valueString);
                  }
                }
              }
            }
          }
          rtn.add(slot);
        }
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected Buffers buffers(final CommonTree modelTree) {
    Buffers _xblockexpression = null;
    {
      final Buffers rtn = ModelFragmentFactory.eINSTANCE.createBuffers();
      Collection<CommonTree> _allDescendantsWithType = ASTSupport.getAllDescendantsWithType(modelTree, JACTRBuilder.BUFFER);
      for (final CommonTree bufferAST : _allDescendantsWithType) {
        {
          final Buffer buffer = ModelFragmentFactory.eINSTANCE.createBuffer();
          buffer.setName(ASTSupport.getName(bufferAST));
          final CommonTree parametersAST = ASTSupport.getFirstDescendantWithType(bufferAST, JACTRBuilder.PARAMETERS);
          if (((parametersAST != null) && (parametersAST.getChildCount() != 0))) {
            buffer.setParameters(this.parameters(parametersAST));
          }
          rtn.getBuffers().add(buffer);
        }
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected ArrayList<ModelModule> modules(final CommonTree modelTree) {
    ArrayList<ModelModule> _xblockexpression = null;
    {
      final ArrayList<ModelModule> rtn = new ArrayList<ModelModule>();
      Collection<CommonTree> _allDescendantsWithType = ASTSupport.getAllDescendantsWithType(modelTree, JACTRBuilder.MODULE);
      for (final CommonTree module : _allDescendantsWithType) {
        {
          final ModelModule moduleDesc = ModelFragmentFactory.eINSTANCE.createModelModule();
          moduleDesc.setModuleClass(ASTSupport.getFirstDescendantWithType(module, JACTRBuilder.CLASS_SPEC).getText());
          final CommonTree parametersAST = ASTSupport.getFirstDescendantWithType(module, JACTRBuilder.PARAMETERS);
          if ((parametersAST != null)) {
            moduleDesc.setParameters(this.parameters(parametersAST));
          }
          rtn.add(moduleDesc);
        }
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected ArrayList<ModelExtension> extensions(final CommonTree modelTree) {
    ArrayList<ModelExtension> _xblockexpression = null;
    {
      final ArrayList<ModelExtension> rtn = new ArrayList<ModelExtension>();
      Collection<CommonTree> _allDescendantsWithType = ASTSupport.getAllDescendantsWithType(modelTree, JACTRBuilder.EXTENSION);
      for (final CommonTree module : _allDescendantsWithType) {
        {
          final ModelExtension extDesc = ModelFragmentFactory.eINSTANCE.createModelExtension();
          extDesc.setExtensionClass(ASTSupport.getFirstDescendantWithType(module, JACTRBuilder.CLASS_SPEC).getText());
          final CommonTree parametersAST = ASTSupport.getFirstDescendantWithType(module, JACTRBuilder.PARAMETERS);
          if ((parametersAST != null)) {
            extDesc.setParameters(this.parameters(parametersAST));
          }
          rtn.add(extDesc);
        }
      }
      _xblockexpression = rtn;
    }
    return _xblockexpression;
  }
  
  protected ParametersBlock parameters(final CommonTree parameters) {
    ParametersBlock _xblockexpression = null;
    {
      final ParametersBlock pBlock = ModelFragmentFactory.eINSTANCE.createParametersBlock();
      Collection<CommonTree> _allDescendantsWithType = ASTSupport.getAllDescendantsWithType(parameters, JACTRBuilder.PARAMETER);
      for (final CommonTree parameterAST : _allDescendantsWithType) {
        {
          final String name = ASTSupport.getName(parameterAST);
          final String str = ASTSupport.getFirstDescendantWithType(parameterAST, JACTRBuilder.STRING).getText();
          final Parameter p = ModelFragmentFactory.eINSTANCE.createParameter();
          p.setName(name);
          p.setValue(ModelFragmentFactory.eINSTANCE.createValue());
          Value _value = p.getValue();
          _value.setString(str);
          pBlock.getParameter().add(p);
        }
      }
      _xblockexpression = pBlock;
    }
    return _xblockexpression;
  }
}
