package com.lab6.server;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lab6.server.entities.MusicBand;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Scanner;

public class FileProcessing {

        HashSet<MusicBand> readCollectionFromFile(String fileName) {

            File file = new File(fileName);
            StringBuilder fileContent = new StringBuilder();
            Gson gson = new Gson();
            HashSet<MusicBand> collection = new HashSet<>();

            try {
                Scanner fileScanner = new Scanner(file);
                while (fileScanner.hasNext()) {
                    String fileLine = fileScanner.nextLine();
                    fileContent.append(fileLine);
                }
                fileScanner.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Type collectionType = new TypeToken<HashSet<MusicBand>>(){}.getType();
            collection = gson.fromJson(fileContent.toString(), collectionType);

            return collection;
        }




        void writeCollectionIntoFile(HashSet<MusicBand> collection, String fileName) {

            Gson gson = new Gson();
            String collectionString = gson.toJson(collection);
            byte[] collectionStringBytes = collectionString.getBytes();

            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                FileOutputStream outputStream = new FileOutputStream(fileName);
                byteArrayOutputStream.write(collectionStringBytes);
                byteArrayOutputStream.writeTo(outputStream);
                outputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
