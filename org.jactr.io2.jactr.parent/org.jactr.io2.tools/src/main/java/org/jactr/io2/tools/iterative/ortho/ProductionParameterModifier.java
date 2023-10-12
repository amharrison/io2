package org.jactr.io2.tools.iterative.ortho;

import java.util.Optional;

import org.antlr.runtime.tree.CommonTree;
import org.jactr.io2.compilation.ICompilationUnit;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.modelFragment.ModelFragmentFactory;
import org.jactr.io2.jactr.modelFragment.PackageDeclaration;
import org.jactr.io2.jactr.modelFragment.Parameter;
import org.jactr.io2.jactr.modelFragment.ParametersBlock;
import org.jactr.io2.jactr.modelFragment.Production;

public class ProductionParameterModifier extends org.jactr.tools.itr.ProductionParameterModifier {

	@Override
	protected void setParameter(ICompilationUnit modelDescriptor, String parameter, String value) {
		Object normalized = modelDescriptor.getNormalizedAST();
		if (normalized instanceof CommonTree)
			setParameter((CommonTree) normalized, parameter, value);
		else if (normalized instanceof ModelFragment)
			io2SetParameter((ModelFragment) normalized, parameter, value);
	}

	protected void io2SetParameter(ModelFragment fragment, String parameter, String value) {
		fragment.getPackage().getElements().stream().filter(e -> {
			return e instanceof Production;
		}).map(p -> {
			return (Production) p;
		}).forEach(p -> {
			if (_production.matcher(p.getName()).matches()) {
				ParametersBlock pb = getParametersBlock(p);
				setParameter(pb, parameter, value);
			}
		});
	}

	protected ParametersBlock getParametersBlock(Production m) {
		ParametersBlock pb = m.getParameters();
		if (pb == null) {
			pb = ModelFragmentFactory.eINSTANCE.createParametersBlock();
			m.setParameters(pb);
		}
		return pb;
	}

	protected void setParameter(ParametersBlock pb, String parameter, String value) {
		Optional<Parameter> p = pb.getParameter().stream().filter(pp -> {
			return parameter.equals(pp.getName());
		}).findFirst();
		if (p.isEmpty()) {
			Parameter pp = ModelFragmentFactory.eINSTANCE.createParameter();
			pp.setName(parameter);
			pp.setValue(ModelFragmentFactory.eINSTANCE.createValue());
			pp.getValue().setString(value);
			pb.getParameter().add(pp);
		} else {
			p.get().getValue().setString(value);
		}
	}
}
