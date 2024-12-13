package Persistencia;

import Logica.Cliente;
import Logica.Comercial;
import Logica.Login;
import Logica.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GestorJDBC {
	private Connection con;
	private Statement st;
	
	public GestorJDBC() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");            
        con  = DriverManager.getConnection("jdbc:mysql://localhost/ventas", "root", "root");        
        System.out.println("Base conectada ");		
	}

	public boolean crearTablaLogin() throws ClassNotFoundException, SQLException {


		boolean creado = true;

		Class.forName("com.mysql.cj.jdbc.Driver");
		con  = DriverManager.getConnection("jdbc:mysql://localhost/ventas", "root", "root");

		String consulta = "DROP TABLE IF EXISTS login";

		PreparedStatement pt1 = con.prepareStatement(consulta);

		if(pt1.execute()) creado = false;

		String crearTablaLogin = "CREATE TABLE IF NOT EXISTS login (" +
				"id INT AUTO_INCREMENT, " +
				"nombre VARCHAR(20) NOT NULL, " +
				"pass VARCHAR(10) NOT NULL, " +
				"PRIMARY KEY (id))";



		PreparedStatement pt2 = con.prepareStatement(crearTablaLogin);

		if(pt2.execute()) creado = false;

		pt2.close();
		pt1.close();
		con.close();

		return creado;
	}

	public int insertarLogin(String nombre, String clave) throws ClassNotFoundException, SQLException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		con  = DriverManager.getConnection("jdbc:mysql://localhost/ventas", "root", "root");

		String consulta = "INSERT INTO login (nombre, pass) VALUES (?,?)";

		PreparedStatement pt = con.prepareStatement(consulta);
		pt.setString(1, nombre);
		pt.setString(2, clave);
		int filasAfectadas = pt.executeUpdate();
		pt.close();
		con.close();
		return filasAfectadas;

	}

	public Login buscarLogin(String nombre) throws ClassNotFoundException, SQLException {

		String nombreLowerCase = nombre.toLowerCase();

		Class.forName("com.mysql.cj.jdbc.Driver");
		con  = DriverManager.getConnection("jdbc:mysql://localhost/ventas", "root", "root");

		String consulta = "SELECT * FROM login WHERE nombre = ?";

		PreparedStatement pt = con.prepareStatement(consulta);
		pt.setString(1, nombreLowerCase);
		ResultSet rs = pt.executeQuery();

		Login login = null;
		if(rs.next()) {
			login = new Login(rs.getInt("id"), rs.getString("nombre"), rs.getString("pass"));
		}
		pt.close();
		con.close();

		return login;

	}

	public List<Pedido> listaPedidos() throws ClassNotFoundException, SQLException {

		List<Pedido> lista = new ArrayList<Pedido>();

		String consulta = "SELECT * FROM pedido";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas", "root", "root");

		PreparedStatement pt = con.prepareStatement(consulta);

		ResultSet rs = pt.executeQuery();

		while(rs.next()) {
			int id = rs.getInt(1);
			Double total = rs.getDouble(2);
			Date fecha = rs.getDate(3);
			int id_cliente = rs.getInt(4);
			int id_comercio = rs.getInt(5);

			Pedido p = new Pedido(id, total, fecha, id_cliente, id_comercio);
			lista.add(p);
		}
		return lista;
	}

	public List<Cliente> listaClientesConPedidos() throws SQLException, ClassNotFoundException {

		List<Cliente> lista = new ArrayList<Cliente>();

		String consulta = "SELECT * FROM cliente WHERE id IN (SELECT id_cliente FROM pedido)";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas", "root", "root");

		PreparedStatement pt = con.prepareStatement(consulta);

		ResultSet rs = pt.executeQuery();

		while(rs.next()) {
			int id = rs.getInt(1);
			String nombre = rs.getString(2);
			String apellido1 = rs.getString(3);
			String apellido2 = rs.getString(4);
			String ciudad = rs.getString(5);
			int categoria = rs.getInt(6);

			Cliente c =  new Cliente(id, nombre, apellido1, apellido2, ciudad, categoria);
			lista.add(c);
		}

		return lista;
	}

	public Pedido pedidoConMayorValor() throws ClassNotFoundException, SQLException {

		Pedido p = null;

		String consulta = "SELECT * FROM pedido WHERE total = (SELECT MAX(total) FROM pedido)";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas", "root", "root");

		try (PreparedStatement pt = con.prepareStatement(consulta);
			 ResultSet rs = pt.executeQuery()) {

			while (rs.next()) {
				int id = rs.getInt(1);
				Double total = rs.getDouble(2);
				Date fecha = rs.getDate(3);
				int id_cliente = rs.getInt(4);
				int id_comercio = rs.getInt(5);
				p = new Pedido(id, total, fecha, id_cliente, id_comercio);
			}
		} finally {
			con.close();
		}
		return p;
	}

	public List<Comercial> listaComercialSinPedidos() throws SQLException, ClassNotFoundException {

		List<Comercial> lista = new ArrayList<Comercial>();

		String consulta = "SELECT * FROM comercial WHERE id NOT IN (SELECT id_comercial FROM pedido)";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas", "root", "root");

		PreparedStatement pt = con.prepareStatement(consulta);

		ResultSet rs = pt.executeQuery();

		while(rs.next()) {
			int id = rs.getInt(1);
			String nombre = rs.getString(2);
			if(nombre == null) {
				nombre = "null";
			}
			String apellido1 = rs.getString(3);
			if(apellido1 == null) {
				apellido1 = "null";
			}
			String apellido2 = rs.getString(4);
			if(apellido2 == null) {
				apellido2 = "null";
			}
			String ciudad = rs.getString(5);
			if(ciudad == null) {
				ciudad = "null";
			}
			float categoria = rs.getFloat(6);
			if(rs.wasNull()) {
				categoria = 0;
			}

			Comercial c = new Comercial(id, nombre, apellido1, apellido2, ciudad, categoria);

			lista.add(c);
		}
		return lista;
	}

	public Cliente buscarCliente(int idCliente) throws SQLException, ClassNotFoundException {

		String conculta = "SELECT * FROM cliente WHERE id = ?";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas", "root", "root");

		PreparedStatement pt = con.prepareStatement(conculta);

		pt.setInt(1, idCliente);

		ResultSet rs = pt.executeQuery();

		while(rs.next()) {

			int idc = rs.getInt(1);
			String nombre = rs.getString(2);
			String apellido1 = rs.getString(3);
			String apellido2 = rs.getString(4);
			String ciudad = rs.getString(5);
			int categoria = rs.getInt(6);

			Cliente c = new Cliente(idc, nombre, apellido1, apellido2, ciudad, categoria);
			return c;
		}
		return null;

	}

	public Comercial buscarComercial(int idComercial) throws ClassNotFoundException, SQLException {

		String conculta = "SELECT * FROM comercial WHERE id = ?";

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas", "root", "root");

		PreparedStatement pt = con.prepareStatement(conculta);

		pt.setInt(1, idComercial);

		ResultSet rs = pt.executeQuery();

		while(rs.next()) {

			int idc = rs.getInt(1);
			String nombre = rs.getString(2);
			String apellido1 = rs.getString(3);
			String apellido2 = rs.getString(4);
			String ciudad = rs.getString(5);
			float categoria = rs.getFloat(6);

			Comercial c = new Comercial(idc, nombre, apellido1, apellido2, ciudad, categoria);
			return c;
		}
		return null;
	}

	public String metadata() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas", "root", "root");

		PreparedStatement pt = con.prepareStatement("SELECT * FROM cliente");

		ResultSet rs = null;

		try {
			rs = pt.executeQuery();

			ResultSetMetaData metaData = rs.getMetaData();

			int comumns = metaData.getColumnCount();

			String text = "\n Clientes \n";
			for(int i = 1; i <= comumns; i++) {

				text += "\33[34m Columna " + i + " : " + metaData.getColumnName(i)
						+ " - Tipo : " + metaData.getColumnTypeName(i) + "\33[0m\n";
			}

			while(rs.next()) {
				text += rs.getInt(1) + " | " + rs.getString(2) + " | "
						+ rs.getString(3) + " | " + rs.getString(4) + " | "
						+ rs.getString(5) + " | " + rs.getInt(6) + "\n";
			}

			rs = pt.executeQuery("SELECT * FROM comercial");

			metaData = rs.getMetaData();

			comumns = metaData.getColumnCount();

			text += "\n Comerciales \n";
			for(int i = 1; i <= comumns; i++) {

				text += "\33[34m Columna " + i + " : " + metaData.getColumnName(i)
						+ " - Tipo : " + metaData.getColumnTypeName(i) + "\33[0m\n";
			}

			while(rs.next()) {
				text += rs.getInt(1) + " | " + rs.getString(2) + " | "
						+ rs.getString(3) + " | " + rs.getString(4) + " | "
						+ rs.getString(5) + " | " + rs.getFloat(6) + "\n";
			}

			rs = pt.executeQuery("SELECT * FROM pedido");

			metaData = rs.getMetaData();

			comumns = metaData.getColumnCount();

			text += "\n Pedidos \n";
			for(int i = 1; i <= comumns; i++) {

				text += "\33[34m Columna " + i + " : " + metaData.getColumnName(i)
						+ " - Tipo : " + metaData.getColumnTypeName(i) + "\33[0m\n";
			}

			while(rs.next()) {
				text += rs.getInt(1) + " | " + rs.getDouble(2) + " | "
						+ rs.getDate(3) + " | " + rs.getInt(4) + " | "
						+ rs.getInt(5) + "\n";
			}

			rs = pt.executeQuery("SELECT * FROM login");

			metaData = rs.getMetaData();

			comumns = metaData.getColumnCount();

			text += "\n Login \n";
			for(int i = 1; i <= comumns; i++) {

				text += "\33[34m Columna " + i + " : " + metaData.getColumnName(i)
						+ " - Tipo : " + metaData.getColumnTypeName(i) + "\33[0m\n";
			}

			while(rs.next()) {
				text += rs.getInt(1) + " | " + rs.getString(2) + " | "
						+ rs.getString(3) + "\n";
			}

			return text;


		} catch (SQLException e) {
			System.out.println("ERROR MOSTRANDO METADATA DE LA BASE DE DATOS");
			return null;

		}
	}
}

