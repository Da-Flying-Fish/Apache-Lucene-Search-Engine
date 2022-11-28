package com.tcd.app.helper;

import org.apache.lucene.analysis.CharArraySet;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Utilities {

    public static List<File> getFilesInDir(String rootFolderPath){

        List<File> filesInDirectory = new ArrayList<>();
        try {
            try (DirectoryStream<Path> stream = Files
                    .newDirectoryStream(Paths.get(rootFolderPath))) {
                for (Path path : stream) {
                    if (!Files.isDirectory(path)) {
                        filesInDirectory.add(path.toFile());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filesInDirectory;
    }
    public static void deleteFolder(File file){
        for (File subFile : file.listFiles()) {
            if(subFile.isDirectory()) {
                deleteFolder(subFile);
            } else {
                subFile.delete();
            }
        }
        file.delete();
    }
    public static CharArraySet getStopWords(){
        CharArraySet stopwords = null;
        try {
            byte[] encoded = Files.readAllBytes(Paths.get("Project/resources/stopwords.txt"));
            String[] words = new String(encoded, StandardCharsets.UTF_8).split("\n");
            stopwords =  new CharArraySet(Arrays.asList(words), true);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return stopwords;
    }
}
