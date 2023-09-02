package controladores;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import dao.ReservaDAO;
import factory.Conexion;
import modelo.Reservas;

public class ReservasControlador {
	private ReservaDAO reservaDAO;
	
	public ReservasControlador() {
		Connection conn = new Conexion().ConexionSQL();
		this.reservaDAO = new ReservaDAO(conn);
	}
	
	public void guardar(Reservas reserva) {
		this.reservaDAO.guardar(reserva);
	}
	
	public List<Reservas> mostrar() {
		return this.reservaDAO.mostrar();
	}
	
	public List<Reservas> buscar(String id) {
		return this.reservaDAO.buscar(id);
	}

	public void modificar(LocalDate dataEntrada, LocalDate dataSalida, String valor, String formaPago, Integer id) {
		this.reservaDAO.modificar(dataEntrada, dataSalida, valor, formaPago, id);
	}

	public void modificar(Date dataEntrada, Date dataSalida, String valor, String formaPago, Integer id) {
		this.reservaDAO.modificar(dataEntrada, dataSalida, valor, formaPago, id);
		
	}
	
	public void eliminar(Integer id) {
		this.reservaDAO.eliminar(id);
	}
}
