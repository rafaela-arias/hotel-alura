package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

import dao.HuespedDAO;
import factory.Conexion;
import modelo.Huespedes;
import modelo.Reservas;

public class HuespedesControlador {
	private HuespedDAO huespedDAO;
	
	public HuespedesControlador() {
		Connection conn = new Conexion().ConexionSQL();
		this.huespedDAO = new HuespedDAO(conn);
	}
	
	public void guardar(Huespedes huesped) {
		this.huespedDAO.guardar(huesped);
	}
	
	public List<Huespedes> mostrar() {
		return this.huespedDAO.mostrar();
	}
	
	public List<Huespedes> buscarID(String id) {
		return this.huespedDAO.buscarID(id);
	}

	public void eliminar(Integer id) {
		this.huespedDAO.eliminar(id);
		
	}

	public void modificar(Integer id, String nombre, String apellido, Date fechaNacimiento, String nacionalidad, String telefono,
			Integer id_reserva) {
		this.huespedDAO.modificar(nombre, apellido, fechaNacimiento, nacionalidad, telefono, id_reserva);
		
	}
}
