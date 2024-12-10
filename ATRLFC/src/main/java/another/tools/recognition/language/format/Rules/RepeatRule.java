package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public class RepeatRule implements Rule {
	private final Rule rule;
	private final int permit;

	public RepeatRule(Rule rule, int onlyPermit) {
		this.rule = rule;
		this.permit = onlyPermit;
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> matched;
		final ArrayList<String> match = rule.match(input, position);

		while (position < input.length() && (matched = rule.match(input, position)) != null) {
			result.addAll(matched);
			position += Rule.getPosition(matched);
		}

		if (result.size() == (match.size() * permit)) {
			return result;
		}

		return null;
	}
}