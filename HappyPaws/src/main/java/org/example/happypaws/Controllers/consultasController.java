package org.example.happypaws.Controllers;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.happypaws.Tablas.consultasCitas;
import org.example.happypaws.DBConnection;
import org.example.happypaws.Models.Cita;
import org.example.happypaws.Models.Cliente;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class consultasController implements Initializable{
    private String rootURL = "/org/example/happypaws/";
    private Cliente clienteModel;
    private Cita citaModel;
    @FXML private TextField buscador;
    @FXML private TableView<consultasCitas> tabla;
    @FXML private TableColumn<consultasCitas, Integer> colClienteID;
    @FXML private TableColumn<consultasCitas, String> colClienteNombre;
    @FXML private TableColumn<consultasCitas, Integer> colMascotaID;
    @FXML private TableColumn<consultasCitas, String> colMascotaNombre;
    @FXML private TableColumn<consultasCitas, String> colEspecie;
    @FXML private TableColumn<consultasCitas, String> colRaza;
    @FXML private TableColumn<consultasCitas, Integer> colCitaID;
    @FXML private TableColumn<consultasCitas, Date> colFecha;
    @FXML private TableColumn<consultasCitas, String> colHora;
    @FXML private TableColumn<consultasCitas, String> colConsulta;
    @FXML private TableColumn<consultasCitas, String> colEstado;
    @FXML private TableColumn<consultasCitas, String> colObservaciones;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConnection db = new DBConnection();
        clienteModel = new Cliente(db);
        citaModel = new Cita(db);
        cargarTabla();
        colClienteID.setCellValueFactory(new PropertyValueFactory<>("idCliente"));
        colClienteNombre.setCellValueFactory(new PropertyValueFactory<>("nombreCliente"));
        colMascotaID.setCellValueFactory(new PropertyValueFactory<>("idMascota"));
        colMascotaNombre.setCellValueFactory(new PropertyValueFactory<>("nombreMascota"));
        colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
        colRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
        colCitaID.setCellValueFactory(new PropertyValueFactory<>("idCita"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
        colConsulta.setCellValueFactory(new PropertyValueFactory<>("tipoConsulta"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colObservaciones.setCellValueFactory(new PropertyValueFactory<>("observaciones"));
    }

    public void cargarTabla(){
        ObservableList<consultasCitas> listaCitasJV = FXCollections.observableArrayList();
        ResultSet listaCitasDB = citaModel.cargarTabla();
        try {
            while (listaCitasDB.next()){
                consultasCitas cita = new consultasCitas(
                        listaCitasDB.getInt("ID_CLIENTE"),
                        listaCitasDB.getString("NOMBRE_CLIENTE"),
                        listaCitasDB.getInt("ID_MASCOTA"),
                        listaCitasDB.getString("NOMBRE_MASCOTA"),
                        listaCitasDB.getString("ESPECIE"),
                        listaCitasDB.getString("RAZA"),
                        listaCitasDB.getInt("ID_CITA"),
                        listaCitasDB.getDate("FECHA"),
                        listaCitasDB.getString("HORA"),
                        listaCitasDB.getString("TIPO_CONSULTA"),
                        listaCitasDB.getString("ESTADO"),
                        listaCitasDB.getString("OBSERVACIONES")
                );
                listaCitasJV.add(cita);
            }
            tabla.setItems(listaCitasJV);
            listaCitasDB.close();
        }catch (Exception e){
            System.out.println("Error al cargar la tabla en consultasController initialize" + e.getMessage());
        }

    }
    public boolean esNumerico(String aVerificar){
        try {
            Integer.parseInt(aVerificar);
            System.out.println("El valor verificado es: "+ aVerificar);
            return true;
        }catch (Exception e){
            System.out.println("Error verificando: "+ aVerificar);
            return false;
        }
    }
    public void cargarTablaPorId(){
        ObservableList<consultasCitas> listaCitasJV = FXCollections.observableArrayList();
        if (esNumerico(buscador.getText())){

        ResultSet listaCitasDB = citaModel.cargarTablaPorId(Integer.parseInt(buscador.getText()));

        try {
            while (listaCitasDB.next()){
                consultasCitas cita = new consultasCitas(
                        listaCitasDB.getInt("ID_CLIENTE"),
                        listaCitasDB.getString("NOMBRE_CLIENTE"),
                        listaCitasDB.getInt("ID_MASCOTA"),
                        listaCitasDB.getString("NOMBRE_MASCOTA"),
                        listaCitasDB.getString("ESPECIE"),
                        listaCitasDB.getString("RAZA"),
                        listaCitasDB.getInt("ID_CITA"),
                        listaCitasDB.getDate("FECHA"),
                        listaCitasDB.getString("HORA"),
                        listaCitasDB.getString("TIPO_CONSULTA"),
                        listaCitasDB.getString("ESTADO"),
                        listaCitasDB.getString("OBSERVACIONES")
                );
                listaCitasJV.add(cita);
            }
            tabla.setItems(listaCitasJV);
        }catch (Exception e){
            System.out.println("Error al cargar la tabla por ID en consultasController" + e.getMessage());
        }
        }else{
            JOptionPane.showMessageDialog(null, "Para buscar por ID se ocupa valor numerico existente");
        }
    }

    public void cargarTablaPorCliente(){
        ObservableList<consultasCitas> listaCitasJV = FXCollections.observableArrayList();
        if (buscador.getText().length() > 0){
            ResultSet listaCitasDB = citaModel.cargarTablaPorCliente(buscador.getText());

            try {
                while (listaCitasDB.next()){
                    consultasCitas cita = new consultasCitas(
                            listaCitasDB.getInt("ID_CLIENTE"),
                            listaCitasDB.getString("NOMBRE_CLIENTE"),
                            listaCitasDB.getInt("ID_MASCOTA"),
                            listaCitasDB.getString("NOMBRE_MASCOTA"),
                            listaCitasDB.getString("ESPECIE"),
                            listaCitasDB.getString("RAZA"),
                            listaCitasDB.getInt("ID_CITA"),
                            listaCitasDB.getDate("FECHA"),
                            listaCitasDB.getString("HORA"),
                            listaCitasDB.getString("TIPO_CONSULTA"),
                            listaCitasDB.getString("ESTADO"),
                            listaCitasDB.getString("OBSERVACIONES")
                    );
                    listaCitasJV.add(cita);
                }
                tabla.setItems(listaCitasJV);
            }catch (Exception e){
                System.out.println("Error al cargar la tabla por CLIENTE en consultasController" + e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Para buscar por cliente se ocupa nombre existente");
        }
    }

    public void cargarTablaPorMascota(){
        ObservableList<consultasCitas> listaCitasJV = FXCollections.observableArrayList();
        if (buscador.getText().length() > 0){
            ResultSet listaCitasDB = citaModel.cargarTablaPorMascota(buscador.getText());

            try {
                while (listaCitasDB.next()){
                    consultasCitas cita = new consultasCitas(
                            listaCitasDB.getInt("ID_CLIENTE"),
                            listaCitasDB.getString("NOMBRE_CLIENTE"),
                            listaCitasDB.getInt("ID_MASCOTA"),
                            listaCitasDB.getString("NOMBRE_MASCOTA"),
                            listaCitasDB.getString("ESPECIE"),
                            listaCitasDB.getString("RAZA"),
                            listaCitasDB.getInt("ID_CITA"),
                            listaCitasDB.getDate("FECHA"),
                            listaCitasDB.getString("HORA"),
                            listaCitasDB.getString("TIPO_CONSULTA"),
                            listaCitasDB.getString("ESTADO"),
                            listaCitasDB.getString("OBSERVACIONES")
                    );
                    listaCitasJV.add(cita);
                }
                tabla.setItems(listaCitasJV);
            }catch (Exception e){
                System.out.println("Error al cargar la tabla por MASCOTA en consultasController" + e.getMessage());
            }
        }else{
            JOptionPane.showMessageDialog(null, "Para buscar por mascota se ocupa mascota existente");
        }
    }

    /// VUELVE AL CRUD
    public void volver(ActionEvent event) throws IOException { ///NO REUSABLE
        Parent root = FXMLLoader.load(getClass().getResource(rootURL+"menuCrud.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.centerOnScreen();
        stage.show();
    }
}
