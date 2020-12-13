package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOEntrante {
	public static Agente agente = new Agente();
	public static void leerEntrantes(LinkedList<Entrante> listaEntrantes) {
		ResultSet rs;
		String consulta = "SELECT * FROM Entrante";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				Entrante entrante = new Entrante(rs.getInt(1), rs.getString(2), rs.getInt(3));
				listaEntrantes.add(entrante);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerNombreEntrantes(LinkedList<String> listaEntrantes) {
		ResultSet rs;
		String consulta = "SELECT nombre FROM Entrante";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				listaEntrantes.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int leerStockEntrante(Entrante entrante) {
		ResultSet rs;
		int stock=0;
		String consulta = "SELECT 'Stock' FROM Entrante WHERE 'Nombre'="+entrante.getNombre();
		try {
			rs=agente.Read(consulta);
			stock=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;
	}
}
