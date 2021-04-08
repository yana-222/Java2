package Lesson4HW;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Controller {
    @FXML
    public Menu menuFile;
    @FXML
    public Button btn;
    @FXML
    public TextField textField;
    @FXML
    public TextArea textArea;

    LocalDateTime date = LocalDateTime.now();
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public void clickClose(ActionEvent actionEvent) {
        Platform.runLater(()->{
            Stage stage = (Stage) btn.getScene().getWindow();
            stage.close();
        });
    }
    @FXML
    public void clickSend(ActionEvent actionEvent) throws Exception{
        textArea.appendText(formatter.format(date) + " " + textField.getText()+"\n");
        textField.clear();
        textField.requestFocus();
    }

}
