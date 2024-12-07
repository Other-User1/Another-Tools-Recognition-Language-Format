package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public class ErrorGrammatical implements Grammatical {
	private final Grammatical grammatical;
	private final CompilerTaskException error;

	public ErrorGrammatical(Grammatical grammar, CompilerTaskException error) {
		this.grammatical = grammar;
		this.error = error;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		ArrayList<Token> matched = grammatical.match(list, position);
		if (matched == null) {
			throw error;
		}
		return matched;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() throws CompilerTaskException {
		return Grammatical.toGrammatical(grammatical);
	}
}
