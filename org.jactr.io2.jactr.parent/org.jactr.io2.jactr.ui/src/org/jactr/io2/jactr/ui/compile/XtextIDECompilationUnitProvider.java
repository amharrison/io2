package org.jactr.io2.jactr.ui.compile;

import java.net.URI;

import org.jactr.io2.compilation.ICompilationUnit;
import org.jactr.io2.compilation.ICompilationUnitProvider;
import org.jactr.io2.jactr.ui.util.JactrUIUtilities;

public class XtextIDECompilationUnitProvider implements ICompilationUnitProvider
{

  public XtextIDECompilationUnitProvider()
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
    return new XtextIDECompilationUnit(modelToLoad,
        JactrUIUtilities.loadModel(modelToLoad));
  }

}
