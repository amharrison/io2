/**
 * generated by Xtext 2.16.0
 */
package org.jactr.io2.jactr;

/**
 * Initialization support for running Xtext languages without Equinox extension registry.
 */
@SuppressWarnings("all")
public class ModelFragmentStandaloneSetup extends ModelFragmentStandaloneSetupGenerated {
  public static void doSetup() {
    new ModelFragmentStandaloneSetup().createInjectorAndDoEMFRegistration();
  }
}
