package org.jactr.io2.jactr.validation;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.inject.Inject;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.jactr.core.model.IModel;

public class DefaultClassNameValidator implements IClassNameValidator
{
  private final ClassLoader _loader;

  @Inject
  IContainer.Manager        _manager;

  @Inject
  IResourceDescriptions     _descriptions;

  @Inject
  private IAllContainersState.Provider _stateProvider;

  public DefaultClassNameValidator(ClassLoader classLoader)
  {
    _loader = classLoader;
  }

  public DefaultClassNameValidator()
  {
    this(IModel.class.getClassLoader());
  }

  @Override
  public boolean isValidClassName(Resource resource, String className)
  {
    try
    {
      _loader.loadClass(className);
      return true;
    }
    catch (Exception e)
    {

      return false;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T> T instantiate(String className)
  {
    try
    {
      return (T) _loader.loadClass(className).newInstance();
    }
    catch (Exception e)
    {
      return null;
    }
  }

  @Override
  public boolean canResolve(Resource resource, String resourceName)
  {
    return resolve(resource, resourceName) != null;
  }

  @Override
  public URI resolve(Resource resource, String resourceName)
  {
    try
    {
      URL url = _loader.getResource(resourceName);
      if (url != null) return url.toURI();

      IResourceDescription desc = _descriptions
          .getResourceDescription(resource.getURI());
      for (IContainer container : _manager.getVisibleContainers(desc,
          _descriptions))
        for (IResourceDescription desc2 : container.getResourceDescriptions())
          if (desc2.getURI().toString().endsWith(resourceName))
            return new URI(desc.getURI().toString());

      return null;
    }
    catch (URISyntaxException e)
    {
      return null;
    }
  }

}
