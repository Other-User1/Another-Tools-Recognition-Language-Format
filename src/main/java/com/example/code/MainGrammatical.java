package com.example.code;

import another.tools.recognition.language.format.Syntactics.*;
import another.tools.recognition.language.format.Tokens.*;
import another.tools.recognition.language.format.Grammaticals.*;

import static another.tools.recognition.language.format.Tokens.TokenType.*;
import static com.example.code.ExtraTokenType.*;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public class MainGrammatical extends SyntacticGrammatical {
	@Override
	public Grammatical run() {
		return Variable();
	}

	public Grammatical Variable() {
		return Action(
				GrammaticalGrammar(
						Sequence(
							IdentifierToken, ColonToken, IdentifierToken
						), new GrammarGrammatical.Action() {
							@Override
							public Grammatical execute(ArrayList<Token> tokens) throws CompilerTaskException {
								return Grammar(new Grammar() {
									@Override
									public Grammatical run() {
										return Sequence(
												Optional(
														initialize(tokens.getFirst().getImage())
												), SemiColonToken
										);
									}

									private Grammatical initialize(String type) {
										return Sequence(
												EqualToken,
												switch (type) {
													case "string" -> StringLiteralToken;
													case "character" -> CharacterLiteralToken;
													case "number" -> NumberToken;
													case "integer" -> IntegerToken;
													default -> Sequence(
															Text("new"), Text(type), ParenthesisLeftToken, ParenthesisRightToken
													);
												}
										);
									}
								});
							}
						}
				), new SyntacticAction() {
					@Override
					public void execute(ArrayList<Token> tokens) throws CompilerTaskException {
						for (Token token : tokens) {
							System.out.print(token.getImage());
							System.out.print(' ');
						}
						System.out.println();
					}
				}
		);
	}
}