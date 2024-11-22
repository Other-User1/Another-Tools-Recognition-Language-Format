package another.tools.recognition.language.format.rules;

public class TextRule extends Rule {
	private final String text;

	public TextRule(String text) {
		this.text = text;
	}

	@Override
	public String match(String input, int position) {
		String target = input.substring(position, Math.min(position + text.length(), input.length()));
		return target.equals(text) ? text : null;
	}
}