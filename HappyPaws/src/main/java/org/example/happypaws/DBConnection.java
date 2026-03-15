package org.example.happypaws;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.sql.*;
import java.util.Scanner;


public class DBConnection implements Initializable {
    @FXML
    private TextArea database;
    /*LA FUNCION FUNCIONA REGULAR, PERO ES PRINCIPALMENTE PARA PROBAR EL FUNCIONAMIENTO GENERAL*/
    public String setQueryToUse(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el numero del query respectivo:");
        int queryToUse = sc.nextInt();
        switch (queryToUse){
            case 1:
                return "SELECT * FROM VW_RESUMEN_SISTEMA";
        }
        return "SELECT * FROM CLIENTES";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            Properties props = new Properties();
            props.setProperty("user", "SYS");
            props.setProperty("password", "Oracle2026");
            props.setProperty("internal_logon", "SYSDBA");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", props);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(setQueryToUse());

            System.out.println("Tamanno de rs es: "+rs.getFetchSize());

            while (rs.next()){
                database.appendText("=== CLIENTE ===\n");
                database.appendText("ID Cliente: " + rs.getInt(1) + "\n");
                database.appendText("Nombre: " + rs.getString(1) + "\n\n");
                database.appendText("Apellido: " + rs.getString(2) + "\n\n");

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
                System.out.println("base de datos accedida dbconnection");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}