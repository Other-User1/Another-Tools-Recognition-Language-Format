package another.tools.recognition.language.format.Lexers;

import another.tools.recognition.language.format.Rules.*;
import another.tools.recognition.language.format.Tokens.SpecialTokenType;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class LexerTokenizer extends Rule {
	HashMap<String, Enum<?>> types = new HashMap<>(); // FIXME: Temporaly for fix the error null of TokenType (Enum<?>), because return null!

	public final SequenceRule Sequence(Rule... sequences) {
		return new SequenceRule(sequences);
	}

	public final AlternativesRule Alternatives(Rule... alternatives) {
		return new AlternativesRule(alternatives);
	}

	public final CharacterRule Character(char target) {
		return new CharacterRule(target);
	}

	public final CharacterRangeRule CharacterRange(char min, char max) {
		return new CharacterRangeRule(min, max);
	}

	public final TextRule Text(String text) {
		return new TextRule(text);
	}

	public final OptionalRule Optional(Rule rule) {
		return new OptionalRule(rule);
	}

	public final ZeroOrMoreRule ZeroOrMore(Rule rule) {
		return new ZeroOrMoreRule(rule);
	}

	public final OneOrMoreRule OneOrMore(Rule rule) {
		return new OneOrMoreRule(rule);
	}

	public final MoreRule More(Rule rule) {
		return new MoreRule(rule);
	}

	public final RangeRule Range(Rule rule, int min, int max) {
		return new RangeRule(rule, min, max);
	}

	public final RepeatRule Repeat(Rule rule, int permit) {
		return new RepeatRule(rule, permit);
	}

	public final NotRule Not(Rule rule) {
		return new NotRule(rule);
	}

	public final GroupRule Group(Rule rule) {
		return new GroupRule(rule);
	}

	public final Rules SubRules(Rules rule) {
		return rule;
	}

	public final AnyRule Any() {
		return new AnyRule();
	}

	public final Rule Token(Rule rule, Enum<?> type) {
		if (type.getDeclaringClass().toString().equals("class another.tools.recognition.language.format.tokens.SpecialTokenType")) {
			throw new RuntimeException("invalid use this enum!");
		}
		return Token(rule, new RuleAction() {
			@Override
			public Enum<?> execute(ArrayList<String> value) {
				return type;
			}
		});
	}

	public final Rule Token(Rule rule) {
		return Token(rule, SpecialTokenType.UndefinedToken);
	}

	public final Rule Token(Rule rule, RuleAction action) {
		return new Rule() {
			@Override
			public ArrayList<String> match(String input, int position) throws CompilerTaskException {
				ArrayList<String> matched = rule.match(input, position);
				if (matched != null) {
					types.put(getString(matched), action.execute(matched));
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

	public Rule skip() {
		return null;
	}

	public abstract Rule execute() throws CompilerTaskException;

	@Override
	public final ArrayList<String> match(String input, int position) throws CompilerTaskException {
		return null;
	}
}