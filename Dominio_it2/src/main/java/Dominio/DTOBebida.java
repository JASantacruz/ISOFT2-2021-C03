package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOBebida {
	public static Agente agente = new Agente();
	
	public static void leerBebidas(LinkedList<Bebida> listaBebidas) {
		ResultSet rs;
		String consulta = "SELECT * FROM Bebida";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				Bebida bebida = new Bebida(rs.getInt(1), rs.getString(2), rs.getInt(3));
				listaBebidas.add(bebida);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerNombreBebidas(LinkedList<String> listaBebidas) {
		ResultSet rs;
		String consulta = "SELECT nombre FROM Bebida";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				listaBebidas.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int leerStockBebida(Bebida bebida) {
		ResultSet rs;
		int stock=0;
		String consulta = "SELECT 'Stock' FROM Bebidas WHERE 'nombre'="+bebida.getNombre();
		try {
			rs=agente.Read(consulta);
			stock=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;
	}
}
