package com.example.cloudsim_backend.service;

import com.example.cloudsim_backend.controller.Graph;
import org.springframework.stereotype.Service;

@Service
public class BackendFunction {
    public Graph processGraph(){
        Graph graph = new Graph();
        graph.setA(5);
        graph.setB("qing");
        return graph;
    }
}
