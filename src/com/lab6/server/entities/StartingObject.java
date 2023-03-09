package com.lab6.server.entities;

import java.util.HashSet;

public class StartingObject {

    private String fileName;
    private HashSet<MusicBand> collection;

    public StartingObject() {}

    public StartingObject(String fileName, HashSet<MusicBand> collection) {
        this.fileName = fileName;
        this.collection = collection;
    }

    public String getFileName() {
        return fileName;
    }

    public HashSet<MusicBand> getCollection() {
        return collection;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setCollection(HashSet<MusicBand> collection) {
        this.collection = collection;
    }
}
