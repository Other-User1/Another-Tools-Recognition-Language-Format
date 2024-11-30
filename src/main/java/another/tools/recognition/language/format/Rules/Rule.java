package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

public interface Rule {
	public String match(String input, int position) throws CompilerTaskException;
}
