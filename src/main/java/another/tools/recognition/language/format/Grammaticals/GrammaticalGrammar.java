package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public abstract class GrammaticalGrammar implements Grammatical {
	private final Grammatical grammars;

	public GrammaticalGrammar(Grammatical grammars) {
		this.grammars = grammars;
	}

	@Override
	public final ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		ArrayList<Token> subResult = new ArrayList<>();
		ArrayList<Token> cutList = subList(list, position, position + grammars.getGrammars().size());

		{
			ArrayList<Token> matched = grammars.match(cutList, 0);
			if (matched == null) {
				return null;
			}
			subResult.addAll(matched);
		}

		ArrayList<Grammatical> tmp = new ArrayList<>();
		tmp.add(grammars);
		tmp.addAll(execute(subResult));

		ArrayList<Token> result = new ArrayList<>();
		ArrayList<Grammatical> subFinal = new ArrayList<>(tmp);

		for (Grammatical rule : subFinal) {
			ArrayList<Token> matched = rule.match(list, position);
			if (matched == null) {
				return null;
			}
			result.addAll(matched);
			position += matched.size();
		}

		return result;
	}

	private <E> ArrayList<E> subList(ArrayList<E> list, int start, int end) {
		ArrayList<E> es = new ArrayList<>();
		for (; start < end; start++) {
			es.add(list.get(start));
		}
		return es;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() {
		return new ArrayList<>(List.of(grammars));
	}

	public abstract ArrayList<Grammatical> execute(ArrayList<Token> tokens) throws CompilerTaskException;

	public interface Action {
		public ArrayList<Grammatical> execute(ArrayList<Token> tokens) throws CompilerTaskException;
	}
}
