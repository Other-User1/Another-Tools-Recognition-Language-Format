package another.tools.recognition.language.format.rules;

import java.util.Arrays;
import java.util.List;

public class AlternativesRule extends Rule {
	private final List<Rule> rules;

	public AlternativesRule(Rule... rules) {
		this.rules = Arrays.asList(rules);
	}

	@Override
	public String match(String input, int position) {
		for (Rule rule : rules) {
			String matched = rule.match(input, position);
			if (matched != null) {
				return matched;
			}
		}
		return null;
	}
}