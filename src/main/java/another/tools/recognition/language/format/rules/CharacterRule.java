package another.tools.recognition.language.format.rules;

public class CharacterRule extends Rule {
	private final char character;

	public CharacterRule(char character) {
		this.character = character;
	}

	@Override
	public String match(String input, int position) {
		return (position < input.length() && input.charAt(position) == character) ? String.valueOf(character) : null;
	}
}
