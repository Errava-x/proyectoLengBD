package org.example.happypaws.Models;
import org.example.happypaws.DBConnection;
import java.sql.*;

public class Cita {
    private Connection conn;

    public Cita (DBConnection db) {
        this.conn = db.connect();
    }

    public ResultSet getAll() {

        try {
            String sql = "SELECT * FROM VW_RESUMEN_SISTEMA";
            Statement stm = conn.createStatement();
            return stm.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("Error en getAll: " + e.getMessage());
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