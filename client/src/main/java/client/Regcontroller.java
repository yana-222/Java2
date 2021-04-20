package client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Regcontroller {
    @FXML
    private Controller controller;
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public TextField nickField;
    @FXML
    private TextArea textArea;

    public void tryToReg(ActionEvent actionEvent) {
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();
        String nick = nickField.getText().trim();

        controller.registration(login,password,nick);
    }

    public void showResult (String resul){
        if(resul.equals("/reg_ok")){
            textArea.appendText("Registration complete.\n");
        } else {
            textArea.appendText("Registration failed. Try another login or nickname.\n");
        }

    }

    public void setController(Controller controller) {
        this.controller = controller;
    }
}
