package another.tools.recognition.language.format.rules;

public abstract class Rule {
	private Enum<?> tokenType;

	public Rule() { }

	public Rule(Enum<?> tokenType) {
		this.tokenType = tokenType;
	}

	public Enum<?> getTokenType() {
		return tokenType;
	}

	public abstract String match(String input, int position);
}
