package Dominio;

import java.sql.*;
import java.time.LocalDateTime;
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
	public static void leerAvisos(String camarero,LinkedList<String> lista) {
		LocalDateTime turno=DTOReserva.obtenerTurno();
		String consultaIdCamarero="SELECT idCamarero FROM Camarero WHERE nombre='"+camarero+"'";
		ResultSet idCamarero, rsAvisos;
		try {
			idCamarero=agente.Read(consultaIdCamarero);
			if(idCamarero.next()) {
				rsAvisos=agente.Read("SELECT descripcion FROM Aviso WHERE idCamarero="+idCamarero.getInt(1)+" "
						+ "AND turno='"+turno+"'");
				while(rsAvisos.next()) {
					lista.add(rsAvisos.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
