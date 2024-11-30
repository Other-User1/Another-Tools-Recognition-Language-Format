package another.tools.recognition.language.format.Rules;

public class TextRule implements Rule {
	final String text;

	public TextRule(String text) {
		this.text = text;
	}

	@Override
	public String match(String input, int position) {
		String target = input.substring(position, Math.min(position + text.length(), input.length()));
		return target.equals(text) ? text : null;
	}
}