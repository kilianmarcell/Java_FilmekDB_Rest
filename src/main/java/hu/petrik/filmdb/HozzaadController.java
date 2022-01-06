package hu.petrik.filmdb;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class HozzaadController {
    @javafx.fxml.FXML
    private TextField cimHozzaadTextField;
    @javafx.fxml.FXML
    private TextField kategoriaHozzaadTextField;
    @javafx.fxml.FXML
    private Spinner<Integer> hosszHozzaadSpinner;
    @javafx.fxml.FXML
    private ChoiceBox<Integer> ertekelesHozzadChoiceBox;

    @javafx.fxml.FXML
    public void gombHozzaadButton(ActionEvent actionEvent) {
        String cim = cimHozzaadTextField.getText().trim();
        String kategoria = kategoriaHozzaadTextField.getText().trim();
        int hossz = hosszHozzaadSpinner.getValue();
        //int ertekelesIndex = ertekelesHozzadChoiceBox.getSelectionModel().getSelectedIndex();
        int ertekeles = ertekelesHozzadChoiceBox.getValue();
        if (cim.isEmpty()) {
            alert("Cím megadása kötelező!");
            return;
        }
        if (kategoria.isEmpty()) {
            alert("Kategória megadása kötelező!");
            return;
        }
        try {
            hossz = hosszHozzaadSpinner.getValue();
        } catch (Exception e) {
            System.out.println(e);
            alert("A hossz csak 1 és 999 közötti szám lehet.");
            return;
        }
    }

    private void alert(String s) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setContentText(s);
        alert.getButtonTypes().add(ButtonType.OK);
        alert.show();
    }
}
