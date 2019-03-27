package sample;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ResourceBundle;

//need to set up client for two threads:
// 1. checking to see if server has another client connected
// 2. checking if messages have been received.

//will occasionally cut off message that sender sends.

public class Controller implements Initializable {

    @FXML private ToolBar headerBar;
    @FXML private TextArea chatBar;
    @FXML private Button closeButton;
    @FXML private Button miniButton;

    @FXML public ListView chatList;

    private Stage stage;
    private double xPos, yPos;
    private Socket socket = null;
    DataInputStream input = null;

    public  static ObservableList<VBox> oList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource) {

        headerBar.setOnMousePressed(e->{
            xPos = stage.getX() - e.getScreenX();
            yPos = stage.getY() - e.getScreenY();
        });
        headerBar.setOnMouseDragged(e->{
            stage.setX(e.getScreenX() + xPos);
            stage.setY(e.getScreenY() + yPos);
        });

        chatBar.setWrapText(true);

        closeButton.setPadding(Insets.EMPTY);
        miniButton.setPadding(Insets.EMPTY);

        try {
            socket = new Socket("localhost", 5000);
            System.out.println("Socket connected");

            input = new DataInputStream(socket.getInputStream());
            Runnable task = () -> {
                try {
                    while (true) {
                        String receivedStr = input.readUTF();
                        String receivedName = input.readUTF();

                        VBox receivedBlock = new VBox();
                        receivedBlock.setId("receivedBlock");

                        Label receivedText = new Label();
                        receivedText.setId("receivedText");
                        receivedText.setWrapText(true);

                        Label senderName = new Label();
                        senderName.setId("senderName");

                        receivedBlock.getChildren().addAll(receivedText, senderName);

                        receivedText.setText(receivedStr);
                        senderName.setText(receivedName);

                        Platform.runLater(() -> {
                            oList.add(receivedBlock);
                            chatList.setItems(oList);
                        });
                    }
                } catch (IOException e1) { e1.printStackTrace(); }
            };
            new Thread(task).start();

        } catch(IOException e){
            System.out.println("Client Error: " + e.getMessage());
        }
    }

    @FXML
    private void closeClicked() {
        System.exit(0);
    }

    @FXML
    private void minimizeStage(MouseEvent e){
        ((Stage)((Button)e.getSource()).getScene().getWindow()).setIconified(true);
    }

    @FXML private void sendMessage(){
        VBox chatBlock = new VBox();
        chatBlock.setId("chatBlock");

        Label chatText = new Label(chatBar.getText());
        chatText.setId("chatText");
        chatText.setWrapText(true);
        chatBar.clear();

        Label chatName = new Label("Nathan");
        chatName.setId("chatName");

        chatBlock.getChildren().addAll(chatText, chatName);

        oList.add(chatBlock);
        sendToServer(chatText.getText(), chatName.getText());
        chatList.setItems(oList);
        chatList.scrollTo(chatList.getItems().size()-1);
    }

    public void sendToServer(String sentText, String sentName){
        try {

            socket.setSoTimeout(10000);

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            output.writeUTF(sentText);
            output.writeUTF("Server");





        } catch(SocketTimeoutException e1) {
            e1.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void receiveFromServer(){

    }

    public void setStage(Stage stage){
        this.stage = stage;
    }
}

