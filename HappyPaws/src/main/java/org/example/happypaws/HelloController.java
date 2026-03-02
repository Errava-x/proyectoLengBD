package org.example.happypaws;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;
import java.sql.*;


public class HelloController implements Initializable {

    @FXML
    private TextArea database;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "SYS as SYSDBA", "Oracle2026");
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery("SELECT * FROM VW_RESUMEN_SISTEMA");
            while (rs.next()){
                database.appendText("=== CLIENTE ===\n");
                database.appendText("ID Cliente: " + rs.getInt(1) + "\n");
                database.appendText("Nombre: " + rs.getString(2) + "\n\n");

                database.appendText("=== MASCOTA ===\n");
                database.appendText("ID Mascota: " + rs.getInt(3) + "\n");
                database.appendText("Nombre: " + rs.getString(4) + "\n");
                database.appendText("Especie: " + rs.getString(5) + "\n");
                database.appendText("Raza: " + rs.getString(6) + "\n\n");

                database.appendText("=== MÉDICO ===\n");
                database.appendText("ID Médico: " + rs.getInt(7) + "\n");
                database.appendText("Nombre: " + rs.getString(8) + "\n\n");

                database.appendText("=== VENTA ===\n");
                database.appendText("ID Venta: " + rs.getInt(9) + "\n");
                database.appendText("Producto: " + rs.getString(10) + "\n");
                database.appendText("Monto: " + rs.getDouble(11) + "\n\n");

                database.appendText("=== CITA ===\n");
                database.appendText("ID Cita: " + rs.getInt(12) + "\n");
                database.appendText("Fecha: " + rs.getDate(13) + "\n");
                database.appendText("Tipo Consulta: " + rs.getString(14) + "\n");
                database.appendText("Estado: " + rs.getString(15) + "\n");

                database.appendText("\n---------------------------------\n\n");
                database.setText(database.getText() + "\n");
                System.out.println("base de datos accedida");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
