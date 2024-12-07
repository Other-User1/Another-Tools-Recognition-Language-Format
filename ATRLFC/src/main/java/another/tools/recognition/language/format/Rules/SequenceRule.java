package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SequenceRule extends Rule {
	private final List<Rule> rules;

	public SequenceRule(Rule... rules) {
		this.rules = Arrays.asList(rules);
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		ArrayList<String> result = new ArrayList<>();

		for (Rule rule : rules) {
			ArrayList<String> matched = rule.match(input, position);
			if (matched == null) {
				return null;
			}
			result.addAll(matched);
			position += getPosition(matched);
		}
		return result;
	}
}