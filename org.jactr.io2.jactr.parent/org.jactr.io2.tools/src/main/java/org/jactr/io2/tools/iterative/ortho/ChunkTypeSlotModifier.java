package org.jactr.io2.tools.iterative.ortho;

import java.util.Optional;

import org.antlr.runtime.tree.CommonTree;
import org.jactr.io2.compilation.ICompilationUnit;
import org.jactr.io2.jactr.modelFragment.ChunkType;
import org.jactr.io2.jactr.modelFragment.Chunks;
import org.jactr.io2.jactr.modelFragment.ModelFragment;
import org.jactr.io2.jactr.modelFragment.ModelFragmentFactory;
import org.jactr.io2.jactr.modelFragment.Parameter;
import org.jactr.io2.jactr.modelFragment.ParametersBlock;
import org.jactr.io2.jactr.modelFragment.SimpleSlot;

public class ChunkTypeSlotModifier extends org.jactr.tools.itr.ChunkTypeSlotModifier {

	@Override
	protected void setParameter(ICompilationUnit modelDescriptor, String parameter, String value) {
		Object normalized = modelDescriptor.getNormalizedAST();
		if (normalized instanceof CommonTree)
			setParameter((CommonTree) normalized, parameter, value);
		else if (normalized instanceof ModelFragment)
			io2SetParameter((ModelFragment) normalized, parameter, value);
	}

	protected void io2SetParameter(ModelFragment fragment, String parameter, String value) {
		// get the module node
		fragment.getPackage().getElements().stream().filter(e -> {
			return e instanceof ChunkType;
		}).forEach(c -> {
			ChunkType ct = ((ChunkType) c);
			if (_chunkTypeName.equals(ct.getName())) {

				SimpleSlot slot = getSlot(ct);
				try {
					Double d = Double.parseDouble(value);
					slot.getValue().setNumber(d);
				} catch (Exception e) {
					if ("true".equals(value) || "false".equals(value))
						slot.getValue().setBoolean(Boolean.parseBoolean(value));
					else
						slot.getValue().setString(value);
				}
			}
		});

	}

	private SimpleSlot getSlot(ChunkType cc) {
		Optional<SimpleSlot> ss = cc.getSlots().stream().filter(s -> {
			return getParameterName().equals(s.getName());
		}).findFirst();
		if (ss.isEmpty()) {
			SimpleSlot s = ModelFragmentFactory.eINSTANCE.createSimpleSlot();
			s.setName(getParameterName());
			s.setValue(ModelFragmentFactory.eINSTANCE.createValue());
			;
			cc.getSlots().add(s);
			return s;
		}
		return ss.get();
	}

}
