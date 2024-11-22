package another.tools.recognition.language.format.rules;

public class NotRule extends Rule {
	private final Rule rule;

	public NotRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public String match(String input, int position) {
		String value = rule.match(input, position);
		if (value == null) return input.substring(position, position + 1);
		return null;
	}
}
