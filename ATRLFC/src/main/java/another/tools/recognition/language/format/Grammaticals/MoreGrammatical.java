package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class MoreGrammatical implements Grammatical {
	private final Grammatical grammar;

	public MoreGrammatical(Grammatical grammar) {
		this.grammar = grammar;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws Exception {
		ArrayList<Token> result = new ArrayList<>();
		ArrayList<Token> matched;

		for (int index = 0; index < 2; index++) {
			matched = grammar.match(list, position);

			if (matched == null) {
				return null;
			}

			result.addAll(matched);
			position += matched.size();
		}

		while (position < list.size() && (matched = grammar.match(list, position)) != null) {
			result.addAll(matched);
			position += matched.size();
		}

		return result;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() {
		return new ArrayList<>(List.of(grammar));
	}
}
