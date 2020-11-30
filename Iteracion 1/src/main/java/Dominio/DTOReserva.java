package Dominio;

import java.time.LocalDateTime;
import java.util.LinkedList;

import Persistencia.ReservaDAO;

@SuppressWarnings("static-access")
public class DTOReserva {

	public static ReservaDAO reserDAO = new ReservaDAO();


	public static void leerReservas(LinkedList<Reserva> lista) {
		reserDAO.Read("SELECT * FROM Reserva", lista);
	}

	static int CambiarEstadoMesa(Reserva reserva, String estado) {
		return reserDAO.Update("UPDATE Reserva SET estado = '"+estado+"' WHERE (idReserva = '"+reserva.getId()+"');");
	}

	public static int ReservarMesa(Reserva reserva) {
		return reserDAO.Update("UPDATE Reserva SET estado = 'Reservada' WHERE (idReserva = '"+reserva.getId()+"');");
	}

	public static int CancelarReserva(Reserva reserva) {
		return reserDAO.Update("UPDATE Reserva SET estado = 'Libre' WHERE (idMesa = '"+reserva.getId()+"');");
	}

	/*public static void leerMesasLibres(LinkedList<Reserva> listaMesaLibre) {
		reserDAO.Read("SELECT idMesa, idCamarero FROM Reserva WHERE estado='Libre';", listaMesaLibre);
	}*/

	public static void anadirReserva(String comensales, String estado, LocalDateTime fecha, String mesa, String turno, String nombre, LinkedList<Mesa> lista) {
		reserDAO.Create("INSERT INTO Reserva (`num_comensales`, `estado`, `fecha`, `idMesa`, `turno`, `nombre`) VALUES ("+comensales+", 'Reservado', '"+fecha+"', "+mesa+", 'Comida', '"+nombre+"');");
	}

	public static void borrarReserva(String id) {
		reserDAO.Delete("DELETE FROM Reserva WHERE idReserva = "+id+";");
	}

	public static void leerReservasAux(int id, LocalDateTime fecha, LinkedList<Reserva> listaAux) {
		reserDAO.Read("SELECT * FROM Reserva WHERE IdMesa = "+id+" AND Fecha = '"+fecha+"';", listaAux );		
	}

	public static void cambiarEstado(int id, String estado) {
		reserDAO.Update("UPDATE Reserva SET estado = '"+estado+"' WHERE (`idReserva` = '"+id+"');");
	}

	public static void leerReserva(int id, LinkedList<Reserva> listaAux) {
		reserDAO.Read("SELECT * FROM Reserva WHERE IdReserva = "+id+";", listaAux);
	}
}
