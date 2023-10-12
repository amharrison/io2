package org.jactr.io2.jactr.source;

import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;

public interface ISourceLocator
{

  public Map<String, SourceLocation> locateSourceElements(EObject root);

  static public class SourceLocation
  {
    public final URI  _uri;

    public final long _line;

    public SourceLocation(long line, URI uri)
    {

      _line = line;
      _uri = uri;
    }
  }
}
