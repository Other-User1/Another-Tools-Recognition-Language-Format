package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.function.Supplier;

public class RecursiveGrammatical implements Grammatical {
	public interface Recursive {
		Grammatical getRecursive();
	}

	private final Recursive resolvedGrammar;

	public RecursiveGrammatical(Recursive resolvedGrammar) {
		this.resolvedGrammar = resolvedGrammar;
	}

	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		return resolvedGrammar.getRecursive() != null ? resolvedGrammar.getRecursive().match(list, position) : null;
	}
}