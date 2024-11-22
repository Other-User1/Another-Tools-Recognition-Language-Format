package another.tools.recognition.language.format.syntactics;

import another.tools.recognition.language.format.Grammaticals.*;
import another.tools.recognition.language.format.lexers.Lexer;
import another.tools.recognition.language.format.tokens.SpecialTokenType;
import another.tools.recognition.language.format.tokens.Token;
import another.tools.recognition.language.format.tokens.TokenText;
import another.tools.recognition.language.format.tokens.TokenType;
import com.java.components.lang.CompilerTaskException;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;

public final class Syntactic {
	private final ArrayList<Token> tokens;
	private final ArrayList<Token> listToken;
	private final ArrayList<GrammaticalRule> grammaticalRules;
	private int position;
	private int subPosition = 0;

	public Syntactic(Lexer lx) throws CompilerTaskException {
		this.tokens = lx.tokenize();
		this.grammaticalRules = new ArrayList<>();
		this.listToken = tokens;
	}

	public void addNewGrammatical(SyntacticGrammatical syntacticGrammatical, SyntacticAction syntacticAction) throws CompilerTaskException {
		if (syntacticGrammatical == null) {
			throw new CompilerTaskException();
		}
		if (syntacticGrammatical.run() == null) {
			throw new CompilerTaskException();
		}
		if (syntacticAction == null) {
			throw new CompilerTaskException();
		}
		this.grammaticalRules.add(new GrammaticalRule(syntacticGrammatical, syntacticAction));
	}

	public void onParse() throws CompilerTaskException {
		if (this.grammaticalRules == null) {
			throw new CompilerTaskException();
		}
		while (this.position < tokens.size()) {
			boolean isMatched = false;
			for (GrammaticalRule grammaticalRule : this.grammaticalRules) {
				int oldPosition = this.subPosition;
				if (isMatch(grammaticalRule.grammar().run())) {
					ArrayList<Token> subTokens = new ArrayList<>(this.tokens.subList(oldPosition, this.subPosition));
					grammaticalRule.action().execute(subTokens);
					isMatched = true;
					break;
				}
			}

			if (!isMatched) {
				if (this.tokens.get(1 + this.position).getType() == SpecialTokenType.EndOfFileToken) return;
				throw new CompilerTaskException("Error in: " + this.tokens.get(this.position).getImage());
			}
		}
	}

	private boolean isMatch(Grammatical grammar) {
		if (grammar == null) return false;
		if (this.position >= this.tokens.size()) return false;
		ArrayList<Token> matched = null;
		try {
			matched = grammar.match(this.listToken, this.position);
		} catch (Exception _) { }
		if (matched != null) {
			this.position = this.position + matched.size();
			if (this.position == this.tokens.size()) {
				this.subPosition = this.position - 1;
			} else {
				this.subPosition = this.position;
			}
			return true;
		}
		this.position = TokenType.getPosition();
		return false;
	}

	private record GrammaticalRule(SyntacticGrammatical grammar, SyntacticAction action) { }
}
