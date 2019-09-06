/*
 * JTreeSS - A Java library for reading and evaluating TreeSS documents
 * Copyright (C) 2019 Ben Stöver, Charlotte Schmitt
 * <http://bioinfweb.info/JTreeSS>
 * 
 * This file is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This file is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package info.bioinfweb.jtreess;


import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import info.bioinfweb.commons.appversion.ApplicationType;
import info.bioinfweb.commons.appversion.ApplicationVersion;
import info.bioinfweb.jtreess.document.Document;
import info.bioinfweb.jtreess.execute.ApplicationDataProvider;
import info.bioinfweb.jtreess.reader.SyntaxTreeListener;
import info.bioinfweb.jtreess.reader.parser.TreeSSLexer;
import info.bioinfweb.jtreess.reader.parser.TreeSSParser;



public class TreeSSProcessor {
	private static TreeSSProcessor firstInstance = null;
	
	private ApplicationVersion version = new ApplicationVersion(0, 0, 0, 132, ApplicationType.ALPHA); 
	
	
	private TreeSSProcessor() {
		super();
	}


	public static TreeSSProcessor getInstance() {
		if (firstInstance == null) {
			firstInstance = new TreeSSProcessor();
		}
		return firstInstance;
	}
	
	
	public ApplicationVersion getVersion() {
		return version;
	}


	public Document readTreeSS(String fileName) throws IOException {
		return readTreeSS(CharStreams.fromFileName(fileName));
	}
	
	
	public Document readTreeSS(InputStream stream) throws IOException {
		return readTreeSS(CharStreams.fromStream(stream));
	}
	
	
	public Document readTreeSS(InputStream stream, Charset charset) throws IOException {
		return readTreeSS(CharStreams.fromStream(stream, charset));
	}
	
	
	private Document readTreeSS(CharStream charStream) {
		SyntaxTreeListener listener = new SyntaxTreeListener();
		new ParseTreeWalker().walk(listener, new TreeSSParser(new CommonTokenStream(new TreeSSLexer(charStream))).document());
		//TODO Does the character stream or any other resource need to be closed or released?
		return listener.getDocument();  //TODO Perform semantic analysis before returning the document
	}
	
	//TODO Possibly add method to write TreeSS file in the future.
	
	
	public <N> void applyDocument(Document document, ApplicationDataProvider<N> dataProvider) {
		//TODO Process document with information from dataProvider to generate necessary calls of dataProvider.setFormat().
	}
}
