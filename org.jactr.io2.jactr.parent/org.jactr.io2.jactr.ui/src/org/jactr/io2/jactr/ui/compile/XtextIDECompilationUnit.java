package org.jactr.io2.jactr.ui.compile;

import java.net.URI;

import org.jactr.core.model.IModel;
import org.jactr.io2.compilation.ICompilationUnit;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.ui.util.JactrUIUtilities;

public class XtextIDECompilationUnit implements ICompilationUnit
{

  ModelFragment                      _fragment;

  URI                                _uri;

  public XtextIDECompilationUnit(URI uri, ModelFragment fragment)
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

  @Override
  public IModel build() throws Exception
  {
    return JactrUIUtilities.build(_fragment).get();
  }

  @Override
  public Object getNormalizedAST()
  {// we ignore this
    return _fragment;
  }

}
