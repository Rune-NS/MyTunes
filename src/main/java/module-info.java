module org.example.mytunes {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.mytunes to javafx.fxml;
    exports org.example.mytunes;
}