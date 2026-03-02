module org.example.happypaws {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.happypaws to javafx.fxml;
    exports org.example.happypaws;
}