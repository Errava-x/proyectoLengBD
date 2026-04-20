package org.example.happypaws.Controllers;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.happypaws.DBConnection;
import org.example.happypaws.Models.Cita;
import org.example.happypaws.Models.Cliente;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.Date;
import java.util.ResourceBundle;

public class registrosController implements Initializable{
    private String rootURL = "/org/example/happypaws/";
    private Cliente clienteModel;
    private Cita citaModel;
    @FXML TextField mascotaID;
    @FXML TextField medicoID;
    @FXML TextField tipoDeConsulta;
    @FXML DatePicker fecha;
    @FXML TextField hora;
    @FXML TextArea observaciones;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConnection db = new DBConnection();
        clienteModel = new Cliente(db);
        citaModel = new Cita(db);
    }
/// REALIZA EL REGISTRO DE LA CITA
    public void registrarCita(){
        java.sql.Date sqlFecha = java.sql.Date.valueOf(fecha.getValue());
        System.out.println("EL VALOR DE FECHA ES: "+fecha.getValue());
        System.out.println("EL TIPO DE FECHA ES: "+((Object)fecha).getClass().getSimpleName());
        try{citaModel.createCita(citaMayor(), sqlFecha , hora.getText(), tipoDeConsulta.getText(), observaciones.getText(), Integer.parseInt(mascotaID.getText()),
                Integer.parseInt(medicoID.getText()));
                limpiarRegistro();
                JOptionPane.showMessageDialog(null, "La cita se ha registrado exitosamente!");
        }catch (Exception e){
            System.out.println("Error en REGISTRAR CITA de controller registros: "+ e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "La cita no se ha logrado registrar!");
        }
    }

/// Obtiene la cita de mayor numero
    public int citaMayor(){
        int citaMayor = 0;
        ResultSet citaMayorRs = citaModel.citaMayor();
        try{
        if(citaMayorRs.next()){
            citaMayor = citaMayorRs.getInt("citaMayor");
            return citaMayor + 1;
        }
        }catch (Exception e){
            System.out.println("Error en cita mayor registrosController: "+ e.getMessage());
            e.printStackTrace();
        }
        return 0;
    }

/// LIMPIA LOS CAMPOS
    public void limpiarRegistro(){
    mascotaID.clear();
    medicoID.clear();
    tipoDeConsulta.clear();
    fecha.setValue(null);
    hora.clear();
    observaciones.clear();
    }

///VUELVE AL CRUD MENU
public void volver(ActionEvent event) throws IOException { ///NO REUSABLE
    Parent root = FXMLLoader.load(getClass().getResource(rootURL+"menuCrud.fxml"));
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(new Scene(root));
    stage.centerOnScreen();
    stage.show();
}



}
