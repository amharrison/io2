package org.jactr.io2.jactr.scoping;

import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.scoping.ICaseInsensitivityHelper;
import org.eclipse.xtext.scoping.IGlobalScopeProvider;
import org.eclipse.xtext.scoping.impl.ImportedNamespaceAwareLocalScopeProvider;

public class JactrImportedNamespaceAwareLocalScopeProvider
    extends ImportedNamespaceAwareLocalScopeProvider
{

  public JactrImportedNamespaceAwareLocalScopeProvider()
  {
  }

  public JactrImportedNamespaceAwareLocalScopeProvider(
      IGlobalScopeProvider globalScopeProvider,
      IQualifiedNameProvider qualifiedNameProvider,
      IQualifiedNameConverter qualifiedNameConverter,
      ICaseInsensitivityHelper caseInsensitivityHelper)
  {
    super(globalScopeProvider, qualifiedNameProvider, qualifiedNameConverter,
        caseInsensitivityHelper);
  }

//  @Override
//  protected List<ImportNormalizer> getImplicitImports(boolean ignoreCase)
//  {
//    return Collections.singletonList(
//        createImportedNamespaceResolver("jactr.declarative.*", ignoreCase));
//  }

}
