package another.tools.recognition.lenguage.format;

public class Token {
	private String value;
	private Enum<?> type;

	public Token(String value, Enum<?> type) {
		this.value = value;
		this.type = type;
	}

	public Token(char value, Enum<?> type) {
	    this(String.valueOf(value), type);
	}

	public String getValue() {
		return this.value;
	}

	public Enum<?> getType() {
		return this.type;
	}
}
