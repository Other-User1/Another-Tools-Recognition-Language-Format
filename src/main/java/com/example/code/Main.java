package com.example.code;

import another.tools.recognition.lenguage.format.*;

import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Syntactic sa = getSyntactic();

		Grammatical variableNew = new Grammatical(
			TypeToken.DigitToken, TypeToken.PlusToken, TypeToken.DigitToken
		);

		sa.addGrammaticalRule(variableNew, new ParseAction() {
			@Override
			public void action(ArrayList<String> values) {
				System.out.println(Integer.parseInt(values.getFirst()) + Integer.parseInt(values.getLast()));
			}
		});

		sa.parse();
	}

	private static Syntactic getSyntactic() {
		Lexer lx = new Lexer("1 + 3");
		Grammatical.setLexer(lx);

		lx.setTokenizerLexer(new TokenizerLexer() {
			@Override
			public Token symbols(char symbol) {
				return switch (symbol) {
					case '+' -> new Token(symbol, TypeToken.PlusToken);
					default -> null;
				};
			}

			@Override
			public Token identifier(String word) {
				return null;
			}
		});

		return new Syntactic(lx);
	}

	public enum KeywordToken {
		BooleanTypeToken, VariableToken
	}
}