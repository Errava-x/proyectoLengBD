package org.example.happypaws.Models;
import org.example.happypaws.DBConnection;
import java.sql.*;

public class Mascota {
    private Connection conn;

    public Mascota(DBConnection db) {
        this.conn = db.connect();
    }

    public ResultSet getAll() {
        try {
            String sql = "SELECT * FROM MASCOTAS";
            Statement stm = conn.createStatement();
            return stm.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("Error en getAll: " + e.getMessage());
            return null;
        }
    }

    public boolean registrarMascota(int p_id_mascota,
                                    String p_nombre,
                                    String p_especie,
                                    String p_raza,
                                    Date p_fecha_nacimiento,
                                    String p_sexo,
                                    String p_color,
                                    double p_peso,
                                    int p_id_cliente) {

        try {
            String sql = "{ call SP_REGISTRAR_MASCOTA(?, ?, ?, ?, ?, ?, ?, ?, ?) }";

            CallableStatement stmt = conn.prepareCall(sql);
            stmt.setInt(1, p_id_mascota);
            stmt.setString(2, p_nombre);
            stmt.setString(3, p_especie);
            stmt.setString(4, p_raza);
            stmt.setDate(5, p_fecha_nacimiento);
            stmt.setString(6, p_sexo);
            stmt.setString(7, p_color);
            stmt.setDouble(8, p_peso);
            stmt.setInt(9, p_id_cliente);

            stmt.execute();
            stmt.close();

            return true;

        } catch (Exception e) {
            System.out.println("Error en registrarMascota: " + e.getMessage());
            return false;
        }
    }
}