package com.example.prhack.parsers;

import java.io.File;
import java.util.List;

public class ParserFileSystem {

//    File rootFile = new File("");
//    List<File> fileList = new List<File>;
//
//    public ParserFileSystem(
//            this.rootFile = rootFile
//    );


    public ParserFileSystem() {
    }

    public static void searchFiles(File rootFile, List<File> fileList) {
        System.out.println("searching at: " + rootFile.getAbsolutePath());
        if (rootFile.isDirectory()) {
            File[] directoryFile = rootFile.listFiles();
            if (directoryFile != null) {
                for (File file : directoryFile) {
                    if (file.isDirectory()) {
                        searchFiles(file, fileList);
                    } else {
                        if (file.getName().toLowerCase().endsWith(".pdf")) {
                            fileList.add(file);
                        }
                        if (file.getName().toLowerCase().endsWith(".doc")){
                            fileList.add(file);
                        }
                    }
                }
            }
        }
    }
}
