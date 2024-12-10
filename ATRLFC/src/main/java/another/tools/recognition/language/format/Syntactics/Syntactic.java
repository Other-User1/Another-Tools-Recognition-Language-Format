package another.tools.recognition.language.format.Syntactics;

import another.tools.recognition.language.format.Grammaticals.*;
import another.tools.recognition.language.format.Lexers.Lexer;
import another.tools.recognition.language.format.Tokens.SpecialTokenType;
import another.tools.recognition.language.format.Tokens.Token;
import another.tools.recognition.language.format.Tokens.TokenText;
import another.tools.recognition.language.format.Tokens.TokenType;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public final class Syntactic {
	private final ArrayList<Token> tokens;
	private SyntacticGrammatical grammaticalRules;
	private int position;

	public Syntactic(Lexer lx) throws CompilerTaskException {
		this.tokens = lx.onLexical();
	}

	public void setSyntacticGrammar(SyntacticGrammatical syntacticGrammatical) throws CompilerTaskException {
		if (syntacticGrammatical == null) {
			throw new CompilerTaskException();
		}
		if (syntacticGrammatical.run() == null) {
			throw new CompilerTaskException();
		}
		this.grammaticalRules = syntacticGrammatical;
	}

	public void onSyntactic() throws Exception {
		if (this.grammaticalRules == null)
			throw new CompilerTaskException();
		while (this.position < this.tokens.size()) {
			Pair pair = isMatch(new SequenceGrammatical(grammaticalRules.run(), SpecialTokenType.EndOfFileToken));
			if (pair.booleans()) {
				this.position = this.position + pair.tokens().size();
				continue;
			}

			throw new CompilerTaskException("Error in: '" + this.tokens.get(this.position).getImage() + "', position: " + this.position);
			// return;
		}
	}

	private Pair isMatch(Grammatical grammar) throws Exception {
		if (grammar == null) return new Pair(null, false);
		if (this.position >= this.tokens.size()) return new Pair(null, false);
		ArrayList<Token> matched = grammar.match(this.tokens, this.position);
		if (matched != null) {
			return new Pair(matched, true);
		}
		this.position = TokenType.getPosition();
		return new Pair(null, false);
	}

	private record Pair(ArrayList<Token> tokens, boolean booleans) { }
}
