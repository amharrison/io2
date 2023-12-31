/*
 * generated by Xtext 2.16.0
 */
package org.jactr.io2.jactr.ide

import com.google.inject.Guice
import org.eclipse.xtext.util.Modules2
import org.jactr.io2.jactr.ModelFragmentRuntimeModule
import org.jactr.io2.jactr.ModelFragmentStandaloneSetup

/**
 * Initialization support for running Xtext languages as language servers.
 */
class ModelFragmentIdeSetup extends ModelFragmentStandaloneSetup {

	override createInjector() {
		Guice.createInjector(Modules2.mixin(new ModelFragmentRuntimeModule, new ModelFragmentIdeModule))
	}
	
}
