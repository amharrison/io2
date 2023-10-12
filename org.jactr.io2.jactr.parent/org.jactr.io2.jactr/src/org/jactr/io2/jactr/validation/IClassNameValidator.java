package org.jactr.io2.jactr.validation;

import java.net.URI;

import org.eclipse.emf.ecore.resource.Resource;

public interface IClassNameValidator
{

  public boolean isValidClassName(Resource resource, String className);

  public boolean canResolve(Resource resource, String resourceName);

  public <T> T instantiate(String className);

  public URI resolve(Resource resource, String resourceName);

}
