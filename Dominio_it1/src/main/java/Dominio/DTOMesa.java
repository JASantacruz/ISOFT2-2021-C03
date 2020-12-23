package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOMesa {

	private static Agente agente = new Agente();

	public static void leerMesas(final LinkedList<Mesa> pLista) {
		ResultSet rs;
		rs = agente.Read("SELECT * FROM Mesa");
		try {
			while (rs.next()) {
				Mesa mesa = new Mesa(rs.getInt(1), rs.getString(2));
				pLista.add(mesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void leerMesasOcupadas(final LinkedList<Mesa> pLista) {
		ResultSet rs;
		rs = agente.Read("SELECT * FROM Mesa WHERE estado='ocupada'");
		try {
			while (rs.next()) {
				Mesa mesa = new Mesa(rs.getInt(1), rs.getString(2));
				
				pLista.add(mesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void leerMesasPorCamarero(final LinkedList<Mesa> pLista, final String pCamarero) {
		ResultSet rs;
		LocalDateTime turnoActual = DTOReserva.obtenerTurno();
		String subConsultaIdCamarero = "(SELECT idCamarero FROM Camarero WHERE nombre='" + pCamarero + "')";
		
		rs = agente.Read("SELECT idMesa FROM MesaCamareroReserva WHERE turno='" + turnoActual + "' "
				+ "AND idCamarero=" + subConsultaIdCamarero);
		try {
			while (rs.next()) {
				Mesa mesa = new Mesa(rs.getInt(1), "");
				pLista.add(mesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int asignarCamarero(final Camarero pCamarero, final String pIdReserva) {
		return agente.Update("UPDATE MesaCamareroReserva SET idCamarero"
				+ "=" + pCamarero.getIdCamarero() + " WHERE idReserva=" + pIdReserva);
	}
	public static int cambiarMesaOcupada(final String pIdReserva) {
		ResultSet resultIdMesa = agente.Read("SELECT idMesa FROM MesaCamareroReserva WHERE idReserva=" + pIdReserva);
		int idMesa, resultado = 0;
		try {
			if (resultIdMesa.next()) {
				idMesa = resultIdMesa.getInt(1);
				resultado = cambiarEstado(idMesa + "", "ocupada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public static void leerMesa(final int pId, final LinkedList<Mesa> pLista) {
		ResultSet rs;
		rs = agente.Read("SELECT * FROM Mesa WHERE IdMesa= " + pId + ";");
		try {
			while (rs.next()) {
				Mesa mesa = new Mesa(rs.getInt(1), rs.getString(2));
				pLista.add(mesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String consultarEstadoMesa(final String pId) {
		ResultSet rs;
		String estado = "";
		rs = agente.Read("SELECT estado FROM Mesa WHERE idMesa=" + pId);
		try {
				if (rs.next()) {
					estado = rs.getString(1);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estado;
	}
	public static int cambiarEstado(final String pId, final String pEstado) {
		return agente.Update("UPDATE Mesa SET estado = '" + pEstado + "' WHERE idMesa = " + pId);
	}
	public static String secuenciarEstado(final String pEstadoActual) {
		String estado = "";
		switch (pEstadoActual) {
		case "libre":
			estado = "reservada";
			break;
		case "reservada":
			estado = "ocupada";
			break;
		case "ocupada":
			estado = "pidiendo";
			break;
		case "pidiendo":
			estado = "en espera de comida";
			break;
		case "en espera de comida":
			estado = "servidos";
			break;
		case "servidos":
			estado = "esperando la cuenta";
			break;
		case "esperando la cuenta":
			estado = "pagando";
			break;
		case "pagando":
			estado = "en preparacion";
			break;
		case "en preparacion":
			estado = "libre";
			break;
		}
		return estado;
	}
	public static boolean estadoCorrecto(final String pIdMesa) {
		boolean correcto = false;
		ResultSet rs = agente.Read("SELECT estado FROM Mesa WHERE idMesa=" + pIdMesa);
		try {
			if (rs.next()) {
				if (rs.getString(1).equals("pidiendo")) {
					correcto = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return correcto;
	}
	public static void actualizarEstadoMesasPorTurno() {
		LocalDateTime turnoActual = DTOReserva.obtenerTurno();
		String subConsulta = "SELECT idMesa FROM MesaCamareroReserva WHERE turno='" + turnoActual + "'";
		ResultSet rs = agente.Read(subConsulta);
		try {
			while (rs.next()) {
				String consulta = "UPDATE Mesa SET estado='reservada' WHERE estado='libre' "
						+ "AND idMesa=" + rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}