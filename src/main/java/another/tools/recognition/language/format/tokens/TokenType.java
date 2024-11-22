package another.tools.recognition.language.format.tokens;

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
	PlusToken; // +

	private Grammatical grammar;
	private static int position = -1;

	static void setPosition(int position) {
		TokenType.position = position;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) {
		if (list.get(position).getImage().equals("\0")) {
			return new ArrayList<>(list.subList(position - 1, position));
		}
		if (list.get(position).getType().toString().equals(name())) {
			grammar = new SequenceGrammatical((Grammatical) list.get(position).getType());
			return new ArrayList<>(list.subList(position, position + 1));
		}
		TokenType.position = position;
		return null;
	}

	public static ArrayList<Token> isMatchUtil(ArrayList<Token> list, int position, String name) {
		if (list.get(position).getImage().equals("\0")) {
			return null;
		}
		if (list.get(position).getType().toString().equals(name)) {
			return new ArrayList<>(list.subList(position, position + 1));
		}
		TokenType.position = position;
		return null;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() {
		return new ArrayList<>(List.of(grammar));
	}

	public static int getPosition() {
		return position;
	}
}
