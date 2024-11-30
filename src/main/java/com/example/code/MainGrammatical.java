package com.example.code;

import another.tools.recognition.language.format.Grammaticals.*;
import another.tools.recognition.language.format.Syntactics.SyntacticGrammatical;
import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

import static another.tools.recognition.language.format.Tokens.TokenType.*;


public class MainGrammatical {

	public static class VariableDeclarationGrammar extends SyntacticGrammatical {
		@Override
		public Grammatical run() throws CompilerTaskException {
			return OneOrMore(Sequence(
					CurlyLeftToken,
					(GrammaticalGrammar(
							Sequence(
									WordToken, ColonToken, WordToken
							),
							new GrammaticalGrammar.Action() {
								@Override
								public Grammatical execute(ArrayList<Token> tokens) throws CompilerTaskException {
									return Sequence(SemiColonToken);
								}
							}
					)),
					CurlyRightToken
			));
		}
	}
}