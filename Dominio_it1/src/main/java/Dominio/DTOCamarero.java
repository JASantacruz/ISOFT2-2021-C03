package Dominio;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.LinkedList;

import Persistencia.Agente;

/**
 * Clase DTOCamarero.
 * @author Asus
 *
 */
public class DTOCamarero {
	/**
	 * Variable agente.
	 */
	private static Agente agente = new Agente();
	/**
	 * Metodo leerCamareros.
	 * @param lista
	 */
	public static void leerCamareros(final LinkedList<Camarero> lista) {
		ResultSet rs;
		String consulta = "SELECT * FROM Camarero";
		rs = agente.Read(consulta);
		try {
			while (rs.next()) {
				Camarero camarero = new Camarero(rs.getInt(1),
						rs.getString(2));
				lista.add(camarero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo leerCamareroId.
	 * @param id
	 * @param lista
	 */
	public static void leerCamareroId(final int id,
			final LinkedList<Camarero> lista) {
		ResultSet rs;
		String consulta = "SELECT * FROM Camarero WHERE idCamarero= "
					+ id + ";";
		rs = agente.Read(consulta);
		try {
			while (rs.next()) {
				Camarero camarero = new Camarero(rs.getInt(1),
						rs.getString(2));
				lista.add(camarero);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Metodo leerAvisos.
	 * @param camarero
	 * @param lista
	 */
	public static void leerAvisos(final String camarero,
			final LinkedList<String> lista) {
		LocalDateTime turno = DTOReserva.obtenerTurno();
		String consultaIdCamarero = "SELECT idCamarero FROM Camarero"
					+ " WHERE nombre='" + camarero + "'";
		ResultSet idCamarero;
		ResultSet rsAvisos;
		try {
			idCamarero = agente.Read(consultaIdCamarero);
			if (idCamarero.next()) {
				rsAvisos = agente.Read("SELECT descripcion "
					+ "FROM Aviso WHERE idCamarero="
					+ idCamarero.getInt(1) + " "
					+ "AND turno='" + turno + "'");
				while (rsAvisos.next()) {
					lista.add(rsAvisos.getString(1));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
