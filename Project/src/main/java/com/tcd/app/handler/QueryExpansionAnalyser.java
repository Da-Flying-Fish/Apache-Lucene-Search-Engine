package com.tcd.app.handler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import java.text.ParseException;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.standard.ClassicTokenizer;
import org.apache.lucene.analysis.synonym.SynonymGraphFilter;
import org.apache.lucene.analysis.core.FlattenGraphFilter;
import org.apache.lucene.analysis.synonym.SynonymMap;
import org.apache.lucene.analysis.synonym.WordnetSynonymParser;

public class QueryExpansionAnalyser extends Analyzer
{
	protected TokenStreamComponents createComponents(String fieldName){
	      System.out.println("1");
	    // TODO Auto-generated method stub
	    Tokenizer source = new ClassicTokenizer();

	    TokenStream filter = new LowerCaseFilter(source);
	    SynonymMap mySynonymMap = null;

	    try {

	        try {
				mySynonymMap = buildSynonym();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    filter = new SynonymGraphFilter(filter, mySynonymMap, false); 
	    filter = new FlattenGraphFilter(filter);

	    return new TokenStreamComponents(source, filter);
	}
	
	private static SynonymMap buildSynonym() throws IOException, ParseException
	{    
		System.out.print("build");
	    File file = new File("wn\\wn_s.pl");

	    InputStream stream = new FileInputStream(file);

	    Reader rulesReader = new InputStreamReader(stream); 
	    SynonymMap.Builder parser = null;
	    parser = new WordnetSynonymParser(true, true, new StandardAnalyzer(CharArraySet.EMPTY_SET));
	    System.out.print(parser.toString());
	   ((WordnetSynonymParser) parser).parse(rulesReader);  
	    SynonymMap synonymMap = parser.build();
	    return synonymMap;
	}

}
