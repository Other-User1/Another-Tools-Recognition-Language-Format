package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.*;

public class AmbiguousGrammatical implements Grammatical {
	private final ArrayList<Grammatical> grammatical;

	public AmbiguousGrammatical(Grammatical... grammatical) {
		this.grammatical = new ArrayList<>(List.of(grammatical));
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		ArrayList<Grammatical> pendingGrammars = new ArrayList<>(this.grammatical);
		ArrayList<Token> consumedTokens = new ArrayList<>();

		while (!pendingGrammars.isEmpty()) {
			boolean matched = false;

			Iterator<Grammatical> iterator = pendingGrammars.iterator();
			while (iterator.hasNext()) {
				Grammatical grammar = iterator.next();
				try {
					ArrayList<Token> matchedTokens = grammar.match(list, position);
					if (matchedTokens != null && !matchedTokens.isEmpty()) {
						matched = true;
						consumedTokens.addAll(matchedTokens);
						position += matchedTokens.size();
						iterator.remove();
						break;
					}
				} catch (CompilerTaskException e) {
				}
			}

			if (!matched) {
				return null;
			}
		}

		return consumedTokens;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() throws CompilerTaskException {
		return Grammatical.toGrammatical(grammatical);
	}
}
