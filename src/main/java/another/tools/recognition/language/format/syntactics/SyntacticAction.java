package another.tools.recognition.language.format.syntactics;

import another.tools.recognition.language.format.tokens.Token;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public interface SyntacticAction {
	public void execute(ArrayList<Token> tokens) throws CompilerTaskException;
}
