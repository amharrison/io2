package org.jactr.io2.jactr.value;

import org.eclipse.xtext.common.services.DefaultTerminalConverters;
import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverter;

public class ModelFragmentConverters extends DefaultTerminalConverters
{
  private CodeValueConverter _codeConverter = new CodeValueConverter();

  @ValueConverter(rule = "CODE")
  public IValueConverter<String> CODE()
  {
    return _codeConverter;
  }

}
