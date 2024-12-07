package another.tools.recognition.language.format.Syntactics;

import another.tools.recognition.language.format.Grammaticals.AlternativesGrammatical;
import another.tools.recognition.language.format.Grammaticals.Grammatical;
import another.tools.recognition.language.format.Grammaticals.RecursiveGrammatical;
import another.tools.recognition.language.format.Grammaticals.RepeatGrammatical;
import another.tools.recognition.language.format.Tokens.TokenText;

import java.util.ArrayList;

public abstract class BetterSyntacticGrammatical extends SyntacticGrammatical {
	protected final AlternativesGrammatical Texts(String... targets) {
		ArrayList<TokenText> tts = new ArrayList<>();
		for (String target : targets)
			tts.add(Text(target));
		return Alternatives(tts.toArray(new TokenText[0]));
	}

	protected final AlternativesGrammatical Repeats(Grammatical grammatical, int... counts) {
		ArrayList<RepeatGrammatical> rs = new ArrayList<>();
		for (int count : counts)
			rs.add(Repeat(grammatical, count));
		return Alternatives(rs.toArray(new RepeatGrammatical[0]));
	}
}
