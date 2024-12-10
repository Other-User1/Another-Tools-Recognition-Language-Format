package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class TextRule implements Rule {
	final String text;

	public TextRule(String text) {
		this.text = text;
	}

	@Override
	public ArrayList<String> match(String input, int position) throws CompilerTaskException {
		//String target = input.substring(position, Math.min(position + text.length(), input.length()));
		return test(input, position) ? new ArrayList<>(List.of(text)) : null;
	}

	public boolean test(String input, int position) throws CompilerTaskException {
		ArrayList<CharacterRule> chars = new ArrayList<>();
		for (int i = 0; i < text.length(); i++)
			chars.add(new CharacterRule(text.charAt(i)));
		SequenceRule rule = new SequenceRule(chars.toArray(new CharacterRule[0]));
		return rule.match(input, position) != null;
	}
}