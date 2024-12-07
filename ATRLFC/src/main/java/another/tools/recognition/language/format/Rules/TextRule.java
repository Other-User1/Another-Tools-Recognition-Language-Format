package another.tools.recognition.language.format.Rules;

import java.util.ArrayList;
import java.util.List;

public class TextRule extends Rule {
	final String text;

	public TextRule(String text) {
		this.text = text;
	}

	@Override
	public ArrayList<String> match(String input, int position) {
		String target = input.substring(position, Math.min(position + text.length(), input.length()));
		return target.equals(text) ? new ArrayList<>(List.of(text)) : null;
	}
}