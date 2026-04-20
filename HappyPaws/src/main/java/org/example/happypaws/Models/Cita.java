package org.example.happypaws.Models;
import org.example.happypaws.DBConnection;
import oracle.jdbc.OracleTypes;
import java.sql.*;

public class Cita {
    private Connection conn;

    public Cita (DBConnection db) {
        this.conn = db.connect();
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
            String sql = "{ call SP_AGENDAR_CITA(?, ?, ?, ?, ?, ?, ?) }";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p_id_cita);
            stmt.setDate(2, p_fecha);
            stmt.setString(3, p_hora);
            stmt.setString(4, p_tipo_consulta);
            stmt.setString(5, p_observaciones);
            stmt.setInt(6, p_id_mascota);
            stmt.setInt(7, p_id_medico);

            stmt.execute();
            stmt.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error en createCita: " + e.getMessage());
            return false;
        }
    }
}