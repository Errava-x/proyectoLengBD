module org.example.happypaws {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;

    opens org.example.happypaws to javafx.fxml;
    exports org.example.happypaws;
    exports org.example.happypaws.Controllers;
    opens org.example.happypaws.Controllers to javafx.fxml;
}