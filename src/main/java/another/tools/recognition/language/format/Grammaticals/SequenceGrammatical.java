package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public final class SequenceGrammatical implements Grammatical {
	private final ArrayList<Grammatical> grammars;
	//private boolean skip;

	public SequenceGrammatical(Grammatical... grammars) {
		this.grammars = new ArrayList<>(List.of(grammars));
	}

	/*public SequenceGrammatical(boolean skip, Grammatical... grammars) {
		this.grammars = new ArrayList<>(List.of(grammars));
		this.skip = skip;
	}*/

	SequenceGrammatical(ArrayList<Grammatical> tmp2) {
		grammars = tmp2;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		ArrayList<Token> result = new ArrayList<>();

		for (Grammatical rule : grammars) {
			ArrayList<Token> matched = rule.match(list, position);
			if (matched == null) {
				return null;
			}

			//if (skip) matched = skip(matched);

			result.addAll(matched);
			position += matched.size();
		}
		return result;
	}

	private ArrayList<Token> skip(ArrayList<Token> list) {
		ArrayList<Token> newList = new ArrayList<>();
		for (Token element : list)
			newList.add(new Token(element.getImage(), element.getType(), "Skip"));
		return newList;
	}

	public ArrayList<Grammatical> getGrammars() {
		return grammars;
	}
}
