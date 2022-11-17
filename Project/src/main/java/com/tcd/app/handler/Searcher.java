package com.tcd.app.handler;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.similarities.ClassicSimilarity;
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
 		
        
        //STANDARD ANALYSER
        Analyzer analyzer = new StandardAnalyzer();
        
       // Create the query parser.
       String[] fields = {"ti", "Date1", "text"};
       MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
        
       try{
            // Open the directory that contains the search index
            Directory directory = FSDirectory.open(Paths.get(indexDirPath));
            
            path = Paths.get(resultFilePath);
    		Files.deleteIfExists(path);
            
            // Create objects to read and search across the index
    		DirectoryReader ireader = DirectoryReader.open(directory);
    		IndexSearcher isearcher = new IndexSearcher(ireader);

            for (int i = 0; i < collection.size(); i++) {
            	scoreQuery(isearcher, collection.get(i), resultFilePath, parser);
            }

            ireader.close();
            directory.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }
        
		
	}
	
	private static void scoreQuery(IndexSearcher isearcher, Map<String, String> queryMap, 
						String resultFile, MultiFieldQueryParser parser) throws Exception {
		
		String queryString = queryMap.get("title") + " "
				     + queryMap.get("description") + " "
		             + queryMap.get("narrative");
		queryString = queryString.replace("/", "");
		Query query = parser.parse(queryString);
		ScoreDoc[] hits = isearcher.search(query, MAX_RESULTS).scoreDocs;
	    for (int i = 0; i < hits.length; i++)
	    {
		   Document hitDoc = isearcher.doc(hits[i].doc);
		   System.out.println("hello");
		   String id = queryMap.get("number");
		   
		   FileWriter fw = new FileWriter(resultFile, true);
		   fw.write(id + " 0 " + hitDoc.get("ID") + " 0 " + hits[i].score + " STANDARD\n");
		   fw.close();
	    }
		
	}
	
}
