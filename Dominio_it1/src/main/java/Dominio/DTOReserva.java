package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
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

	public static void leerReservas(LinkedList<Reserva> lista) {
		ResultSet rs=agente.Read("SELECT * FROM Reserva");
		try {
			while(rs.next()){
				Reserva reserva = new Reserva(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs.getString(6), rs.getString(7));
				lista.add(reserva);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int anadirReserva(String comensales, String estado, LocalDateTime fecha, String mesa, String turno, String nombre) {
		int res=0;	
		try {
			res=agente.Insert("INSERT INTO Reserva (`num_comensales`, `estado`, `fecha`, `idMesa`, `turno`, `nombre`) VALUES ("+comensales+", 'Reservado', '"+fecha+"', "+mesa+", 'Comida', '"+nombre+"');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		LinkedList<Reserva>listaAux=new LinkedList<Reserva>();
		leerReservas(listaAux);
		final int id=listaAux.get(listaAux.size()-1).getId();
		TimerTask timerTask = new TimerTask()
		{
			public void run() 
			{
				DTOReserva reserDTO=new DTOReserva();
				//DTOMesa.cambiarEstado(id, "libre");
				this.cancel();
			}
		};

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(timerTask, 50000, 0);
		return res;

	}

	public static int borrarReserva(String id) {
		int result=0;	
		try {
			result=agente.Delete("DELETE FROM Reserva WHERE idReserva = "+id+";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	public static void leerReservasAux(int id, LocalDateTime turno, LinkedList<Reserva> listaAux) {
		ResultSet rs=agente.Read("SELECT * FROM Reserva WHERE IdMesa = "+id+" AND turno = '"+turno+"';");
		try {
			while(rs.next()){
				Reserva reserva = new Reserva(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs.getString(6), rs.getString(7));
				listaAux.add(reserva);
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
			subConsulta="(SELECT idReserva FROM MesaCamareroReserva WHERE idMesa="+mesa+ " AND turno='"+turno+"')";
			rs=agente.Read(subConsulta);
			try {
				if(rs.next())
					idReserva=rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			consulta="UPDATE Reserva SET tiempoOcupada = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "pidiendo":
			subConsulta="(SELECT idReserva FROM MesaCamareroReserva WHERE idMesa="+mesa+ " AND turno='"+turno+"')";
			rs=agente.Read(subConsulta);
			try {
				if(rs.next())
					idReserva=rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			consulta="UPDATE Reserva SET tiempoPidiendo = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "en espera de comida":
			subConsulta="(SELECT idReserva FROM MesaCamareroReserva WHERE idMesa="+mesa+ " AND turno='"+turno+"')";
			rs=agente.Read(subConsulta);
			try {
				if(rs.next())
					idReserva=rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			consulta="UPDATE Reserva SET tiempoEnEsperaComida = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "servidos":
			subConsulta="(SELECT idReserva FROM MesaCamareroReserva WHERE idMesa="+mesa+ " AND turno='"+turno+"')";
			rs=agente.Read(subConsulta);
			try {
				if(rs.next())
					idReserva=rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			consulta="UPDATE Reserva SET tiempoServido = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "esperando la cuenta":
			subConsulta="(SELECT idReserva FROM MesaCamareroReserva WHERE idMesa="+mesa+ " AND turno='"+turno+"')";
			rs=agente.Read(subConsulta);
			try {
				if(rs.next())
					idReserva=rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			consulta="UPDATE Reserva SET tiempoEsperandoCuenta = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "pagando":
			subConsulta="(SELECT idReserva FROM MesaCamareroReserva WHERE idMesa="+mesa+ " AND turno='"+turno+"')";
			rs=agente.Read(subConsulta);
			try {
				if(rs.next())
					idReserva=rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			consulta="UPDATE Reserva SET tiempoPagando = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		case "en preparacion":
			subConsulta="(SELECT idReserva FROM MesaCamareroReserva WHERE idMesa="+mesa+" AND turno='"+turno+"')";
			rs=agente.Read(subConsulta);
			try {
				if(rs.next())
					idReserva=rs.getInt(1);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			consulta="UPDATE Reserva SET tiempoEnPreparacion = '"+tiempoActual+"' WHERE "
					+ "idReserva="+idReserva;
			break;
		}
		System.out.println(consulta);
		return agente.Update(consulta);
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
		ResultSet rs=agente.Read("SELECT * FROM Reserva WHERE IdReserva = "+id+";");
		try {
			while(rs.next()){
				Reserva reserva = new Reserva(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs.getString(6), rs.getString(7));
				listaAux.add(reserva);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
