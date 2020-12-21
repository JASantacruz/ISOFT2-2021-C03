package Dominio;

import Persistencia.Agente;
import java.sql.*;
import java.util.LinkedList;

public class DTOReceta {
	public static Agente agente = new Agente();
	
	public static int leerReceta(String nombre, LinkedList<Receta> lista) {
		String consulta = "SELECT codigo FROM Carta WHERE nombre='"+nombre+"';";
		ResultSet rs;
		int codigo = 0;
		Receta rec;
		int resultado = 0;
		try {
			rs = agente.Read(consulta);
			while(rs.next())
				codigo = rs.getInt(1);
			consulta = "SELECT nombreIngrediente, cantidad FROM C03dbservice.Receta WHERE codigoCarta="+codigo+";";
			rs = agente.Read(consulta);
			while(rs.next()) {
				rec = new Receta(rs.getString(1), rs.getInt(2));
				lista.add(rec);
			}
			resultado = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}
}
