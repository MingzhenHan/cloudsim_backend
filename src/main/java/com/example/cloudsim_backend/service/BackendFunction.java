package com.example.cloudsim_backend.service;

import com.example.cloudsim_backend.controller.Conf;
import com.example.cloudsim_backend.controller.Edge;
import com.example.cloudsim_backend.controller.Graph;
import com.example.cloudsim_backend.controller.Node;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;

@Service
public class BackendFunction {
    public static Graph analyzeXML(String name) throws ParserConfigurationException, IOException, SAXException {
        //读xml文件
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        DocumentBuilder db=dbf.newDocumentBuilder();
        Document document=db.parse("D:\\projects\\cloudsim_backend\\inputfile\\"+name);

        //获取edge信息 先摸排一遍大小
        NodeList childs=document.getElementsByTagName("child");
        int p=0;
        for(int i=0;i<childs.getLength();i++) {
            Element child=(Element) childs.item(i);
            NodeList parents=child.getElementsByTagName("parent");
            for(int j=0;j<parents.getLength();j++) {
                p++;
            }
        }

        Edge [] edges = new Edge [p];
        p=0;
        for(int i=0;i<childs.getLength();i++) {
            Element child=(Element) childs.item(i);
            NodeList parents=child.getElementsByTagName("parent");
            for(int j=0;j<parents.getLength();j++) {
                Element parent=(Element) parents.item(j);
                edges[p] = new Edge();
                edges[p].setSource(parent.getAttribute("ref"));
                edges[p++].setTarget(child.getAttribute("ref"));
            }
        }

        Graph graph = new Graph();
        graph.setEdges(edges);
        return graph;
    }

    public static Graph storeResult(Graph graph,double [][] result){
        //按照id从小到大给result数组排序
        Arrays.sort(result, new Comparator<double[]>() {
            @Override
            public int compare(double[] t0, double[] t1) {
                return (int)(t0[0] - t1[0]);
            }
        });

        Node[] nodes = new Node [result.length-1];
        for(int i=0;i< nodes.length;i++){
            nodes[i]=new Node();
            DecimalFormat df = new DecimalFormat("0.##");
            nodes[i].setId(df.format(result[i][0]));
            nodes[i].setName(df.format(result[i][0]));

            Conf [] confs=new Conf[5];
            for(int j=0;j<5;j++){
                confs[j]=new Conf();
            }
            confs[0].setLabel("STATUS");
            confs[0].setValue("SUCCESS");
            confs[1].setLabel("VM ID");
            confs[1].setValue(df.format(result[i][3]));
            confs[2].setLabel("Time");
            confs[2].setValue(String.valueOf(result[i][4]));
            confs[3].setLabel("Start time");
            confs[3].setValue(String.valueOf(result[i][5]));
            confs[4].setLabel("Finish time");
            confs[4].setValue(String.valueOf(result[i][6]));
            nodes[i].setConf(confs);
        }
        graph.setNodes(nodes);
        return graph;
    }
}
