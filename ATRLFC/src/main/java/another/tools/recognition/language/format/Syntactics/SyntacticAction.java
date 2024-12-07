package another.tools.recognition.language.format.Syntactics;

import another.tools.recognition.language.format.Grammaticals.Grammatical;
import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public abstract class SyntacticAction implements Grammatical {
	public abstract void execute(ArrayList<Token> tokens) throws CompilerTaskException;

	@Override
	public final ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException { throw new CompilerTaskException("Unsupported method!"); }

	@Override
	public final ArrayList<Grammatical> getGrammars() throws CompilerTaskException { throw new CompilerTaskException("Unsupported method!"); }
}
