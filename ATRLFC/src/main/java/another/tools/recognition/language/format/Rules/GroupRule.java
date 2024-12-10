package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupRule implements Rule {
	private final Rule rule;

	public GroupRule(Rule rules) {
		this.rule = rules;
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		StringBuilder sb = new StringBuilder();

		ArrayList<String> matched = rule.match(input, position);
		if (matched == null) {
			return null;
		}
		for (String element : matched)
			sb.append(element);

		return new ArrayList<>(List.of(sb.toString()));
	}
}