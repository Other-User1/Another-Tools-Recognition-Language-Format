package another.tools.recognition.language.format.lexers;

import another.tools.recognition.language.format.rules.*;

import java.util.ArrayList;
import java.util.Arrays;
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

	public final AlternativesRule CharacterRange(char... targets) {
		return getCharacterRange(targets);
	}

	private AlternativesRule getCharacterRange(char... targets) {
		AlternativesRule ar;
		ArrayList<Rule> rule = new ArrayList<>();
		int min = targets.length / 2;
		for (int i = 0; i < min; i += 2) {
			rule.add(new CharacterRangeRule(targets[i], targets[i + 1]));
		}
		Rule[] rules = rule.toArray(new Rule[0]);
		return new AlternativesRule(rules);
	}

	// public final CharacterPermitRangeRule CharacterPermitRange(char target, int... permit) { return new CharacterPermitRangeRule(target, permit); }

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

	public final PermitRangeRule PermitRange(Rule rule, int... permit) {
		return new PermitRangeRule(rule, permit);
	}

	public final NotRule Not(Rule rule) {
		return new NotRule(rule);
	}

	public final Rule Token(Rule rule, Enum<?> type) {
		if (type.getDeclaringClass().toString().equals("class another.tools.recognition.language.format.tokens.SpecialTokenType")) {
			throw new RuntimeException("invalid use this enum!");
		}
		return new Rule(type) {
			@Override
			public String match(String input, int position) {
				String matched = rule.match(input, position);
				if (matched != null) {
					types.put(matched, type);
				}
				return matched;
			}
		};
	}

	public abstract Rule execute();

	public Rule skip() {
		return null;
	}
}