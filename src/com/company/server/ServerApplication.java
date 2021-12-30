package com.company.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApplication {

    private final ServerSocket serverSocket;

    public static void main(String[] args){
        try {
            ServerApplication server = new ServerApplication(1111);
            server.start();
        } catch (IOException e) {
            throw new IllegalStateException("No connection to server", e);
        }
    }

    public ServerApplication(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    public void start() throws IOException {
        System.out.println("Server port: " + serverSocket.getLocalPort());
        while (true){
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client address: " + clientSocket.getInetAddress());
            ChessSession session = new ChessSession(clientSocket);
            Thread t = new Thread(session);
            t.start();
        }
    }
}
