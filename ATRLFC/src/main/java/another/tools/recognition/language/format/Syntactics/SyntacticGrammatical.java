package another.tools.recognition.language.format.Syntactics;

import another.tools.recognition.language.format.Grammaticals.*;
import another.tools.recognition.language.format.Tokens.Token;
import another.tools.recognition.language.format.Tokens.TokenText;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public abstract class SyntacticGrammatical implements Grammatical {
	protected final SequenceGrammatical Sequence(Grammatical... grammars) {
		return new SequenceGrammatical(grammars);
	}

	protected final SkipGrammatical Skip(Grammatical grammar) {
		return new SkipGrammatical(grammar);
	}

	protected final AlternativesGrammatical Alternatives(Grammatical... alternatives) {
		return new AlternativesGrammatical(alternatives);
	}

	protected final OptionalOrMoreGrammatical OptionalOrMore(Grammatical grammar) { // es el "ZeroOrMore" (se puede una 0, 1 o mas)
		return new OptionalOrMoreGrammatical(grammar);
	}

	protected final ZeroOrMoreGrammatical ZeroOrMore(Grammatical grammar) { // solo se puede "0" o mas de dos veces!
		return new ZeroOrMoreGrammatical(grammar);
	}

	protected final OneOrMoreGrammatical OneOrMore(Grammatical grammar) {
		return new OneOrMoreGrammatical(grammar);
	}

	protected final MoreGrammatical More(Grammatical grammatical) {
		return new MoreGrammatical(grammatical);
	}

	protected final OptionalGrammatical Optional(Grammatical optional) {
		return new OptionalGrammatical(optional);
	}

	protected final TokenText Text(String text) {
		return new TokenText(text);
	}

	protected final TokenText Text(char text) {
		return new TokenText(String.valueOf(text));
	}
	
	protected final TokenText Character(char character) {
		return new TokenText(String.valueOf(character));
	}

	protected final RangeGrammatical Range(int min, int max, Grammatical grammatical) {
		return new RangeGrammatical(grammatical, min, max);
	}

	protected final RepeatGrammatical Repeat(Grammatical rule, int count) {
		return new RepeatGrammatical(rule, count);
	}

	protected final ActionGrammatical GrammaticalAction(Grammatical grammar, ActionGrammatical.Action action) {
		return new ActionGrammatical(grammar) {
			@Override
			public ArrayList<Token> run(ArrayList<Token> tokens) {
				return new ArrayList<>(action.run(tokens).getTokens());
			}
		};
	}

	protected final GrammarGrammatical GrammaticalGrammar(Grammatical grammar, GrammarGrammatical.Action action) {
		return new GrammarGrammatical(grammar) {
			@Override
			public Grammatical execute(ArrayList<Token> tokens) throws CompilerTaskException {
				return action.execute(tokens);
			}
		};
	}

	protected final Grammatical Action(Grammatical grammatical, SyntacticAction action) {
		return new Grammatical() {
			@Override
			public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
				ArrayList<Token> tokens = grammatical.match(list, position);
				if (tokens != null) {
					action.execute(skip(tokens));
				}
				return tokens;
			}

			private ArrayList<Token> skip(ArrayList<Token> oldList) {
				ArrayList<Token> newList = new ArrayList<>();
				for (Token element : oldList)
					if (!element.getNameType().equals("Skip"))
						newList.add(element);
				return newList;
			}
		};
	}

	protected final Grammatical Grammar(Grammar grammar) throws CompilerTaskException {
		return toGrammar(grammar.getGrammars());
	}

	protected final Grammatical toGrammar(ArrayList<Grammatical> grammaticals) {
		return toGrammar(grammaticals.toArray(new Grammatical[0]));
	}

	protected final Grammatical toGrammar(Grammatical... grammaticals) {
		return Sequence(grammaticals);
	}

	protected final ErrorGrammatical Error(Grammatical grammatical, CompilerTaskException error) {
		return new ErrorGrammatical(grammatical, error);
	}

	protected final RecursiveGrammatical Recursive(RecursiveGrammatical.Recursive grammatical) {
		return new RecursiveGrammatical(grammatical);
	}

	protected final AnyGrammatical Any() {
		return new AnyGrammatical();
	}

	protected abstract Grammatical run() throws CompilerTaskException;

	@Override
	public final ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		throw new CompilerTaskException("Unsupported method!");
	}
}
