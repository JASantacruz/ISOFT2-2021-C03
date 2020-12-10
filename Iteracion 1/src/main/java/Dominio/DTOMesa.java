package Dominio;

import java.util.LinkedList;

import Persistencia.*;

@SuppressWarnings("static-access")
public class DTOMesa {

	public static MesaDAO mesaDAO = new MesaDAO();

	public static void leerMesas(LinkedList<Mesa> lista) {
		mesaDAO.Read("SELECT * FROM Mesa", lista);
	}

	public static int asignarCamarero(Camarero camarero, int mesa) {
		return mesaDAO.Update("UPDATE Mesa SET idCamarero = '"+camarero.getIdCamarero()+"' WHERE (`idMesa` = '"+mesa+"');");
	}

	public static void leerMesa(int id, LinkedList<Mesa> lista) {
		mesaDAO.Read("SELECT * FROM Mesa WHERE IdMesa= "+id+";", lista);
	}
}