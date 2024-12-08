package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import another.tools.recognition.language.format.Tokens.GrammaticalToken;
import another.tools.recognition.language.format.Tokens.Tokens;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public abstract class GrammaticalAction implements Grammatical {
	private final Grammatical grammars;

	public GrammaticalAction(Grammatical grammars) {
		this.grammars = grammars;
	}

	@Override
	public final ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		ArrayList<Token> cutList = new ArrayList<>(list.subList(position, position + this.grammars.getGrammars().size()));

		ArrayList<Token> matched = this.grammars.match(cutList, position);
		if (matched == null) {
			return null;
		}
		ArrayList<Token> subResult = new ArrayList<>(matched);

		return run(subResult) == null ? new ArrayList<>() : run(subResult);
	}

	@Override
	public ArrayList<Grammatical> getGrammars() {
		return new ArrayList<>(List.of(grammars));
	}

	public abstract ArrayList<Token> run(ArrayList<Token> tokens);

	public static abstract class Action extends GrammaticalToken {
		public abstract Tokens run(ArrayList<Token> tokens);
	}
}
