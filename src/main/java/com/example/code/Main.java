package com.example.code;

import another.tools.recognition.lenguage.format.*;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static HashMap<String, ArrayList<Object>> variables = new HashMap<>();

	public static void main(String[] args) {
		Syntactic sa = getSyntactic();

		Grammatical variableNew = new Grammatical(
			"boolean", TypeToken.ColonToken,
			TypeToken.IdentifierToken, TypeToken.EqualsToken, KeywordToken.BooleanTypeToken, TypeToken.SemiColonToken
		);

		sa.addGrammaticalRule(variableNew, new ParseAction() {
			@Override
			public void action(ArrayList<String> values) {
				variables.put(values.get(2), new ArrayList<>() {{
					add(values.getFirst());
					add(values.get(4));
				}});
				System.out.println(values);
			}
		});

		sa.parse();
		System.out.println(variables);
	}

	private static Syntactic getSyntactic() {
		Lexer lx = new Lexer("boolean: ab = true; boolean: ac = false;");
		Grammatical.setLexer(lx);

		lx.setTokenizerLexer(new TokenizerLexer() {
			@Override
			public Token symbols(char symbol) {
				return switch (symbol) {
					case ':' -> new Token(symbol, TypeToken.ColonToken);
					case ';' -> new Token(symbol, TypeToken.SemiColonToken);
					case '=' -> new Token(symbol, TypeToken.EqualsToken);
					default -> null;
				};
			}

			@Override
			public Token identifier(String word) {
				return switch (word.toLowerCase()) {
					case "boolean", "float", "string" -> new Token(word, KeywordToken.VariableToken);
					case "true", "false" -> new Token(word, KeywordToken.BooleanTypeToken);
					default -> null;
				};
			}
		});

		return new Syntactic(lx);
	}

	public enum KeywordToken {
		BooleanTypeToken, VariableToken
	}
}