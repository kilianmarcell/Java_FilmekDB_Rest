package hu.petrik.filmdb.controllers;

import hu.petrik.filmdb.Film;
import hu.petrik.filmdb.FilmApp;
import hu.petrik.filmdb.FilmDB;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MainController extends Controller {

    @FXML
    private TableView<Film> filmTable;
    @FXML
    private TableColumn<Film, String> cimOszlop;
    @FXML
    private TableColumn<Film, String> kategoriaOszlop;
    @FXML
    private TableColumn<Film, Integer> hosszOszlop;
    @FXML
    private TableColumn<Film, Integer> ertekelesOszlop;
    private FilmDB db;

    public void initialize() {
        //A tárolt objektumban egy getCim függvényt fog keresni
        cimOszlop.setCellValueFactory(new PropertyValueFactory<>("cim"));
        kategoriaOszlop.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        hosszOszlop.setCellValueFactory(new PropertyValueFactory<>("hossz"));
        ertekelesOszlop.setCellValueFactory(new PropertyValueFactory<>("ertekeles"));

        try {
            db = new FilmDB();
            List<Film> filmList = db.getFilmek();
            for (Film film: filmList) {
                filmTable.getItems().add(film);
            }
        } catch (SQLException e) {
            hibaKiir(e);
        }

        //filmTable.getItems().add(new Film(1, "Teszt", "akcio", 110, 5)); tesztadat
    }

    @FXML
    public void onHozzaadasButtonClick(ActionEvent actionEvent) {
        try {
            Controller modositas = ujAblak("hozzaad-view.fxml", "File hozzáadása", 320, 400);
            modositas.getStage().setOnCloseRequest(event -> filmListaFeltolt());
            modositas.getStage().setOnHiding(event -> filmTable.refresh());
            modositas.getStage().show();
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    private void filmListaFeltolt() {
        try {
            List<Film> filmList = db.getFilmek();
            filmTable.getItems().clear();
            for (Film film: filmList) {
                filmTable.getItems().add(film);
            }
        } catch (Exception e) {
            hibaKiir(e);
        }
    }

    @FXML
    public void onModositasButtonClick(ActionEvent actionEvent) {
        int selectedIndex = filmTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("A módosításhoz válasszon ki egy elemet a táblából!");
            return;
        }
        Film modositando = filmTable.getSelectionModel().getSelectedItem();
        try {
            ModositController modositas = (ModositController) ujAblak("modosit-view-fxml", "Film módosítása",
                    320, 400);
            modositas.setModositando(modositando);
            modositas.getStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onTorlesButtonClick(ActionEvent actionEvent) {
        int selectedIndex = filmTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex == -1) {
            alert("A törléshez válasszon ki egy elemet a táblából!");
            return;
        }
        Film torlendofilm = filmTable.getSelectionModel().getSelectedItem();
        if (!confirm("Biztosan törli az alábbi filmet?: " + torlendofilm.getCim())) {
            return;
        }
        try {
            db.filmtorlese(torlendofilm.getId());
            alert("Sikeres törlés!");
            filmListaFeltolt();
        } catch (SQLException e) {
            hibaKiir(e);
        }
    }
}