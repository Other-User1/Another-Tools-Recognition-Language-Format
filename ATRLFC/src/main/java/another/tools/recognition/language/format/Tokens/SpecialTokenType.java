package another.tools.recognition.language.format.Tokens;

import another.tools.recognition.language.format.Grammaticals.Grammatical;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public enum SpecialTokenType implements Grammatical {
	VirtualToken,
	EndOfFileToken,
	UndefinedToken;

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		return TokenType.isMatchUtil(list, position, name());
	}
}
