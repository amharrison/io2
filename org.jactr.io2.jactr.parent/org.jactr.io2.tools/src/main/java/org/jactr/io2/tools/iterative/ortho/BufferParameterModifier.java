package org.jactr.io2.tools.iterative.ortho;

import java.util.Optional;

import org.antlr.runtime.tree.CommonTree;
import org.jactr.io2.compilation.ICompilationUnit;
import org.jactr.io2.jactr.modelFragment.Buffer;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.modelFragment.ModelFragmentFactory;
import org.jactr.io2.jactr.modelFragment.Parameter;
import org.jactr.io2.jactr.modelFragment.ParametersBlock;

public class BufferParameterModifier extends org.jactr.tools.itr.BufferParameterModifier {

	@Override
	protected void setParameter(ICompilationUnit modelDescriptor, String parameter, String value)
	{
		Object normalized = modelDescriptor.getNormalizedAST();
		if(normalized instanceof CommonTree)
			setBufferParameter((CommonTree) normalized, _bufferName, parameter, value);
		else
	    if(normalized instanceof ModelFragment)
	      io2SetParameter((ModelFragment) normalized, parameter, value);
	}
	
	protected void io2SetParameter(ModelFragment fragment, String parameter, String value)
	{
		//get the module node
		fragment.getPackage().getBuffers().getBuffers().stream().filter(b->{
			return b.getName().equals(_bufferName);
		}).forEach(m->{
			ParametersBlock pb = getParametersBlock(m);
			setParameter(pb, parameter, value);
		});
	}
	
	protected ParametersBlock getParametersBlock(Buffer b)
	{
		ParametersBlock pb = b.getParameters();
		if(pb==null)
		{
			pb = ModelFragmentFactory.eINSTANCE.createParametersBlock();
			b.setParameters(pb);
		}
		return pb;
	}
	
	protected void setParameter(ParametersBlock pb, String parameter, String value)
	{
		Optional<Parameter> p = pb.getParameter().stream().filter(pp->{
		 return parameter.equals(pp.getName());
		}).findFirst();
		if(p.isEmpty())
		{
			Parameter pp = ModelFragmentFactory.eINSTANCE.createParameter();
			pp.setName(parameter);
			pp.setValue(ModelFragmentFactory.eINSTANCE.createValue());
			pp.getValue().setString(value);
			pb.getParameter().add(pp);
		}
		else
		{
			p.get().getValue().setString(value);
		}
	}
}
