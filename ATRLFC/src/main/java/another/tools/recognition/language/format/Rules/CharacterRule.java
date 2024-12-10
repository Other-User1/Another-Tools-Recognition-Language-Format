package another.tools.recognition.language.format.Rules;

import java.util.ArrayList;
import java.util.List;

public class CharacterRule implements Rule {
	private final char character;

	public CharacterRule(char character) {
		this.character = character;
	}

	@Override
	public ArrayList<String> match(String input, int position) {
		return (position < input.length() && input.charAt(position) == character) ? new ArrayList<>(List.of(String.valueOf(character))) : null;
	}
}
