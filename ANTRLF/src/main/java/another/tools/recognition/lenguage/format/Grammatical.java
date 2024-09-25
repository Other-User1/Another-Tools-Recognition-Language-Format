package another.tools.recognition.lenguage.format;

import java.util.ArrayList;

public class Grammatical {
	private final ArrayList<Object> values;
	private final Object[] expectedTokens;
	private static Lexer lexer;

	public Grammatical(Object... tokens) {
		values = new ArrayList<>();
		for (Object type : tokens) {
			if (type instanceof Enum<?> e) {
				values.add(e);
			} else if (type instanceof String s) {
				values.add(s);
			} else if (type instanceof GrammaticalAction ga) {
				Syntactic syntactic = new Syntactic(lexer);
				syntactic.addGrammaticalRule(new Grammatical(values), new ParseAction() {
					@Override
					public void action(ArrayList<String> code) {
						values.add(ga.action(code));
					}
				});
				syntactic.subParse();
			}
		}
		this.expectedTokens = values.toArray();
	}

	public Object[] getExpectedTokens() {
		return expectedTokens;
	}

	public static void setLexer(Lexer input) {
		lexer = input;
	}
}