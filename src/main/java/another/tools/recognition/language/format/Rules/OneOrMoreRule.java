package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

public class OneOrMoreRule implements Rule {
	private final Rule rule;

	public OneOrMoreRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public String match(String input, int position) throws CompilerTaskException {
		StringBuilder result = new StringBuilder();
		String matched = rule.match(input, position);

		if (matched == null) {
			return null;
		}

		while (position < input.length() && (matched = rule.match(input, position)) != null) {
			result.append(matched);
			position += matched.length();
		}

		return result.toString();
	}
}