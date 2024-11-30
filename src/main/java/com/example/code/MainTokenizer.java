package com.example.code;

import another.tools.recognition.language.format.Lexers.LexerTokenizer;
import another.tools.recognition.language.format.Rules.Rule;
import another.tools.recognition.language.format.Rules.RuleAction;

import static another.tools.recognition.language.format.Tokens.TokenType.*;

import static com.example.code.ExtraTokenType.*;

public class MainTokenizer extends LexerTokenizer {
	@Override
	public Rule skip() {
		return OneOrMore(
				Alternatives(
					Character(' '),
					Character('\r'),
					Character('\t'),
					Character('\f'),
					Character('\n')
				)
		);
	}

	@Override
	public Rule execute() {
		return Alternatives(
				keywords(),
				identifiers(),
				ordinals(),
				punctuation()
		);
	}

	public Rule keywords() {
		return Alternatives(
				Token(Text("module"), ModuleToken),
				Token(Text("package"), PackageToken),
				Token(Text("import"), ImportToken),
				Token(Text("static"), StaticToken),
				Token(Text("public"), PublicToken),
				Token(Text("private"), PrivateToken),
				Token(Text("protected"), ProtectedToken),
				Token(Text("open"), OpenToken),
				Token(Text("close"), CloseToken),
				Token(Text("exner"), ExnerToken),
				Token(Text("inner"), InnerToken),
				Token(Text("external"), ExternalToken),
				Token(Text("internal"), InternalToken),
				Token(Text("sealed"), SealedToken),
				Token(Text("abstract"), AbstractToken),
				Token(Text("final"), FinalToken),
				Token(Text("native"), NativeToken),
				Token(Text("function"), FunctionToken),
				Token(Text("neutral"), NeutralToken),
				Token(Character('0'), NeutralToken)
		);
	}

	public Rule identifiers() {
		return Alternatives(
				Token(
						Sequence(
								Alternatives(
										CharacterRange('a', 'z'),
										CharacterRange('A', 'Z')
								),
								ZeroOrMore(
										Alternatives(
												Alternatives(
														CharacterRange('a', 'z'),
														CharacterRange('A', 'Z'),
														CharacterRange('0', '9')
												),
												Character('_'),
												Character('$')
										)
								)
						),
						IdentifierToken
				)
		);
	}

	public Rule ordinals() {
		return Alternatives(
				Token(
						Sequence(
								CharacterRange('1', '9'),
								ZeroOrMore(
										CharacterRange('0', '9')
								), Optional(
										Alternatives(
												Character('n'),
												Character('b'),
												Character('s'),
												Character('i'),
												Character('l'),
												Character('f'),
												Character('d'),
												Character('N'),
												Character('B'),
												Character('S'),
												Character('I'),
												Character('L'),
												Character('F'),
												Character('D')
										)
								)
						), new RuleAction() {
							@Override
							public Enum<?> execute(String value) {
								if (value.endsWith("b") || value.endsWith("B")) return ByteToken;
								if (value.endsWith("s") || value.endsWith("S")) return ShortToken;
								if (value.endsWith("i") || value.endsWith("I")) return IntegerToken;
								if (value.endsWith("l") || value.endsWith("L")) return LongToken;
								if (value.endsWith("f") || value.endsWith("F")) return FloatToken;
								if (value.endsWith("d") || value.endsWith("D")) return DoubleToken;
								if (value.endsWith("n") || value.endsWith("N")) return NumberToken;
								return NumberToken;
							}
						}
				),
				Token(
						Sequence(
								CharacterRange('1', '9'),
								ZeroOrMore(
										CharacterRange('0', '9')
								), Character('.'), ZeroOrMore(
										CharacterRange('0', '9')
								), Alternatives(
										Character('n'),
										Character('f'),
										Character('d'),
										Character('N'),
										Character('F'),
										Character('D')
								)
						), new RuleAction() {
							@Override
							public Enum<?> execute(String value) {
								if (value.endsWith("f") || value.endsWith("F")) return FloatToken;
								if (value.endsWith("d") || value.endsWith("D")) return DoubleToken;
								if (value.endsWith("n") || value.endsWith("N")) return NumberToken;
								return NumberToken;
							}
						}
				)
		);
	}

	public Rule punctuation() {
		return Alternatives(
				symbols(),
				characters()
		);
	}

	public Rule symbols() {
		return Alternatives(
				Token(
						Sequence(
								Character('"'),
								ZeroOrMore(
										Not(
												Alternatives(
														Character('"'),
														Character('\n'),
														Character('\f')
												)
										)
								),
								Character('"')
						),
						StringLiteralToken
				),
				Token(
						Sequence(
								Character('\''),
								Not(Character('\'')),
								Character('\'')
						),
						CharacterLiteralToken
				)
		);
	}

	public Rule characters() {
		return Alternatives(
				Token(Text("->"), ArrowToken),
				Token(Character(';'), SemiColonToken),
				Token(Character(':'), ColonToken),
				Token(Character('.'), PointToken),
				Token(Character(','), CommaToken),
				Token(Character('='), EqualToken),
				Token(Character('-'), MinusToken),
				Token(Character('+'), PlusToken),
				Token(Character('*'), StarToken),
				Token(Character('/'), SlashToken),
				Token(Character('('), ParenthesisLeftToken),
				Token(Character(')'), ParenthesisRightToken),
				Token(Character('['), SquareLeftToken),
				Token(Character(']'), SquareRightToken),
				Token(Character('{'), CurlyLeftToken),
				Token(Character('}'), CurlyRightToken),
				Token(Character('>'), GreaterToken),
				Token(Character('<'), LessToken),
				Token(Character('&'), AmpersandToken),
				Token(Character('|'), VerticalLineToken),
				Token(Character('?'), QuestionToken),
				Token(Character('!'), ExclamationToken),
				Token(Character('_'), LowLineToken),
				Token(Character('"'), QuotationToken),
				Token(Character('\''), ApostropheToken)
		);
	}
}