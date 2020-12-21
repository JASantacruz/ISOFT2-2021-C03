package Dominio;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOPrevision {

	static Agente agente = new Agente();

	public static void leerIngredientes(LinkedList<Ingrediente> lista) {
		String consulta = "SELECT nombre FROM Ingrediente;";
		ResultSet rs = agente.Read(consulta);
		Ingrediente ig;
		try {
			while(rs.next()) {
				ig = new Ingrediente(rs.getString(1), 0, 0);
				lista.add(ig);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int leerStock(String nombre) {
		String consulta = "SELECT stock FROM Ingrediente WHERE nombre='"+nombre+"';";
		int res = 0;
		ResultSet rs = agente.Read(consulta);
		try {
			while(rs.next()) {
				res = rs.getInt(1);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static int actualizarStock(String nombre, int cantidad) {
		String consulta = "SELECT stock FROM Ingrediente WHERE nombre='"+nombre+"';";
		int stock = 0;
		ResultSet rs;
		rs = agente.Read(consulta);
		int res=-1;
		try {
			while(rs.next())
				stock = rs.getInt(1);
			stock = stock + cantidad;
			consulta = "UPDATE Ingrediente SET stock="+stock+" WHERE nombre='"+nombre+"';";
			res=agente.Update(consulta);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static int hacerPrevision(String ingrediente, int cantidad, LocalDateTime fecha) {
		int res=-1;
		String consulta = "INSERT INTO Prevision (ingrediente, cantidad, fecha) VALUES ('"+ingrediente+"', "+cantidad+", '"+fecha+"');";
		try {
			res=agente.Insert(consulta);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static void comprobarFecha(LinkedList<Prevision> historial) {
		ResultSet rs;
		String consulta = "SELECT * FROM Prevision WHERE (SELECT DATEDIFF(NOW(), fecha) > 7);";
		rs = agente.Read(consulta);
		try {
			while(rs.next()) 
				historial.add(new Prevision(rs.getString(1), rs.getInt(2), rs.getTimestamp(3).toLocalDateTime()));
			
			retirarAlimentos(historial);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void retirarAlimentos(LinkedList<Prevision> historial) {
		String consulta;
		int stock = 0;
		ResultSet rs;
		for(int i = 0; i < historial.size(); i++) {
			consulta = "SELECT stock FROM Ingrediente WHERE nombre='"+historial.get(i).getIngrediente()+"';";
			rs = agente.Read(consulta);
			try {
				while(rs.next()) {
					stock = rs.getInt(1);
				}
				consulta = "UPDATE Ingrediente SET stock="+(stock-historial.get(i).getCantidad())+" WHERE  nombre='"+historial.get(i).getIngrediente()+"';";
				agente.Update(consulta);
				consulta = "DELETE FROM Prevision WHERE fecha='"+historial.get(i).getFecha().toLocalDate()+"';";
				agente.Delete(consulta);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

}

