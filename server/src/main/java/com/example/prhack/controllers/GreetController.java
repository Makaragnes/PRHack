package com.example.prhack.controllers;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import com.example.prhack.restservice.FSResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/files")
    public String greet(@RequestParam(value = "name", defaultValue = "World") String name) {
        ArrayList<File> fileList = new ArrayList<>();
        searchFiles(new File("/home/riki/PromrelationBankHack/dataset/"+name), fileList,name);

        ArrayList<String> files = new ArrayList<>();
        ArrayList<String> folders = new ArrayList<>();


        for (File file : fileList) {
            System.out.println(file.getName());

            if (file.isDirectory()) {
                folders.add(file.getName());
            } else {
                files.add(file.getName());
            }

        }

        FSResponse response = new FSResponse(folders, files);

        Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = prettyGson.toJson(response);

        return prettyJson;
    }


    public static void searchFiles(File rootFile, List<File> fileList,String name) {
        System.out.println("searching at: " + rootFile.getAbsolutePath());
        if (rootFile.isDirectory()) {
            File[] directoryFile = rootFile.listFiles();
            if (directoryFile != null) {
                for (File file : directoryFile) {
                    //fileList.add(file);

                   // searchFiles(file, fileList,name);
                    if (file.isDirectory()) {
                       // searchFiles(file, fileList);
                        fileList.add(file);
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








//    ParserFileSystem parserFileSystem = new ParserFileSystem();
//    parserFileSystem.searchFiles("", "")

