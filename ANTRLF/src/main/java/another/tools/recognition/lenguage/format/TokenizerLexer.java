package another.tools.recognition.lenguage.format;

public interface TokenizerLexer {
	Token symbols(char symbols);
	Token identifier(String word);
}
