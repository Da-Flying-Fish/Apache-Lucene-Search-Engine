package com.tcd.app.handler;

import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public final class CustomAnalyzer extends StopwordAnalyzerBase {

  @Override
  protected TokenStreamComponents createComponents(String fieldName) {
    final Tokenizer source = new StandardTokenizer();
    TokenStream result = new EnglishPossessiveFilter(source);
    result = new LowerCaseFilter(result);
    result = new StopFilter(result, EnglishAnalyzer.ENGLISH_STOP_WORDS_SET);
    return new TokenStreamComponents(source, result);
  }

}