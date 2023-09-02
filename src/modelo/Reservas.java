package modelo;

import java.time.LocalDate;

public class Reservas {
	private Integer id;
	private LocalDate dataEntrada;
	private LocalDate dataSalida;
	private String valor;
	private String formaPago;
	
	public Reservas(LocalDate dataEntrada, LocalDate dataSalida,
			String valor, String formaPago) {
		this.dataEntrada = dataEntrada;
		this.dataSalida = dataSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Reservas(int id, LocalDate dataEntrada, LocalDate dataSalida, String valor, String formaPago) {
		this.id = id;
		this.dataEntrada = dataEntrada;
		this.dataSalida = dataSalida;
		this.valor = valor;
		this.formaPago = formaPago;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataEntrada() {
		return dataEntrada;
	}

	public void setDataEntrada(LocalDate dataEntrada) {
		this.dataEntrada = dataEntrada;
	}

	public LocalDate getDataSalida() {
		return dataSalida;
	}

	public void setDataSalida(LocalDate dataSalida) {
		this.dataSalida = dataSalida;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getFormaPago() {
		return formaPago;
	}

	public void setFormaPago(String formaPago) {
		this.formaPago = formaPago;
	}
	
	
}
