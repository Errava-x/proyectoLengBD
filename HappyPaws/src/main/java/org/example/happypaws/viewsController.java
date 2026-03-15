package org.example.happypaws;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Scanner;


public class viewsController implements Initializable {


    @FXML
    private TextArea database;

    /*LA FUNCION FUNCIONA REGULAR, PERO ES PRINCIPALMENTE PARA PROBAR EL FUNCIONAMIENTO GENERAL*/
    public String setQueryToUse() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduzca el numero del query respectivo:");
        int queryToUse = 1;
        /*int queryToUse = sc.nextInt();*/
        switch (queryToUse) {
            case 1:
                return "SELECT * FROM VW_RESUMEN_SISTEMA";
        }
        return "SELECT * FROM CLIENTES";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /*try{
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
        }*/
    }

    public void cargarDatos() {
        try {
            Properties props = new Properties();
            props.setProperty("user", "SYS");
            props.setProperty("password", "Oracle2026");
            props.setProperty("internal_logon", "SYSDBA");
            Connection conn = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1521:orcl", props);
            Statement stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(setQueryToUse());

            System.out.println("Tamanno de rs es: " + rs.getFetchSize());

            while (rs.next()) {
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
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS */
    public void irLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irRegistroUsuario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registroUsuario.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("menuCrud.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irConsultas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("consultarCitas.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irEliminar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("eliminarCitas.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irActualizar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("actualizarCitas.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irRegistrar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("registrarCitas.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irDB(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("dbConnectionProof.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irAdminUsuarios(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("adminUsuarios.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void cerrarPrograma(){
        Platform.exit();
    }
    /*NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS */

}
