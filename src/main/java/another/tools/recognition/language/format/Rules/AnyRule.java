package another.tools.recognition.language.format.Rules;

public class AnyRule implements Rule {
	@Override
	public String match(String input, int position) {
		return position < input.length() ? String.valueOf(input.charAt(position)) : null;
	}
}
