package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Reservas;

public class ReservaDAO {
	private Connection conn;
	
	public ReservaDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void guardar(Reservas reserva) {
		try {
			String sql = "INSERT INTO reservas (fecha_entrada, fecha_salida, valor, forma_de_pago)"
					+ "VALUES (?, ?, ?, ?)";
			
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)){
				preparedStatement.setObject(1, reserva.getDataEntrada());
				preparedStatement.setObject(2, reserva.getDataSalida());
				preparedStatement.setString(3, reserva.getValor());
				preparedStatement.setString(4, reserva.getFormaPago());
				preparedStatement.executeUpdate();
				
				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
					while (resultSet.next()) {
						reserva.setId(resultSet.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Reservas> mostrar(){
		List<Reservas> reservas = new ArrayList<>();
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)){
					preparedStatement.execute();
					
					transformarResultado(reservas, preparedStatement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservas;
	}
	
	public void transformarResultado(List<Reservas> reservas, PreparedStatement preparedStatement) throws SQLException {
		try (ResultSet resultSet = preparedStatement.getResultSet()) {
			while(resultSet.next()) {
				int id = resultSet.getInt("id");
				LocalDate dataEntrada = resultSet.getDate("fecha_entrada").toLocalDate().plusDays(1);
				LocalDate dataSalida = resultSet.getDate("fecha_salida").toLocalDate().plusDays(1);
				String valor = resultSet.getString("valor");
				String formaPago = resultSet.getString("forma_de_pago");
				
				Reservas producto = new Reservas(id, dataEntrada, dataSalida, valor, formaPago);
				reservas.add(producto);
			}
		}
	}
	
	public List<Reservas> buscar(String id){
		List<Reservas> reservas = new ArrayList<>();
		try {
			String sql = "SELECT id, fecha_entrada, fecha_salida, valor, forma_de_pago FROM reservas WHERE id = ?";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)){
					preparedStatement.setString(1, id);
					preparedStatement.execute();
					
					transformarResultado(reservas, preparedStatement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return reservas;
	}
	
	public void modificar(LocalDate dataEntrada, LocalDate dataSalida, String valor, String formaPago, Integer id) {
		try (PreparedStatement preparedStatement = conn.prepareStatement(" UPDATE reservas SET fecha_entrada=?, "
				+ " fecha_salida=?, valor=?, forma_de_pago=? WHERE id=? ")) {
			preparedStatement.setObject(1, dataEntrada);
			preparedStatement.setObject(2, dataSalida);
			preparedStatement.setString(3, valor);
			preparedStatement.setString(4, formaPago);
			preparedStatement.setInt(5, id);
			preparedStatement.execute();
			//System.out.println("estoy en la base??");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void modificar(Date dataEntrada, Date dataSalida, String valor, String formaPago, Integer id) {
		try (PreparedStatement preparedStatement = conn.prepareStatement(" UPDATE reservas SET fecha_entrada=?, "
				+ " fecha_salida=?, valor=?, forma_de_pago=? WHERE id=? ")) {
			preparedStatement.setObject(1, dataEntrada);
			preparedStatement.setObject(2, dataSalida);
			preparedStatement.setString(3, valor);
			preparedStatement.setString(4, formaPago);
			preparedStatement.setInt(5, id);
			preparedStatement.execute();
			//System.out.println("estoy en la base??");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void eliminar(Integer id) {
		try {
			Statement statement = conn.createStatement();
			statement.execute(" SET FOREIGN_KEY_CHECKS=0 ");
			PreparedStatement preparedStatement = conn.prepareStatement(" DELETE FROM reservas WHERE id = ? ");
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			statement.execute(" SET FOREIGN_KEY_CHECKS=1 ");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
