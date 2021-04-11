package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    public TextArea textArea;
    @FXML
    public TextField textField;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public HBox authPanel;
    @FXML
    public HBox standartPanel;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private final String IP_ADDRESS="localhost";
    private final int PORT =8189;
    private boolean authenticated;
    private String nickName;
    private Stage stage;

     @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(()->{
            stage = (Stage) textArea.getScene().getWindow();
        });

         setAuthenticated(false);

     }

    private void connect() {
        try {
            socket = new Socket(IP_ADDRESS,PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(()->{
                try{
                    while(true){
                        String str = in.readUTF();

                        if(str.startsWith("/")) {

                            if (str.equals("/end")) {
                                System.out.println("Disconnected");
                                break;
                            }
                            if (str.startsWith("/auth_ok")){
                               nickName = str.split("\\s+")[1];
                               setAuthenticated(true);
                               break;
                            }
                        } else {
                            textArea.appendText(str +"\n");
                        }
                    }

                    while(authenticated){
                        String str = in.readUTF();
                        if (str.equals("/end")){
                            System.out.println("Disconnected");
                            break;
                        }
                        textArea.appendText(str +"\n");
                    }
                } catch (IOException e){
                    e.printStackTrace();
                } finally {
                    setAuthenticated(false);
                    try {
                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void sendMsg() {
            try {
                String st = textField.getText();
                out.writeUTF(st);
                textField.clear();
                textField.requestFocus();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
    @FXML
    public void tryToAuth(ActionEvent actionEvent) {
         if (socket == null || socket.isClosed()) connect();
         String msg = String.format("/auth %s %s",loginField.getText().trim(),passwordField.getText().trim()); //trim убирает пробелы
        try {
            out.writeUTF(msg);
            passwordField.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
        authPanel.setVisible(!authenticated);
        authPanel.setManaged(!authenticated);
        standartPanel.setVisible(authenticated);
        standartPanel.setManaged(authenticated);
        textArea.setVisible(authenticated);
        textArea.setManaged(authenticated);

        if(!authenticated){
            nickName = null;
        }
        setTitle(nickName);
        textArea.clear();
    }
    private void setTitle(String nickName){
         Platform.runLater(()->{
             if (nickName.equals("")) {
                 stage.setTitle("Open chat");
             } else {
                 stage.setTitle(String.format("Open chat [%s]" ,nickName));
             }
         });
    }

}
