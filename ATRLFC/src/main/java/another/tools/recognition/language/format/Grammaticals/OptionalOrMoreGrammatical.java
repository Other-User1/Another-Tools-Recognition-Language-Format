package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class OptionalOrMoreGrammatical implements Grammatical {
	private final Grammatical grammar;

	public OptionalOrMoreGrammatical(Grammatical grammar) {
		this.grammar = grammar;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		// if (grammar instanceof GrammarGrammatical) throw new CompilerTaskException("cannot first is GrammaticalGrammar!");
		ArrayList<Token> matched = grammar.match(list, position);

		if (matched == null) {
			return new ArrayList<>();
		}

		ArrayList<Token> result = new ArrayList<>(matched);
		position += matched.size();

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
