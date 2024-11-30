package another.tools.recognition.language.format.Rules;

import another.tools.recognition.language.format.Lexers.BetterLexerTokenizer;
import another.tools.recognition.language.format.Lexers.LexerTokenizer;
import com.java.components.lang.CompilerTaskException;

public abstract class Rules extends BetterLexerTokenizer implements Rule {
	abstract Rule executes();

	@Override
	public final Rule execute() { return executes(); }

	@Override
	public final String match(String input, int position) throws CompilerTaskException { throw new CompilerTaskException("Unsupported this method!"); }
}
