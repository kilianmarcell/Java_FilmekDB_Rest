package hu.petrik.filmdb;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;
import java.util.List;

public class MainController {

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

    public void initialize() {
        //A tárolt objektumban egy getCim függvényt fog keresni
        cimOszlop.setCellValueFactory(new PropertyValueFactory<>("cim"));
        kategoriaOszlop.setCellValueFactory(new PropertyValueFactory<>("kategoria"));
        hosszOszlop.setCellValueFactory(new PropertyValueFactory<>("hossz"));
        ertekelesOszlop.setCellValueFactory(new PropertyValueFactory<>("ertekeles"));

        try {
            FilmDB filmDB = new FilmDB();
            List<Film> filmList = filmDB.getFilmek();
            for (Film film: filmList) {
                filmTable.getItems().add(film);
            }
        } catch (SQLException e) {
            System.out.println("Hiba");
        }

        //filmTable.getItems().add(new Film(1, "Teszt", "akcio", 110, 5)); tesztadat
    }

    @FXML
    public void onHozzaadasButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onModositasButtonClick(ActionEvent actionEvent) {
    }

    @FXML
    public void onTorlesButtonClick(ActionEvent actionEvent) {
    }
}