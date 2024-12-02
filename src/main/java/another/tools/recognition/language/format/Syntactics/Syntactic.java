package another.tools.recognition.language.format.Syntactics;

import another.tools.recognition.language.format.Grammaticals.*;
import another.tools.recognition.language.format.Lexers.Lexer;
import another.tools.recognition.language.format.Tokens.SpecialTokenType;
import another.tools.recognition.language.format.Tokens.Token;
import another.tools.recognition.language.format.Tokens.TokenType;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public final class Syntactic {
	private final ArrayList<Token> tokens;
	private SyntacticGrammatical grammaticalRules;
	private int position;

	public Syntactic(Lexer lx) throws CompilerTaskException {
		//ArrayList<Token> sub = lx.tokenize();
		this.tokens = lx.tokenize();
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

	public void onSyntactic() throws CompilerTaskException {
		if (this.grammaticalRules == null)
			throw new CompilerTaskException();
		while (this.position <= this.tokens.size() - 1) {
			Pair pair = isMatch(grammaticalRules.run());
			if (pair.booleans()) {
				this.position = this.position + pair.tokens().size();
				continue;
			}

			if (this.tokens.get(this.position).getType() == SpecialTokenType.EndOfFileToken)
				return;
			else
				throw new CompilerTaskException("Error in: " + this.tokens.get(this.position).getImage() + ", position: " + this.position);
			// return;
		}
	}

	private Pair isMatch(Grammatical grammar) {
		if (grammar == null) return new Pair(null, false);
		if (this.position >= this.tokens.size()) return new Pair(null, false);
		ArrayList<Token> matched = null;
		try {
			matched = grammar.match(this.tokens, this.position);
		} catch (Exception _) { }
		if (matched != null) {
			return new Pair(matched, true);
		}
		this.position = TokenType.getPosition();
		return new Pair(null, false);
	}

	private record Pair(ArrayList<Token> tokens, boolean booleans) { }
}
