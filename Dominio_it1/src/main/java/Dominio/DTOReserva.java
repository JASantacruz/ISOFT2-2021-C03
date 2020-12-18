package Dominio;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import Persistencia.Agente;


@SuppressWarnings("static-access")
public class DTOReserva implements Turnos{

	public static Agente agente = new Agente();
	private static DateTimeFormatter df2 = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm").localizedBy(new Locale("es-ES"));
	private static ZoneId z= ZoneId.of("Europe/London");

	/**
	 * Lee las reservas que estan reservadas (en la tabla Reserva y MesaCamareroReserva) y cuya mesa tiene estado Reservada
	 * en el turno actual
	 * @param lista
	 */
	public static void leerReservas(LinkedList<Integer> lista) {
		LocalDateTime turnoActual= obtenerTurno();

		String consultaMesasTurnoActual="(SELECT idMesa FROM MesaCamareroReserva WHERE turno='"+turnoActual+"')";
		String consultaMesasReservadasActuales="(SELECT idMesa FROM Mesa WHERE estado='reservada' AND idMesa IN "+consultaMesasTurnoActual+")";
		String consultaReservasMesaReservada="SELECT idReserva FROM MesaCamareroReserva WHERE turno='"+turnoActual+"' AND idMesa IN "+consultaMesasReservadasActuales;
		try {
			ResultSet rs=agente.Read(consultaReservasMesaReservada);
			while(rs.next()){
				lista.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void leerTodasReservas(LinkedList<Integer> lista) {
		LocalDateTime turnoActual= obtenerTurno();
		//reservas distintas del turno actual
		String consulta="(SELECT idReserva FROM MesaCamareroReserva WHERE turno<>'"+turnoActual+"')";
		System.out.println(consulta);
		//String consultaReservasNoActuales="SELECT * FROM Reserva WHERE idReserva="+consulta;
		try {
			ResultSet resultReservas=agente.Read(consulta);
			while(resultReservas.next()) {
				//Reserva reservaAux= new Reserva(resultReservas.getInt(1), resultReservas.getInt(2), resultReservas.getString(3));
				lista.add(resultReservas.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//reservas con mesa reservada del turno actual
		String consultaMesasTurnoActual="(SELECT idMesa FROM MesaCamareroReserva WHERE turno='"+turnoActual+"')";
		String consultaMesasReservadasActuales="(SELECT idMesa FROM Mesa WHERE estado='reservada' AND idMesa IN "+consultaMesasTurnoActual+")";
		String consultaReservasMesaReservada="(SELECT idReserva FROM MesaCamareroReserva WHERE turno='"+turnoActual+"' AND idMesa IN "+consultaMesasReservadasActuales+")";
		//String consultaReservasActuales="SELECT idReserva FROM Reserva WHERE idReserva="+consultaReservasMesaReservada;
		try {
			ResultSet rs=agente.Read(consultaReservasMesaReservada);

			while(rs.next()){
				//Reserva reservaAux= new Reserva(rs.getInt(1), rs.getInt(2), rs.getString(3));
				lista.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static int anadirReserva(String comensales, LocalDateTime fecha, String nombre, String idMesa) {
		int res=0;
		ResultSet resultIdReserva;
		int idReserva;
		try {
			res=agente.Insert("INSERT INTO Reserva (idReserva,num_comensales, nombre, tiempoReservada)"
					+ " VALUES (null,"+comensales+",'"+nombre+"', '"+fecha+"')");
			resultIdReserva=agente.Read("SELECT idReserva FROM Reserva WHERE num_comensales="+comensales+" AND "
					+ "nombre='"+nombre+"' AND tiempoReservada='"+fecha+"'");
			if(resultIdReserva.next()){
				idReserva=resultIdReserva.getInt(1);
				int res2=agente.Insert("INSERT INTO MesaCamareroReserva (idReserva, idMesa, turno, idCamarero) VALUES "
						+ "("+idReserva+", "+idMesa+", '"+fecha+"', null)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;

	}

	public static int borrarReserva(String id) {
		int result=0;
		LocalDateTime turnoActual=obtenerTurno();
		LocalDateTime turno;
		String consultaTurno="SELECT turno FROM MesaCamareroReserva WHERE idReserva="+id;

		try {
			ResultSet rsTurno=agente.Read(consultaTurno);
			if(rsTurno.next()) {
				turno=rsTurno.getTimestamp(1).toLocalDateTime();
				if(turno.equals(turnoActual)) {
					String consultaMesaCancelar="(SELECT idMesa FROM MesaCamareroReserva WHERE idReserva="+id+")";
					String con="UPDATE Mesa SET (estado) VALUES ('libre') WHERE idMesa="+consultaMesaCancelar;
					agente.Update(con);
				}
				agente.Delete("DELETE FROM Reserva WHERE idReserva = "+id+"");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void leerReservasAux(int id, LocalDateTime turno, LinkedList<Integer> listaAux) {
		ResultSet rs=agente.Read("SELECT idReserva FROM MesaCamareroReserva WHERE idMesa = "+id+" AND turno = '"+turno+"'");
		try {
			while(rs.next()){

				//Reserva reserva = new Reserva(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs.getString(6), rs.getString(7));
				listaAux.add(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int cambiarTiempoEstado(String mesa, String estado) {
		String consulta="";
		String subConsulta="";
		LocalDateTime turno=obtenerTurno();
		String tiempoActual=LocalDateTime.now().format(df2);
		ResultSet rs;
		int idReserva=0;
		switch(estado) {
		case "ocupada":
			idReserva=obtenerIdResesrvaPorMesaTurno(mesa, turno);
			consulta="UPDATE Reserva SET tiempoOcupada = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "pidiendo":
			idReserva=obtenerIdResesrvaPorMesaTurno(mesa, turno);
			consulta="UPDATE Reserva SET tiempoPidiendo = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "en espera de comida":
			idReserva=obtenerIdResesrvaPorMesaTurno(mesa, turno);
			consulta="UPDATE Reserva SET tiempoEnEsperaComida = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "servidos":
			idReserva=obtenerIdResesrvaPorMesaTurno(mesa, turno);
			consulta="UPDATE Reserva SET tiempoServido = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "esperando la cuenta":
			idReserva=obtenerIdResesrvaPorMesaTurno(mesa, turno);
			consulta="UPDATE Reserva SET tiempoEsperandoCuenta = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "pagando":
			idReserva=obtenerIdResesrvaPorMesaTurno(mesa, turno);
			consulta="UPDATE Reserva SET tiempoPagando = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "en preparacion":
			idReserva=obtenerIdResesrvaPorMesaTurno(mesa, turno);
			consulta="UPDATE Reserva SET tiempoEnPreparacion = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		}
		System.out.println(consulta);
		return agente.Update(consulta);
	}
	public static int obtenerIdResesrvaPorMesaTurno(String idMesa, LocalDateTime turno) {
		int idReserva=-1;
		String consulta="(SELECT idReserva FROM MesaCamareroReserva WHERE idMesa="+idMesa+ " AND turno='"+turno+"')";
		try {
			ResultSet rs=agente.Read(consulta);
			if(rs.next())
				idReserva=rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return idReserva;
	}
	public static LocalDateTime obtenerTurno() {
		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH:mm").localizedBy(new Locale("es-ES"));
		LocalDateTime turno=null;
		LocalDate fechaActual =LocalDate.now(ZoneId.systemDefault());
		LocalTime horaPrueba=LocalTime.parse("13:30", df2);
		LocalDateTime turno1Comida = LocalDateTime.of(fechaActual, Turno1Comida);
		LocalDateTime turno2Comida = LocalDateTime.of(fechaActual, Turno2Comida);
		LocalDateTime turno3Comida = LocalDateTime.of(fechaActual, Turno3Comida);
		LocalDateTime turno1Cena = LocalDateTime.of(fechaActual, Turno1Cena);
		LocalDateTime turno2Cena = LocalDateTime.of(fechaActual, Turno2Cena);
		LocalDateTime turno3Cena = LocalDateTime.of(fechaActual, Turno3Cena);
		LocalDateTime turnoLimite = LocalDateTime.of(fechaActual, limiteTurno);
		//LocalDateTime tiempoActual = LocalDateTime.now();
		LocalDateTime tiempoActual = LocalDateTime.of(fechaActual, horaPrueba);

		if(tiempoActual.isAfter(turno1Comida) && tiempoActual.isBefore(turno2Comida)) {
			turno=turno1Comida;
		}else if(tiempoActual.isAfter(turno2Comida) && tiempoActual.isBefore(turno3Comida)){
			turno=turno2Comida;
		}else if(tiempoActual.isAfter(turno3Comida) && tiempoActual.isBefore(turno1Cena)) {
			turno=turno3Comida;
		}else if(tiempoActual.isAfter(turno1Cena) && tiempoActual.isBefore(turno2Cena)) {
			turno=turno1Cena;
		}else if(tiempoActual.isAfter(turno2Cena) && tiempoActual.isBefore(turno3Cena)) {
			turno=turno2Cena;
		}else if(tiempoActual.isAfter(turno3Cena) && tiempoActual.isBefore(turnoLimite)) {
			turno=turno3Cena;
		}else if(tiempoActual.isBefore(turno1Comida)) {
			turno=null;
		}else if(tiempoActual.isAfter(turnoLimite)) {
			turno=null;
		}
		return turno;
	}

	public static void leerReserva(int id, LinkedList<Reserva> listaAux) {
		ResultSet rs=agente.Read("SELECT * FROM Reserva WHERE idReserva = "+id+";");
		try {
			while(rs.next()){
				Reserva reserva = new Reserva(rs.getInt(1), rs.getInt(2), rs.getString(3));
				listaAux.add(reserva);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static LocalDateTime obtenerFechaReserva(String idReserva) {
		String consulta="SELECT turno FROM MesaCamareroReserva WHERE idReserva="+idReserva;
		LocalDateTime turno=null;
		try {
			ResultSet resultTurno=agente.Read(consulta);
			if(resultTurno.next()) {
				System.out.println(idReserva);
				turno=resultTurno.getTimestamp(1).toInstant().atZone(z).toLocalDateTime();
				//turno=new DateTime(resultTurno.getTimestamp(1).getTime(), DateTimeZone.UTC);
				System.out.println(turno);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return turno;
	}
	public static void comprobarClienteTardio() {
		DateTimeFormatter df2 = DateTimeFormatter.ofPattern("HH:mm").localizedBy(new Locale("es-ES"));
		LocalDateTime turno=null;
		LocalDate fechaActual =LocalDate.now(ZoneId.systemDefault());
		LocalTime horaActual=LocalTime.now();
		LocalDateTime turnoActual=DTOReserva.obtenerTurno();
		LocalDateTime tiempoActual = LocalDateTime.of(fechaActual, LocalTime.parse(horaActual.format(df2)));
		if(tiempoActual.isAfter(turnoActual.plusMinutes(20))) {
			System.out.println("he entrado");
			ResultSet resultIdMesa;
			String consultaMesasTurnoActual="(SELECT idMesa FROM MesaCamareroReserva WHERE turno='"+turnoActual+"')";
			String consulta="SELECT idMesa FROM Mesa WHERE estado='reservada' AND idMesa IN"+consultaMesasTurnoActual;
			try {
				resultIdMesa=agente.Read(consulta);
				while(resultIdMesa.next()) {
					String consultaIdReservaEliminar="(SELECT idReserva FROM MesaCamareroReserva WHERE "
							+ "idMesa="+resultIdMesa.getInt(1)+" AND turno='"+turnoActual+"')";
					agente.Delete("DELETE FROM Reserva WHERE idReserva="+consultaIdReservaEliminar);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
