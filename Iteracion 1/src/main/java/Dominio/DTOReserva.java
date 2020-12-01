package Dominio;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import Persistencia.ReservaDAO;

@SuppressWarnings("static-access")
public class DTOReserva {

	public static ReservaDAO reserDAO = new ReservaDAO();
	
	
	public static void leerReservas(LinkedList<Reserva> lista) {
		reserDAO.Read("SELECT * FROM Reserva", lista);
	}

	public static int anadirReserva(String comensales, String estado, LocalDateTime fecha, String mesa, String turno, String nombre) {
		int res=0;	
		res=reserDAO.Create("INSERT INTO Reserva (`num_comensales`, `estado`, `fecha`, `idMesa`, `turno`, `nombre`) VALUES ("+comensales+", 'Reservado', '"+fecha+"', "+mesa+", 'Comida', '"+nombre+"');");
		LinkedList<Reserva>listaAux=new LinkedList<Reserva>();
		leerReservas(listaAux);
		final int id=listaAux.get(listaAux.size()-1).getId();
		TimerTask timerTask = new TimerTask()
	     {
	         public void run() 
	         {
	             DTOReserva reserDTO=new DTOReserva();
	             reserDTO.cambiarEstado(id, "libre");
	             this.cancel();
	         }
	     };

	     Timer timer = new Timer();
	     timer.scheduleAtFixedRate(timerTask, 1200000, 1000);
		return res;
				
	}

	public static int borrarReserva(String id) {
			return reserDAO.Delete("DELETE FROM Reserva WHERE idReserva = "+id+";");
	}
	public static void leerReservasAux(int id, LocalDateTime fecha, LinkedList<Reserva> listaAux) {
		reserDAO.Read("SELECT * FROM Reserva WHERE IdMesa = "+id+" AND Fecha = '"+fecha+"';", listaAux );		
	}
	
	public static int cambiarEstado(int id, String estado) {
		return reserDAO.Update("UPDATE Reserva SET estado = '"+estado+"' WHERE (`idReserva` = '"+id+"');");
	}
	
	public static void leerReserva(int id, LinkedList<Reserva> listaAux) {
		reserDAO.Read("SELECT * FROM Reserva WHERE IdReserva = "+id+";", listaAux);
	}
}
