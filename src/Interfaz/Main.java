package Interfaz;

import java.sql.SQLException;

import Logica.Ventas;

public class Main {
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Ventas ventas =new Ventas();
			System.out.println(ANSI_BLUE+"Consulta 1: Creacion de la tabla login");
			System.out.println(ANSI_BLACK+ventas.consulta1());
			System.out.println("*********************************************");
			String nombres[] = {"Pepe", "Juan", "Paco"};
            String claves[] = {"A3F5", "V4Q4", "R5T5"};
			System.out.println(ANSI_BLUE+"Consulta 2: insercion de nuevos datos");
			System.out.println(ANSI_BLACK+"Numero total de datos "+ventas.consulta2(nombres,claves));
			System.out.println("*********************************************");
			System.out.println(ANSI_BLUE+"Consulta 3: validar usuarios");
			System.out.println(ANSI_BLACK+"<Pepe,A3F5>"+ventas.consulta3("pepe","A3F5") );
			System.out.println(ANSI_BLUE+"Consulta 3: validar usuarios");
			System.out.println(ANSI_BLACK+"<Pepe,XXX>"+ventas.consulta3("pepe","XXX") );
			System.out.println(ANSI_BLUE+"Consulta 3: validar usuarios");
			System.out.println(ANSI_BLACK+"<Carlos,XXX>"+ventas.consulta3("carlos","XXX") );
			System.out.println(ANSI_BLUE+"Consulta 4: Obtener los datos de los pedidos ");
			System.out.println(ANSI_BLACK+ventas.consulta4());
			System.out.println("*********************************************");
			System.out.println(ANSI_BLUE+"Consulta 5: listado de los clientes que han realizado un pedido");;
			System.out.println(ANSI_BLACK+ventas.consulta5());
			System.out.println("***********************************************");
			System.out.println(ANSI_BLUE+"consulta 6 : Datos del pedido con mayor valor");
			System.out.println(ANSI_BLACK+ventas.consulta6());
			System.out.println("*********************************************");
			System.out.println(ANSI_BLUE+"Consulta 7: Listado de los comerciales que no han intervenido ningun pedido ");
			System.out.println(ANSI_BLACK+ventas.consulta7());
			System.out.println(ANSI_BLUE+"Consulta 8 : Metadata");
			System.out.println(ventas.consulta8());
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}
		System.out.println("FIN!!!!!");
	}

}
