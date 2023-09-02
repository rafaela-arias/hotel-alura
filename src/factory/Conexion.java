package factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;


public class Conexion {
	public DataSource dataSource;
	
	public Conexion() {
		ComboPooledDataSource comboPool = new ComboPooledDataSource();
		comboPool.setJdbcUrl("jdbc:mysql://localhost:3306/hotel_alura_rafaelaarias");
		comboPool.setUser("root");
		comboPool.setPassword("123");
		
		this.dataSource = comboPool;
	}
	
	public Connection ConexionSQL() {
		try {
			return this.dataSource.getConnection();
		} catch (SQLException e) {
			System.out.println("Se produjo un error inesperado");
			throw new RuntimeException(e);
		}
	}
}
