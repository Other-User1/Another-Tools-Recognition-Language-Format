package another.tools.recognition.lenguage.format;

import java.util.ArrayList;

public class Lexer {
	private String input;
	private TokenizerLexer tokenizer;
	private int position;

	public Lexer(String text) {
		this.input = text;
		this.position = 0;
	}

	public void setTokenizerLexer(TokenizerLexer tokenizer) {
		this.tokenizer = tokenizer;
	}

	public ArrayList<Token> tokenizer() {
		ArrayList<Token> tokens = new ArrayList<>();
		while (position < input.length()) {
			char current = input.charAt(position);

			if (Character.isWhitespace(current)) {
				position++;
				continue;
			}

			if (Character.isLetter(current)) {
				StringBuilder letter = new StringBuilder();
				while (position < input.length() && Character.isLetter(input.charAt(position))) {
					letter.append(input.charAt(position));
					position++;
				}
				if (this.tokenizer != null) {
					Token token = tokenizer.identifier(letter.toString());
					if (token != null) {
						tokens.add(token);
					} else {
						if (letter.length() > 1) {
							tokens.add(new Token(letter.toString(), TypeToken.IdentifierToken));
						} else {
							tokens.add(new Token(letter.toString(), TypeToken.LetterToken));
						}
					}
				}
				continue;
			}

			if (Character.isDigit(current)) {
				StringBuilder digit = new StringBuilder();
				while (position < input.length() && Character.isDigit(input.charAt(position))) {
					digit.append(input.charAt(position));
					position++;
				}
				if (digit.length() > 1) {
					tokens.add(new Token(digit.toString(), TypeToken.DigitToken));
				} else {
					tokens.add(new Token(digit.toString(), TypeToken.NumberToken));
				}
			}

			if (this.tokenizer != null) {
				Token token = tokenizer.symbols(current);
				if (token != null) {
					tokens.add(token);
				}
			}
			position++;
		}
		tokens.add(new Token('\0', TypeToken.EndOfFileToken));
		return tokens;
	}
}
