package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOEstadistica {
	public static Agente agente = new Agente();

	public static double calcularTiempoTomaComandasRestaurante(String num_comensales, String restaurante) {
		String consulta="SELECT AVG(tiempoEnEsperaComida - tiempoPidiendo)/100 "
				+ " FROM Reserva WHERE tiempoLibre IS NOT NULL AND num_comensales="+num_comensales+" "
						+ "AND restaurante="+restaurante+"";
		double tiempoTomaComandas=0;
		try {
			ResultSet rs=agente.Read(consulta);
			if(rs.next()) {
				tiempoTomaComandas=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiempoTomaComandas;
	}
	public static double calcularTiempoTomaComandasCiudad(String num_comensales, String ciudad) {
		String consultaCiudad="(SELECT idRestaurante FROM Restaurante WHERE ciudad='"+ciudad+"')";
		String consulta="SELECT AVG(tiempoEnEsperaComida - tiempoPidiendo)/100 FROM Reserva"
				+ " WHERE tiempoLibre IS NOT NULL AND num_comensales="+num_comensales+" "
						+ "AND restaurante IN "+consultaCiudad;
		double tiempoTomaComandas=0;
		try {
			ResultSet rs=agente.Read(consulta);
			if(rs.next()) {
				tiempoTomaComandas=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiempoTomaComandas;
	}

	public static double calcularTiempoPreparacionComandasRestaurante(String num_comensales, String restaurante) {
		String consulta="SELECT AVG(tiempoServido - tiempoEnEsperaComida)/100 FROM Reserva"
				+ " WHERE tiempoLibre IS NOT NULL AND num_comensales="+num_comensales+" "
						+ "AND restaurante="+restaurante;
		double tiempoPreparacionComandas=0;
		try {
			ResultSet rs=agente.Read(consulta);
			if(rs.next()) {
				tiempoPreparacionComandas=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiempoPreparacionComandas;
	}
	public static double calcularTiempoPreparacionComandasCiudad(String num_comensales, String ciudad) {
		String consultaCiudad="(SELECT idRestaurante FROM Restaurante WHERE ciudad='"+ciudad+"')";
		String consulta="SELECT AVG(tiempoServido - tiempoEnEsperaComida)/100 FROM Reserva"
				+ " WHERE tiempoLibre IS NOT NULL AND num_comensales="+num_comensales+" "
						+ "AND restaurante IN "+consultaCiudad;
		double tiempoPreparacionComandas=0;
		try {
			ResultSet rs=agente.Read(consulta);
			if(rs.next()) {
				tiempoPreparacionComandas=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiempoPreparacionComandas;
	}
	public static double calcularTiempoEntregaNotaRestaurante(String num_comensales, String restaurante) {
		String consulta="SELECT AVG(tiempoPagando - tiempoEsperandoCuenta)/100 FROM Reserva"
				+" WHERE tiempoLibre IS NOT NULL AND num_comensales="+num_comensales+ ""
						+ " AND restaurante="+restaurante+"";
		double tiempoEntregaNota=0;
		try {
			ResultSet rs=agente.Read(consulta);
			if(rs.next()) {
				tiempoEntregaNota=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiempoEntregaNota;
	}
	public static double calcularTiempoEntregaNotaCiudad(String num_comensales, String ciudad) {
		String consultaCiudad="(SELECT idRestaurante FROM Restaurante WHERE ciudad='"+ciudad+"')";
		String consulta="SELECT AVG(tiempoPagando - tiempoEsperandoCuenta)/100 FROM Reserva"
				+" WHERE tiempoLibre IS NOT NULL AND num_comensales="+num_comensales+ ""
						+ " AND restaurante IN "+consultaCiudad;
		double tiempoEntregaNota=0;
		try {
			ResultSet rs=agente.Read(consulta);
			if(rs.next()) {
				tiempoEntregaNota=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiempoEntregaNota;
	}
	public static double calcularTiempoPreparacionMesaLibreRestaurante(String num_comensales, String restaurante) {
		String consulta="SELECT AVG(tiempoLibre - tiempoEnPreparacion)/100 FROM Reserva"
				+ " WHERE tiempoLibre IS NOT NULL AND num_comensales="+num_comensales+ ""
						+ " AND restaurante="+restaurante+"";
		double tiempoPreparacionMesaLibre=0;
		try {
			ResultSet rs=agente.Read(consulta);
			if(rs.next()) {
				tiempoPreparacionMesaLibre=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiempoPreparacionMesaLibre;
	}
	public static double calcularTiempoPreparacionMesaLibreCiudad(String num_comensales, String ciudad) {
		String consultaCiudad="(SELECT idRestaurante FROM Restaurante WHERE ciudad='"+ciudad+"')";
		String consulta="SELECT AVG(tiempoLibre - tiempoEnPreparacion)/100 FROM Reserva"
				+ " WHERE tiempoLibre IS NOT NULL AND num_comensales="+num_comensales+ ""
						+ " AND restaurante IN "+consultaCiudad;
		double tiempoPreparacionMesaLibre=0;
		try {
			ResultSet rs=agente.Read(consulta);
			if(rs.next()) {
				tiempoPreparacionMesaLibre=rs.getDouble(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiempoPreparacionMesaLibre;
	}
	public static void leerRestaurantes(LinkedList<String> restaurantes) {
		String consulta="SELECT idRestaurante FROM Restaurante";
		try {
			ResultSet rs=agente.Read(consulta);
			while(rs.next()) {
				restaurantes.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void leerCiudades(LinkedList<String> ciudades) {
		String consulta="SELECT DISTINCT ciudad FROM Restaurante";
		try {
			ResultSet rs=agente.Read(consulta);
			while(rs.next()) {
				ciudades.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}