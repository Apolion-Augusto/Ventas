package Logica;

public class Cliente {
	private int idCliente;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String ciudad;
	private int categoria;
	public Cliente(int idCliente, String nombre, String apellido1, String apellido2, String ciudad, int categoria) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.ciudad = ciudad;
		this.categoria = categoria;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public String getApellido1() {
		return apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public String getCiudad() {
		return ciudad;
	}
	public int getCategoria() {
		return categoria;
	}
	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2="
				+ apellido2 + ", ciudad=" + ciudad + ", categoria=" + categoria + "]";
	}
	
	
	
}
