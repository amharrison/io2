package org.jactr.io2.jactr.normalize;

import org.jactr.io2.jactr.modelFragment.ModelFragment;

/**
 * normalizes a ModelFragment by coping all its dependencies into itself
 * 
 * @author harrison
 */
public interface INormalizer
{
  ModelFragment normalize(ModelFragment modelFragment);
}
