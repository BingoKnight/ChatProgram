package sample;

import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread {

    private Socket socket;
    private int id;
    private boolean activeSession = true;
    private DataInputStream input;
    private DataOutputStream output;

    public ClientThread(Socket socket, int id){
        this.socket = socket;
        this.id = id;
    }

    public void run(){
        System.out.println("Client Connected");

        try {
            while(true) {
                String inputStr = input.readUTF();
                String username = input.readUTF();

                for (ClientThread ct : Main.clientList) {
                    if (activeSession && ct.id != this.id) {
                        System.out.println("inputStr: " + inputStr);
                        System.out.println("name: " + username);
                        ct.output.writeUTF(inputStr);
                        ct.output.flush();
                        ct.output.writeUTF(username);
                        ct.output.flush();
                    }
                }
            }
        } catch (IOException e){
            e.printStackTrace();
            activeSession = false;
//            try {
//                socket.close();
//            } catch (IOException e1) { e.printStackTrace(); }
        }
    }

    public void setStreamReaders(DataInputStream input, DataOutputStream output){
       this.input = input;
       this.output = output;
    }
}

