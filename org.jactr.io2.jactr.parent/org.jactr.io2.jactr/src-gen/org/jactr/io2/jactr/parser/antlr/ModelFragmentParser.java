/*
 * generated by Xtext 2.25.0
 */
package org.jactr.io2.jactr.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.jactr.io2.jactr.parser.antlr.internal.InternalModelFragmentParser;
import org.jactr.io2.jactr.services.ModelFragmentGrammarAccess;

public class ModelFragmentParser extends AbstractAntlrParser {

	@Inject
	private ModelFragmentGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalModelFragmentParser createParser(XtextTokenStream stream) {
		return new InternalModelFragmentParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "ModelFragment";
	}

	public ModelFragmentGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(ModelFragmentGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}