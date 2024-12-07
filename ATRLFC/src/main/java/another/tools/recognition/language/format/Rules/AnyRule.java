package another.tools.recognition.language.format.Rules;

import java.util.ArrayList;
import java.util.List;

public class AnyRule extends Rule {
	@Override
	public ArrayList<String> match(String input, int position) {
		return position < input.length() ? new ArrayList<>(List.of(String.valueOf(input.charAt(position)))) : null;
	}
}
