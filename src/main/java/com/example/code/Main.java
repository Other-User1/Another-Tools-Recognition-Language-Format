package com.example.code;

import another.tools.recognition.language.format.Syntactics.*;
import another.tools.recognition.language.format.Lexers.*;

import com.java.components.lang.CompilerTaskException;

import java.io.File;

@SuppressWarnings( { "unused" } )
public class Main {
	public static void main(String[] args) throws Exception {
		Lexer lx = getLexer(new File("test.txt"));
		getSyntactic(lx).onSyntactic();
	}

	private static Syntactic getSyntactic(Lexer lx) throws CompilerTaskException {
		Syntactic sa = new Syntactic(lx);
		sa.setSyntacticGrammar(new MainGrammatical());
		return sa;
	}

	private static Lexer getLexer(String str) throws CompilerTaskException {
		Lexer lx = new Lexer(str);
		optionsLexer(lx);
		return lx;
	}

	private static Lexer getLexer(File file) throws CompilerTaskException {
		Lexer lx = new Lexer(file);
		optionsLexer(lx);
		return lx;
	}

	private static void optionsLexer(Lexer lexer) throws CompilerTaskException {
		lexer.setLexerTokenizer(new MainTokenizer());
	}
}