package org.jactr.io2.jactr.validation;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import com.google.inject.Inject;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.IResourceServiceProvider;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.jactr.io2.jactr.modelFragment.ChunkDef;
import org.jactr.io2.jactr.modelFragment.ChunkType;
import org.jactr.io2.jactr.modelFragment.Chunks;
import org.jactr.io2.jactr.modelFragment.Import;
import org.jactr.io2.jactr.modelFragment.IsaBlock;
import org.jactr.io2.jactr.modelFragment.Match;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.modelFragment.ModelFragmentPackage;
import org.jactr.io2.jactr.modelFragment.Overridable;
import org.jactr.io2.jactr.modelFragment.Production;
import org.jactr.io2.jactr.modelFragment.ProductionCondition;
import org.jactr.io2.jactr.modelFragment.Proxy;
import org.jactr.io2.jactr.modelFragment.Script;
import org.jactr.io2.jactr.modelFragment.SimpleSlot;
import org.jactr.io2.jactr.modelFragment.Value;

@SuppressWarnings("all")
public class ModelFragmentUtil {
  @Inject
  private ResourceDescriptionsProvider _descriptors;

  @Inject
  private IContainer.Manager _containerManager;

  @Inject
  private IQualifiedNameConverter _converter;

  @Inject
  private IResourceServiceProvider.Registry rspr;

  public void dump(final Resource resource) {
    this.printExportedObjects(resource);
    this.printVisibleResources(resource, this._descriptors.getResourceDescriptions(resource));
  }

  public void printExportedObjects(final Resource resource) {
    final IResourceServiceProvider resServiceProvider = this.rspr.getResourceServiceProvider(resource.getURI());
    final IResourceDescription.Manager manager = resServiceProvider.getResourceDescriptionManager();
    final IResourceDescription description = manager.getResourceDescription(resource);
    Iterable<IEObjectDescription> _exportedObjects = description.getExportedObjects();
    for (final IEObjectDescription eod : _exportedObjects) {
      InputOutput.<String>println(this._converter.toString(eod.getQualifiedName()));
    }
  }

  public void printVisibleResources(final Resource resource, final IResourceDescriptions index) {
    final IResourceServiceProvider resServiceProvider = this.rspr.getResourceServiceProvider(resource.getURI());
    final IResourceDescription.Manager manager = resServiceProvider.getResourceDescriptionManager();
    final IResourceDescription description = manager.getResourceDescription(resource);
    List<IContainer> _visibleContainers = this._containerManager.getVisibleContainers(description, index);
    for (final IContainer visibleContainer : _visibleContainers) {
      Iterable<IResourceDescription> _resourceDescriptions = visibleContainer.getResourceDescriptions();
      for (final IResourceDescription visibleResourceDesc : _resourceDescriptions) {
        InputOutput.<URI>println(visibleResourceDesc.getURI());
      }
    }
  }

