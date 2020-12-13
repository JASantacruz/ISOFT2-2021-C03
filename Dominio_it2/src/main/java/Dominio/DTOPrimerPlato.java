package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOPrimerPlato {
	public static Agente agente = new Agente();

	public static void leerPrimerosPlatos(LinkedList<PrimerPlato> listaPrimerosPlatos) {
		ResultSet rs;
		String consulta = "SELECT * FROM PrimerPlato";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				PrimerPlato primerPlato = new PrimerPlato(rs.getInt(1), rs.getString(2), rs.getInt(3));
				listaPrimerosPlatos.add(primerPlato);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void leerNombrePrimerosPlatos(LinkedList<String> listaPrimerosPlatos) {
		ResultSet rs;
		String consulta = "SELECT nombre FROM PrimerPlato";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				listaPrimerosPlatos.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int leerStockPrimerPlato(PrimerPlato primerPlato) {
		ResultSet rs;
		int stock=0;
		String consulta = "SELECT 'Stock' FROM PrimerPlato WHERE nombre="+primerPlato.getNombre();
		try {
			rs=agente.Read(consulta);
			stock=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;
	}
}
