package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class RepeatGrammatical implements Grammatical {
	private final Grammatical rule;
	private final int permit;

	public RepeatGrammatical(Grammatical rule, int permit) {
		this.rule = rule;
		this.permit = permit;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		final ArrayList<Token> match = rule.match(list, position);
		ArrayList<Token> matched = rule.match(list, position);

		if (matched == null) {
			return null;
		}

		ArrayList<Token> result = new ArrayList<>(matched);
		position += matched.size();

		while (position < list.size() && (matched = rule.match(list, position)) != null) {
			result.addAll(matched);
			position += matched.size();
		}

		if (result.size() == repeat(match, permit)) {
			return result;
		}

		return null;
	}

	private int repeat(ArrayList<Token> tokens, int min) {
		ArrayList<Token> result = new ArrayList<>();
		for (int i = 0; i < min; i++) {
			result.addAll(tokens);
		}
		return result.size();
	}

	@Override
	public ArrayList<Grammatical> getGrammars() {
		return new ArrayList<>(List.of(rule));
	}
}
