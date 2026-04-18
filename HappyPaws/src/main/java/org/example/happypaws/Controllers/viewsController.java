package org.example.happypaws.Controllers;

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
import org.example.happypaws.DBConnection;
import org.example.happypaws.Models.Cliente;

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
    private String rootURL = "/org/example/happypaws/";
    private Cliente clienteModel;
    @FXML
    private TextArea database;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConnection db = new DBConnection();
        clienteModel = new Cliente(db);
    }

    public void cargarDatos() {
        ResultSet infoClientes = clienteModel.getAll();
            try {
                System.out.println("Tamanno de rs es: " + infoClientes.getFetchSize());
                while (infoClientes.next()) {
                    database.appendText("=== CLIENTE ===\n");
                    database.appendText("ID Cliente: " + infoClientes.getInt(1) + "\n");
                    database.appendText("Nombre: " + infoClientes.getString(2) + "\n\n");

                    database.appendText("=== MASCOTA ===\n");
                    database.appendText("ID Mascota: " + infoClientes.getInt(3) + "\n");
                    database.appendText("Nombre: " + infoClientes.getString(4) + "\n");
                    database.appendText("Especie: " + infoClientes.getString(5) + "\n");
                    database.appendText("Raza: " + infoClientes.getString(6) + "\n\n");

                    database.appendText("=== MÉDICO ===\n");
                    database.appendText("ID Médico: " + infoClientes.getInt(7) + "\n");
                    database.appendText("Nombre: " + infoClientes.getString(8) + "\n\n");

                    database.appendText("=== VENTA ===\n");
                    database.appendText("ID Venta: " + infoClientes.getInt(9) + "\n");
                    database.appendText("Producto: " + infoClientes.getString(10) + "\n");
                    database.appendText("Monto: " + infoClientes.getDouble(11) + "\n\n");

                    database.appendText("=== CITA ===\n");
                    database.appendText("ID Cita: " + infoClientes.getInt(12) + "\n");
                    database.appendText("Fecha: " + infoClientes.getDate(13) + "\n");
                    database.appendText("Tipo Consulta: " + infoClientes.getString(14) + "\n");
                    database.appendText("Estado: " + infoClientes.getString(15) + "\n");

                    database.appendText("\n---------------------------------\n\n");
                    database.setText(database.getText() + "\n");
                }
            }catch (Exception e){
                System.out.println("Error en la funcion cargar datos viewsController" + e.getMessage());
            }
    }

    /*NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS NAVEGACIÓN DE VISTAS */
    public void irLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(rootURL+"login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irRegistroUsuario(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(rootURL+"registroUsuario.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irMenu(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(rootURL+"menuCrud.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irConsultas(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(rootURL+"consultarCitas.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irEliminar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(rootURL+"eliminarCitas.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irActualizar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(rootURL+"actualizarCitas.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irRegistrar(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(rootURL+"registrarCitas.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irDB(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(rootURL+"dbConnectionProof.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }

    public void irAdminUsuarios(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(rootURL+"adminUsuarios.fxml"));
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
