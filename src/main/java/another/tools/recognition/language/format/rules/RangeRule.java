package another.tools.recognition.language.format.rules;

public class RangeRule extends Rule {
	private final Rule rule;
	private final int min;
	private final int max;

	public RangeRule(Rule rule, int min, int max) {
		this.rule = rule;
		this.min = min;
		this.max = max;
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

		if (result.length() >= match.repeat(min).length() && result.length() <= match.repeat(max).length()) {
			return result.toString();
		}

		return null;
	}
}