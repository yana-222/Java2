package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Changecontroller {
    @FXML
    public TextField newNickField;
    @FXML
    private Controller controller;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    private TextArea textArea;


    public void tryToChangeNick(ActionEvent actionEvent) {
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();
        String newNick = newNickField.getText().trim();

        controller.change(login,password,newNick);
    }

    public void showResult (String resul){
        if(resul.equals("/change_ok")){
            textArea.appendText("New nickname has set.\n");
        } else {
            textArea.appendText("New nick you have entered is busy \nor \nyou have entered wrong pare login and password.\nTry again.\n");
        }
    }
    public void setController(Controller controller) {
        this.controller = controller;
    }

}
