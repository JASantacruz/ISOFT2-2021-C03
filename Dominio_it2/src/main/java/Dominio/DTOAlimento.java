package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOAlimento {
	public static Agente agente = new Agente();

	public static void leerAlimentosPorTipo(LinkedList<Alimento> listaAlimentos, String tipo) {
		ResultSet rs;
		String consulta = "SELECT * FROM Alimento WHERE tipo='"+tipo+"'";
		try {
			rs=agente.Read(consulta);
			while(rs.next()){
				Alimento alimento = new Alimento(rs.getInt(1), rs.getString(2), rs.getInt(3));
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

	public static int leerStockAlimento(Alimento alimento) {
		ResultSet rs;
		int stock=0;
		String consulta = "SELECT stock FROM Alimento WHERE nombre="+alimento.getNombre();
		try {
			rs=agente.Read(consulta);
			stock=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stock;
	}
}
