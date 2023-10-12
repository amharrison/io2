package org.jactr.io2.jactr.ui.contentassist;

import java.util.Arrays;
import java.util.Collection;

import org.jactr.core.production.ISubsymbolicProduction;
import org.jactr.core.production.six.ISubsymbolicProduction6;
import org.jactr.core.utils.parameter.IParameterized;

public class MockProductionParameters implements IParameterized
{

  public MockProductionParameters()
  {

  }

  @Override
  public void setParameter(String key, String value)
  {

  }

  @Override
  public String getParameter(String key)
  {
    return null;
  }

  @Override
  public Collection<String> getPossibleParameters()
  {
    return getSetableParameters();
  }

  @Override
  public Collection<String> getSetableParameters()
  {
    return Arrays.asList(ISubsymbolicProduction.CREATION_TIME,
        ISubsymbolicProduction6.EXPECTED_UTILITY_PARAM,
        ISubsymbolicProduction6.UTILITY_PARAM,
        ISubsymbolicProduction6.REWARD_PARAM,
        ISubsymbolicProduction.FIRING_TIME);
  }

}
