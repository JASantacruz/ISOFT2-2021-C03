package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOAlimento {
	public static Agente agente = new Agente();

	public static void leerAlimentosPorTipo(LinkedList<Alimento> listaAlimentos, String tipo) {
		ResultSet rs;
		String consulta = "SELECT * FROM Carta WHERE tipo='"+tipo+"'";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				Alimento alimento = new Alimento(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4));
				listaAlimentos.add(alimento);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void leerNombreAlimentosPorTipo(LinkedList<String> listaAlimentos, String tipo) {
		ResultSet rs;
		String consulta = "SELECT nombre FROM Carta WHERE tipo='"+tipo+"'";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				listaAlimentos.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
