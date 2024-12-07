package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import another.tools.recognition.language.format.Tokens.TokenText;
import another.tools.recognition.language.format.Tokens.TokenType;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public class NotGrammatical implements Grammatical {
	private Grammatical grammatical;

	public NotGrammatical(Grammatical grammar) {
		this.grammatical = grammar;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws Exception {
		ArrayList<Token> tokens = grammatical.match(list, position);
		if (tokens == null) {
			return new ArrayList<>(list.subList(position, ParseTree(grammatical, position)));
		}
		return null;
	}

	private static int ParseTree(Grammatical grammar, int position) throws CompilerTaskException {
		int count = position;
		for (Object g : grammar.getGrammars()) {
			if (g instanceof TokenType) {
				count = count + 1;
				continue;
			}
			if (g instanceof TokenText) {
				count = count + 1;
				continue;
			}
			if (g instanceof Grammatical g2) {
				count = count + ParseTree(grammar, 0);
			}
		}
		return count;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() throws CompilerTaskException {
		return Grammatical.super.getGrammars();
	}
}
