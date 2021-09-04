package com.example.prhack.restservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

public class FSResponse {

    private final ArrayList<String> folders;
    private final ArrayList<String> files;

    public FSResponse(ArrayList<String> folders, ArrayList<String> files) {
        this.folders = folders;
        this.files = files;
    }

    @PostMapping("/create")
    @ResponseBody
    public FSResponse createProduct(@RequestBody FSResponse product) {
        // custom logic
        return product;
    }
}
