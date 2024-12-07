package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public interface Grammatical {
	ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException, Exception;
	public default ArrayList<Grammatical> getGrammars() throws CompilerTaskException { return new ArrayList<>(); }
	default ArrayList<Grammatical> toGrammatical(Grammatical grammatical) {
		return toGrammatical(new Grammatical[] {grammatical});
	}
	default ArrayList<Grammatical> toGrammatical(Grammatical... grammatical) {
		return new ArrayList<>(List.of(grammatical));
	}
}
