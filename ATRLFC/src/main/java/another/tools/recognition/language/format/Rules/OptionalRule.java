package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public class OptionalRule extends Rule {
	private final Rule rule;

	public OptionalRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		ArrayList<String> matched = rule.match(input, position);
		return matched != null ? matched : new ArrayList<>();
	}
}