module org.example.mytunes {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens org.example.mytunes to javafx.fxml;
    exports org.example.mytunes;
}