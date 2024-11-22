package com.example.code;

import another.tools.recognition.language.format.lexers.LexerTokenizer;
import another.tools.recognition.language.format.rules.Rule;
import another.tools.recognition.language.format.tokens.TokenType;
import com.example.code.tmp.Main;

public class MainTokenizer extends LexerTokenizer {
	@Override
	public Rule skip() {
		return ZeroOrMore(
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
				Token(Text("module"), com.example.code.tmp.Main.ExtraTokenType.ModuleToken),
				Token(Text("package"), com.example.code.tmp.Main.ExtraTokenType.PackageToken),
				Token(Text("import"), com.example.code.tmp.Main.ExtraTokenType.ImportToken),
				Token(Text("static"), com.example.code.tmp.Main.ExtraTokenType.StaticToken),
				Token(Text("public"), com.example.code.tmp.Main.ExtraTokenType.PublicToken),
				Token(Text("private"), com.example.code.tmp.Main.ExtraTokenType.PrivateToken),
				Token(Text("protected"), com.example.code.tmp.Main.ExtraTokenType.ProtectedToken),
				Token(Text("sealed"), com.example.code.tmp.Main.ExtraTokenType.SealedToken),
				Token(Text("protected"), com.example.code.tmp.Main.ExtraTokenType.ProtectedToken),
				Token(Text("abstract"), com.example.code.tmp.Main.ExtraTokenType.AbstractToken),
				Token(Text("final"), com.example.code.tmp.Main.ExtraTokenType.FinalToken),
				Token(Text("native"), com.example.code.tmp.Main.ExtraTokenType.NativeToken),
				Token(Text("neutral"), com.example.code.tmp.Main.ExtraTokenType.NeutralToken),
				Token(Character('0'), com.example.code.tmp.Main.ExtraTokenType.NeutralToken)
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
						TokenType.WordToken
				)
		);
	}

	public Rule ordinals() {
		return Alternatives(
				Token(
						Sequence(
								Optional(
										Alternatives(
												Character('-'),
												Character('+')
										)
								),
								CharacterRange('1', '9'),
								ZeroOrMore(CharacterRange('0', '9')),
								Character('.'),
								ZeroOrMore(CharacterRange('0', '9'))
						),
						TokenType.DecimalNumberToken
				),
				Token(
						Sequence(
								Optional(
										Alternatives(
												Character('-'),
												Character('+')
										)
								),
								CharacterRange('1', '9'),
								ZeroOrMore(CharacterRange('0', '9'))
						),
						TokenType.NumberToken
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
														CharacterRange('\n'),
														CharacterRange('\f')
												)
										)
								),
								Character('"')
						),
						com.example.code.tmp.Main.ExtraTokenType.StringLiteralToken
				),
				Token(
						Sequence(
								Character('\''),
								Not(Character('\'')),
								Character('\'')
						),
						Main.ExtraTokenType.CharacterLiteralToken
				)
		);
	}

	public Rule characters() {
		return Alternatives(
				Token(Character(';'), TokenType.SemiColonToken),
				Token(Character(':'), TokenType.ColonToken),
				Token(Character('.'), TokenType.PointToken),
				Token(Character(','), TokenType.CommaToken),
				Token(Character('='), TokenType.EqualToken),
				Token(Character('-'), TokenType.MinusToken),
				Token(Character('+'), TokenType.PlusToken),
				Token(Character('('), TokenType.ParenthesisLeftToken),
				Token(Character(')'), TokenType.ParenthesisRightToken),
				Token(Character('['), TokenType.SquareLeftToken),
				Token(Character(']'), TokenType.SquareRightToken),
				Token(Character('{'), TokenType.CurlyLeftToken),
				Token(Character('}'), TokenType.CurlyRightToken),
				Token(Character('>'), TokenType.GreaterToken),
				Token(Character('<'), TokenType.LessToken),
				Token(Character('&'), TokenType.AmpersandToken),
				Token(Character('|'), TokenType.VerticalLineToken),
				Token(Character('?'), TokenType.QuestionToken),
				Token(Character('!'), TokenType.ExclamationToken),
				Token(Character('_'), TokenType.LowLineToken),
				Token(Character('"'), TokenType.QuotationToken),
				Token(Character('\''), TokenType.ApostropheToken)
		);
	}
}