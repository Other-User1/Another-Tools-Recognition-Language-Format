package another.tools.recognition.language.format.rules;

public class CharacterRangeRule extends Rule {
	private final char min;
	private final char max;

	public CharacterRangeRule(char min, char max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public String match(String input, int position) {
		if (position < input.length()) {
			char c = input.charAt(position);
			if (c >= min && c <= max) {
				return String.valueOf(c);
			}
		}
		return null;
	}
}