module ec.edu.espol.concursomascotav2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.concursomascotav2 to javafx.fxml;
    exports ec.edu.espol.concursomascotav2;
    opens ec.edu.espol.controllers to javafx.fxml;
    exports ec.edu.espol.controllers;
    opens ec.edu.espol.model to javafx.fxml;
    exports ec.edu.espol.model;
}
