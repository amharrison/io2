package org.jactr.io2.jactr.value;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.nodemodel.INode;

public class CodeValueConverter implements IValueConverter<String>
{

  private final String PREFIX = "<[[";

  private final String SUFFIX = "]]>";

  @Override
  public String toValue(String string, INode node)
      throws ValueConverterException
  {
    int prefix = string.indexOf(PREFIX);
    int suffix = string.indexOf(SUFFIX);
    if (prefix == -1 || suffix == -1) throw new ValueConverterException(
        "Code blocks must be enclosed in " + PREFIX + " " + SUFFIX, node, null);

    return string.substring(prefix + PREFIX.length(), suffix);
  }

  @Override
  public String toString(String value) throws ValueConverterException
  {
    return PREFIX + value + SUFFIX;
  }

}
