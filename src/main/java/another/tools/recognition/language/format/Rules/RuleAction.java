package another.tools.recognition.language.format.Rules;

import another.tools.recognition.language.format.Lexers.LexerTokenizer;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public abstract class RuleAction extends LexerTokenizer  {
	@Override
	public final Rule execute() throws CompilerTaskException {
		throw new CompilerTaskException("unsupported class");
	}

	public abstract Enum<?> execute(ArrayList<String> value);
}
