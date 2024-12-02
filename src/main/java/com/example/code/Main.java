package com.example.code;

import another.tools.recognition.language.format.Grammaticals.*;
import another.tools.recognition.language.format.Lexers.*;
import another.tools.recognition.language.format.Syntactics.*;

import another.tools.recognition.language.format.Tokens.*;

import com.java.components.lang.CompilerTaskException;

import java.io.File;
import java.util.ArrayList;

import static another.tools.recognition.language.format.Tokens.TokenType.*;
import static com.example.code.ExtraTokenType.IdentifierToken;

@SuppressWarnings( { "unused" } )
public class Main {
	public static void main(String[] args) throws CompilerTaskException {
		getSyntactic(getLexer(new File("test.txt"))).onSyntactic();
	}

	private static Syntactic getSyntactic(Lexer lx) throws CompilerTaskException {
		Syntactic sa = new Syntactic(lx);
		sa.setSyntacticGrammar(new SyntacticGrammatical() {
			@Override
			public Grammatical run() throws CompilerTaskException {
				return OneOrMore(variable());
			}

			public Grammatical variable() {
				return Action(
						Sequence(
								variableGrammar()
						), new SyntacticAction() {
							@Override
							public void execute(ArrayList<Token> tokens) throws CompilerTaskException {
								tokens.forEach(token -> System.out.print(token.getImage() + ' '));
								System.out.println();
							}
						}
				);
			}

			public Grammatical variableGrammar() {
				return Sequence(
						IdentifierToken, Skip(ColonToken), IdentifierToken, Optional(initialize()), ZeroOrMore(addMoreVariable()), Skip(SemiColonToken)
				);
			}

			public Grammatical initialize() {
				return Sequence(
						EqualToken, NumberToken
				);
			}

			public Grammatical addMoreVariable() {
				return Sequence(
						CommaToken, IdentifierToken, Optional(initialize())
				);
			}
		});
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

	public static String ParseTree(Grammatical grammar, int tab) throws CompilerTaskException {
		StringBuilder sb = new StringBuilder();
		for (Object g : grammar.getGrammars()) {
			if (g instanceof TokenType tt) {
				sb.append(" ".repeat(tab)).append(tt.name());
				sb.append("\n");
				continue;
			}
			if (g instanceof TokenText tt) {
				sb.append(" ".repeat(tab)).append(tt.getText());
				sb.append("\n");
				continue;
			}
			if (g instanceof Grammatical g2) {
				sb.append(ParseTree(g2, tab + (tab / 3)));
			}
		}
		return sb.toString();
	}

	public String parseTree;
}