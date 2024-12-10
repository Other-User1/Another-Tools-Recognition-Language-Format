package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public interface Grammatical {
	ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException;
	public default ArrayList<Grammatical> getGrammars() throws CompilerTaskException { return Grammatical.toGrammatical(this); }

	static ArrayList<Grammatical> toGrammatical(Grammatical grammatical) {
		return toGrammatical(new Grammatical[] {grammatical});
	}

	static ArrayList<Grammatical> toGrammatical(Grammatical... grammatical) {
		return toGrammatical(List.of(grammatical));
	}

	private static ArrayList<Grammatical> toGrammatical(List<Grammatical> grammatical) {
		return new ArrayList<>(grammatical);
	}

	static ArrayList<Grammatical> toGrammatical(ArrayList<Grammatical> grammatical) {
		return new ArrayList<>(grammatical);
	}
}
