package com.socket.client.impl;

import com.context.ChatConstant;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;

public class Client {
    private final static Logger LOGGER = Logger.getLogger(Client.class);

    private static Socket clientSocket;

    private static BufferedReader in;
    private static BufferedWriter out;

    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    private static final String HOST = ChatConstant.HOSTNAME;
    private static final String PORT = ChatConstant.PORT;

    public static void main(String[] args) {
        try {
            try {
                initClient();
                while (true) {
                    System.out.println("Say a word:");
                    String word = reader.readLine();
                    out.write(word + "\n");
                    out.flush();
                    String serverWord = in.readLine();
                    System.out.println(serverWord);
                }
            } finally {
                LOGGER.error("Something went wrong... Will close connection.");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void initClient() throws IOException{
        clientSocket = new Socket(HOST, Integer.valueOf(PORT));
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        LOGGER.info(String.format("Connected to %s:%s", HOST, PORT));
    }

}
