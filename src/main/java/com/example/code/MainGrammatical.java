package com.example.code;

import another.tools.recognition.language.format.Syntactics.*;
import another.tools.recognition.language.format.Tokens.*;
import another.tools.recognition.language.format.Grammaticals.*;
import static another.tools.recognition.language.format.Tokens.TokenType.*;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public class MainGrammatical {

	public static class VariableDeclarationGrammar extends SyntacticGrammatical {
		@Override
		public Grammatical run() {
			return Action(
					addAndSub(),
					new SyntacticAction() {
						@Override
						public void execute(ArrayList<Token> tokens) throws CompilerTaskException {
							for (Token token : tokens) {
								System.out.print(token.getImage());
								System.out.print(' ');
							};
							System.out.println();
						}
					}
			);
		}

		public Grammatical addAndSub() {
			return Sequence(
					mulAndDiv(), OptionalOrMore(
							Sequence(
									Alternatives(
											Text('+'), Text('-')
									), mulAndDiv()
							)
					)
			);
		}

		public Grammatical mulAndDiv() {
			return Sequence(
					Term(), OptionalOrMore(
							Sequence(
									Alternatives(
											Text('*'), Text('/')
									), Term()
							)
					)
			);
		}

		public Grammatical Term() {
			return Recursive(() ->
					Alternatives(
							NumberToken, Sequence(Text('('), addAndSub(), Text(')'))
					)
			);
		}

		public Grammatical Number() {
			return Sequence(
					Digit(), OptionalOrMore(Digit())
			);
		}

		public Grammatical Digit() {
			return NumberToken;
		}
	}
}