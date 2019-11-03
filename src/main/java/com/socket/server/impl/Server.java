package com.socket.server.impl;

import com.context.ChatConstant;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final Logger LOGGER = Logger.getLogger(Server.class);

    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {

        try {
            try  {
                initServer();
                try {
                    while(true) {
                        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                        String word = in.readLine();
                        LOGGER.info("Client wrote: ".concat(word));
                        out.write(String.format("Hi! it's serever. You just wrote %s \n", word));
                        out.flush();
                    }

                } finally {
                    LOGGER.warn("Server closed.");
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                LOGGER.warn("Server closed.");
                server.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initServer() throws IOException {
        final String PORT = ChatConstant.PORT;
        server = new ServerSocket(Integer.valueOf(PORT));
        LOGGER.info("Listening on ".concat(PORT));
        clientSocket = server.accept();
    }
}
