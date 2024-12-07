package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public final class SequenceGrammatical implements Grammatical {
	private final ArrayList<Grammatical> grammars;

	public SequenceGrammatical(Grammatical... grammars) {
		this.grammars = new ArrayList<>(List.of(grammars));
	}

	SequenceGrammatical(ArrayList<Grammatical> tmp2) {
		grammars = tmp2;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws Exception {
		ArrayList<Token> result = new ArrayList<>();

		for (Grammatical rule : grammars) {
			ArrayList<Token> matched = rule.match(list, position);
			if (matched == null) {
				return null;
			}

			result.addAll(matched);
			position += matched.size();
		}
		return result;
	}

	public ArrayList<Grammatical> getGrammars() {
		return grammars;
	}
}
