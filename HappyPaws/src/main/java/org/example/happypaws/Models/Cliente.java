package org.example.happypaws.Models;
import org.example.happypaws.DBConnection;
import java.sql.*;

public class Cliente {
    private Connection conn;

    public Cliente(DBConnection db) {
        this.conn = db.connect();
    }

    public ResultSet getResumenTest() {
        try {
            String sql = "SELECT * FROM VW_RESUMEN_SISTEMA";
            Statement stm = conn.createStatement();
            return stm.executeQuery(sql);
        } catch (Exception e) {
            System.out.println("Error en getAll: " + e.getMessage());
            return null;
        }
    }

    public boolean login(String username, String password) {
        try {
            String sql = "{CALL PKG_HP_USUARIOS.SP_LOGIN_USUARIO(?, ?, ?, ?)}";

            CallableStatement stmt = conn.prepareCall(sql);
            //IN
            stmt.setString(1, username);
            stmt.setString(2, password);
            //OUT
            stmt.registerOutParameter(3, java.sql.Types.INTEGER);
            stmt.registerOutParameter(4, java.sql.Types.VARCHAR);
            stmt.execute();
            //OUT VALUES
            int ok = stmt.getInt(3);
            String rol = stmt.getString(4);
            //LOG
            System.out.println("Resultado de login: " + ok);
            System.out.println("Rol: " + rol);

            return ok == 1;
        } catch (Exception e) {
            System.out.println("Error en SP_LOGIN_USUARIO: " + e.getMessage());
            return false;
        }
    }

    public boolean registrarUsuario(String p_nombre, String p_primer_apellido, String p_segundo_apellido,
                                    String p_username, String p_pass, String p_rol) {
        try {
            String sql = "{CALL PKG_HP_USUARIOS.SP_REGISTRAR_USUARIO(?, ?, ?, ?, ?, ?) }";

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


