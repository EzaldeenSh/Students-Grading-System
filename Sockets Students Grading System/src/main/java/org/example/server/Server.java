package org.example.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {


    public static void main(String[] args) {
        try {
             ServerSocket serverSocket;
            serverSocket = new ServerSocket(8000);
            serverSocket.setReuseAddress(true);
            System.out.println("Server Started");


            while(true){
                Socket client = serverSocket.accept();
                System.out.println("Client Accepted");

                ClientHandler clientHandler = new ClientHandler(client);
                new Thread(clientHandler).start();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
