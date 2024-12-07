package com.example.code;

import another.tools.recognition.language.format.Syntactics.*;
import another.tools.recognition.language.format.Tokens.*;
import another.tools.recognition.language.format.Grammaticals.*;
import static another.tools.recognition.language.format.Tokens.TokenType.*;
import static com.example.code.ExtraTokenType.IdentifierToken;
import static com.example.code.ExtraTokenType.NeutralToken;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.function.Supplier;

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
					mulAndDiv(), ZeroOrMore(
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
					Term(), ZeroOrMore(
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
							Number(), Sequence(Text('('), addAndSub(), Text(')'))
					)
			);
		}

		public Grammatical Number() {
			return Sequence(
					Digit(), ZeroOrMore(Digit())
			);
		}

		public Grammatical Digit() {
			return NumberToken;
		}
	}
}