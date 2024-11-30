package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.Arrays;
import java.util.List;

public class AlternativesRule implements Rule {
	private final List<Rule> rules;

	public AlternativesRule(Rule... rules) {
		this.rules = Arrays.asList(rules);
	}

	@Override
	public String match(String input, int position) throws CompilerTaskException {
		for (Rule rule : rules) {
			String matched = rule.match(input, position);
			if (matched != null) {
				return matched;
			}
		}
		return null;
	}
}