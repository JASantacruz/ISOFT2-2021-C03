package Dominio;

<<<<<<< HEAD
import java.time.LocalDateTime;
import java.util.LinkedList;

import Persistencia.ReservaDAO;

@SuppressWarnings("static-access")
public class DTOReserva {

	public static ReservaDAO reserDAO = new ReservaDAO();
	
	
	public static void leerReservas(LinkedList<Reserva> lista) {
		reserDAO.Read("SELECT * FROM Reserva", lista);
	}


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
=======
import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import Persistencia.Agente;
import Persistencia.ReservaDAO;
import Presentacion.IU_Principal;

public class DTOReserva {


	public static void leerReservas(Connection con, LinkedList<Reserva> lista) {
		ReservaDAO.Read("SELECT * FROM Reserva", con, lista);
	}


	public static void anadirReserva(String comensales, String estado, LocalDateTime fecha, String mesa, String turno, Connection con, LinkedList<Mesa> lista) {
		try {
			ReservaDAO.Insert("INSERT INTO Reserva (`num_comensales`, `estado`, `fecha`, `idMesa`, `turno`) VALUES ("+comensales+", 'Reservado', '"+fecha+"', "+mesa+", 'Comida');", con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void borrarReserva(String id, Connection con) {
		try {
			ReservaDAO.Delete("DELETE FROM Reserva WHERE idReserva = "+id+";", con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void leerReservasAux(int id, LocalDateTime fecha, Connection con, LinkedList<Reserva> listaAux) {
		ReservaDAO.Read("SELECT * FROM Reserva WHERE IdMesa = "+id+" AND Fecha = '"+fecha+"';", con, listaAux );		
	}

	public static Connection getConexion() {
		Connection con = null;
		try {
			IU_Principal.lblConexion.setForeground(Color.green);
			IU_Principal.lblConexion.setText("OK");
			IU_Principal.lblConexion.setVisible(true);
			con = Agente.conexion();
		} catch (SQLException e) {
			e.printStackTrace();
			IU_Principal.lblConexion.setForeground(Color.red);
			IU_Principal.lblConexion.setText("Error");
			IU_Principal.lblConexion.setVisible(true);
		}
		return con;
	}

>>>>>>> main
}
