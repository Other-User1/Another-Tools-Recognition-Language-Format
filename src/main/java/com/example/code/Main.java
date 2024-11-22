package com.example.code;
// Test Class for "ATRLF"
import another.tools.recognition.language.format.lexers.Lexer;
import another.tools.recognition.language.format.syntactics.Syntactic;
import another.tools.recognition.language.format.syntactics.SyntacticAction;
import another.tools.recognition.language.format.tokens.Token;

import com.java.components.lang.CompilerTaskException;

import java.io.File;
import java.util.ArrayList;

@SuppressWarnings( { "unused" } )
public class Main {
	public static void main(String[] args) throws CompilerTaskException {
		Lexer lexer = new Lexer(new File("test.capp"));
		lexer.setLexerTokenizer(new MainTokenizer());

		Syntactic syntactic = new Syntactic(lexer);
		syntactic.addNewGrammatical(new MainGrammatical.VariableNumberDeclarationGrammar(), new SyntacticAction() {
			@Override
			public void execute(ArrayList<Token> tokens) {
				StringBuilder sb = new StringBuilder();
				tokens.forEach(token -> sb.append(token.getImage()).append(' '));
				sb.setLength(sb.length() - 1);
				System.out.println(sb);
			}
		});
		syntactic.onParse();
	}
}