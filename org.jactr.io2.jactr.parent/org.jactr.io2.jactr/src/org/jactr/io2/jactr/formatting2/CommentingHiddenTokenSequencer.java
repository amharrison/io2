package org.jactr.io2.jactr.formatting2;

import java.util.List;
import java.util.Optional;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.sequencer.HiddenTokenSequencer;
import org.jactr.io2.jactr.comment.CommentUtilities;
import org.jactr.io2.jactr.services.ModelFragmentGrammarAccess;

import com.google.inject.Inject;

public class CommentingHiddenTokenSequencer extends HiddenTokenSequencer
{

  @Inject
  private ModelFragmentGrammarAccess _grammarAccess;

  private Optional<String> _commentToWrite = Optional.empty();

  @Override
  public boolean enterAssignedParserRuleCall(RuleCall rc, EObject semanticChild,
      ICompositeNode node)
  {
    _commentToWrite = CommentUtilities.getComment(semanticChild);
    return super.enterAssignedParserRuleCall(rc, semanticChild, node);
  }

  @Override
  protected void emitHiddenTokens(
      List<INode> hiddens /* Set<INode> comments, */)
  {
    _commentToWrite.ifPresent((text) -> {
      for (String line : text.split("\n"))
        delegate.acceptComment(_grammarAccess.getSL_COMMENTRule(),
            "\n// " + line, null);
    });
    _commentToWrite = Optional.empty();
    super.emitHiddenTokens(hiddens);
  }

}
