package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

public class NotRule implements Rule {
	private final Rule rule;

	public NotRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public String match(String input, int position) throws CompilerTaskException {
		String value = rule.match(input, position);
		if (value == null) {
			if (this.rule instanceof TextRule tr) {
				return input.substring(position, position + tr.text.length());
			}
			return input.substring(position, position + 1);
		}
		return null;
	}
}
