package another.tools.recognition.language.format.Rules;

import another.tools.recognition.language.format.Lexers.BetterLexerTokenizer;

public abstract class Rules extends BetterLexerTokenizer {
	abstract Rule executes();

	@Override
	public final Rule execute() { return executes(); }
}
