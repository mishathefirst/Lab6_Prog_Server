package com.lab6.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

public class ClientInteraction {

    public void start() {

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
                    System.out.println(socketChannel.read(byteBuffer));
                    byteBuffer.put(dataBytes);
                    System.out.println(new String(dataBytes, StandardCharsets.UTF_8));
                    byteBuffer.clear();
                }
            }


            //serverSocketChannel.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

}
