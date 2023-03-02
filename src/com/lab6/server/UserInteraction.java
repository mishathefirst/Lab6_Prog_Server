package com.lab6.server;

import com.lab6.server.entities.MusicBand;

import java.util.HashSet;
import java.util.Scanner;

public class UserInteraction {

    CollectionManagement collectionManagement = new CollectionManagement();
    FileProcessing fileProcessing = new FileProcessing();

    public void start() {

        Scanner in = new Scanner(System.in);
        System.out.println("Type in the name of the file with the collection:");
        String fileName = in.nextLine();

        if (fileProcessing.readCollectionFromFile(fileName) == null) {
            collectionManagement.setCollection(new HashSet<MusicBand>());
        } else {
            collectionManagement.setCollection(fileProcessing.readCollectionFromFile(fileName));
        }

        System.out.println("Setting connection with the client...");
        //Set the connection with the client here


    }

}
