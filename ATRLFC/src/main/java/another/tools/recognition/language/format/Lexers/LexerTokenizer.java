package another.tools.recognition.language.format.Lexers;

import another.tools.recognition.language.format.Rules.*;
import another.tools.recognition.language.format.Tokens.SpecialTokenType;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class LexerTokenizer implements Rule {
	HashMap<String, Enum<?>> type = new HashMap<>(); // FIXME: Temporaly for fix the error null of TokenType (Enum<?>), because return null!
	HashMap<String, ArrayList<Enum<?>>> types = new HashMap<>(); // FIXME: Temporaly for fix the error null of TokenType (Enum<?>), because return null!

	public final SequenceRule Sequence(Rule... sequences) throws CompilerTaskException {
		return new SequenceRule(sequences);
	}

	public final AlternativesRule Alternatives(Rule... alternatives) throws CompilerTaskException {
		return new AlternativesRule(alternatives);
	}

	public final CharacterRule Character(char target) throws CompilerTaskException {
		return new CharacterRule(target);
	}

	public final CharacterRangeRule CharacterRange(char min, char max) throws CompilerTaskException {
		return new CharacterRangeRule(min, max);
	}

	public final TextRule Text(String text) throws CompilerTaskException {
		return new TextRule(text);
	}

	public final OptionalRule Optional(Rule rule) throws CompilerTaskException {
		return new OptionalRule(rule);
	}

	public final ZeroOrMoreRule ZeroOrMore(Rule rule) throws CompilerTaskException {
		return new ZeroOrMoreRule(rule);
	}

	public final OptionalOrMoreRule OptionalOrMore(Rule rule) throws CompilerTaskException {
		return new OptionalOrMoreRule(rule);
	}

	public final OneOrMoreRule OneOrMore(Rule rule) throws CompilerTaskException {
		return new OneOrMoreRule(rule);
	}

	public final MoreRule More(Rule rule) throws CompilerTaskException {
		return new MoreRule(rule);
	}

	public final RangeRule Range(Rule rule, int min, int max) throws CompilerTaskException {
		return new RangeRule(rule, min, max);
	}

	public final RepeatRule Repeat(Rule rule, int permit) throws CompilerTaskException {
		return new RepeatRule(rule, permit);
	}

	public final NotRule Not(Rule rule) throws CompilerTaskException {
		return new NotRule(rule);
	}

	public final GroupRule Group(Rule rule) throws CompilerTaskException {
		return new GroupRule(rule);
	}

	public final Rules SubRules(Rules rule) throws CompilerTaskException {
		return rule;
	}

	public final AnyRule Any() throws CompilerTaskException {
		return new AnyRule();
	}

	public final Rule Token(Rule rule, Enum<?> type) throws CompilerTaskException {
		if (type.getDeclaringClass().toString().equals("class another.tools.recognition.language.format.tokens.SpecialTokenType")) {
			throw new RuntimeException("invalid use this enum!");
		}
		return Token(rule, new RulesRule() {
			@Override
			public Enum<?> execute(ArrayList<String> value) {
				return type;
			}
		});
	}

	public final Rule Token(Rule rule, Enum<?>... types) throws CompilerTaskException {
		for (Enum<?> type : types) {
			if (type.getDeclaringClass().toString().equals("class another.tools.recognition.language.format.tokens.SpecialTokenType")) {
				throw new RuntimeException("invalid use this enum!");
			}
		}
		return Token(rule, new RulesRule() {
			@Override
			public ArrayList<Enum<?>> executes(ArrayList<String> value) throws CompilerTaskException {
				return Sequence(types);
			}
		});
	}

	public final Rule Token(Rule rule) throws CompilerTaskException {
		return Token(rule, SpecialTokenType.UndefinedToken);
	}

	public final Rule Token(Rule rule, RulesRule rules) throws CompilerTaskException {
		return new Rule() {
			@Override
			public ArrayList<String> match(String input, int position) throws CompilerTaskException {
				ArrayList<String> matched = rule.match(input, position);
				if (matched != null) {
					if (rules.execute(matched) != null) {
						type.put(getString(matched), rules.execute(matched));
					} else if (rules.executes(matched) != null) {
						types.put(getString(matched), rules.executes(matched));
					}
				}
				return matched;
			}

			private String getString(ArrayList<String> list) {
				StringBuilder newString = new StringBuilder();
				for (String element : list)
					newString.append(element);
				return newString.toString();
			}
		};
	}

	public Rule skip() throws CompilerTaskException {
		return null;
	}

	public abstract Rule execute() throws CompilerTaskException;

	@Override
	public final ArrayList<String> match(String input, int position) throws CompilerTaskException {
		return null;
	}
}