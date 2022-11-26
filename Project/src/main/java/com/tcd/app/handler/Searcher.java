package com.tcd.app.handler;

import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.tcd.app.dataModels.Constants;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queries.mlt.MoreLikeThisQuery;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import com.tcd.app.helper.PropertyHelper;

public class Searcher 
{
	// Limit the number of search results we get
	private static int MAX_RESULTS = 1000;
		
	public static void queryIndex(ArrayList<HashMap<String,String>> collection){

        Properties properties = PropertyHelper.readPropFile("src/config.Properties");
        
        String indexDirPath = properties.getProperty("IndexedDataFolderPath");
        
        String resultDirPath = properties.getProperty("ResultDataFolderPath");
        String resultFilePath = resultDirPath +  "/" + properties.getProperty("ResultsName");
        
        // Check if index exists
 		Path path = Paths.get(indexDirPath);
 		if(!Files.exists(path))
 		{
 			System.out.println("Error! Index directory does not exist");
 			return;
 		}
        
        //CUSTOM ANALYSER
        Analyzer analyzer = new CustomAnalyzer();
        
       // Create the query parser.
       String[] fields = Constants.documentFieldList;
       MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
        
       try{
            // Open the directory that contains the search index
            Directory directory = FSDirectory.open(Paths.get(indexDirPath));
            
            path = Paths.get(resultDirPath);
            
            if(!Files.exists(path))
            	Files.createDirectory(path);
            
            path = Paths.get(resultFilePath);
    		Files.deleteIfExists(path);
            
            // Create objects to read and search across the index
    		DirectoryReader ireader = DirectoryReader.open(directory);
    		IndexSearcher isearcher = new IndexSearcher(ireader);
		    isearcher.setSimilarity(new BM25Similarity());

		    System.out.println("Searching...");
            for (int i = 0; i < collection.size(); i++) {
				Parser.progressBar(i, collection.size());
            	scoreQuery(isearcher, collection.get(i), resultFilePath, parser, analyzer, ireader);
            }
            ireader.close();
            directory.close();
		    System.out.println("\nSearch Completed");
	   }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        
		
	}
	
	private static void scoreQuery(IndexSearcher isearcher, Map<String, String> queryMap, 
						String resultFile, MultiFieldQueryParser parser, Analyzer analyzer, DirectoryReader ireader) throws Exception {
		String queryString = queryMap.get("title") + " " + queryMap.get("description") + " " + queryMap.get("narrative");
		queryString = queryString.replace("/", "");
		Query query = parser.parse(queryString);
		Query queryExpanded = expandQuery(isearcher, analyzer, query, ireader);
		ScoreDoc[] hits = isearcher.search(queryExpanded, MAX_RESULTS).scoreDocs;
	    for (int i = 0; i < hits.length; i++)
	    {
		   Document hitDoc = isearcher.doc(hits[i].doc);
		   String id = queryMap.get("number");
		   
		   FileWriter fw = new FileWriter(resultFile, true);
		   fw.write(id + " 0 " + hitDoc.get("DOC_ID") + " 0 " + hits[i].score + " STANDARD\n");
		   fw.close();
	    }
		
	}
	
	private static Query expandQuery(IndexSearcher isearcher, Analyzer analyzer, Query query,
						 DirectoryReader ireader) throws Exception {
		BooleanQuery.Builder queryBuilder = new BooleanQuery.Builder();
		queryBuilder.add(query, BooleanClause.Occur.SHOULD);
		ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;

		for (int i = 0; i < hits.length; i++)
	    {
			Document hitDoc = ireader.document(hits[i].doc);
			String field = hitDoc.getField("TEXT").stringValue();
			String[] moreLikeThisField = {"TEXT"};
			MoreLikeThisQuery moreLikeThisQueryExpanded = new MoreLikeThisQuery(field, moreLikeThisField, analyzer, "TEXT");
			Query queryExpanded = moreLikeThisQueryExpanded.rewrite(ireader);
			queryBuilder.add(queryExpanded, BooleanClause.Occur.SHOULD);
		}
		
		return queryBuilder.build();
	}
	
}
