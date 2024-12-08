package another.tools.recognition.language.format.Tokens;

import another.tools.recognition.language.format.Grammaticals.Grammatical;
import another.tools.recognition.language.format.Grammaticals.SequenceGrammatical;

import java.util.ArrayList;
import java.util.List;

public enum TokenType implements Grammatical {
	WordToken, // Hola
	DecimalNumberToken, // 12.34
	NumberToken, // 1234
	ParenthesisLeftToken, // (
	ParenthesisRightToken, // )
	SquareLeftToken, // [
	SquareRightToken, // ]
	CurlyLeftToken, // {
	CurlyRightToken, // }
	EqualToken, // =
	GreaterToken, // >
	SemiColonToken, // ;
	ColonToken, // :
	MinusToken, // -
	CommaToken, // ,
	PointToken, // .
	LessToken, // <
	AmpersandToken, // &
	VerticalLineToken, // |
	QuestionToken, // ?
	ExclamationToken, // !
	LowLineToken, // _
	QuotationToken, // "
	ApostropheToken, // '
	PlusToken, SlashToken, StarToken; // +

	private Grammatical grammar = new SequenceGrammatical();
	private static int position = -1;

	static void setPosition(int position) {
		TokenType.position = position;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) {
		TokenType.position = position;
		if (list.get(position).getType().name().equals(name())) {
			grammar = new SequenceGrammatical((Grammatical) list.get(position).getType());
			return new ArrayList<>(list.subList(position, position + 1));
		}
		return null;
	}

	public static ArrayList<Token> match(ArrayList<Token> list, int position, String name) {
		TokenType.position = position;
		if (list.get(position).getType().toString().equals(name)) {
			return new ArrayList<>(list.subList(position, position + 1));
		}
		return null;
	}

	public static ArrayList<Grammatical> getGrammars(String name) {
		return new ArrayList<>(List.of(new TokenText(name)));
	}

	@Override
	public ArrayList<Grammatical> getGrammars() {
		return new ArrayList<>(List.of(grammar));
	}

	public static int getPosition() {
		return position;
	}
}
