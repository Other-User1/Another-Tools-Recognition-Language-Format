package another.tools.recognition.language.format.rules;

import java.util.Arrays;
import java.util.List;

public class SequenceRule extends Rule {
	private final List<Rule> rules;

	public SequenceRule(Rule... rules) {
		this.rules = Arrays.asList(rules);
	}

	@Override
	public String match(String input, int position) {
		StringBuilder result = new StringBuilder();
		int initialPosition = position;

		for (Rule rule : rules) {
			String matched = rule.match(input, position);
			if (matched == null) {
				return null;
			}
			result.append(matched);
			position += matched.length();
		}
		return result.toString();
	}
}