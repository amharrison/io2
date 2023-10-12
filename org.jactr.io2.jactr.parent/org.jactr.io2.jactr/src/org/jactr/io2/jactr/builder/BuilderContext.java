package org.jactr.io2.jactr.builder;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.eclipse.xtext.resource.IEObjectDescription;
import org.jactr.core.buffer.IActivationBuffer;
import org.jactr.core.chunk.IChunk;
import org.jactr.core.chunk.ISymbolicChunk;
import org.jactr.core.chunktype.IChunkType;
import org.jactr.core.model.IModel;

import com.google.common.collect.Multimap;

public class BuilderContext
{

  public IModel                                model;

  public Multimap<String, IEObjectDescription> variableTable;

  private Map<String, IChunkType>              _chunkTypes   = new TreeMap<>();

  private Map<IChunkType, Map<String, IChunk>> _chunksByType = new HashMap<>();

  private Map<String, IChunk>                  _chunksByName = new TreeMap<>();

  /**
   * checks chunks, types, buffers, productions in that order
   * 
   * @param name
   * @return
   */
  public Object resolve(String name)
  {
    if (_chunksByName.containsKey(name)) return _chunksByName.get(name);
    if (_chunkTypes.containsKey(name)) return _chunkTypes.get(name);
    IActivationBuffer buffer = model.getActivationBuffer(name);
    if (buffer != null) return buffer;
    return null;
  }

  public void encodeChunkType(String name)
  {
    IChunkType ct = getChunkType(name);
    _chunkTypes.remove(name);
    try
    {
      ct = model.getDeclarativeModule().addChunkType(ct).get();
      add(ct);
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public void add(IChunkType chunkType)
  {
    _chunkTypes.put(chunkType.getSymbolicChunkType().getName(), chunkType);
  }

  public IChunkType getChunkType(String name)
  {
    return _chunkTypes.get(name);
  }

  public IChunk encodeChunk(String name)
  {
    IChunk ct = getChunk(name);
    _chunksByType.get(ct.getSymbolicChunk().getChunkType()).remove(name);
    _chunksByName.remove(name);
    try
    {
      IChunk nct = model.getDeclarativeModule().addChunk(ct).get();
      add(nct);

      /*
       * since we merged, the name is now old.. but lets keep the mapping
       */
      if (nct != ct) // intentional strict equality
      {
        System.err.println(
            name + " was merged with " + nct.getSymbolicChunk().getName());
        _chunksByName.put(name, nct);
      }

      return nct;
    }
    catch (Exception e)
    {
      throw new RuntimeException(e);
    }
  }

  public void add(IChunk chunk)
  {
    ISymbolicChunk sc = chunk.getSymbolicChunk();
    _chunksByType.computeIfAbsent(sc.getChunkType(), k -> new HashMap<>())
        .put(sc.getName(), chunk);
    _chunksByName.put(sc.getName(), chunk);
  }

  public IChunk getChunk(String name)
  {
    if (name == null) throw new IllegalStateException("null name for chunk?");
    return _chunksByName.get(name);
  }

  public Set<String> knownChunks()
  {
    return _chunksByName.keySet();
  }

}
