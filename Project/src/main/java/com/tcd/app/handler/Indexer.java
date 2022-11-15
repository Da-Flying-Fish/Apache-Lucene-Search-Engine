package com.tcd.app.handler;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class Indexer {

    public void createIndex(List<Map<String, String>> collection){
        //STANDARD ANALYSER
        Analyzer analyzer = new StandardAnalyzer();

        try{
            // Open the directory that contains the search index
            Directory directory = FSDirectory.open(Paths.get("Project/resources/index/latimes.index"));

            // Set up an index writer to add process and save documents to the index
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);

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

    private void addDocument(IndexWriter iwriter, Map<String, String> doc) throws IOException {
        Document document = new Document();
        for(Map.Entry<String, String> attribute : doc.entrySet()){
            document.add(new StringField(attribute.getKey(), attribute.getValue(), Field.Store.YES));
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
