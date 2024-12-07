package another.tools.recognition.language.format.Tokens;

import another.tools.recognition.language.format.Grammaticals.Grammatical;
import another.tools.recognition.language.format.Grammaticals.SequenceGrammatical;

import java.util.ArrayList;
import java.util.List;

public final class TokenText extends Tokens implements Grammatical {
	private final String text;
	private Grammatical grammar = new SequenceGrammatical();

	public TokenText(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) {
		if (list.get(position).getImage().equals(text)) {
			grammar = new SequenceGrammatical((Grammatical) list.get(position).getType());
			return new ArrayList<>(list.subList(position, position + 1));
		}
		TokenType.setPosition(position);
		return null;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() {
		return new ArrayList<>(List.of(grammar));
	}

	@Override
	public ArrayList<Token> getTokens() {
		return new ArrayList<>(List.of(new Token(text, SpecialTokenType.VirtualToken)));
	}
}
