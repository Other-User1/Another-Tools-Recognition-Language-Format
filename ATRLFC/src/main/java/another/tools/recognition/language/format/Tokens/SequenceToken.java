package another.tools.recognition.language.format.Tokens;

import java.util.ArrayList;
import java.util.List;

public class SequenceToken extends Tokens {
	private final ArrayList<Tokens> tokens;

	public SequenceToken(Tokens... tokens) {
		this.tokens = new ArrayList<>(List.of(tokens));
	}

	@Override
	public ArrayList<Token> getTokens() {
		ArrayList<Token> tokens = new ArrayList<>();
		for (Tokens token : this.tokens)
			tokens.addAll(token.getTokens());
		return tokens;
	}
}
