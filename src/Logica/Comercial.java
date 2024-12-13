package Logica;

public class Comercial {
	private int idComercial;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String ciudad;
	private float comision;
	public Comercial(int id, String nombre, String apellido1, String apellido2, String ciudad, float comision) {
		this.idComercial = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.ciudad = ciudad;
		this.comision = comision;
	}
	public int getIdComercial() {
		return idComercial;
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
	public float getComision() {
		return comision;
	}
	@Override
	public String toString() {
		return "Comercial [id=" + idComercial + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", ciudad=" + ciudad + ", comision=" + comision + "]";
	}
	
	
}
