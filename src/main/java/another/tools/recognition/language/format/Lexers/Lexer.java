package another.tools.recognition.language.format.Lexers;

import another.tools.recognition.language.format.Rules.*;
import another.tools.recognition.language.format.Tokens.*;
import com.java.components.lang.CompilerTaskException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public final class Lexer {
	private final String input;
	private int position = 0;
	private LexerTokenizer lexerTokenizer;
	private final ArrayList<Token> tokens = new ArrayList<>();
	private int kind = 0;
	private int line = 0;
	private final ArrayList<String> lines;

	public Lexer(String text) throws CompilerTaskException {
		if (text == null) {
			throw new CompilerTaskException("cannot text is null!", "valid text", new NullPointerException("text is null"));
		}
		this.input = text.trim();
		this.lines = new ArrayList<>(List.of(text.split("[\\n\\r]")));
	}

	public Lexer(File file) throws CompilerTaskException {
		this(readFile(file));
	}

	public Lexer(InputStream input) throws CompilerTaskException {
		this(readInput(input));
	}

	public void setLexerTokenizer(LexerTokenizer lexerTokenizer) throws CompilerTaskException {
		if (lexerTokenizer == null) {
			throw new CompilerTaskException();
		}
		this.lexerTokenizer = lexerTokenizer;
	}

	public ArrayList<Token> tokenize() throws CompilerTaskException {
		if (this.lexerTokenizer == null) {
			throw new CompilerTaskException();
		}
		while (position < this.input.length()) {
			Token test = matchRule(this.lexerTokenizer.skip());
			if (test != null) {
				if (test.getImage().matches("[\n\r]")) {
					this.line++;
				}
			}
			Token token = matchRule(this.lexerTokenizer.execute());
			if (token != null) {
				if (token.getImage().matches("[\n\r]")) {
					this.line++;
				}
				this.tokens.add(token);
			} else {
				throw new CompilerTaskException(
						"the character: '" + this.input.charAt(this.position) + "' is unknwon for the Lexer-Compiler, in the position: " + this.position + " & line: " + this.line,
						"please set a valid character",
						new TypeNotPresentException("character", null)
				);
			}
			this.kind++;
		}
		this.tokens.add(new Token('\0', SpecialTokenType.EndOfFileToken, this.kind++));
		if (this.tokens.size() <= 2) {
			System.err.println("Please add more type for more tokens!");
			try {
				Thread.sleep(2500);
			} catch (InterruptedException e) { }
		}
		return this.tokens;
	}

	private Token matchRule(Rule rule) throws CompilerTaskException {
		if (rule == null) return null;
		int oldPosition = this.position;
		ArrayList<String> list = rule.match(this.input, this.position);
		if (list != null) {
			this.position += getPosition(list);
			String value = getString(list);
			if (value.length() == 1) {
				return new Token(value, getType(value), this.position, 0, lines.get(this.line).length(), this.line, 0, lines.size(), kind);
			}
			return new Token(value, getType(value), oldPosition, this.position, 0, lines.get(this.line).length(), this.line, 0, lines.size(), kind);
		}
		return null;
	}

	private String getString(ArrayList<String> list) {
		StringBuilder newString = new StringBuilder();
		for (String element : list)
			newString.append(element);
		return newString.toString();
	}

	public int getPosition(ArrayList<String> list) {
		int newPosition = 0;
		for (String element : list)
			newPosition = newPosition + element.length();
		return newPosition;
	}

	private Enum<?> getType(String key) {
		HashMap<String, Enum<?>> types = this.lexerTokenizer.types;
		for (Map.Entry<String, Enum<?>> entry : types.entrySet()) {
			if (entry.getKey() != null) {
				if (entry.getKey().equals(key)) {
					return entry.getValue();
				}
			}
		}
		return null;
	}

	private static String readInput(InputStream input) throws CompilerTaskException {
		if (input == null) {
			throw new CompilerTaskException();
		}
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
			String line;
			while ((line = reader.readLine()) !=  null) {
				content.append(line).append('\n');
			}
		} catch (IOException _) {}
		return content.toString().trim();
	}

	private static String readFile(File file) throws CompilerTaskException {
		if (file == null) {
			throw new CompilerTaskException();
		}
		StringBuilder content = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				content.append(line).append("\n");
			}
		} catch (Exception _) {}
		return content.toString().trim();
	}
}