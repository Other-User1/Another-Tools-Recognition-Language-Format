package another.tools.recognition.language.format.syntactics;

import another.tools.recognition.language.format.Grammaticals.*;
import another.tools.recognition.language.format.tokens.Token;
import another.tools.recognition.language.format.tokens.TokenText;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public abstract class SyntacticGrammatical {

	public static final SequenceGrammatical Sequence(Grammatical... grammar) {
		return new SequenceGrammatical(grammar);
	}

	public static final AlternativesGrammatical Alternatives(Grammatical... alternatives) {
		return new AlternativesGrammatical(alternatives);
	}

	public static final ZeroOrMoreGrammatical ZeroOrMore(Grammatical grammar) {
		return new ZeroOrMoreGrammatical(grammar);
	}

	public static final OneOrMoreGrammatical OneOrMore(Grammatical grammar) {
		return new OneOrMoreGrammatical(grammar);
	}

	public static final OptionalGrammatical Optional(Grammatical optional) {
		return new OptionalGrammatical(optional);
	}

	public static final TokenText Text(String text) {
		return new TokenText(text);
	}

	public static final RangeGrammatical Range(int min, int max, Grammatical grammatical) {
		return new RangeGrammatical(grammatical, min, max);
	}

	public static final GrammaticalAction GrammaticalAction(Grammatical grammar, GrammaticalAction.Action action) {
		return new GrammaticalAction(grammar) {
			@Override
			public ArrayList<Token> run(ArrayList<Token> tokens) {
				return action.run(tokens);
			}
		};
	}

	public static final GrammaticalGrammar GrammaticalGrammar(Grammatical grammar, GrammaticalGrammar.Action action) {
		return new GrammaticalGrammar(grammar) {
			@Override
			public ArrayList<Grammatical> execute(ArrayList<Token> tokens) throws CompilerTaskException {
				return action.execute(tokens);
			}
		};
	}

	public static final Grammar Grammar(Grammar grammar) {
		return grammar;
	}

	public abstract Grammatical run();
}
