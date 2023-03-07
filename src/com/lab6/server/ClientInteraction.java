package com.lab6.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

public class ClientInteraction {

    public void start() {

        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.connect(new InetSocketAddress("localhost", 8080));
            socketChannel.configureBlocking(false);




            socketChannel.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

}
