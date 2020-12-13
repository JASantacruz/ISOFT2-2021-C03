package Dominio;

import java.sql.*;
import java.util.*;

import Persistencia.Agente;

@SuppressWarnings("static-access")
public class DTOCamarero {

	static Agente agente = new Agente();
	
	public static void leerCamareros(LinkedList<Camarero> lista) {
		ResultSet rs;
		String consulta = "SELECT * FROM Camarero";
		rs=agente.Read(consulta);
		try {
			while(rs.next()){
				Camarero camarero = new Camarero(rs.getInt(1), rs.getString(2));
				lista.add(camarero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void leerCamareroId(int id, LinkedList<Camarero> lista) {
		ResultSet rs;
		String consulta = "SELECT * FROM Camarero WHERE idCamarero= "+id+";";
		rs=agente.Read(consulta);
		try {
			while(rs.next()){
				Camarero camarero = new Camarero(rs.getInt(1), rs.getString(2));
				lista.add(camarero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
