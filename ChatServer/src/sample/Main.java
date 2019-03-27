package sample;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static ArrayList<ClientThread> clientList = new ArrayList<>();

    public static void main(String[] args) {

        int id = 0;

        try(ServerSocket serverSocket = new ServerSocket(5000)){

            while(true){
                Socket socket = serverSocket.accept();
                ClientThread client = new ClientThread(socket, id++);
                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
                client.setStreamReaders(input, output);
                clientList.add(client);
                client.start();
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
}