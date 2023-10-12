package org.jactr.io2.jactr.ast;

import org.jactr.core.chunk.IChunk;
import org.jactr.core.chunktype.IChunkType;
import org.jactr.core.model.IModel;
import org.jactr.core.production.IProduction;
import org.jactr.io2.ast.IASTGenerator;
import org.jactr.io2.jactr.util.IO2Utilities;

public class XtextASTGenerator implements IASTGenerator {

	
	private ModelToModelFragment _generator;
	
	@Override
	public boolean generates(String format) {
		return "jactr".equals(format);
	}

	@Override
	public Object generate(IModel model, String format, boolean trimIfPossible) {
		
		return IO2Utilities.convert(model).get();
	}

	private ModelToModelFragment getGenerator() {
		if(_generator==null)
			_generator = (ModelToModelFragment) IO2Utilities.instantiate(ModelToModelFragment.class);
		return _generator;
	}

	@Override
	public Object generate(IChunkType chunkType, String format, boolean includeChunks) {
		if(!"jactr".equals(format)) return null;
		return getGenerator().convert(chunkType, includeChunks);
	}

	@Override
	public Object generate(IChunk chunk, String format) {
		if(!"jactr".equals(format)) return null;
		return getGenerator().convert(chunk);
	}

	@Override
	public Object generate(IProduction production, String format) {
		if(!"jactr".equals(format)) return null;
		return getGenerator().convert(production);
	}
	
	
}
