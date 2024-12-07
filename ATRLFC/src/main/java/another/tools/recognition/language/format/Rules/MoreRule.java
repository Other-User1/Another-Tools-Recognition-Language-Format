package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public class MoreRule extends Rule {
	private final Rule rule;

	public MoreRule(Rule rule) {
		this.rule = rule;
	}
	
	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> matched;

		for (int index = 0; index < 2; index++) {
			matched = rule.match(input, position);

			if (matched == null) {
				return null;
			}

			result.addAll(matched);
			position += matched.size();
		}

		while (position < input.length() && (matched = rule.match(input, position)) != null) {
			result.addAll(matched);
			position += matched.size();
		}

		return result;
	}
}
