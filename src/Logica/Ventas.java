package Logica;

import java.sql.SQLException;
import java.util.List;

import Persistencia.GestorJDBC;

public class Ventas {

	GestorJDBC gestor;
	
	public Ventas() throws ClassNotFoundException, SQLException {
		gestor=new GestorJDBC();
	}
	public boolean consulta1() throws SQLException, ClassNotFoundException {
		boolean result = false;

		result = gestor.crearTablaLogin();

		return result;
	}

	public int consulta2(String[] nombres, String[] claves) throws SQLException, ClassNotFoundException {

		int result = 0;

		for(int i=0; i<nombres.length; i++) {
			result += gestor.insertarLogin(nombres[i], claves[i]);
		}

		return result;
	}

	public String consulta3(String nombre, String pass) throws SQLException, ClassNotFoundException {

		Login login = gestor.buscarLogin(nombre);
		String text = "";

		if(login == null) {
			text += "Usuario no registrado";
		}else {
			if(login.getClave().compareToIgnoreCase(pass) == 0) {
				text += "Usuario corrrecto";
			}else {
				text += "Clave Incorrecta";
			}
		}

		return text;
	}	
	public String consulta4() throws SQLException, ClassNotFoundException {
		String text = "";

		List<Pedido> pedidos = gestor.listaPedidos();
		for(Pedido p : pedidos) {
			Cliente cl = gestor.buscarCliente(p.getIdCliente());
			Comercial co = gestor.buscarComercial(p.getIdComercial());
			if(p != null)
				text += p.toString() + "\n";
				text += "\t" + co.toString() + "\n";
				text += "\t" + cl.toString() + "\n";

		}
		return text;
	}


	public String consulta5() throws SQLException, ClassNotFoundException {
		String text = "";

		List<Cliente> clientes = gestor.listaClientesConPedidos();
		for(Cliente c : clientes) {
			text += c.toString()+"\n";
		}
		return text;
	}

	public String consulta6() throws SQLException, ClassNotFoundException {

		String text = "";
		Pedido p = gestor.pedidoConMayorValor();
		Cliente cl = gestor.buscarCliente(p.getIdCliente());
		Comercial co = gestor.buscarComercial(p.getIdComercial());

		if(p != null)
			text += p.toString() + "\n";
			text += "\t" + cl.toString() + "\n";
			text += "\t" + co.toString() + "\n";
			return text;
	}

	public String consulta7() throws SQLException, ClassNotFoundException {

		String text = "";

		List<Comercial> comercial = gestor.listaComercialSinPedidos();

		for(Comercial c : comercial) {
			text += c.toString()+"\n";
		}
		return text;

	}

	public String consulta8() throws SQLException, ClassNotFoundException {
		String text = "";

		text = gestor.metadata();

		return text;
	}
}
