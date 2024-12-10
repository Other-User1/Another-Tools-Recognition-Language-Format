package another.tools.recognition.language.format.Rules;

import java.util.ArrayList;
import java.util.List;

public class CharacterRangeRule implements Rule {
	private final char min;
	private final char max;

	public CharacterRangeRule(char min, char max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public ArrayList<String> match(String input, int position) {
		if (position < input.length()) {
			char c = input.charAt(position);
			if (c >= min && c <= max) {
				return new ArrayList<>(List.of(String.valueOf(c)));
			}
		}
		return null;
	}
}