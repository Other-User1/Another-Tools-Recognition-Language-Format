package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.syntactics.SyntacticGrammatical;
import another.tools.recognition.language.format.tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public abstract class Grammar extends SyntacticGrammatical implements Grammatical {
	@Override
	public final ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		throw new CompilerTaskException("This method is not compatible or unsupported with 'Grammar' because 'Grammar' is an abstract support class.", "Please stop calling this class!", new NoSuchMethodError("Incompatible and unbearable method."));
	}

	@Override
	public final ArrayList<Grammatical> getGrammars() throws CompilerTaskException {
		return new ArrayList<>(List.of(run()));
	}

	public abstract Grammatical run();
}
