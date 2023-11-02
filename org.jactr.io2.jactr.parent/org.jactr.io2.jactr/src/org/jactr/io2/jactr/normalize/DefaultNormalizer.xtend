package org.jactr.io2.jactr.normalize

import com.google.inject.Inject
import com.google.inject.Provider
import java.io.File
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.emf.ecore.util.EcoreUtil
import org.jactr.core.model.IModel
import org.jactr.io2.jactr.modelFragment.Import
import org.jactr.io2.jactr.modelFragment.ModelFragment
import org.jactr.io2.jactr.modelFragment.ModelFragmentFactory
import org.jactr.io2.jactr.modelFragment.Parameters
import org.jactr.io2.jactr.util.IO2Utilities

class DefaultNormalizer implements INormalizer {

  @Inject
  public Provider<ResourceSet> resourceSetProvider;

  ModelFragment _root

  override normalize(ModelFragment modelFragment) {
    _root = EcoreUtil.copy(modelFragment)

    /*
     * make sure buffers is defined, even if empty
     */
    if (_root.package.buffers === null)
      _root.package.buffers = ModelFragmentFactory.eINSTANCE.createBuffers

    val imports = _root.package.imports.toSet
    for (imp : imports) {
      importContents(imp)
      _root.package.imports.remove(imp)
    }

    val set = resourceSetProvider.get(); // new XtextResourceSet();
    val file = File.createTempFile("normalized-",".jactr")
    file.deleteOnExit
    val resource = set.getResource(URI.createFileURI(file.path), true);
    resource.contents.add(_root)

    _root
  }

  def importContents(Import importStatement) {
    val resource = "/" +
      importStatement.importedNamespace.substring(0, importStatement.importedNamespace.lastIndexOf(".")).replace(".",
        "/") + ".jactr"
    val importFragment = IO2Utilities.loadModel(IModel.getClassLoader().getResource(resource).toURI).get()
    /* 
     * copy core elements, except the parameters command
     */
    importFragment.package.elements.forEach [ element |
      if (!(element instanceof Parameters))
        _root.package.elements.add(EcoreUtil.copy(element))
    ]

    /*
     * now we copy the buffers
     */
    if (importFragment.package.buffers !== null)
      importFragment.package.buffers.buffers.forEach [ buffer |
        _root.package.buffers.buffers.add(EcoreUtil.copy(buffer))
      ]
  }

}
