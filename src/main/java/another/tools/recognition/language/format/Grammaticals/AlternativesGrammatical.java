package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class AlternativesGrammatical implements Grammatical {
	private final ArrayList<Grammatical> grammars;

	public AlternativesGrammatical(Grammatical... rules) {
		this.grammars = new ArrayList<>(List.of(rules));
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		for (Grammatical grammar : grammars) {
			ArrayList<Token> matched = grammar.match(list, position);
			if (matched != null) {
				return matched;
			}
		}
		return null;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() {
		return grammars;
	}
}
