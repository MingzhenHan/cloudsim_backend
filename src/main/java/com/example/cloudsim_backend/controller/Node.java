package com.example.cloudsim_backend.controller;

public class Node {
    private String id;
    private String name;
    private Conf [] conf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Conf[] getConf() {
        return conf;
    }

    public void setConf(Conf[] conf) {
        this.conf = conf;
    }
}
