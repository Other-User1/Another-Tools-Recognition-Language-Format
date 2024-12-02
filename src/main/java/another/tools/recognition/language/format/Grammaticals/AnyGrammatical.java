package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public class AnyGrammatical implements Grammatical {
	@Override
	public ArrayList<Token> match(ArrayList<Token> list, int position) throws CompilerTaskException {
		return new ArrayList<>(list.subList(position, position + 1));
	}

	@Override
	public ArrayList<Grammatical> getGrammars() throws CompilerTaskException {
		return Grammatical.super.getGrammars();
	}
}
