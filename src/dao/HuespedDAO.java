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

import modelo.Huespedes;
import modelo.Reservas;

public class HuespedDAO {
	private Connection conn;
	
	public HuespedDAO(Connection conn) {
		this.conn = conn;
	}
	
	public void guardar(Huespedes huespedes) {
		try {
			String sql = "INSERT INTO huespedes("
					+ "nombre, apellido, fecha_nacimiento,"
					+ "nacionalidad, telefono, id_reserva)"
					+ "VALUES(?, ?, ?, ?, ?, ?)";
			try (PreparedStatement preparedStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
				preparedStatement.setString(1, huespedes.getNombre());
				preparedStatement.setString(2, huespedes.getApellido());
				preparedStatement.setObject(3, huespedes.getFechaNacimiento());
				preparedStatement.setString(4, huespedes.getNacionalidad());
				preparedStatement.setString(5, huespedes.getTelefono());
				preparedStatement.setInt(6, huespedes.getIdReserva());
				preparedStatement.execute();
				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()){
					while(resultSet.next()) {
						huespedes.setId(resultSet.getInt(1));
					}
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Huespedes> mostrar(){
		List<Huespedes> huespedes = new ArrayList<>();
		try {
			String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)){
					preparedStatement.execute();
					
					transformarResultado(huespedes, preparedStatement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return huespedes;
	}
	
	public void transformarResultado(List<Huespedes> huespedes, PreparedStatement preparedStatement) throws SQLException {
		try (ResultSet resultSet = preparedStatement.getResultSet()) {
			while(resultSet.next()) {
				Object id = resultSet.getObject("id");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				Date fechaNacimiento = resultSet.getDate("fecha_nacimiento");
				String nacionalidad = resultSet.getString("nacionalidad");
				String telefono = resultSet.getString("telefono");
				int idReserva = resultSet.getInt("id_reserva");
				
				Huespedes huesped = new Huespedes(id, nombre, apellido, fechaNacimiento, nacionalidad, telefono, idReserva);
				huespedes.add(huesped);
			}
		}
	}

	public List<Huespedes> buscarID(String id){
		List<Huespedes> huespedes = new ArrayList<>();
		try {
			String sql = "SELECT id, nombre, apellido, fecha_nacimiento, nacionalidad, telefono, id_reserva FROM huespedes WHERE id = ?";
				try (PreparedStatement preparedStatement = conn.prepareStatement(sql)){
					preparedStatement.setString(1, id);
					preparedStatement.execute();
					
					transformarResultado(huespedes, preparedStatement);
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return huespedes;
	}

	public void eliminar(Integer id) {
		try {
			Statement statement = conn.createStatement();
			statement.execute(" SET FOREIGN_KEY_CHECKS=0 ");
			String sql = (" DELETE FROM huespedes WHERE id = ? ");
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			preparedStatement.execute();
			statement.execute(" SET FOREIGN_KEY_CHECKS=1 ");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	public void modificar(String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono,
			Integer id_reserva) {
		try (PreparedStatement preparedStatement = conn.prepareStatement(" UPDATE huespedes SET nombre=?, "
				+ " apellido=?, fecha_nacimiento=?, nacionalidad=? telefono=? id_reserva=? WHERE id=? ")) {
			preparedStatement.setString(1, nombre);
			preparedStatement.setString(2, apellido);
			preparedStatement.setDate(3, fechaNacimiento);
			preparedStatement.setString(4, nacionalidad);
			preparedStatement.setString(5, telefono);
			preparedStatement.setInt(6, id_reserva);
			preparedStatement.execute();
			//System.out.println("estoy en la base??");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

}
