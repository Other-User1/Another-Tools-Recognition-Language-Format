package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public interface Grammatical {
	ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException;
	public default ArrayList<Grammatical> getGrammars() throws CompilerTaskException { return new ArrayList<>(); }
}
