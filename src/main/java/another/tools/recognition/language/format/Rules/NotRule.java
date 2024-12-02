package another.tools.recognition.language.format.Rules;

import another.tools.recognition.language.format.Tokens.TokenText;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class NotRule extends Rule {
	private final Rule rule;

	public NotRule(Rule rule) {
		this.rule = rule;
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		ArrayList<String> value = rule.match(input, position);
		if (value == null) {
			if (rule instanceof TextRule tr) new ArrayList<>(List.of(input.substring(position, position + tr.text.length())));
			return new ArrayList<>(List.of(input.substring(position, position + 1)));
		}
		return null;
	}
}
