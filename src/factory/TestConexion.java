package factory;

import java.sql.Connection;
import java.sql.SQLException;

public class TestConexion {
	public static void main(String[] args) throws SQLException {
		Conexion con = new Conexion();
		Connection conn = con.ConexionSQL();
		
		System.out.println("Conexion exitosa");
		conn.close();
		
		System.out.println("Cierre exitoso");
	}
}
