package org.example.happypaws.Tablas;

import java.util.Date;

public class consultasCitas {
    private int idCliente;
    private String nombreCliente;

    private int idMascota;
    private String nombreMascota;
    private String especie;
    private String raza;

    private int idCita;
    private Date fecha;
    private String hora;
    private String tipoConsulta;
    private String estado;
    private String observaciones;

    public consultasCitas(int idCliente, String nombreCliente, int idMascota, String nombreMascota, String especie, String raza, int idCita, Date fecha, String hora, String tipoConsulta, String estado, String observaciones) {
        this.idCliente = idCliente;
        this.nombreCliente = nombreCliente;
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.especie = especie;
        this.raza = raza;
        this.idCita = idCita;
        this.fecha = fecha;
        this.hora = hora;
        this.tipoConsulta = tipoConsulta;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public int getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(int idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(String tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
