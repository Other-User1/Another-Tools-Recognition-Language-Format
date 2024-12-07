package another.tools.recognition.language.format.Tokens;

import java.util.ArrayList;

public class RepeatToken extends Tokens {
	private final Tokens tokens;
	private final int count;

	public RepeatToken(Tokens tokens, int count) {
		this.tokens = tokens;
		this.count = count;
	}

	@Override
	public ArrayList<Token> getTokens() {
		ArrayList<Token> Rtokens = new ArrayList<>();
		for (int i = 0; i < count; i++) {
			Rtokens.addAll(tokens.getTokens());
		}
		return Rtokens;
	}
}
