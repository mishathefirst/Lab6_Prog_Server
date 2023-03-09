package com.lab6.server;

import com.lab6.server.entities.CollectionData;
import com.lab6.server.entities.StartingObject;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class ClientInteraction {

    private final CollectionManagement collectionManagement = new CollectionManagement();
    private final UserInteraction userInteraction = new UserInteraction();
    private final StartingObject start = userInteraction.start();
    private final FileProcessing fileProcessing = new FileProcessing();

    public void start() {

        collectionManagement.setCollection(start.getCollection());


        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress("localhost", 8080));
            serverSocketChannel.configureBlocking(false);

            ByteBuffer byteBuffer = ByteBuffer.allocate(50);
            byteBuffer.clear();
            byte[] dataBytes = new byte[50];



            while (true) {
                SocketChannel socketChannel = serverSocketChannel.accept();

                if (socketChannel != null) {
                    socketChannel.read(byteBuffer);

                    for (int i = 0; i < byteBuffer.limit(); i++) {
                        dataBytes[i] = byteBuffer.get(i);
                    }
                    executeCommand(new String(dataBytes, StandardCharsets.UTF_8), start);
                    Arrays.fill(dataBytes, (byte) 0);
                    byteBuffer.clear();
                    for (int i = 0; i < byteBuffer.limit(); i++) {
                        byteBuffer.put((byte) 0);
                    }
                    byteBuffer.position(0);

                }
            }


            //serverSocketChannel.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }


    private void executeCommand(String command, StartingObject start) {

        if (command.equals("clear")) {
            int response = clearCollection(start);
        } else if (command.equals("info")) {
            CollectionData response = collectionInfo(start);
        }
    }


    private int clearCollection(StartingObject start) {
        collectionManagement.clear();
        fileProcessing.writeCollectionIntoFile(start.getCollection(), start.getFileName());
        return 200;
    }

    private CollectionData collectionInfo(StartingObject start) {
        return new CollectionData(start.getCollection().getClass().getName(), collectionManagement.getInitialisationDate(),
                start.getCollection().size(), start.getCollection().isEmpty());
    }

}
