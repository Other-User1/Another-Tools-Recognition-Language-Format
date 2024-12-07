package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class OptionalGrammatical implements Grammatical {
	private final Grammatical rule;

	public OptionalGrammatical(Grammatical rule) {
		this.rule = rule;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws Exception {
		ArrayList<Token> matched = rule.match(list, position);
		return matched == null ? new ArrayList<>() : matched;
	}

	@Override
	public ArrayList<Grammatical> getGrammars() {
		return new ArrayList<>(List.of(rule));
	}
}