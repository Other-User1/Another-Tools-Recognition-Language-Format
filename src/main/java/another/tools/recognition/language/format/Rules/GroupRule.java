package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupRule extends Rule {
	private final List<Rule> rules;

	public GroupRule(Rule... rules) {
		this.rules = Arrays.asList(rules);
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		StringBuilder sb = new StringBuilder();

		for (Rule rule : rules) {
			ArrayList<String> matched = rule.match(input, position);
			if (matched == null) {
				return null;
			}
			for (String element : matched)
				sb.append(element);
			position += getPosition(matched);
		}
		return new ArrayList<>(List.of(sb.toString()));
	}
}