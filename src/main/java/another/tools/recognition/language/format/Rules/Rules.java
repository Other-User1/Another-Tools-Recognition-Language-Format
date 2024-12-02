package another.tools.recognition.language.format.Rules;

import another.tools.recognition.language.format.Lexers.BetterLexerTokenizer;
import another.tools.recognition.language.format.Lexers.LexerTokenizer;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public abstract class Rules extends BetterLexerTokenizer {
	abstract Rule executes();

	@Override
	public final Rule execute() { return executes(); }
}
