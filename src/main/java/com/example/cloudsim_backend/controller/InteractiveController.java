package com.example.cloudsim_backend.controller;

import com.example.cloudsim_backend.service.BackendFunction;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.workflowsim.examples.WorkflowSimBasicExample1;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;


@RestController
@CrossOrigin
public class InteractiveController {
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

    @GetMapping("/getGraph")
    public Graph getGraph(String name,int algorithm) throws ParserConfigurationException, IOException, SAXException {
        Graph graph = BackendFunction.analyzeXML(name);
        double result[][] = WorkflowSimBasicExample1.mySimulation(name,algorithm);
        Graph graph1 = BackendFunction.storeResult(graph,result);
        return graph1;
    }
}
