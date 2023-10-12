package org.jactr.io2.jactr.normalize;

import com.google.inject.Provider;
import java.io.File;
import java.util.Set;
import java.util.function.Consumer;
import javax.inject.Inject;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.jactr.core.model.IModel;
import org.jactr.io2.jactr.modelFragment.Buffer;
import org.jactr.io2.jactr.modelFragment.Buffers;
import org.jactr.io2.jactr.modelFragment.Element;
import org.jactr.io2.jactr.modelFragment.Import;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.modelFragment.ModelFragmentFactory;
import org.jactr.io2.jactr.modelFragment.PackageDeclaration;
import org.jactr.io2.jactr.modelFragment.Parameters;
import org.jactr.io2.jactr.util.IO2Utilities;

@SuppressWarnings("all")
public class DefaultNormalizer implements INormalizer {
  @Inject
  public Provider<ResourceSet> resourceSetProvider;
  
  private ModelFragment _root;
  
  @Override
  public ModelFragment normalize(final ModelFragment modelFragment) {
    try {
      ModelFragment _xblockexpression = null;
      {
        this._root = EcoreUtil.<ModelFragment>copy(modelFragment);
        Buffers _buffers = this._root.getPackage().getBuffers();
        boolean _tripleEquals = (_buffers == null);
        if (_tripleEquals) {
          PackageDeclaration _package = this._root.getPackage();
          _package.setBuffers(ModelFragmentFactory.eINSTANCE.createBuffers());
        }
        final Set<Import> imports = IterableExtensions.<Import>toSet(this._root.getPackage().getImports());
        for (final Import imp : imports) {
          {
            this.importContents(imp);
            this._root.getPackage().getImports().remove(imp);
          }
        }
        final ResourceSet set = this.resourceSetProvider.get();
        final File file = File.createTempFile("normalized-", ".jactr");
        file.deleteOnExit();
        final Resource resource = set.getResource(URI.createFileURI(file.getPath()), true);
        resource.getContents().add(this._root);
        _xblockexpression = this._root;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void importContents(final Import importStatement) {
    try {
      String _replace = importStatement.getImportedNamespace().substring(0, importStatement.getImportedNamespace().lastIndexOf(".")).replace(".", 
        "/");
      String _plus = ("/" + _replace);
      final String resource = (_plus + ".jactr");
      final ModelFragment importFragment = IO2Utilities.loadModel(IModel.class.getClassLoader().getResource(resource).toURI()).get();
      final Consumer<Element> _function = (Element element) -> {
        if ((!(element instanceof Parameters))) {
          this._root.getPackage().getElements().add(EcoreUtil.<Element>copy(element));
        }
      };
      importFragment.getPackage().getElements().forEach(_function);
      Buffers _buffers = importFragment.getPackage().getBuffers();
      boolean _tripleNotEquals = (_buffers != null);
      if (_tripleNotEquals) {
        final Consumer<Buffer> _function_1 = (Buffer buffer) -> {
          this._root.getPackage().getBuffers().getBuffers().add(EcoreUtil.<Buffer>copy(buffer));
        };
        importFragment.getPackage().getBuffers().getBuffers().forEach(_function_1);
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
