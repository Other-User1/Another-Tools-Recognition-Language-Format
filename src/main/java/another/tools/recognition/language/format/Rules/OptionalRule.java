package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

public class OptionalRule implements Rule {
	private final Rule rule;

	public OptionalRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public String match(String input, int position) throws CompilerTaskException {
		String matched = rule.match(input, position);
		return matched != null ? matched : "";
	}
}