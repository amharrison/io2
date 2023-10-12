package org.jactr.io2.jactr.comment;

import org.eclipse.emf.common.notify.impl.AdapterImpl;

public class CommentAdapter extends AdapterImpl
{
  private final String _comment;

  public CommentAdapter(String comment)
  {
    _comment = comment;
  }

  /**
   * get unformatted comment content
   * 
   * @return
   */
  public String getComment()
  {
    return _comment;
  }
}
