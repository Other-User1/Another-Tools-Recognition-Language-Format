package another.tools.recognition.language.format.Tokens;

import java.util.ArrayList;

public abstract class GrammaticalToken {
	protected Tokens Text(String target) {
		return new Token(target, SpecialTokenType.VirtualToken);
	}

	protected Tokens Character(char target) {
		return new Token(target, SpecialTokenType.VirtualToken);
	}

	protected Tokens Repeat(Tokens tokens, int count) {
		return new RepeatToken(tokens, count);
	}

	protected Tokens Sequence(Tokens... tokens) {
		return new SequenceToken(tokens);
	}
}
