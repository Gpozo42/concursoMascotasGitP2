module ec.edu.espol.concursomascotav2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens ec.edu.espol.concursomascotav2 to javafx.fxml;
    exports ec.edu.espol.concursomascotav2;
}
