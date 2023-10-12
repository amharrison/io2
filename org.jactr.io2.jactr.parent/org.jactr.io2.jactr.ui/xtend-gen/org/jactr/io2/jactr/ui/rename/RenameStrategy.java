package org.jactr.io2.jactr.ui.rename;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.ide.refactoring.IRenameStrategy2;
import org.eclipse.xtext.ide.refactoring.RenameChange;
import org.eclipse.xtext.ide.refactoring.RenameContext;
import org.eclipse.xtext.ide.serializer.IChangeSerializer;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.jactr.io2.jactr.modelFragment.Add;
import org.jactr.io2.jactr.modelFragment.ChunkDef;
import org.jactr.io2.jactr.modelFragment.ChunkType;
import org.jactr.io2.jactr.modelFragment.Chunks;
import org.jactr.io2.jactr.modelFragment.ConditionalSlot;
import org.jactr.io2.jactr.modelFragment.IsaBlock;
import org.jactr.io2.jactr.modelFragment.Match;
import org.jactr.io2.jactr.modelFragment.Production;
import org.jactr.io2.jactr.modelFragment.ProductionAction;
import org.jactr.io2.jactr.modelFragment.ProductionCondition;
import org.jactr.io2.jactr.modelFragment.SimpleSlot;
import org.jactr.io2.jactr.modelFragment.Value;

@SuppressWarnings("all")
public class RenameStrategy extends IRenameStrategy2.DefaultImpl {
  @Override
  public void applyRename(final RenameContext context) {
    final Function1<RenameChange, Boolean> _function = (RenameChange change) -> {
      return Boolean.valueOf(this.canHandle(change));
    };
    final Consumer<RenameChange> _function_1 = (RenameChange change) -> {
      EObject object = context.getResourceSet().getEObject(change.getTargetURI(), true);
      if ((object instanceof Value)) {
        boolean _isVariable = this.isVariable(((Value)object));
        if (_isVariable) {
          final Function1<EObject, Boolean> _function_2 = (EObject e) -> {
            return Boolean.valueOf((e instanceof Production));
          };
          final Iterable<EObject> productions = IterableExtensions.<EObject>filter(EcoreUtil2.getAllContainers(object), _function_2);
          int _size = IterableExtensions.size(productions);
          boolean _greaterThan = (_size > 0);
          if (_greaterThan) {
            final Consumer<EObject> _function_3 = (EObject child) -> {
              final IChangeSerializer.IModification<EObject> _function_4 = (EObject it) -> {
                this.doRename(child, change, context);
              };
              context.addModification(change, _function_4);
            };
            this.allVariablesMatchingName(IterableExtensions.<EObject>head(productions), ((Value)object).getName()).forEach(_function_3);
          }
        }
      }
      if ((object instanceof ChunkDef)) {
        final Consumer<EObject> _function_4 = (EObject child) -> {
          final IChangeSerializer.IModification<EObject> _function_5 = (EObject it) -> {
            this.doRename(child, change, context);
          };
          context.addModification(change, _function_5);
        };
        this.allValuesMatchingName(object, ((ChunkDef)object).getName()).forEach(_function_4);
      }
      if ((object instanceof SimpleSlot)) {
        final EObject container = ((SimpleSlot)object).eContainer();
        final String slotName = ((SimpleSlot)object).getName();
        if ((container instanceof ChunkType)) {
          final Set<ChunkType> hierarchy = this.chunkTypeDescendents(((ChunkType)container));
          final Consumer<EObject> _function_5 = (EObject production) -> {
            final Consumer<EObject> _function_6 = (EObject child) -> {
              final IChangeSerializer.IModification<EObject> _function_7 = (EObject it) -> {
                this.doRename(child, change, context);
              };
              context.addModification(change, _function_7);
            };
            this.allSlotsMatchingNames(production, slotName).forEach(_function_6);
          };
          this.allProductionsAgainst(object, hierarchy).forEach(_function_5);
          final Consumer<ChunkDef> _function_6 = (ChunkDef chunk) -> {
            final Consumer<EObject> _function_7 = (EObject child) -> {
              final IChangeSerializer.IModification<EObject> _function_8 = (EObject it) -> {
                this.doRename(child, change, context);
              };
              context.addModification(change, _function_8);
            };
            this.allSlotsMatchingNames(chunk, slotName).forEach(_function_7);
          };
          this.allChunksOf(container, hierarchy).forEach(_function_6);
        }
      }
      final IChangeSerializer.IModification<EObject> _function_7 = (EObject it) -> {
        this.doRename(it, change, context);
      };
      context.addModification(change, _function_7);
    };
    IterableExtensions.filter(context.getChanges(), _function).forEach(_function_1);
  }
  
  public List<EObject> allVariablesMatchingName(final EObject production, final String name) {
    ArrayList<EObject> _xblockexpression = null;
    {
      final ArrayList<EObject> set = Lists.<EObject>newArrayList();
      final Consumer<Value> _function = (Value value) -> {
        if ((this.isVariable(value) && value.getName().equals(name))) {
          set.add(value);
        }
      };
      EcoreUtil2.<Value>getAllContentsOfType(production, Value.class).forEach(_function);
      final Consumer<IsaBlock> _function_1 = (IsaBlock value) -> {
        boolean _equals = name.equals(value.getName());
        if (_equals) {
          set.add(value);
        }
      };
      EcoreUtil2.<IsaBlock>getAllContentsOfType(production, IsaBlock.class).forEach(_function_1);
      _xblockexpression = set;
    }
    return _xblockexpression;
  }
  
