package another.tools.recognition.language.format.Tokens;

import java.util.ArrayList;
import java.util.List;

public class Token extends Tokens {
	private final String image;
	private final Enum<?> type;
	private final String nameType;
	private final int beginPosition;
	private final int endPosition;
	private final int beginColumn;
	private final int endColumn;
	private final int position;
	private final int beginLine;
	private final int endLine;
	private final int line;
	private final int id;

	public Token(String image, Enum<?> type, String nameType) {
		this.image = image;
		this.type = type;
		this.nameType = nameType;
		this.beginPosition = -1;
		this.endPosition = -1;
		this.beginColumn = -1;
		this.endColumn = -1;
		this.position = -1;
		this.line = -1;
		this.beginLine = -1;
		this.endLine = -1;
		this.id = -1;
	}

	public Token(char image, Enum<?> type) {
		this(String.valueOf(image), type);
	}

	public Token(String image, Enum<?> type) {
		this(image, type, -1, -1, -1, -1, -1);
	}

	public Token(String image, Enum<?> type, int id) {
		this(image, type, -1, -1, id);
	}

	public Token(char image, Enum<?> type, int id) {
		this(String.valueOf(image), type, -1, -1, id);
	}

	public Token(char image, Enum<?> type, int position, int line, int id) {
		this(String.valueOf(image), type, position, line, id);
	}

	public Token(String image, Enum<?> type, int position, int line, int id) {
		this(image, type, position, -1, -1, line, -1, -1, id);
	}

	public Token(String image, Enum<?> type, int positionStart, int positionEnd, int columnStart, int columnEnd, int line, int lineStart, int lineEnd, int id) {
		this.image = image;
		this.type = type;
		this.nameType = type == null ? "UnknownEnum" : type.getDeclaringClass().getSimpleName();
		this.beginPosition = positionStart;
		this.endPosition = positionEnd;
		this.beginColumn = columnStart;
		this.endColumn = columnEnd;
		this.position = -1;
		this.line = line;
		this.beginLine = lineStart;
		this.endLine = lineEnd;
		this.id = id;
	}

	public Token(String image, Enum<?> type, int positionStart, int positionEnd, int line, int lineStart, int lineEnd, int id) {
		this(image, type, positionStart, positionEnd, -1, -1, line, lineStart, lineEnd, id);
	}

	public Token(String image, Enum<?> type, int position, int columnStart, int columnEnd, int line, int lineStart, int lineEnd, int id) {
		this.image = image;
		this.type = type;
		this.nameType = type == null ? "UnknownEnum" : type.getDeclaringClass().getSimpleName();
		this.beginPosition = -1;
		this.endPosition = -1;
		this.beginColumn = columnStart;
		this.endColumn = columnEnd;
		this.position = position;
		this.line = line;
		this.beginLine = lineStart;
		this.endLine = lineEnd;
		this.id = id;
	}

	public Token(String image, Enum<?> type, int position, int line, int lineStart, int lineEnd, int id) {
		this(image, type, position, -1, -1, line, lineStart, lineEnd, id);
	}

	public final String getImage() {
		return image;
	}

	public final Enum<?> getType() {
		return type;
	}

	public final String getNameType() {
		return nameType;
	}

	public final int getBeginPosition() {
		return beginPosition;
	}

	public final int getPosition() {
		return position;
	}

	public final int getEndPosition() {
		return endPosition;
	}

	public final int getBeginColumn() {
		return beginColumn;
	}

	public final int getEndColumn() {
			return endColumn;
	}

	public final int getBeginLine() {
		return beginLine;
	}

	public final int getLine() {
		return line;
	}

	public final int getEndLine() {
		return endLine;
	}

	public final int getId() {
		return id;
	}

	@Override
	public final String toString() {
		return "{Token:{image: '" + getImage() + "', types:{type: " + getType() + ", group: " + getNameType() + "}, " + (getPosition() == -1 ? "position start: " + getBeginPosition() + ", position end: " + getEndPosition() : "position: " +getPosition() ) + ", column start: " + getBeginColumn() + ", column end: " + getEndColumn() + ", lines: {line: " + getLine() + ", line start: " + getBeginLine() + ", line end: " + getEndLine() + "}, id: " + getId() + "}};";
	}

	public final String toStringBetter() {
		return "{\n\tToken:{\n\t\timage: '" + getImage() + "',\n\t\ttypes: {\n\t\t\ttype: " + getType() + ",\n\t\t\tgroup: " + getNameType() + "\n\t\t},\n\t\t" + (getPosition() == -1 ? "position start: " + getBeginPosition() + ",\n\t\tposition end: " + getEndPosition() : "position: " +getPosition() ) + ",\n\t\tcolumn start: " + getBeginColumn() + ",\n\t\tcolumn end: " + getEndColumn() + ",\n\t\tlines: {\n\t\t\tline: " + getLine() + ",\n\t\t\tline start: " + getBeginLine() + ",\n\t\t\tline end: " + getEndLine() + "\n\t\t},\n\t\tid: " + getId() + "\n\t}\n};";
	}

	@Override
	public ArrayList<Token> getTokens() {
		return new ArrayList<>(List.of(this));
	}
}