  public HashSet<ChunkType> chunkTypeHierarchy(final ChunkType chunkType) {
    HashSet<ChunkType> _xblockexpression = null;
    {
      final HashSet<ChunkType> visited = new HashSet<ChunkType>();
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

  /**
   * all possible slots for type, set
   */
  public HashSet<String> allSlotNames(final ChunkType type) {
    HashSet<String> _xblockexpression = null;
    {
      ChunkType cType = type;
      final HashSet<String> set = new HashSet<String>();
      if ((type == null)) {
        return set;
      }
      final HashSet<ChunkType> visited = new HashSet<ChunkType>();
      while ((cType != null)) {
        {
          boolean _contains = visited.contains(cType);
          if (_contains) {
            return set;
          }
          visited.add(cType);
          EList<SimpleSlot> _slots = cType.getSlots();
          for (final SimpleSlot slot : _slots) {
            set.add(slot.getName());
          }
          cType = cType.getSuperType();
        }
      }
      _xblockexpression = set;
    }
    return _xblockexpression;
  }

  /**
   * all possible slot names for a given chunkDef.
   */
  public HashSet<String> allSlotNames(final ChunkDef chunk) {
    HashSet<String> _xblockexpression = null;
    {
      final HashSet<String> set = new HashSet<String>();
      Chunks chunks = this.findChunksForChunkDef(chunk);
      if ((chunks != null)) {
        set.addAll(this.allSlotNames(chunks.getType()));
      }
      _xblockexpression = set;
    }
    return _xblockexpression;
  }

  private Chunks findChunksForChunkDef(final ChunkDef chunkDef) {
    Object _xblockexpression = null;
    {
      if ((chunkDef == null)) {
        return ((Chunks) null);
      }
      final String chunkName = chunkDef.getName();
      Chunks _last = IterableExtensions.<Chunks>last(Iterables.<Chunks>filter(EcoreUtil2.getAllContainers(chunkDef), Chunks.class));
      Chunks localWrapper = ((Chunks) _last);
      if ((localWrapper != null)) {
        return localWrapper;
      }
      final EObject root = EcoreUtil.getRootContainer(chunkDef);
      List<Chunks> _allContentsOfType = EcoreUtil2.<Chunks>getAllContentsOfType(root, Chunks.class);
      for (final Chunks chunks : _allContentsOfType) {
        EList<ChunkDef> _chunks = chunks.getChunks();
        for (final ChunkDef chunk : _chunks) {
          boolean _equals = chunkName.equals(chunk.getName());
          if (_equals) {
            return chunks;
          }
        }
      }
      _xblockexpression = null;
    }
    return ((Chunks)_xblockexpression);
  }

  public HashSet<String> guessSlots(final EObject node) {
    HashSet<String> _xblockexpression = null;
    {
      final HashSet<String> set = new HashSet<String>();
      final Iterable<EObject> allContainers = EcoreUtil2.getAllContainers(node);
      final Consumer<Match> _function = (Match match) -> {
        IsaBlock _isa = match.getIsa();
        if ((_isa instanceof ChunkType)) {
          IsaBlock _isa_1 = match.getIsa();
          HashSet<String> _allSlotNames = this.allSlotNames(((ChunkType) _isa_1));
          for (final String slot : _allSlotNames) {
            set.add(slot);
          }
        }
        IsaBlock _isa_2 = match.getIsa();
        if ((_isa_2 instanceof ChunkDef)) {
          IsaBlock _isa_3 = match.getIsa();
          HashSet<String> _allSlotNames_1 = this.allSlotNames(((ChunkDef) _isa_3));
          for (final String slot_1 : _allSlotNames_1) {
            set.add(slot_1);
          }
        }
      };
      Iterables.<Match>filter(allContainers, Match.class).forEach(_function);
      _xblockexpression = set;
    }
    return _xblockexpression;
  }

  public HashSet<String> variables(final Production node) {
    HashSet<String> _xblockexpression = null;
    {
      final HashSet<String> set = new HashSet<String>();
      final Consumer<ProductionCondition> _function = (ProductionCondition condition) -> {
        if ((condition instanceof Match)) {
          String _name = ((Match)condition).getName().getName();
          String _plus = ("=" + _name);
          set.add(_plus);
        }
        final Consumer<Value> _function_1 = (Value value) -> {
          boolean _isVariable = this.isVariable(value);
          if (_isVariable) {
            set.add(value.getName());
          }
        };
        EcoreUtil2.<Value>getAllContentsOfType(condition, Value.class).forEach(_function_1);
      };
      EcoreUtil2.<ProductionCondition>getAllContentsOfType(node, ProductionCondition.class).forEach(_function);
      _xblockexpression = set;
    }
    return _xblockexpression;
  }

  public HashSet<String> variables(final Production node, final Match ignoring) {
    HashSet<String> _xblockexpression = null;
    {
      final HashSet<String> set = new HashSet<String>();
      final Function1<ProductionCondition, Boolean> _function = (ProductionCondition n) -> {
        return Boolean.valueOf((n != ignoring));
      };
      final Consumer<ProductionCondition> _function_1 = (ProductionCondition condition) -> {
        if ((condition instanceof Match)) {
          String _name = ((Match)condition).getName().getName();
          String _plus = ("=" + _name);
          set.add(_plus);
        }
        final Consumer<Value> _function_2 = (Value value) -> {
          boolean _isVariable = this.isVariable(value);
          if (_isVariable) {
            set.add(value.getName());
          }
        };
        EcoreUtil2.<Value>getAllContentsOfType(condition, Value.class).forEach(_function_2);
      };
      IterableExtensions.<ProductionCondition>filter(EcoreUtil2.<ProductionCondition>getAllContentsOfType(node, ProductionCondition.class), _function).forEach(_function_1);
      _xblockexpression = set;
    }
    return _xblockexpression;
  }

  public boolean couldHaveHiddenBindings(final Production node) {
    boolean _xblockexpression = false;
    {
      final Function1<ProductionCondition, Boolean> _function = (ProductionCondition condition) -> {
        return Boolean.valueOf(((condition instanceof Script) || (condition instanceof Proxy)));
      };
      int _size = IterableExtensions.size(IterableExtensions.<ProductionCondition>filter(node.getConditions(), _function));
      final boolean hasScriptOrProxy = (_size > 0);
      final String meta = "=meta";
      final Function1<ProductionCondition, Boolean> _function_1 = (ProductionCondition condition) -> {
        return Boolean.valueOf((condition instanceof Match));
      };
      final Function1<ProductionCondition, Match> _function_2 = (ProductionCondition c) -> {
        return ((Match) c);
      };
      final Function1<Match, Boolean> _function_3 = (Match match) -> {
        return Boolean.valueOf((meta.equals(match.getIsa().getName()) && (!meta.equals(match.getName().getName()))));
      };
      int _size_1 = IterableExtensions.size(IterableExtensions.<Match>filter(IterableExtensions.<ProductionCondition, Match>map(IterableExtensions.<ProductionCondition>filter(node.getConditions(), _function_1), _function_2), _function_3));
      final boolean hasMeta = (_size_1 > 0);
      _xblockexpression = (hasScriptOrProxy || hasMeta);
    }
    return _xblockexpression;
  }

  /**
   * methods for the exported resources
   */
  public IResourceDescription getResourceDescription(final EObject o) {
    IResourceDescription _xblockexpression = null;
    {
      final IResourceDescriptions index = this._descriptors.getResourceDescriptions(o.eResource());
      _xblockexpression = index.getResourceDescription(o.eResource().getURI());
    }
    return _xblockexpression;
  }

  public Iterable<IEObjectDescription> getExportedObjectDescriptions(final EObject o) {
    return this.getResourceDescription(o).getExportedObjects();
  }

  public Iterable<IEObjectDescription> getExportedObjectDescriptionsByType(final EObject o, final EClass type) {
    final Function1<IEObjectDescription, Boolean> _function = (IEObjectDescription object) -> {
      EClass _eClass = object.getEClass();
      return Boolean.valueOf(Objects.equal(_eClass, type));
    };
    return IterableExtensions.<IEObjectDescription>filter(this.getResourceDescription(o).getExportedObjects(), _function);
  }

  /**
   * visibility
   */
  public List<IContainer> getVisibileContainers(final EObject o) {
    List<IContainer> _xifexpression = null;
    if (((o.eResource() != null) && (o.eResource().getURI() != null))) {
      List<IContainer> _xblockexpression = null;
      {
        final IResourceServiceProvider resServiceProvider = this.rspr.getResourceServiceProvider(o.eResource().getURI());
        final IResourceDescription.Manager manager = resServiceProvider.getResourceDescriptionManager();
        final IResourceDescription description = manager.getResourceDescription(o.eResource());
        final IResourceDescriptions index = this._descriptors.getResourceDescriptions(o.eResource());
        _xblockexpression = this._containerManager.getVisibleContainers(description, index);
      }
      _xifexpression = _xblockexpression;
    } else {
      _xifexpression = Collections.<IContainer>emptyList();
    }
    return _xifexpression;
  }

  public Set<IEObjectDescription> getVisibileExportedObjectDescriptions(final EObject o, final EClass type) {
    final Function1<IContainer, Iterable<IEObjectDescription>> _function = (IContainer container) -> {
      return container.getExportedObjectsByType(type);
    };
    return IterableExtensions.<IEObjectDescription>toSet(Iterables.<IEObjectDescription>concat(ListExtensions.<IContainer, Iterable<IEObjectDescription>>map(this.getVisibileContainers(o), _function)));
  }

  public Function1<? super IEObjectDescription, Boolean> localAndImportFilter(final EObject root, final boolean excludeLocal) {
    Function1<? super IEObjectDescription, Boolean> _xblockexpression = null;
    {
      final TreeSet<String> startsWith = new TreeSet<String>();
      final Consumer<Import> _function = (Import imp) -> {
        String _importedNamespace = imp.getImportedNamespace();
        int _length = imp.getImportedNamespace().length();
        int _minus = (_length - 1);
        startsWith.add(_importedNamespace.substring(0, _minus));
      };
      EcoreUtil2.<Import>getAllContentsOfType(root, Import.class).forEach(_function);
      if ((!excludeLocal)) {
        if ((root instanceof ModelFragment)) {
          String _name = ((ModelFragment)root).getPackage().getName();
          String _plus = (_name + ".");
          startsWith.add(_plus);
        }
      }
      final Function1<IEObjectDescription, Boolean> _function_1 = (IEObjectDescription desc) -> {
        return Boolean.valueOf(this.startsWith(this._converter.toString(desc.getQualifiedName()), startsWith));
      };
      final Function1<? super IEObjectDescription, Boolean> handler = _function_1;
      _xblockexpression = handler;
    }
    return _xblockexpression;
  }

  public boolean startsWith(final String string, final Set<String> startsWith) {
    for (final String start : startsWith) {
      boolean _startsWith = string.startsWith(start);
      if (_startsWith) {
        return true;
      }
    }
    return false;
  }

  public Map<String, IEObjectDescription> getRawVisibleObjectDescriptionsByType(final EObject o, final EClass type) {
    final Set<IEObjectDescription> allVisible = this.getVisibileExportedObjectDescriptions(o, type);
    final Iterable<IEObjectDescription> exported = this.getExportedObjectDescriptionsByType(o, type);
    final Set<IEObjectDescription> difference = IterableExtensions.<IEObjectDescription>toSet(allVisible);
    difference.removeAll(IterableExtensions.<IEObjectDescription>toSet(exported));
    final Function1<IEObjectDescription, String> _function = (IEObjectDescription desc) -> {
      return desc.getName().getLastSegment();
    };
    return IterableExtensions.<String, IEObjectDescription>toMap(difference, _function);
  }

  /**
   * global scope means everything in the project is visible,
   * we filter these results based on the import directive of the
   * resource
   */
  public Map<String, IEObjectDescription> getVisibleEObjectDescriptionsByType(final EObject o, final EClass type) {
    final Set<IEObjectDescription> allVisible = this.getVisibileExportedObjectDescriptions(o, type);
    final Iterable<IEObjectDescription> exported = this.getExportedObjectDescriptionsByType(o, type);
    final Set<IEObjectDescription> difference = IterableExtensions.<IEObjectDescription>toSet(allVisible);
    difference.removeAll(IterableExtensions.<IEObjectDescription>toSet(exported));
    final Function1<IEObjectDescription, String> _function = (IEObjectDescription desc) -> {
      return desc.getName().getLastSegment();
    };
    return IterableExtensions.<String, IEObjectDescription>toMap(IterableExtensions.<IEObjectDescription>filter(difference, this.localAndImportFilter(EcoreUtil.getRootContainer(o), true)), _function);
  }

  public LinkedListMultimap<String, IEObjectDescription> packageSymbolTable(final EObject o) {
    LinkedListMultimap<String, IEObjectDescription> _xblockexpression = null;
    {
      final LinkedListMultimap<String, IEObjectDescription> map = LinkedListMultimap.<String, IEObjectDescription>create();
      final Set<IEObjectDescription> types = this.getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.PACKAGE_DECLARATION);
      final Consumer<IEObjectDescription> _function = (IEObjectDescription type) -> {
        map.put(type.getQualifiedName().toString(), type);
      };
      types.forEach(_function);
      _xblockexpression = map;
    }
    return _xblockexpression;
  }

  public LinkedListMultimap<String, IEObjectDescription> chunkTypeSymbolTable(final EObject o) {
    LinkedListMultimap<String, IEObjectDescription> _xblockexpression = null;
    {
      final LinkedListMultimap<String, IEObjectDescription> map = LinkedListMultimap.<String, IEObjectDescription>create();
      final Set<IEObjectDescription> types = this.getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.CHUNK_TYPE);
      final Consumer<IEObjectDescription> _function = (IEObjectDescription type) -> {
        map.put(type.getQualifiedName().getLastSegment(), type);
      };
      IterableExtensions.<IEObjectDescription>filter(types, this.localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach(_function);
      _xblockexpression = map;
    }
    return _xblockexpression;
  }

  public LinkedListMultimap<String, IEObjectDescription> chunkSymbolTable(final EObject o) {
    LinkedListMultimap<String, IEObjectDescription> _xblockexpression = null;
    {
      final LinkedListMultimap<String, IEObjectDescription> map = LinkedListMultimap.<String, IEObjectDescription>create();
      Set<IEObjectDescription> chunks = this.getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.CHUNK_DEF);
      final Consumer<IEObjectDescription> _function = (IEObjectDescription chunk) -> {
        map.put(chunk.getQualifiedName().getLastSegment(), chunk);
      };
      IterableExtensions.<IEObjectDescription>filter(chunks, this.localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach(_function);
      _xblockexpression = map;
    }
    return _xblockexpression;
  }

  public LinkedListMultimap<String, IEObjectDescription> bufferSymbolTable(final EObject o) {
    LinkedListMultimap<String, IEObjectDescription> _xblockexpression = null;
    {
      final LinkedListMultimap<String, IEObjectDescription> map = LinkedListMultimap.<String, IEObjectDescription>create();
      final Set<IEObjectDescription> buffers = this.getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.BUFFER);
      final Consumer<IEObjectDescription> _function = (IEObjectDescription buffer) -> {
        map.put(buffer.getQualifiedName().getLastSegment(), buffer);
      };
      IterableExtensions.<IEObjectDescription>filter(buffers, this.localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach(_function);
      _xblockexpression = map;
    }
    return _xblockexpression;
  }

  public LinkedListMultimap<String, IEObjectDescription> productionSymbolTable(final EObject o) {
    LinkedListMultimap<String, IEObjectDescription> _xblockexpression = null;
    {
      final LinkedListMultimap<String, IEObjectDescription> map = LinkedListMultimap.<String, IEObjectDescription>create();
      final Set<IEObjectDescription> productions = this.getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.PRODUCTION);
      final Consumer<IEObjectDescription> _function = (IEObjectDescription production) -> {
        map.put(production.getQualifiedName().getLastSegment(), production);
      };
      IterableExtensions.<IEObjectDescription>filter(productions, this.localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach(_function);
      _xblockexpression = map;
    }
    return _xblockexpression;
  }

  public HashMap<String, IEObjectDescription> fullSymbolTable(final EObject o) {
    HashMap<String, IEObjectDescription> _xblockexpression = null;
    {
      final HashMap<String, IEObjectDescription> map = new HashMap<String, IEObjectDescription>();
      Set<IEObjectDescription> chunks = this.getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.CHUNK_DEF);
      final Set<IEObjectDescription> types = this.getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.CHUNK_TYPE);
      final Set<IEObjectDescription> buffers = this.getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.BUFFER);
      final Set<IEObjectDescription> productions = this.getVisibileExportedObjectDescriptions(o, ModelFragmentPackage.Literals.PRODUCTION);
      final Consumer<IEObjectDescription> _function = (IEObjectDescription buffer) -> {
        map.putIfAbsent(buffer.getQualifiedName().getLastSegment(), buffer);
      };
      IterableExtensions.<IEObjectDescription>filter(buffers, this.localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach(_function);
      final Consumer<IEObjectDescription> _function_1 = (IEObjectDescription type) -> {
        map.putIfAbsent(type.getQualifiedName().getLastSegment(), type);
      };
      IterableExtensions.<IEObjectDescription>filter(types, this.localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach(_function_1);
      final Consumer<IEObjectDescription> _function_2 = (IEObjectDescription production) -> {
        map.putIfAbsent(production.getQualifiedName().getLastSegment(), production);
      };
      IterableExtensions.<IEObjectDescription>filter(productions, this.localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach(_function_2);
      final Consumer<IEObjectDescription> _function_3 = (IEObjectDescription chunk) -> {
        map.put(chunk.getQualifiedName().getLastSegment(), chunk);
      };
      IterableExtensions.<IEObjectDescription>filter(chunks, this.localAndImportFilter(EcoreUtil.getRootContainer(o), false)).forEach(_function_3);
      _xblockexpression = map;
    }
    return _xblockexpression;
  }

  private EObject toObject(final IEObjectDescription descriptor, final ResourceSet resourceSet) {
    return EcoreUtil.resolve(descriptor.getEObjectOrProxy(), resourceSet);
  }

  /**
   * zip through the multimap, for each list > 1, select the one that is local overridden
   * for each selected, resolve
   */
  public Map<String, EObject> resolveSymbolTable(final Resource resource, final Multimap<String, IEObjectDescription> symbols) {
    TreeMap<String, EObject> _xblockexpression = null;
    {
      final TreeMap<String, EObject> newTable = new TreeMap<String, EObject>();
      Set<String> _keySet = symbols.keySet();
      for (final String symbolName : _keySet) {
        {
          final Collection<IEObjectDescription> allDefs = symbols.get(symbolName);
          EObject resolved = ((EObject) null);
          int _size = allDefs.size();
          boolean _equals = (_size == 0);
          if (_equals) {
            throw new RuntimeException((("Failed to resolve " + symbolName) + ". No definition found?"));
          }
          int _size_1 = allDefs.size();
          boolean _equals_1 = (_size_1 == 1);
          if (_equals_1) {
            resolved = this.toObject(IterableExtensions.<IEObjectDescription>last(allDefs), resource.getResourceSet());
          } else {
            int _size_2 = allDefs.size();
            boolean _greaterThan = (_size_2 > 1);
            if (_greaterThan) {
              final Function1<IEObjectDescription, Overridable> _function = (IEObjectDescription d) -> {
                EObject _object = this.toObject(d, resource.getResourceSet());
                return ((Overridable) _object);
              };
              final Function1<Overridable, Boolean> _function_1 = (Overridable c) -> {
                return Boolean.valueOf(c.isOverride());
              };
              Iterable<Overridable> allOverriden = IterableExtensions.<Overridable>filter(IterableExtensions.<IEObjectDescription, Overridable>map(allDefs, _function), _function_1);
              int _size_3 = IterableExtensions.size(allOverriden);
              boolean _equals_2 = (_size_3 == 0);
              if (_equals_2) {
                throw new RuntimeException((("Failed to resolve " + symbolName) + ". Multiple definitions, none overridden."));
              } else {
                int _size_4 = IterableExtensions.size(allOverriden);
                boolean _equals_3 = (_size_4 == 1);
                if (_equals_3) {
                  resolved = IterableExtensions.<Overridable>last(allOverriden);
                } else {
                  int _size_5 = IterableExtensions.size(allOverriden);
                  boolean _greaterThan_1 = (_size_5 > 1);
                  if (_greaterThan_1) {
                    final Function1<Overridable, Boolean> _function_2 = (Overridable c) -> {
                      Resource _eResource = c.eResource();
                      return Boolean.valueOf((_eResource == resource));
                    };
                    allOverriden = IterableExtensions.<Overridable>filter(allOverriden, _function_2);
                    int _size_6 = IterableExtensions.size(allOverriden);
                    switch (_size_6) {
                      case 0:
                        throw new RuntimeException(
                          (("Failed to resolve " + symbolName) + ". Multiple overridden definitions, but none are local."));
                      case 1:
                        resolved = IterableExtensions.<Overridable>last(allOverriden);
                        break;
                      default:
                        throw new RuntimeException(
                          (("Failed to resolve " + symbolName) + ". Multiple local overridden definitions, cannot select."));
                    }
                  }
                }
              }
            }
          }
          newTable.put(symbolName, resolved);
        }
      }
      _xblockexpression = newTable;
    }
    return _xblockexpression;
  }

  public Map<String, EObject> resolveSymbolTableAny(final Resource resource, final Multimap<String, IEObjectDescription> symbols) {
    TreeMap<String, EObject> _xblockexpression = null;
    {
      final TreeMap<String, EObject> newTable = new TreeMap<String, EObject>();
      Set<String> _keySet = symbols.keySet();
      for (final String symbolName : _keySet) {
        {
          final Collection<IEObjectDescription> allDefs = symbols.get(symbolName);
          EObject resolved = ((EObject) null);
          int _size = allDefs.size();
          boolean _equals = (_size == 0);
          if (_equals) {
            throw new RuntimeException((("Failed to resolve " + symbolName) + ". No definition found?"));
          }
          int _size_1 = allDefs.size();
          boolean _equals_1 = (_size_1 == 1);
          if (_equals_1) {
            resolved = this.toObject(IterableExtensions.<IEObjectDescription>last(allDefs), resource.getResourceSet());
          } else {
            int _size_2 = allDefs.size();
            boolean _greaterThan = (_size_2 > 1);
            if (_greaterThan) {
              resolved = this.toObject(IterableExtensions.<IEObjectDescription>last(allDefs), resource.getResourceSet());
            }
          }
          newTable.put(symbolName, resolved);
        }
      }
      _xblockexpression = newTable;
    }
    return _xblockexpression;
  }

  public boolean isString(final Value value) {
    String _string = value.getString();
    return (_string != null);
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

  public boolean isNull(final Value value) {
    String _name = value.getName();
    boolean _tripleEquals = (_name == null);
    if (_tripleEquals) {
      return false;
    }
    return value.getName().equals("null");
  }

  public boolean isNumber(final Value value) {
    Double _number = value.getNumber();
    return (_number != null);
  }

  public boolean isBoolean(final Value value) {
    Boolean _boolean = value.getBoolean();
    return (_boolean != null);
  }

  public boolean isID(final Value value) {
    String _name = value.getName();
    boolean _tripleEquals = (_name == null);
    if (_tripleEquals) {
      return false;
    }
    return ((!this.isVariable(value)) && (!this.isNull(value)));
  }
}