  public List<EObject> allValuesMatchingName(final EObject root, final String name) {
    ArrayList<EObject> _xblockexpression = null;
    {
      final ArrayList<EObject> set = Lists.<EObject>newArrayList();
      final Consumer<Value> _function = (Value value) -> {
        if ((this.isID(value) && value.getName().equals(name))) {
          set.add(value);
        }
      };
      EcoreUtil2.<Value>getAllContentsOfType(EcoreUtil2.getRootContainer(root, false), Value.class).forEach(_function);
      _xblockexpression = set;
    }
    return _xblockexpression;
  }
  
  public Collection<ChunkDef> allChunksOf(final EObject root, final Set<ChunkType> hierarchy) {
    HashSet<ChunkDef> _xblockexpression = null;
    {
      final HashSet<ChunkDef> set = new HashSet<ChunkDef>();
      final Consumer<Chunks> _function = (Chunks chunks) -> {
        boolean _contains = hierarchy.contains(chunks.getType());
        if (_contains) {
          set.addAll(chunks.getChunks());
        }
      };
      EcoreUtil2.<Chunks>getAllContentsOfType(EcoreUtil2.getRootContainer(root, false), Chunks.class).forEach(_function);
      _xblockexpression = set;
    }
    return _xblockexpression;
  }
  
  public Collection<EObject> allProductionsAgainst(final EObject root, final Set<ChunkType> hierarchy) {
    HashSet<EObject> _xblockexpression = null;
    {
      final HashSet<EObject> set = new HashSet<EObject>();
      final Consumer<Production> _function = (Production production) -> {
        final Function1<ProductionCondition, Boolean> _function_1 = (ProductionCondition c) -> {
          return Boolean.valueOf((c instanceof Match));
        };
        final Function1<ProductionCondition, Match> _function_2 = (ProductionCondition match) -> {
          return ((Match) match);
        };
        final Consumer<Match> _function_3 = (Match match) -> {
          boolean _contains = hierarchy.contains(match.getIsa().getType());
          if (_contains) {
            set.add(production);
          }
        };
        IterableExtensions.<ProductionCondition, Match>map(IterableExtensions.<ProductionCondition>filter(production.getConditions(), _function_1), _function_2).forEach(_function_3);
        boolean _contains = set.contains(production);
        boolean _not = (!_contains);
        if (_not) {
          final Function1<ProductionAction, Boolean> _function_4 = (ProductionAction a) -> {
            return Boolean.valueOf((a instanceof Add));
          };
          final Function1<ProductionAction, Add> _function_5 = (ProductionAction add) -> {
            return ((Add) add);
          };
          final Consumer<Add> _function_6 = (Add add) -> {
            boolean _contains_1 = hierarchy.contains(add.getIsa().getType());
            if (_contains_1) {
              set.add(production);
            }
          };
          IterableExtensions.<ProductionAction, Add>map(IterableExtensions.<ProductionAction>filter(production.getActions(), _function_4), _function_5).forEach(_function_6);
        }
      };
      EcoreUtil2.<Production>getAllContentsOfType(EcoreUtil2.getRootContainer(root, false), Production.class).forEach(_function);
      _xblockexpression = set;
    }
    return _xblockexpression;
  }
  
  public List<EObject> allSlotsMatchingNames(final EObject root, final String name) {
    ArrayList<EObject> _xblockexpression = null;
    {
      final ArrayList<EObject> set = Lists.<EObject>newArrayList();
      final Consumer<SimpleSlot> _function = (SimpleSlot value) -> {
        boolean _equals = value.getName().equals(name);
        if (_equals) {
          set.add(value);
        }
      };
      EcoreUtil2.<SimpleSlot>getAllContentsOfType(root, SimpleSlot.class).forEach(_function);
      final Consumer<ConditionalSlot> _function_1 = (ConditionalSlot value) -> {
        boolean _equals = value.getName().equals(name);
        if (_equals) {
          set.add(value);
        }
      };
      EcoreUtil2.<ConditionalSlot>getAllContentsOfType(root, ConditionalSlot.class).forEach(_function_1);
      _xblockexpression = set;
    }
    return _xblockexpression;
  }
  
  public boolean isVariable(final Value value) {
    boolean _xblockexpression = false;
    {
      String _name = value.getName();
      boolean _tripleEquals = (_name == null);
      if (_tripleEquals) {
        return false;
      }
      boolean _startsWith = value.getName().startsWith("=");
      if (_startsWith) {
        return true;
      }
      _xblockexpression = false;
    }
    return _xblockexpression;
  }
  
  public boolean isID(final Value value) {
    String _name = value.getName();
    return (_name != null);
  }
  
  public HashSet<ChunkType> chunkTypeHierarchy(final ChunkType chunkType) {
    HashSet<ChunkType> _xblockexpression = null;
    {
      final HashSet<ChunkType> visited = new HashSet<ChunkType>();
      visited.add(chunkType);
      ChunkType current = chunkType.getSuperType();
      while (((current != null) && (!visited.contains(current)))) {
        {
          visited.add(current);
          current = current.getSuperType();
        }
      }
      _xblockexpression = visited;
    }
    return _xblockexpression;
  }
  
  public Set<ChunkType> chunkTypeDescendents(final ChunkType chunkType) {
    HashSet<ChunkType> _xblockexpression = null;
    {
      final HashSet<ChunkType> potentialParents = new HashSet<ChunkType>();
      potentialParents.add(chunkType);
      final Consumer<ChunkType> _function = (ChunkType ct) -> {
        ChunkType _superType = ct.getSuperType();
        boolean _equals = Objects.equal(_superType, chunkType);
        if (_equals) {
          potentialParents.addAll(this.chunkTypeDescendents(ct));
        }
      };
      EcoreUtil2.<ChunkType>getAllContentsOfType(EcoreUtil2.getRootContainer(chunkType, false), ChunkType.class).forEach(_function);
      _xblockexpression = potentialParents;
    }
    return _xblockexpression;
  }
}
