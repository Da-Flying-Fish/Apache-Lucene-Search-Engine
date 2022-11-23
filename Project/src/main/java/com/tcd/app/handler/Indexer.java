package com.tcd.app.handler;

import com.tcd.app.helper.PropertyHelper;
import com.tcd.app.helper.Utilities;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import com.tcd.app.dataModels.Constants;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import static com.tcd.app.handler.Parser.progressBar;

public class Indexer {

	static int indexProgress = 0;
	
    public static void createIndex(ArrayList<HashMap<String,String>> collection){
        //STANDARD ANALYSER
        Analyzer analyzer = new QueryExpansionAnalyser();
        System.out.println("Indexing Parsed Files ...");

        Properties properties = PropertyHelper.readPropFile("src/config.Properties");
        String indexDirPath = properties.getProperty("IndexedDataFolderPath");

        try{
            // Open the directory that contains the search index
            //Directory directory = FSDirectory.open(Paths.get("Project/resources/index/latimes.index"));
            File file = new File(indexDirPath);
            if(file.listFiles()!=null)
                Utilities.deleteFolder(file);
            Directory directory = FSDirectory.open(Paths.get(indexDirPath));


            // Set up an index writer to add process and save documents to the index
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            // config.setIndexSort() -- USEFULL?!?

            //STANDARD SIMILARITY
            config.setSimilarity(new BM25Similarity());

            IndexWriter iwriter = new IndexWriter(directory, config);
            collection.parallelStream().forEach((i) -> {
            	progressBar(indexProgress, collection.size());
                try {
					addDocument(i, iwriter);
				} catch (IOException e) {
					e.printStackTrace();
				}
                indexProgress++;
            });
            indexProgress = 0;
            System.out.println("\nIndexing Completed");
            System.out.println("Saving Index file...");
            iwriter.close();
            directory.close();
            System.out.println("Save Completed...");
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static void addDocument(Map<String, String> doc, IndexWriter iwriter) throws IOException {
        Document document = new Document();
        for(Map.Entry<String, String> attribute : doc.entrySet()){
            if(Constants.ConstantKeyMapping.keySet().contains(attribute.getKey()))
                document.add(new TextField(Constants.ConstantKeyMapping.get(attribute.getKey()), attribute.getValue(), Field.Store.YES));
            else {
                String other_value =document.get("OTHER");
                if(other_value==null){
                    document.add(new TextField("OTHER", attribute.getValue(), Field.Store.YES));
                }
                else{
                    document.add(new TextField(("OTHER"),other_value+attribute.getValue(),Field.Store.YES));
                }
            }
        }
        iwriter.addDocument(document);
    }
    public void deleteIndex(File file) {

        File[] contents = file.listFiles();
        if (contents != null) {

            for (File f: contents) {
                deleteIndex(f);
            }
        }
        file.delete();
    }
}
