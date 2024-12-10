package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public class OptionalOrMoreRule implements Rule {
	private final Rule rule;

	public OptionalOrMoreRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> matched;
		while (position < input.length() && (matched = rule.match(input, position)) != null) {
			result.addAll(matched);
			position += Rule.getPosition(matched);
		}
		return result;
	}
}