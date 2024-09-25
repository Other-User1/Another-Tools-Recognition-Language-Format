package another.tools.recognition.lenguage.format;

import java.util.ArrayList;

public class Syntactic {
	private Lexer lexer;
	private ArrayList<Token> tokens;
	private int currentTokenIndex;
	private ArrayList<GrammarAction> grammarList;

	public Syntactic(Lexer lexer) {
		this.lexer = lexer;
		this.tokens = lexer.tokenizer();
		this.currentTokenIndex = 0;
		this.grammarList = new ArrayList<>();
	}

	public void addGrammaticalRule(Grammatical grammatical, ParseAction action) {
		grammarList.add(new GrammarAction(grammatical, action));
	}

	public void subParse() {
		while (currentTokenIndex < tokens.size()) {
			boolean matched = false;

			for (GrammarAction pair : grammarList) {
				if (tryParse(pair.gramatical)) {
					pair.action.action(reverse(new ArrayList<>() {{
						for (int i = 0; i < pair.gramatical.getExpectedTokens().length; i++) {
							add(tokens.get(currentTokenIndex - i - 1).getValue());
						}
					}}));
					matched = true;
					break;
				}
			}

			if (matched) {
				return;
			}
		}
	}

	int i = 1, verifyLength, index = 0;

	public void parse() {
		int tmp = 0;
		while (currentTokenIndex < tokens.size()) {
			boolean matched = false;

			for (GrammarAction pair : grammarList) {
				if (tryParse(pair.gramatical)) {
					pair.action.action(reverse(new ArrayList<>() {{
						for (int i = 0; i < pair.gramatical.getExpectedTokens().length; i++) {
							add(tokens.get(currentTokenIndex - i - 1).getValue());
						}
					}}));
					matched = true;
					break;
				}
			}

			if (!matched) {
				tmp = currentTokenIndex;
				currentTokenIndex++;
			}
			if (tokens.getLast().getType() == TypeToken.EndOfFileToken && !matched) {
				if (tokens.get(currentTokenIndex - 1).getType() != TypeToken.EndOfFileToken) {
					if (i == 1) {
						int tmp2 = grammarList.get(index).gramatical.getExpectedTokens().length;
						throw new RuntimeException("Length not margin in: " + tokens.get(verifyLength - 1).getType() + ", only recognition '" + grammarList.get(index).gramatical.getExpectedTokens()[tmp2 - 1] + "'");
					}
					throw new RuntimeException("Rule not margin in: " + tokens.get(tmp).getValue());
				}
			}
		}
	}

	public ArrayList<String> reverse(ArrayList<String> list) {
		ArrayList<String> reversed = new ArrayList<>();
		for (int i = list.size() - 1; i > -1; i--) {
			reversed.add(list.get(i));
		}
		return reversed;
	}

	private boolean tryParse(Grammatical grammatical) {
		int tempIndex = currentTokenIndex;
		int length = grammatical.getExpectedTokens().length;
		verifyLength = 0;
		for (Object expectedType : grammatical.getExpectedTokens()) {
			switch (expectedType) {
				case TypeToken tt: {
					if (tempIndex >= tokens.size() || tokens.get(tempIndex).getType() != tt) {
						return false;
					}
					break;
				}
				case String s: {
					if (tempIndex >= tokens.size() || !tokens.get(tempIndex).getValue().equals(s)) {
						return false;
					}
					break;
				}
				default:
			}
			tempIndex++;
			verifyLength++;
		}
		if (verifyLength != length) {
			i = 0;
			return false;
		}
		currentTokenIndex += grammatical.getExpectedTokens().length;
		index++;
		return true;
	}

	private static class GrammarAction {
		Grammatical gramatical;
		ParseAction action;

		GrammarAction(Grammatical gramatical, ParseAction action) {
			this.gramatical = gramatical;
			this.action = action;
		}
	}
}
