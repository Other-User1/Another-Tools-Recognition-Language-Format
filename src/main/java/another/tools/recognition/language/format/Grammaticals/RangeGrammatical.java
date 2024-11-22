package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.tokens.Token;
import another.tools.recognition.language.format.tokens.TokenType;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class RangeGrammatical implements Grammatical {
	private final Grammatical rule;
	private final int min;
	private final int max;
	private int count = 0;

	public RangeGrammatical(Grammatical rule, int min, int max) {
		this.min = min;
		this.max = max;
		this.rule = rule;
		this.count = 0;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		ArrayList<Token> result = new ArrayList<>();
		ArrayList<Token> matched;
		final ArrayList<Token> match = rule.match(list, position);

		while (position < list.size() - 1 && (matched = rule.match(list, position)) != null && count != max) {
			result.addAll(matched);
			position = position + matched.size();
			count++;
		}

		if (count >= min && count <= max) {
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
