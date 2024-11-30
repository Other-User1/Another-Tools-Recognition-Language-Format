package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

public class RepeatRule implements Rule {
	private final Rule rule;
	private final int permit;

	public RepeatRule(Rule rule, int onlyPermit) {
		this.rule = rule;
		this.permit = onlyPermit;
	}

	@Override
	public String match(String input, int position) throws CompilerTaskException {
		StringBuilder result = new StringBuilder();
		String matched;
		final String match = rule.match(input, position);

		while (position < input.length() && (matched = rule.match(input, position)) != null) {
			result.append(matched);
			position += matched.length();
		}

		if (result.length() == match.repeat(permit).length()) {
			return result.toString();
		}

		return null;
	}
}