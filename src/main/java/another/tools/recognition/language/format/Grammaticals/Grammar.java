package another.tools.recognition.language.format.Grammaticals;

import another.tools.recognition.language.format.Syntactics.SyntacticGrammatical;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public abstract class Grammar extends SyntacticGrammatical implements Grammatical {
	public abstract Grammatical run();

	@Override
	public ArrayList<Grammatical> getGrammars() throws CompilerTaskException {
		return new ArrayList<>(List.of(run()));
	}
}
