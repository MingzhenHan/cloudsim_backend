package com.example.cloudsim_backend.controller;

import com.example.cloudsim_backend.service.BackendFunction;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;


@RestController
@CrossOrigin
public class InteractiveController {
    @Autowired
    private BackendFunction backendFunction;

    @GetMapping("/getGraph")
    public Graph getGraph(){
        Graph graph = new Graph();
        graph.setA(5);
        graph.setB("qing");
        return graph;
    }

    @PostMapping("/uploadfile")
    public Result uploadFile(MultipartFile file, HttpServletRequest req) {
        Result result= new Result();

        try {
            file.transferTo(new File("D:\\projects\\cloudsim_backend\\inputfile", file.getOriginalFilename()));
            result.setStatus(true);
            result.setMsg("file upload success");
        } catch (IOException e) {
            result.setStatus(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }
}
