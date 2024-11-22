package another.tools.recognition.language.format.rules;

import java.util.ArrayList;

public class PermitRangeRule extends Rule {
	private final Rule rule;
	private final ArrayList<Integer> permits;

	public PermitRangeRule(Rule rule, int... onlyPermit) {
		this.rule = rule;
		this.permits = of(onlyPermit);
	}

	private static ArrayList<Integer> of(int... is) {
		ArrayList<Integer> result = new ArrayList<>();
		for (int i : is) {
			result.add(i);
		}
		return result;
	}

	@Override
	public String match(String input, int position) {
		StringBuilder result = new StringBuilder();
		String matched;
		final String match = rule.match(input, position);

		while (position < input.length() && (matched = rule.match(input, position)) != null) {
			result.append(matched);
			position += matched.length();
		}

		for (int only : permits) {
			if (result.length() == match.repeat(only).length()) {
				return result.toString();
			}
		}

		return null;
	}
}