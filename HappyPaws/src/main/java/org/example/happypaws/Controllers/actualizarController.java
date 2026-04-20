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
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class actualizarController implements Initializable {
    private String rootURL = "/org/example/happypaws/";
    private Cliente clienteModel;
    private Cita citaModel;
    @FXML TextField id;
    @FXML TextField cliente;
    @FXML TextField mascota;
    @FXML TextField tipoDeConsulta;
    @FXML DatePicker fecha;
    @FXML TextField hora;
    @FXML TextField buscador;
    @FXML TextArea observaciones;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DBConnection db = new DBConnection();
        clienteModel = new Cliente(db);
        citaModel = new Cita(db);
    }
///  UPDATEA LA CITA EN CUESTION
public void actualizarCita() {
    java.sql.Date sqlFecha = java.sql.Date.valueOf(fecha.getValue());
    try {
        citaModel.actualizarCita(
                Integer.parseInt(id.getText()),
                cliente.getText(),
                mascota.getText(),
                tipoDeConsulta.getText(),
                sqlFecha,
                hora.getText(),
                observaciones.getText()
        );
        limpiarActualizar();
        JOptionPane.showMessageDialog(null, "La cita se ha actualizado exitosamente!");
    } catch (Exception e) {
        System.out.println("Error en ACTUALIZAR CITA de controller: " + e.getMessage());
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "La cita no se ha logrado actualizar!");
    }
}

    /// LIMPIAR LA INFORMACION
    public void limpiarActualizar(){
        id.clear();
        cliente.clear();
        mascota.clear();
        tipoDeConsulta.clear();
        fecha.setValue(null);
        hora.clear();
        observaciones.clear();
    }

    /// BUSCAR POR ID
    public void mostrarCita(){
        try {
            System.out.println("Buscador dice:" + buscador.getText());
            ResultSet citaEncontrada = citaModel.mostrarCitaPorCitaId(Integer.parseInt(buscador.getText()));
         if (citaEncontrada != null && citaEncontrada.next()){
             int idCita = citaEncontrada.getInt("ID_CITA");
             id.setText(String.valueOf((idCita)));
             cliente.setText(citaEncontrada.getString("NOMBRE_CLIENTE"));
             mascota.setText(citaEncontrada.getString("NOMBRE_MASCOTA"));
             tipoDeConsulta.setText(citaEncontrada.getString("TIPO_CONSULTA"));
             fecha.setValue(citaEncontrada.getDate("FECHA").toLocalDate());
             hora.setText(citaEncontrada.getString("HORA"));
             observaciones.setText(citaEncontrada.getString("OBSERVACIONES"));
         }else{
             System.out.println("Error en la busqueda");
             JOptionPane.showMessageDialog(null, "Parametro no valido");
             limpiarActualizar();
         }
        }catch (Exception e){
        System.out.println("Error en la busqueda catcheado mostrarCita actuController: "+ e.getMessage());
        e.printStackTrace();
        }


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
