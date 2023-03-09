package com.lab6.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
                    socketChannel.read(byteBuffer);

                    for (int i = 0; i < byteBuffer.limit(); i++) {
                        dataBytes[i] = byteBuffer.get(i);
                    }
                    System.out.println(new String(dataBytes, StandardCharsets.UTF_8));
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

}
