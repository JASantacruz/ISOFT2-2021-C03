package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import Persistencia.Agente;

@SuppressWarnings("static-access")
public class DTOMesa {

	public static Agente agente = new Agente();

	public static void leerMesas(LinkedList<Mesa> lista) {
		ResultSet rs;
		rs=agente.Read("SELECT * FROM Mesa");
		try {
			while(rs.next()){
				Mesa mesa = new Mesa(rs.getInt(1), rs.getString(2));
				lista.add(mesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerMesasOcupadas(LinkedList<Mesa> lista) {
		ResultSet rs;
		rs=agente.Read("SELECT * FROM Mesa WHERE estado='ocupada'");
		try {
			while(rs.next()){
				Mesa mesa = new Mesa(rs.getInt(1), rs.getString(2));
				
				lista.add(mesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerMesasPorCamarero(LinkedList<Mesa> lista, String camarero) {
		ResultSet rs;
		LocalDateTime turnoActual= DTOReserva.obtenerTurno();
		int id = 0;
		//String subConsultaIdCamarero="(SELECT idCamarero FROM Camarero WHERE nombre='"+camarero+"')";
		rs = agente.Read("SELECT idCamarero FROM Camarero WHERE nombre='"+camarero+"';");
		try {
			while(rs.next())
				id = rs.getInt(1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		rs=agente.Read("SELECT idMesa FROM MesaCamareroReserva WHERE turno='"+turnoActual+"' AND idCamarero="+id+";");
		try {
			while(rs.next()){
				Mesa mesa = new Mesa(rs.getInt(1), "");
				lista.add(mesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int asignarCamarero(Camarero camarero, String idReserva) {
		return agente.Update("UPDATE MesaCamareroReserva SET idCamarero"
				+ "="+camarero.getIdCamarero()+" WHERE idReserva="+idReserva);
	}
	public static int cambiarMesaOcupada(String idReserva) {
		ResultSet resultIdMesa=agente.Read("SELECT idMesa FROM MesaCamareroReserva WHERE idReserva="+idReserva);
		int idMesa, resultado=0;
		try {
			if(resultIdMesa.next()) {
				idMesa=resultIdMesa.getInt(1);
				resultado=cambiarEstado(idMesa+"", "ocupada");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	public static void leerMesa(int id, LinkedList<Mesa> lista) {
		ResultSet rs;
		rs=agente.Read("SELECT * FROM Mesa WHERE IdMesa= "+id+";");
		try {
			while(rs.next()){
				Mesa mesa = new Mesa(rs.getInt(1), rs.getString(2));
				lista.add(mesa);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static String consultarEstadoMesa(String id) {
		ResultSet rs;
		String estado="";
		rs=agente.Read("SELECT estado FROM Mesa WHERE idMesa="+id);
		try {
				if(rs.next()) {
					estado=rs.getString(1);
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return estado;
	}
	public static int cambiarEstado(String id, String estado) {
		return agente.Update("UPDATE Mesa SET estado = '"+estado+"' WHERE idMesa = "+id);
	}
	public static String secuenciarEstado(String estadoActual) {
		String estado="";
		switch(estadoActual) {
		case "libre":
			estado="reservada";
			break;
		case "reservada":
			estado="ocupada";
			break;
		case "ocupada":
			estado="pidiendo";
			break;
		case "pidiendo":
			estado="en espera de comida";
			break;
		case "en espera de comida":
			estado="servidos";
			break;
		case "servidos":
			estado="esperando la cuenta";
			break;
		case "esperando la cuenta":
			estado="pagando";
			break;
		case "pagando":
			estado="en preparacion";
			break;
		case "en preparacion":
			estado="libre";
			break;
		}
		return estado;
	}
	public static boolean estadoCorrecto(int idMesa) {
		boolean correcto=false;
		ResultSet rs=agente.Read("SELECT estado FROM Mesa WHERE idMesa="+idMesa);
		try {
			if(rs.next()) {
				if(rs.getString(1).equals("pidiendo")) {
					correcto=true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return correcto;
	}
	public static void actualizarEstadoMesasPorTurno() {
		LocalDateTime turnoActual=DTOReserva.obtenerTurno();
		System.out.println(turnoActual);
		String subConsulta="SELECT idMesa FROM MesaCamareroReserva WHERE turno='"+turnoActual+"'";
		ResultSet rs=agente.Read(subConsulta);
		try {
			while(rs.next()) {
				String consulta="UPDATE Mesa SET estado='reservada' WHERE estado='libre' "
						+ "AND idMesa="+rs.getInt(1);
				System.out.println(agente.Update(consulta));
				System.out.println(consulta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}