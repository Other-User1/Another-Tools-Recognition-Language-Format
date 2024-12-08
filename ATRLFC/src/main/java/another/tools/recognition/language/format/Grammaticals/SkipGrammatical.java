package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class SkipGrammatical implements Grammatical {
	private final Grammatical grammar;

	public SkipGrammatical(Grammatical grammars) {
		this.grammar = grammars;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		ArrayList<Token> matched = grammar.match(list, position);
		if (matched == null) return null;
		matched = skip(matched);
		return matched;
	}

	private ArrayList<Token> skip(ArrayList<Token> list) {
		ArrayList<Token> newList = new ArrayList<>();
		for (Token element : list)
			newList.add(new Token(element.getImage(), element.getType(), "Skip"));
		return newList;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() throws CompilerTaskException {
		return new ArrayList<>(List.of(grammar));
	}
}
