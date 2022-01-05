package hu.petrik.filmdb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void onHozzaadasButtonClick(ActionEvent actionEvent) {
    }

    public void onModositasButtonClick(ActionEvent actionEvent) {
    }

    public void onTorlesButtonClick(ActionEvent actionEvent) {
    }
}