package org.jactr.io2.jactr.source;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.serializer.impl.Serializer;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.modelFragment.ModelFragmentFactory;
import org.jactr.io2.jactr.util.IO2Utilities;
import org.jactr.io2.source.ISourceGenerator;

public class XtextSourceGenerator implements ISourceGenerator {

	private Serializer _serializer;

	private Serializer getSerializer() {
		if (_serializer == null)
			_serializer = (Serializer) IO2Utilities.instantiate(Serializer.class);
		return _serializer;
	}

	

	@Override
	public boolean canGenerate(Object astNode, String format) {
		return "jactr".equals(format) && (astNode == null || astNode instanceof ModelFragment);
	}

	@Override
	public boolean canSave(Object astNode, URI resource) {
		File fp = new File(resource);
		String str = fp.getName();
		str = str.substring(str.lastIndexOf('.'), str.length());

		return "jactr".equals(str) && astNode instanceof ModelFragment;
	}

	@Override
	public String generate(Object astNode, String format) {
		if (!"jactr".equals(format))
			return null;
		

		return getSerializer().serialize((EObject) astNode);
	}

	@Override
	public void save(Object astNode, URI resource) throws IOException {
		try {
			ModelFragment modelFragment = (ModelFragment) astNode;
			/*
			 * we do this so that we don't have major collisions locally.
			 */
			File fp = new File(resource);
			String packageName = fp.getParentFile().getName() + "." + modelFragment.getPackage().getName();
			modelFragment.getPackage().setName(packageName);

			IO2Utilities.saveModel(modelFragment, resource);
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
