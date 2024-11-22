package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class OptionalGrammatical implements Grammatical {
	private final Grammatical rule;

	public OptionalGrammatical(Grammatical rule) {
		this.rule = rule;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		ArrayList<Token> matched = rule.match(list, position);
		return matched == null ? new ArrayList<>() : matched;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() {
		return new ArrayList<>(List.of(rule));
	}
}