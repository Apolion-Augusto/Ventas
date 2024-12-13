package Logica;

import java.sql.Date;

public class Pedido {
	public int idPedido;
	public double cantidad;
	public Date fecha;
	private int idCliente;
	private int idComercial;
	public Pedido(int idPedido, double cantidad, Date fecha, int idCliente, int idComercial) {
		this.idPedido = idPedido;
		this.cantidad = cantidad;
		this.fecha = fecha;
		this.idCliente = idCliente;
		this.idComercial = idComercial;
	}
	public int getIdPedido() {
		return idPedido;
	}
	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}
	public double getCantidad() {
		return cantidad;
	}
	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public int getIdComercial() {
		return idComercial;
	}
	public void setIdComercial(int idComercial) {
		this.idComercial = idComercial;
	}
	@Override
	public String toString() {
		return "Pedido [idPedido=" + idPedido + ", cantidad=" + cantidad + ", fecha=" + fecha + ", idCliente="
				+ idCliente + ", idComercial=" + idComercial + "]";
	}
	
	
	
}
