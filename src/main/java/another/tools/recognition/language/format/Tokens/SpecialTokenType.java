package another.tools.recognition.language.format.Tokens;

import another.tools.recognition.language.format.Grammaticals.Grammatical;
import another.tools.recognition.language.format.Rules.Rule;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public enum SpecialTokenType implements Grammatical {
	SkipToken,
	EndOfFileToken,
	UndefinedToken;

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		return TokenType.isMatchUtil(list, position, name());
	}
}
