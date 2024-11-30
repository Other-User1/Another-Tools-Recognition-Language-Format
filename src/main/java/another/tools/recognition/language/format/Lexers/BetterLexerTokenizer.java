package another.tools.recognition.language.format.Lexers;

import another.tools.recognition.language.format.Rules.*;
import com.java.components.lang.CompilerTaskException;

import java.util.ArrayList;

public abstract class BetterLexerTokenizer extends LexerTokenizer {
	public final AlternativesRule Characters(char... targets) {
		ArrayList<CharacterRule> cs = new ArrayList<>();
		for (char target : targets)
			cs.add(Character(target));
		return Alternatives(cs.toArray(new CharacterRule[0]));
	}

	public final AlternativesRule Texts(String... targets) {
		ArrayList<TextRule> cs = new ArrayList<>();
		for (String target : targets)
			cs.add(Text(target));
		return Alternatives(cs.toArray(new TextRule[0]));
	}

	public final AlternativesRule Repeats(Rule rule, int... counts) {
		ArrayList<RepeatRule> rs = new ArrayList<>();
		for (int count : counts)
			rs.add(Repeat(rule, count));
		return Alternatives(rs.toArray(new RepeatRule[0]));
	}

	public final AlternativesRule CharactersRanges(char... targets) throws CompilerTaskException {
		if (targets.length % 2 == 1) throw new CompilerTaskException("The count is odd!");
		ArrayList<CharacterRangeRule> crs = new ArrayList<>();
		for (int i = 0; i < targets.length; i += 2)
			crs.add(CharacterRange(targets[i], targets[i + 1]));
		return Alternatives(crs.toArray(new CharacterRangeRule[0]));
	}
}
