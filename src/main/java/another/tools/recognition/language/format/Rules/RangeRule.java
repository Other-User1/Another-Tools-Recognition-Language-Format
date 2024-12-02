package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public class RangeRule extends Rule {
	private final Rule rule;
	private final int min;
	private final int max;

	public RangeRule(Rule rule, int min, int max) {
		this.rule = rule;
		this.min = min;
		this.max = max;
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		ArrayList<String> result = new ArrayList<String>();
		ArrayList<String> matched;
		final ArrayList<String> match = rule.match(input, position);

		while (position < input.length() && (matched = rule.match(input, position)) != null) {
			result.addAll(matched);
			position += getPosition(matched);
		}

		if (result.size() >= (match.size() * min) && result.size() <= (match.size() * max)) {
			return result;
		}

		return null;
	}
}