package com.tcd.app.handler;

import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.miscellaneous.TrimFilter;
import org.apache.lucene.analysis.standard.ClassicFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import static com.tcd.app.helper.Utilities.getStopWords;

public final class CustomAnalyzer extends StopwordAnalyzerBase {

  @Override
  protected TokenStreamComponents createComponents(String fieldName) {
    final Tokenizer source = new StandardTokenizer();
    TokenStream result = new EnglishPossessiveFilter(source);
    TokenStream tokenStream = new ClassicFilter(source);
    tokenStream = new EnglishPossessiveFilter(tokenStream);
    tokenStream = new ASCIIFoldingFilter(tokenStream);
    tokenStream = new LowerCaseFilter(tokenStream);
    tokenStream = new TrimFilter(tokenStream);
    tokenStream = new StopFilter(tokenStream, getStopWords());
    tokenStream = new PorterStemFilter(tokenStream);
    return new TokenStreamComponents(source, tokenStream);
  }

}