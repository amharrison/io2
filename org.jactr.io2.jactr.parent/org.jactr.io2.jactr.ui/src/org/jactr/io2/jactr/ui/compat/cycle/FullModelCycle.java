package org.jactr.io2.jactr.ui.compat.cycle;

import java.util.function.Consumer;

import org.eclipse.core.resources.IFile;
import org.jactr.core.model.IModel;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.ui.util.JactrUIUtilities;
import org.jactr.io2.jactr.util.IO2Utilities;

public class FullModelCycle implements Runnable
{

  private final IFile               _fileToConvert;

  private final IFile               _destination;

  private final Consumer<Throwable> _errorHandler;

  public FullModelCycle(IFile source, IFile destination,
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
      ModelFragment modelFragment = IO2Utilities
          .loadModel(_fileToConvert.getLocationURI()).get();

      IModel model = JactrUIUtilities.build(modelFragment).get();

      ModelFragment newModelFragment = JactrUIUtilities.convert(model).get();

      JactrUIUtilities.saveModel(newModelFragment,
          _destination.getLocationURI());

      _destination.refreshLocal(0,
          new org.eclipse.core.runtime.NullProgressMonitor());
    }
    catch (Exception e)
    {
      _errorHandler.accept(e);
    }
  }


}
