package org.example.happypaws.Models;
import org.example.happypaws.DBConnection;
import java.sql.*;

public class Cliente {
    private Connection conn;

    public Cliente(DBConnection db) {
        this.conn = db.connect();
    }

    public ResultSet getAll() {
        /*de aqui sacamos la informacion de los usuario con la vista que yo cree, por whatsapp la mando por si acaso,
        quiza no la puedas ejecutar, pero se lo pides a GPT para que te ensene como que podrias esperar ver*/
        try {
            String sql = "SELECT * FROM VW_RESUMEN_SISTEMA";
            Statement stm = conn.createStatement();
            return stm.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("Error en getAll: " + e.getMessage());
            return null;
        }
    }

    public boolean createCita(int p_id_cita, Date p_fecha, String p_hora) {
        /*Este SP realmente no existe con 3 valores, nada mas fue para pruebas, DEBE MODIFICARSE*/
        try {
            String sql = "SP_AGENDAR_CITA(?, ?, ?)";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, p_id_cita);
            stmt.setDate(2, p_fecha);
            stmt.setString(3, p_hora);

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println("Error en createCita: " + e.getMessage());
            return false;
        }
    }
}


