module hu.petrik.filmdb {
    requires javafx.controls;
    requires javafx.fxml;


    opens hu.petrik.filmdb to javafx.fxml;
    exports hu.petrik.filmdb;
}