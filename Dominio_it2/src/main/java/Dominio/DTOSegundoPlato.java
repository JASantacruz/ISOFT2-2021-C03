package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOSegundoPlato {
public static Agente agente = new Agente();
	
	public static void leerSegundosPlatos(LinkedList<SegundoPlato> listaSegundoPlato) {
		ResultSet rs;
		String consulta = "SELECT * FROM SegundoPlato";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				SegundoPlato segundoPlato = new SegundoPlato(rs.getInt(1), rs.getString(2), rs.getInt(3));
				listaSegundoPlato.add(segundoPlato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerNombreSegundosPlatos(LinkedList<String> listaSegundoPlato) {
		ResultSet rs;
		String consulta = "SELECT nombre FROM SegundoPlato";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				listaSegundoPlato.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static int leerStockSegundosPlatos(SegundoPlato segundoPlato) {
		ResultSet rs;
		int stock=0;
		String consulta = "SELECT 'Stock' FROM SegundoPlato WHERE 'nombre'="+segundoPlato.getNombre();
		try {
			rs=agente.Read(consulta);
			stock=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;
	}
}
