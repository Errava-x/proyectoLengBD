package org.example.happypaws.Models;

import org.example.happypaws.DBConnection;

import java.sql.*;

public class Cita {
    private Connection conn;

    public Cita(DBConnection db) {
        this.conn = db.connect();
    }

    public boolean eliminarCita(int idCita){
        try{
            CallableStatement cs = conn.prepareCall("{CALL PKG_HP_OPERACIONES.SP_ELIMINAR_CITA(?)}");
            cs.setInt(1, idCita);
            cs.execute();
            conn.commit();
            return true;
        }catch (SQLException e){
            System.out.println("Error en eliminarCita modelo CITA");
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarCita(int idCita, String nombreCliente, String nombreMascota,
                                  String tipoConsulta, Date fecha, String hora, String observaciones) {
        try {
            CallableStatement cs = conn.prepareCall(
                    "{CALL PKG_HP_OPERACIONES.SP_ACTUALIZAR_CITA(?, ?, ?, ?, ?, ?, ?)}"
            );

            cs.setInt(1, idCita);
            cs.setString(2, nombreCliente);
            cs.setString(3, nombreMascota);
            cs.setString(4, tipoConsulta);
            cs.setDate(5, fecha);
            cs.setString(6, hora);
            cs.setString(7, observaciones);

            cs.execute();
            conn.commit();
            return true;

        } catch (Exception e) {
            System.out.println("Error en actualizarCita: " + e.getMessage());
            return false;
        }
    }

    public ResultSet cargarTabla() {

        try {
            String sql = "SELECT * FROM VW_VER_CITAS";
            Statement stm = conn.createStatement();
            return stm.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("Error en VW_VER_CITAS: " + e.getMessage());
            return null;
        }
    }

    public ResultSet cargarTablaPorId(int id) {
        try {
            String sql = "{CALL PKG_HP_USUARIOS.SP_VER_CITAS_X_ID(?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            //IN
            stmt.setInt(1, id);
            //OUT
            stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();
            return (ResultSet) stmt.getObject(2);
        } catch (Exception e) {
            System.out.println("Error en cargar TablaPorId desde modelo CITA: " + e.getMessage());
            return null;
        }
    }

    public ResultSet cargarTablaPorCliente(String cliente) {
        try {
            String sql = "{CALL PKG_HP_USUARIOS.SP_VER_CITAS_X_NOMBRE(?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            //IN
            stmt.setString(1, cliente);
            //OUT
            stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();
            return (ResultSet) stmt.getObject(2);
        } catch (Exception e) {
            System.out.println("Error en cargar TablaPorCLIENTE desde modelo CITA: " + e.getMessage());
            return null;
        }
    }

    public ResultSet cargarTablaPorMascota(String mascota) {
        try {
            String sql = "{CALL PKG_HP_USUARIOS.SP_VER_CITAS_X_MASCOTA(?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            //IN
            stmt.setString(1, mascota);
            //OUT
            stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();
            return (ResultSet) stmt.getObject(2);
        } catch (Exception e) {
            System.out.println("Error en cargar TablaPorMASCOTA desde modelo CITA: " + e.getMessage());
            return null;
        }
    }

    public boolean createCita(int p_id_cita, Date p_fecha, String p_hora,
                              String p_tipo_consulta, String p_observaciones,
                              int p_id_mascota, int p_id_medico) {

        try {
            String sql = "{ call PKG_HP_OPERACIONES.SP_AGENDAR_CITA(?, ?, ?, ?, ?, ?, ?) }";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p_id_cita);
            stmt.setDate(2, p_fecha);
            stmt.setString(3, p_hora);
            stmt.setString(4, p_tipo_consulta);
            stmt.setString(5, p_observaciones);
            stmt.setInt(6, p_id_mascota);
            stmt.setInt(7, p_id_medico);
            stmt.execute();
            conn.commit();
            stmt.close();
            if (stmt.executeUpdate() > 0) {
                conn.commit();
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error en createCita cita modelo: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public ResultSet citaMayor() {
        try {
            String sql = "SELECT MAX(ID_CITA) AS citaMayor FROM VW_VER_CITAS";
            Statement stm = conn.createStatement();
            return stm.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println("Error calculando CITAMAYOR en cita modelo " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet mostrarCitaPorCitaId(int id) {
        try {
            String sql = "{CALL PKG_HP_USUARIOS.SP_VER_CITAS_X_ID_CITA(?, ?)}";
            CallableStatement stmt = conn.prepareCall(sql);
            //IN
            stmt.setInt(1, id);
            //OUT
            stmt.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
            stmt.execute();
            return (ResultSet) stmt.getObject(2);
        } catch (Exception e) {
            System.out.println("Error en cargar TablaPorId desde modelo CITA: " + e.getMessage());
            return null;
        }
    }
}