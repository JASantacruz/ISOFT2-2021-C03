package Dominio;

import java.util.LinkedList;

import Persistencia.*;

@SuppressWarnings("static-access")
public class DTOMesa {

	public static ReservaDAO reserDAO = new ReservaDAO();
	public static MesaDAO mesaDAO = new MesaDAO();

	static void CambiarEstadoMesa(Mesa mesa, String estado) {
		reserDAO.Update("UPDATE Mesa SET estado = '"+estado+"' WHERE (idMesa = '"+mesa.getId()+"');");
	}

	public static void leerMesas(LinkedList<Mesa> lista) {
		mesaDAO.Read("SELECT * FROM Mesa", lista);
	}

	public static void ReservarMesa(Mesa mesa) {
		reserDAO.Update("UPDATE Mesa SET estado = 'Reservada' WHERE (idMesa = '"+mesa.getId()+"');");
	}

	public static void CancelarReserva(Mesa mesa) {
		reserDAO.Update("UPDATE Mesa SET estado = 'Libre' WHERE (idMesa = '"+mesa.getId()+"');");
	}


	public static void leerMesasLibres(LinkedList<Mesa> listaMesaLibre) {

		mesaDAO.Read("SELECT * FROM Mesa WHERE estado='Libre';", listaMesaLibre);
	}

	public static void asignarCamarero(Camarero camarero, int mesa) {
		mesaDAO.Update("UPDATE Mesa SET idCamarero = '"+camarero.getIdCamarero()+"' WHERE (`idMesa` = '"+mesa+"');");
	}

	public static void leerMesa(int id, LinkedList<Mesa> lista) {
		mesaDAO.Read("SELECT * FROM Mesa WHERE IdMesa= "+id+";", lista);
	}
}