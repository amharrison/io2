package org.jactr.io2.jactr.ui.compat.convert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import org.antlr.runtime.tree.CommonTree;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.jactr.io.compiler.DefaultCompiler;
import org.jactr.io.parser.IModelParser;
import org.jactr.io.parser.ModelParserFactory;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.util.IO2Utilities;

public class ConvertModel implements Runnable
{

  private final IFile               _fileToConvert;

  private final IFile               _destination;

  private final Consumer<Throwable> _errorHandler;

  public ConvertModel(IFile source, IFile destination,
      Consumer<Throwable> errorHandler)
  {
    _fileToConvert = source;
    _destination = destination;
    _errorHandler = errorHandler;
  }

  @Override
  public void run()
  {
    if (_fileToConvert.exists()) try
    {
      // force the extension change
      ModelParserFactory.addParser("jactrx",
          org.jactr.io.antlr3.parser.xml.JACTRModelParser.class);

      IModelParser mp = ModelParserFactory
          .getModelParser(_fileToConvert.getLocationURI().toURL());

      if (mp.parse())
      {
        // parse
        CommonTree modelTree = mp.getDocumentTree();
        compile(modelTree);

        ModelFragment model = new ASTToModelFragment().convert(modelTree);

        IO2Utilities.saveModel(model, _destination.getLocationURI());

        _destination.refreshLocal(1, new NullProgressMonitor());
      }
      else
      {
        if (mp.getParseErrors().size() > 0)
          throw mp.getParseErrors().iterator().next();

        throw new RuntimeException("Failed to parse " + _fileToConvert.getName()
            + ". Unknown parse error.");
      }

    }
    catch (Exception e)
    {
      _errorHandler.accept(e);
    }
  }

  protected void compile(CommonTree modelTree) throws Exception
  {
    // compile
    Collection<Exception> errs = new ArrayList<Exception>();
    Collection<Exception> warn = new ArrayList<Exception>();
    Collection<Exception> info = new ArrayList<Exception>();

    DefaultCompiler compiler = new DefaultCompiler();
    compiler.compile(modelTree, info, warn, errs);

    if (errs.size() > 0) throw errs.iterator().next();

  }

}
