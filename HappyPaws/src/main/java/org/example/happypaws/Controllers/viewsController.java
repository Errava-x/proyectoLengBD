package org.example.happypaws.Controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.happypaws.DBConnection;
import org.example.happypaws.Models.Cliente;
import javax.swing.*;
import java.awt.*;
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
    @FXML private TextArea database;
    @FXML private Button cargarDatosBtn;
    @FXML private TextArea pruebaTxtArea;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    @FXML private TextField regName;
    @FXML private TextField regUser;
    @FXML private TextField regFLastName;
    @FXML private TextField regSLastName;
    @FXML private TextField regPassword;
    @FXML private TextField regConfirmPassword;
    @FXML private Button registerButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConnection db = new DBConnection();
        clienteModel = new Cliente(db);
    }

    public void cargarDatos() {
        database.appendText(cargarDatosBtn.getText());
        ResultSet infoClientes = clienteModel.getResumenTest();
        database.appendText(pruebaTxtArea.getText());
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

    public void login(){
        System.out.println("Contrasena y Usuario: " +
                "\nUsuario: "+ usernameTextField.getText()+"\nPassword: "+passwordTextField.getText());
        if(clienteModel.login(usernameTextField.getText(), passwordTextField.getText()) == true){
            try{
                Stage stage = (Stage) usernameTextField.getScene().getWindow();
                irMenu(stage);
            }catch (Exception e){
                System.out.println("Error al cargar vista de login: "+e.getMessage()+"\n\n");
            }
        }else{
            JOptionPane.showMessageDialog(null, "Error en la informacion de logueo");
        }
    }

    public void registrarUsuario(){
        String nombre = regName.getText();
        String usuario = regUser.getText();
        String primerApellido = regFLastName.getText();
        String segundoApellido = regSLastName.getText();
        String password = regPassword.getText();
        String checkPassword = regConfirmPassword.getText();
        String rol = "ADMIN";
        if (password.equals(checkPassword)){
            try{
                clienteModel.registrarUsuario(nombre, usuario, primerApellido, segundoApellido, password, rol);
                JOptionPane.showMessageDialog(null, "=== USUARIO NUEVO REGISTRADO ===\n" +
                        "Nombre: "+ nombre + " " + primerApellido + " " + segundoApellido +
                        "\nUsuario: "+ usuario +
                        "\nRol: "+ rol);
                Stage stage = (Stage) regUser.getScene().getWindow();
                irMenu(stage);
            }catch (Exception e){
                System.out.println("ERROR EN registrarUsuario controller: "+ e.getMessage());
                e.printStackTrace();
            }
        }else{
        JOptionPane.showMessageDialog(null, "Contrasenas son distintas, intente de nuevo");
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

    public void irMenu(ActionEvent event) throws IOException { ///NO REUSABLE
        Parent root = FXMLLoader.load(getClass().getResource(rootURL+"menuCrud.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }
    public void irMenu(Stage stage) throws IOException { ///REUSABLE
        Parent root = FXMLLoader.load(getClass().getResource(rootURL + "menuCrud.fxml"));
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
        try {
            Parent root = FXMLLoader.load(getClass().getResource(rootURL+"registrarCitas.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.centerOnScreen();
            stage.show();
        }catch (Exception e){
            System.out.println("Error en vista de registrar");
            e.printStackTrace();
        }
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
