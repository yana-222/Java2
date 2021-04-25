package client;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
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
    @FXML
    public ListView <String>clientList;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private final String IP_ADDRESS="localhost";
    private final int PORT =8189;
    private boolean authenticated;
    private String nickName;
    private Stage stage;
    private Stage regStage;
    private Stage changeStage;
    private Regcontroller prR;
    private Changecontroller changecontroller;
    private History history;
    private totalHistory totalHistory;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Platform.runLater(()->{
            stage = (Stage) textArea.getScene().getWindow();
            stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    System.out.println("Bye");
                    if (socket != null && !socket.isClosed()){
                        try {
                            out.writeUTF("/end");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
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
                            if (str.startsWith("/reg_ok")){
                                prR.showResult("/reg_ok");
                            }
                            if (str.startsWith("/reg_no")){
                                prR.showResult("/reg_no");
                            }
                            if (str.startsWith("/change_ok")){
                                changecontroller.showResult("/change_ok");
                            }
                            if (str.startsWith("/change_no")){
                                changecontroller.showResult("/change_no");
                            }
                        } else {
                            textArea.appendText(str +"\n");
                        }
                    }

                    while(authenticated){
                        String str = in.readUTF();
                        if(str.startsWith("/")) {
                            if (str.equals("/end")) {
                                System.out.println("Disconnected");
                                break;
                            }
                            if(str.startsWith("/clientslist")){
                                String[] token = str.split("\\s+");
                                Platform.runLater(()->{
                                    clientList.getItems().clear();
                                    for (int i = 1;i< token.length;i++){
                                        clientList.getItems().add(token[i]);
                                    }
                                });
                            }

                        } else
                            textArea.appendText(str +"\n");
                            history.write(str);
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
        //  textArea.setVisible(authenticated);
        //  textArea.setManaged(authenticated);
        clientList.setVisible(authenticated);
        clientList.setManaged(authenticated);

        if(!authenticated){
            nickName = null;
        }
        if(authenticated){
            try {
                history= new History(nickName);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        setTitle(nickName);
        textArea.clear();
    }
    private void setTitle(String nickName){
        Platform.runLater(()->{
            if (nickName == null) {
                stage.setTitle("Open chat");
            } else {
                stage.setTitle(String.format("Open chat [%s]" ,nickName));
                try {
                    totalHistory = new totalHistory();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textArea.appendText(totalHistory.history_100());
            }
        });
    }

    public void clickClientList(MouseEvent mouseEvent) {
        String receiver =  clientList.getSelectionModel().getSelectedItem();
        textField.setText("/w " + receiver + "");
        //textField.requestFocus();
    }

    private void createRegWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/reg.fxml"));
            Parent root = fxmlLoader.load();
            regStage = new Stage();
            regStage.setTitle("Open chat registration");
            regStage.setScene(new Scene(root, 400, 320));

            regStage.initModality(Modality.APPLICATION_MODAL);
            regStage.initStyle(StageStyle.UTILITY);

            prR = fxmlLoader.getController();
            prR.setController(this);

        } catch(IOException i){
            i.printStackTrace();
        }
    }
    private void createChangeWindow(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/change.fxml"));
            Parent root = fxmlLoader.load();
            changeStage = new Stage();
            changeStage.setTitle("Open chat nick change window");
            changeStage.setScene(new Scene(root, 400, 320));

            changeStage.initModality(Modality.APPLICATION_MODAL);
            changeStage.initStyle(StageStyle.UTILITY);

            changecontroller = fxmlLoader.getController();
            changecontroller.setController(this);

        } catch(IOException i){
            i.printStackTrace();
        }
    }

    public void tryToReg(ActionEvent actionEvent) {
        if (regStage ==null){
            createRegWindow();
        }
        Platform.runLater(()->{
            regStage.show();
        });
    }
    public void registration(String login, String password, String nick){
        if (socket == null || socket.isClosed()) {connect();}
        String msg = String.format("/reg %s %s %s", nick, login, password);
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void change (String login, String password, String newNick){
        if (socket == null || socket.isClosed()) {connect();}
        String msg = String.format("/change %s %s %s", login, password, newNick);
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void tryToChange(ActionEvent actionEvent) {
        if (changeStage == null){
            createChangeWindow();
        }
        Platform.runLater(()->{
            changeStage.show();
        });
    }
}
