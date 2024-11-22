package another.tools.recognition.language.format.rules;

public class OptionalRule extends Rule {
	private final Rule rule;

	public OptionalRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public String match(String input, int position) {
		String matched = rule.match(input, position);
		return matched != null ? matched : "";
	}
}