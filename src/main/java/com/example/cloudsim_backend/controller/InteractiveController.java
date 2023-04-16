package com.example.cloudsim_backend.controller;

import com.example.cloudsim_backend.service.BackendFunction;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class InteractiveController {
    @Autowired
    private BackendFunction backendFunction;

    @GetMapping("/getGraph")
    public Graph getGraph(){
        return backendFunction.processGraph();
    }
}
