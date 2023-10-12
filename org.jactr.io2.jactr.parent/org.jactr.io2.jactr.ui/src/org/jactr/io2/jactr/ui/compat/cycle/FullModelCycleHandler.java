package org.jactr.io2.jactr.ui.compat.cycle;

import java.util.Iterator;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.handlers.HandlerUtil;
import org.jactr.io2.jactr.ui.internal.JactrActivator;

public class FullModelCycleHandler extends AbstractHandler
{

  @Override
  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    ISelection selection = HandlerUtil.getActiveWorkbenchWindow(event)
        .getActivePage().getSelection();
    if (selection != null & selection instanceof IStructuredSelection)
    {
      IStructuredSelection strucSelection = (IStructuredSelection) selection;
      for (Iterator<Object> iterator = strucSelection.iterator(); iterator
          .hasNext();)
      {
        Object element = iterator.next();
        if (element instanceof IFile)
        {
          IFile source = (IFile) element;
          String newName = source.getName().substring(0,
              source.getName().indexOf(source.getFileExtension()) - 1);
          IFile destination = source.getParent()
              .getFile(new Path(newName + "Generated.jactr"));

          new FullModelCycle(source, destination, (t) -> {
            Status status = new Status(IStatus.ERROR, JactrActivator.PLUGIN_ID,
                t.getClass().getSimpleName() + ": " + t.getMessage(), t);

            JactrActivator.getInstance().getLog().log(status);
          }).run();
        }
      }
    }
    return null;
  }

}
