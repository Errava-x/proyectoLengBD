module org.example.happypaws {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;
    requires java.desktop;

    opens org.example.happypaws to javafx.fxml;
    opens org.example.happypaws.Tablas to javafx.base;

    exports org.example.happypaws;
    exports org.example.happypaws.Controllers;
    opens org.example.happypaws.Controllers to javafx.fxml;
}