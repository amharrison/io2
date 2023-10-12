package org.jactr.io2.jactr.compile;

import java.net.URI;

import org.jactr.io2.compilation.ICompilationUnit;
import org.jactr.io2.compilation.ICompilationUnitProvider;
import org.jactr.io2.jactr.util.IO2Utilities;

public class XtextCompilationUnitProvider implements ICompilationUnitProvider
{

  public XtextCompilationUnitProvider()
  {
  }

  @Override
  public boolean handles(URI modelToLoad)
  {
    return modelToLoad.getPath().endsWith(".jactr");
  }

  @Override
  public ICompilationUnit get(URI modelToLoad) throws Exception
  {

    return new XtextCompilationUnit(modelToLoad,
        IO2Utilities.loadModel(modelToLoad).get());
  }

}
