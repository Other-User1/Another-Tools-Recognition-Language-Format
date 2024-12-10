package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlternativesRule implements Rule {
	private final List<Rule> rules;

	public AlternativesRule(Rule... rules) {
		this.rules = Arrays.asList(rules);
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		for (Rule rule : rules) {
			ArrayList<String> matched = rule.match(input, position);
			if (matched != null) {
				return matched;
			}
		}
		return null;
	}
}