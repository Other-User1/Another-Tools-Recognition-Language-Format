package com.example.code;

import another.tools.recognition.language.format.Grammaticals.Grammar;
import another.tools.recognition.language.format.Grammaticals.Grammatical;
import another.tools.recognition.language.format.Grammaticals.GrammaticalGrammar;
import another.tools.recognition.language.format.syntactics.SyntacticGrammatical;
import another.tools.recognition.language.format.tokens.Token;
import com.java.components.lang.CompilerTaskException;

import static another.tools.recognition.language.format.tokens.TokenType.*;

import java.util.ArrayList;

import static com.example.code.tmp.Main.ExtraTokenType.*;

public class MainGrammatical {
	private static SyntacticGrammatical sg;

	private static Grammatical visibility() {
		return sg.Alternatives(
				ModuleToken,
				PackageToken,
				PrivateToken,
				ProtectedToken,
				PublicToken
		);
	}

	public static class VariableNumberDeclarationGrammar extends SyntacticGrammatical {
		@Override
		public Grammatical run() {
			return Sequence(
					Optional(visibility()), GrammaticalGrammar(Sequence(
							WordToken, ColonToken, WordToken
					), new GrammaticalGrammar.Action() {
						private int index = 0;

						@Override
						public ArrayList<Grammatical> execute(ArrayList<Token> tokens) throws CompilerTaskException {
							ArrayList<Grammatical> grammars = new ArrayList<>();
							if (equals(tokens.get(index).getImage(), "module", "package", "public", "private", "protected")) {
								index = index + 1;
							}
							String type = tokens.get(index).getImage();
							if (equals(type, "byte", "short", "integer", "long")) {
								grammars.addAll(new Grammar() {
									@Override
									public Grammatical run() {
										return Sequence(initialize(), moreVariable());
									}

									public Grammatical initialize() {
										return Optional(Sequence(EqualToken, Alternatives(NumberToken, NeutralToken)));
									}

									public Grammatical moreVariable() {
										return ZeroOrMore(Sequence(CommaToken, WordToken, initialize()));
									}
								}.getGrammars());
							} else if (equals(type, "float", "double")) {
								grammars.addAll(new Grammar() {
									@Override
									public Grammatical run() {
										return Sequence(initialize(), moreVariable());
									}

									public Grammatical initialize() {
										return Optional(Sequence(EqualToken, Alternatives(DecimalNumberToken, NumberToken, NeutralToken)));
									}

									public Grammatical moreVariable() {
										return ZeroOrMore(Sequence(CommaToken, WordToken, initialize()));
									}
								}.getGrammars());
							} else if (type.equals("string")) {
								grammars.addAll(new Grammar() {
									@Override
									public Grammatical run() {
										return Sequence(initialize(), moreVariable());
									}

									public Grammatical initialize() {
										return Optional(Sequence(EqualToken, StringLiteralToken));
									}

									public Grammatical moreVariable() {
										return ZeroOrMore(Sequence(CommaToken, WordToken, initialize()));
									}
								}.getGrammars());
							} else if (type.equals("character")) {
								grammars.addAll(new Grammar() {
									@Override
									public Grammatical run() {
										return Sequence(initialize(), moreVariable());
									}

									public Grammatical initialize() {
										return Optional(Sequence(EqualToken, CharacterLiteralToken));
									}

									public Grammatical moreVariable() {
										return ZeroOrMore(Sequence(CommaToken, WordToken, initialize()));
									}
								}.getGrammars());
							}
							if (grammars.isEmpty()) {
								return new ArrayList<>();
							}
							return grammars;
						}

						public boolean equals(String equal, String... tos) {
							for (String to : tos) {
								if (equal.equals(to)) {
									return true;
								}
							}
							return false;
						}
					}), SemiColonToken
			);
		}
	}
}