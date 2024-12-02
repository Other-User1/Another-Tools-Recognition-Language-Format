package another.tools.recognition.language.format.Rules;

import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public abstract class Rule {
	public abstract ArrayList<String> match(String input, int position) throws CompilerTaskException;

	public final int getPosition(ArrayList<String> list) {
		int newPosition = 0;
		for (String element : list)
			newPosition = newPosition + element.length();
		return newPosition;
	}
}
