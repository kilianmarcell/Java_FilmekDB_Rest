package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.Film;
import hu.petrik.filmdb.FilmDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class ModositController extends Controller {
    @FXML
    private TextField cimHozzaadTextField;
    @FXML
    private TextField kategoriaHozzaadTextField;
    @FXML
    private Spinner<Integer> hosszHozzaadSpinner;
    @FXML
    private ChoiceBox<Integer> ertekelesHozzadChoiceBox;
    private Film modositando;

    public Film getModositando() {
        return modositando;
    }

    public void setModositando(Film modositando) {
        this.modositando = modositando;
        ertekekBeallitasa();
    }

    private void ertekekBeallitasa() {
        cimHozzaadTextField.setText(modositando.getCim());
        kategoriaHozzaadTextField.setText(modositando.getKategoria());
        hosszHozzaadSpinner.getValueFactory().setValue(modositando.getHossz());
        ertekelesHozzadChoiceBox.setValue(modositando.getErtekeles());
    }

    @FXML
    public void onModositButtonClick(ActionEvent actionEvent) {
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

        modositando.setCim(cim);
        modositando.setKategoria(kategoria);
        modositando.setHossz(hossz);
        modositando.setErtekeles(ertekeles);

        try {
            FilmDB db = new FilmDB();
            if (db.filmModositasa(modositando)) {
                alertWait("Sikeres módosítás!");
                this.stage.close();
            } else {
                alert("Sikertelen módosítás!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
