package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public class ZeroOrMoreRule extends Rule {
	private final Rule rule;

	public ZeroOrMoreRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> matched;
		while (position < input.length() && (matched = rule.match(input, position)) != null) {
			result.addAll(matched);
			position += getPosition(matched);
		}
		return result;
	}
}