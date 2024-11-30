package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.Arrays;
import java.util.List;

public class SequenceRule implements Rule {
	private final List<Rule> rules;

	public SequenceRule(Rule... rules) {
		this.rules = Arrays.asList(rules);
	}

	@Override
	public String match(String input, int position) throws CompilerTaskException {
		StringBuilder result = new StringBuilder();

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