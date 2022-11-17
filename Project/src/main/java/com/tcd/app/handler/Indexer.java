package com.tcd.app.handler;

import com.tcd.app.helper.PropertyHelper;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Indexer {

    public static void createIndex(ArrayList<HashMap<String,String>> collection){
        //STANDARD ANALYSER
        Analyzer analyzer = new StandardAnalyzer();

        Properties properties = PropertyHelper.readPropFile("src/config.Properties");
        String dataSourceDir = properties.getProperty("IndexedDataFolderPath");
        String indexDirPath = dataSourceDir+"/"+properties.getProperty("IndexName");

        try{
            // Open the directory that contains the search index
            //Directory directory = FSDirectory.open(Paths.get("Project/resources/index/latimes.index"));
            Directory directory = FSDirectory.open(Paths.get(indexDirPath));


            // Set up an index writer to add process and save documents to the index
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            // config.setIndexSort() -- USEFULL?!?

            //STANDARD SIMILARITY
            config.setSimilarity(new ClassicSimilarity());

            IndexWriter iwriter = new IndexWriter(directory, config);

            for (int i = 0; i < collection.size(); i++) {
                addDocument(iwriter, collection.get(i));
            }

            iwriter.close();
            directory.close();
        }catch (Exception e){
            e.printStackTrace();
            System.exit(1);
        }

    }

    private static void addDocument(IndexWriter iwriter, Map<String, String> doc) throws IOException {
        Document document = new Document();
        for(Map.Entry<String, String> attribute : doc.entrySet()){
            document.add(new TextField(attribute.getKey(), attribute.getValue(), Field.Store.YES));
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
