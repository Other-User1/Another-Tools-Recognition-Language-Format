package com.example.code;

import another.tools.recognition.language.format.Grammaticals.Grammatical;
import another.tools.recognition.language.format.Tokens.Token;
import another.tools.recognition.language.format.Tokens.TokenType;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public enum ExtraTokenType implements Grammatical {
	ModuleToken,
	PackageToken,
	PrivateToken,
	ProtectedToken,
	PublicToken,
	NeutralToken,
	StringLiteralToken,
	CharacterLiteralToken,
	ImportToken,
	StaticToken,
	SealedToken,
	FinalToken,
	AbstractToken,
	NativeToken,
	ExnerToken,
	InnerToken,
	OpenToken,
	CloseToken,
	ExternalToken,
	InternalToken,
	FunctionToken,
	FloatNumberToken,
	DoubleNumberToken,
	IdentifierToken,
	ArrowToken,
	IntegerToken,
	ByteToken,
	ShortToken,
	LongToken,
	FloatToken,
	DoubleToken;

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		return TokenType.match(list, position, name());
	}

	@Override
	public ArrayList<Grammatical> getGrammars() throws CompilerTaskException {
		return TokenType.getGrammars(name());
	}
}