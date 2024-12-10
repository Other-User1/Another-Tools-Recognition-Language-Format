package com.example.code;

import another.tools.recognition.language.format.Lexers.*;
import another.tools.recognition.language.format.Rules.*;
import com.java.components.lang.CompilerTaskException;

import static another.tools.recognition.language.format.Tokens.TokenType.*;

import java.util.ArrayList;

import static com.example.code.ExtraTokenType.*;

public class MainTokenizer extends LexerTokenizer {
	@Override
	public Rule skip() throws CompilerTaskException {
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
	public Rule execute() throws CompilerTaskException {
		return Alternatives(
				//keywords(),
				identifiers(),
				ordinals(),
				punctuation()
		);
	}

	public Rule keywords() throws CompilerTaskException {
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
				Token(Character('0'), NeutralToken),
				Token(Text("for"), ForToken)
		);
	}

	public Rule identifiers() throws CompilerTaskException {
		return Alternatives(
				Token(
						Sequence(
								Alternatives(
										CharacterRange('a', 'z'),
										CharacterRange('A', 'Z')
								),
								OptionalOrMore(
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

	public Rule ordinals() throws CompilerTaskException {
		return Alternatives(
				Token(
						Sequence(
								CharacterRange('1', '9'),
								OptionalOrMore(
										CharacterRange('0', '9')
								), Alternatives(
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
						), new RulesRule() {
							@Override
							public Enum<?> execute(ArrayList<String> value) throws CompilerTaskException {
								if (value.getLast().equals("b") || value.getLast().equals("B")) return ByteToken;
								if (value.getLast().equals("s") || value.getLast().equals("S")) return ShortToken;
								if (value.getLast().equals("i") || value.getLast().equals("I")) return IntegerToken;
								if (value.getLast().equals("l") || value.getLast().equals("L")) return LongToken;
								if (value.getLast().equals("f") || value.getLast().equals("F")) return FloatToken;
								if (value.getLast().equals("d") || value.getLast().equals("D")) return DoubleToken;
								// if (value.getLast().equals("n") || value.getLast().equals("N")) return NumberToken;
								return NumberToken;
							}
						}
				),
				Token(
						Sequence(
								CharacterRange('1', '9'),
								OptionalOrMore(
										CharacterRange('0', '9')
								), Character('.'), OptionalOrMore(
										CharacterRange('0', '9')
								), Alternatives(
										Character('n'),
										Character('f'),
										Character('d'),
										Character('N'),
										Character('F'),
										Character('D')
								)
						), new RulesRule() {
							@Override
							public Enum<?> execute(ArrayList<String> value) {
								if (value.getLast().equals("f") || value.getLast().equals("F")) return FloatToken;
								if (value.getLast().equals("d") || value.getLast().equals("D")) return DoubleToken;
								if (value.getLast().equals("n") || value.getLast().equals("N")) return NumberToken;
								return NumberToken;
							}
						}
				)
		);
	}

	public Rule punctuation() throws CompilerTaskException {
		return Alternatives(
				symbols(),
				characters()
		);
	}

	public Rule symbols() throws CompilerTaskException {
		return Alternatives(
				Token(
						Sequence(
								Character('"'),
								OptionalOrMore(
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

	public Rule characters() throws CompilerTaskException {
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