package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public abstract class GrammarGrammatical implements Grammatical {
	public Grammatical grammars;

	public GrammarGrammatical(Grammatical grammars) {
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

		ArrayList<Grammatical> tmp2 = new ArrayList<>(grammars.getGrammars());
		tmp2.addAll(new ArrayList<>(List.of(execute(subResult))));

		SequenceGrammatical tmp = new SequenceGrammatical(tmp2);
		grammars.getGrammars().clear();

		ArrayList<Token> result = new ArrayList<>();
		ArrayList<Grammatical> subFinal = new ArrayList<>(tmp.getGrammars());

		for (Grammatical rule : subFinal) {
			ArrayList<Token> matched = rule.match(list, position);
			if (matched == null) {
				return null;
			}
			result.addAll(matched);
			position += matched.size();
		}

		grammars = new SequenceGrammatical(subFinal);

		return result;
	}

	private <E> ArrayList<E> subList(ArrayList<E> list, int start, int end) {
		ArrayList<E> es = new ArrayList<>();
		for (; start < end; start++) {
			if (start >= list.size()) start = list.size() - 1;
			es.add(list.get(start));
		}
		return es;
	}

	@Override
	public final ArrayList<Grammatical> getGrammars() throws CompilerTaskException {
		return null;
	}

	public abstract Grammatical execute(ArrayList<Token> tokens) throws CompilerTaskException;

	public interface Action {
		Grammatical execute(ArrayList<Token> tokens) throws CompilerTaskException;
	}
}
