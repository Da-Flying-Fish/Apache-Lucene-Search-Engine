package com.tcd.app.helper;

import java.io.File;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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


}