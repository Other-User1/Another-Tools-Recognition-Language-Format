package another.tools.recognition.language.format.Syntactics;

import another.tools.recognition.language.format.Grammaticals.*;
import another.tools.recognition.language.format.Tokens.Token;
import another.tools.recognition.language.format.Tokens.TokenText;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public abstract class SyntacticGrammatical implements Grammatical {
	// static HashMap<ArrayList<Token>, SyntacticAction> actions = new HashMap<>();

	public static final SequenceGrammatical Sequence(Grammatical... grammars) {
		return new SequenceGrammatical(grammars);
	}

	public static final SkipGrammatical Skip(Grammatical grammar) {
		return new SkipGrammatical(grammar);
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

	public static final MoreGrammatical More(Grammatical grammatical) {
		return new MoreGrammatical(grammatical);
	}

	public static final OptionalGrammatical Optional(Grammatical optional) {
		return new OptionalGrammatical(optional);
	}

	public static final TokenText Text(String text) {
		return new TokenText(text);
	}
	
	public static final TokenText Character(char character) {
		return new TokenText(String.valueOf(character));
	}

	public static final RangeGrammatical Range(int min, int max, Grammatical grammatical) {
		return new RangeGrammatical(grammatical, min, max);
	}

	public static final RepeatGrammatical Repeat(Grammatical rule, int count) {
		return new RepeatGrammatical(rule, count);
	}

	public static final GrammaticalAction GrammaticalAction(Grammatical grammar, GrammaticalAction.Action action) {
		return new GrammaticalAction(grammar) {
			@Override
			public ArrayList<Token> run(ArrayList<Token> tokens) {
				return new ArrayList<>(List.of(action.run(tokens)));
			}
		};
	}

	public static final GrammaticalGrammar GrammaticalGrammar(Grammatical grammar, GrammaticalGrammar.Action action) {
		return new GrammaticalGrammar(grammar) {
			@Override
			public Grammatical execute(ArrayList<Token> tokens) throws CompilerTaskException {
				return action.execute(tokens);
			}
		};
	}

	public static final Grammatical Action(Grammatical grammatical, SyntacticAction action) {
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

	public static final Grammatical Grammar(Grammar grammar) throws CompilerTaskException {
		return toGrammar(grammar.getGrammars());
	}

	public static final Grammatical toGrammar(ArrayList<Grammatical> grammaticals) {
		return toGrammar(grammaticals.toArray(new Grammatical[0]));
	}

	public static final Grammatical toGrammar(Grammatical... grammaticals) {
		return Sequence(grammaticals);
	}

	public static final AnyGrammatical Any() {
		return new AnyGrammatical();
	}

	public abstract Grammatical run() throws CompilerTaskException;

	@Override
	public final ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		throw new CompilerTaskException("Unsupported method!");
	}
}
