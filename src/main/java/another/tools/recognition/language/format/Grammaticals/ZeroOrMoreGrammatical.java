package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class ZeroOrMoreGrammatical implements Grammatical {
	private final Grammatical grammar;

	public ZeroOrMoreGrammatical(Grammatical grammar) {
		this.grammar = grammar;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		ArrayList<Token> result = new ArrayList<>();
		ArrayList<Token> matched = new ArrayList<>();

		do {
			result.addAll(matched);
			position += matched.size();
		} while (position < list.size() && (matched = grammar.match(list, position)) != null);

		return result;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() {
		return new ArrayList<>(List.of(grammar));
	}
}
