package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOPostre {
public static Agente agente = new Agente();
	
	public static void leerPostres(LinkedList<Postre> listaPostres) {
		ResultSet rs;
		String consulta = "SELECT * FROM Postre";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				Postre postre = new Postre(rs.getInt(1), rs.getString(2), rs.getInt(3));
				listaPostres.add(postre);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerNombrePostres(LinkedList<String> listaPostres) {
		ResultSet rs;
		String consulta = "SELECT nombre FROM Postre";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				listaPostres.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int leerStockPostre(Postre postre) {
		ResultSet rs;
		int stock=0;
		String consulta = "SELECT 'Stock' FROM Postre WHERE 'nombre'="+postre.getNombre();
		try {
			rs=agente.Read(consulta);
			stock=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;
	}
}
