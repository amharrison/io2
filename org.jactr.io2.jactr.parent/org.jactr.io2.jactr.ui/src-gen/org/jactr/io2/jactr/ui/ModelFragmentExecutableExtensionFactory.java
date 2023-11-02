/*
 * generated by Xtext 2.33.0.M1
 */
package org.jactr.io2.jactr.ui;

import com.google.inject.Injector;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.jactr.io2.jactr.ui.internal.JactrActivator;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class ModelFragmentExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return FrameworkUtil.getBundle(JactrActivator.class);
	}
	
	@Override
	protected Injector getInjector() {
		JactrActivator activator = JactrActivator.getInstance();
		return activator != null ? activator.getInjector(JactrActivator.ORG_JACTR_IO2_JACTR_MODELFRAGMENT) : null;
	}

}
