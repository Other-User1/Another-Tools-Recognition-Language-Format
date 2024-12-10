package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public class ZeroOrMoreRule implements Rule {
	private final Rule rule;

	public ZeroOrMoreRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> matched = rule.match(input, position);

		if (matched == null) return result;

		result.addAll(matched);
		position += Rule.getPosition(matched);
		matched = rule.match(input, position);

		if (matched == null) return null;

		do {
			result.addAll(matched);
			position += Rule.getPosition(matched);
		} while (position < input.length() && (matched = rule.match(input, position)) != null);
		return result;
	}
}