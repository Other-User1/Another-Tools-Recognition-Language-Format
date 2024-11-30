package another.tools.recognition.language.format.Lexers;

import another.tools.recognition.language.format.Rules.*;
import another.tools.recognition.language.format.Tokens.SpecialTokenType;
import com.java.components.lang.CompilerTaskException;

import java.util.HashMap;

public abstract class LexerTokenizer {
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

	public final RangeRule Range(Rule rule, int min, int max) {
		return new RangeRule(rule, min, max);
	}

	public final RepeatRule Repeat(Rule rule, int permit) {
		return new RepeatRule(rule, permit);
	}

	public final NotRule Not(Rule rule) {
		return new NotRule(rule);
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
		return new Rule() {
			@Override
			public String match(String input, int position) throws CompilerTaskException {
				String matched = rule.match(input, position);
				if (matched != null) {
					types.put(matched, type);
				}
				return matched;
			}
		};
	}

	public final Rule Token(Rule rule) {
		return Token(rule, SpecialTokenType.UndefinedToken);
	}

	public final Rule Token(Rule rule, RuleAction action) {
		return new Rule() {
			@Override
			public String match(String input, int position) throws CompilerTaskException {
				String matched = rule.match(input, position);
				if (matched != null) {
					types.put(matched, action.execute(matched));
				}
				return matched;
			}
		};
	}

	public Rule skip() {
		return null;
	}

	public abstract Rule execute() throws CompilerTaskException;
}