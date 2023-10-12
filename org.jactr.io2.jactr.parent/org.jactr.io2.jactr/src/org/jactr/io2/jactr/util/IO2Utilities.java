package org.jactr.io2.jactr.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.BinaryResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.xtext.resource.SaveOptions;
import org.jactr.core.model.IModel;
import org.jactr.io2.jactr.ModelFragmentStandaloneSetup;
import org.jactr.io2.jactr.ast.ModelToModelFragment;
import org.jactr.io2.jactr.builder.Builder;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.normalize.DefaultNormalizer;
import org.jactr.io2.jactr.normalize.INormalizer;
import org.jactr.io2.jactr.source.ISourceLocator;

import com.google.inject.Injector;

public class IO2Utilities {
	// can't instantiate here otherwise we might get collisions with the gui
	// registration of xtext
	static private Injector _injector;

	static public Object instantiate(Class clazz) {

		return getInjector().getInstance(clazz);
	}

	static private Injector getInjector() {
		if (_injector == null)
			_injector = new ModelFragmentStandaloneSetup().createInjectorAndDoEMFRegistration();
		return _injector;
	}

	/**
	 * loads a model using only itself for context
	 * 
	 * @param uri
	 * @return
	 * @throws Exception
	 */
	static public Supplier<ModelFragment> loadModel(URI uri) throws Exception {
		return new Supplier<ModelFragment>() {
			@Override
			public ModelFragment get() {
				try {
					final LoadDelegate ld = getInjector().getInstance(LoadDelegate.class);
					ld.setURI(uri);
					return ld.call();
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	static public void saveModel(ModelFragment fragment, URI uri) throws Exception {
		ResourceSet set = new ResourceSetImpl();
		Resource resource = set.createResource(org.eclipse.emf.common.util.URI.createURI(uri.toString()));

		resource.getContents().add(fragment);

		resource.save(SaveOptions.newBuilder().noValidation().format().getOptions().toOptionsMap());
	}

	static public void saveModel(EObject root, OutputStream outputStream) throws IOException {
		Resource resource = new BinaryResourceImpl();
		resource.getContents().add(root);

		Map<Object, Object> options = new HashMap<Object, Object>();
		options.put(BinaryResourceImpl.OPTION_STYLE_DATA_CONVERTER, Boolean.TRUE);
		options.put(BinaryResourceImpl.OPTION_VERSION, BinaryResourceImpl.BinaryIO.Version.VERSION_1_1);

		resource.save(outputStream, options);
	}

	static public Supplier<ModelFragment> normalize(ModelFragment model) {
		return new Supplier<ModelFragment>() {

			@Override
			public ModelFragment get() {
				try {
					INormalizer normalizer = getInjector().getInstance(DefaultNormalizer.class);

					return normalizer.normalize(model);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		};
	}

	static public Supplier<IModel> build(ModelFragment model) {
		return new Supplier<IModel>() {

			@Override
			public IModel get() {
				try {
					Builder builder = getInjector().getInstance(Builder.class);

					return builder.build(model);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		};
	}

	static public Supplier<ModelFragment> convert(IModel model) {
		return new Supplier<ModelFragment>() {

			@Override
			public ModelFragment get() {
				try {
					ModelToModelFragment m2mf = getInjector().getInstance(ModelToModelFragment.class);

					ModelFragment newModelFragment = m2mf.convert(model);

					return newModelFragment;
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}

		};
	}

	static public Map<String, ISourceLocator.SourceLocation> locateSourceElements(ModelFragment modelFragment) {
		ISourceLocator locator = getInjector().getInstance(ISourceLocator.class);
		return locator.locateSourceElements(modelFragment);
	}

}
