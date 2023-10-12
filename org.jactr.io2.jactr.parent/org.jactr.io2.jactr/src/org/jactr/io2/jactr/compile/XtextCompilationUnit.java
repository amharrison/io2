package org.jactr.io2.jactr.compile;

import java.net.URI;

import org.eclipse.emf.ecore.resource.Resource;
import org.jactr.core.model.IModel;
import org.jactr.io2.compilation.ICompilationUnit;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.util.IO2Utilities;

public class XtextCompilationUnit implements ICompilationUnit
{

  ModelFragment _fragment;

  ModelFragment _normalized;

  URI           _uri;

  public XtextCompilationUnit(URI uri, ModelFragment fragment)
  {
    _uri = uri;
    _fragment = fragment;
  }

  @Override
  public URI getURI()
  {
    return _uri;
  }

  @Override
  public Object getAST()
  {
    return _fragment;
  }

  public Object getNormalizedAST()
  {
    if (_normalized == null) _normalized = IO2Utilities.normalize(_fragment).get();
    return _normalized;
  }

  protected void releaseNormalized()
  {
    if (_normalized != null)
    {
      Resource resource = _normalized.eResource();
      resource.getResourceSet().getResources().remove(resource);
      resource.getContents().remove(_normalized);
    }
    _normalized = null;
  }

  @Override
  public IModel build() throws Exception
  {
    if (_normalized != null)
    {
      IModel model = IO2Utilities.build(_normalized).get();
      releaseNormalized();
      return model;
    }
    else
      return IO2Utilities.build(_fragment).get();
  }

}
