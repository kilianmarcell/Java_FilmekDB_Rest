package hu.petrik.filmdb;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.PageLayout;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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
            Stage stage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(FilmApp.class.getResource("hozzaad-view.fxml"));
            Scene scene = null;
            scene = new Scene(fxmlLoader.load(), 320, 400);
            stage.setTitle("Hozzáadás");
            stage.setScene(scene);
            stage.setOnCloseRequest(event -> filmListaFeltolt());
            stage.show();
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