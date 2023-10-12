package org.jactr.io2.jactr.comment;

import java.util.Optional;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EObject;

public class CommentUtilities
{

  static public void comment(EObject object, String unformattedComment)
  {
    object.eAdapters().add(new CommentAdapter(unformattedComment));
  }

  static public Optional<String> getComment(EObject object)
  {
    for(Adapter adapter : object.eAdapters())
      if(adapter instanceof CommentAdapter)
        return Optional.of(((CommentAdapter) adapter).getComment());
    return Optional.empty();
  }
}
