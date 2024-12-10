package another.tools.recognition.language.format.Rules;

import another.tools.recognition.language.format.Lexers.LexerTokenizer;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;
import java.util.List;

public abstract class RulesRule {
	public Enum<?> execute(ArrayList<String> value) throws CompilerTaskException { return null; }
	public ArrayList<Enum<?>> executes(ArrayList<String> value) throws CompilerTaskException { return null; }
	protected final ArrayList<Enum<?>> Sequence(Enum<?>... types) { return new ArrayList<>(List.of(types)); }
}
