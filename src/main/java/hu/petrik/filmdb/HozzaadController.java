package hu.petrik.filmdb;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

public class HozzaadController extends Controller {
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

        try {
            FilmDB db = new FilmDB();
            int siker = db.filmekHozzaadasa(cim, kategoria, hossz, ertekeles);
            if (siker == 1) {
                alert("Film hozzáadása sikeres!");
            } else {
                alert("Film hozzáadása sikertelen!");
            }
        } catch (Exception e) {
            hibaKiir(e);
        }
    }
}
