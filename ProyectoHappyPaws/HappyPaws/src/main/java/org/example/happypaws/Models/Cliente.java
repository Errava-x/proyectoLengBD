package org.example.happypaws.Models;
import org.example.happypaws.DBConnection;
import java.sql.*;

public class Cliente {
    private Connection conn;

    public Cliente(DBConnection db) {
        this.conn = db.connect();
    }

    public ResultSet getAll() {

        try {
            String sql = "SELECT * FROM USUARIO";
            Statement stm = conn.createStatement();
            return stm.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("Error en getAll: " + e.getMessage());
            return null;
        }
    }

    public boolean createCita(String p_nombre,
                              String p_primer_apellido,
                              String p_segundo_apellido,
                              String p_username,
                              String p_pass,
                              String p_rol) {

        try {
            String sql = "{ call SP_REGISTRAR_USUARIO(?, ?, ?, ?, ?, ?) }";

            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setString(1, p_nombre);
            stmt.setString(2, p_primer_apellido);
            stmt.setString(3, p_segundo_apellido);
            stmt.setString(4, p_username);
            stmt.setString(5, p_pass);
            stmt.setString(6, p_rol);

            stmt.execute();
            stmt.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error en registrarUsuario: " + e.getMessage());
            return false;
        }
    }
}


