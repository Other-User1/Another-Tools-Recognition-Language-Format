package another.tools.recognition.language.format.rules;

public class ZeroOrMoreRule extends Rule {
	private final Rule rule;

	public ZeroOrMoreRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public String match(String input, int position) {
		StringBuilder result = new StringBuilder();
		String matched;
		while (position < input.length() && (matched = rule.match(input, position)) != null) {
			result.append(matched);
			position += matched.length();
		}
		return result.toString();
	}
}