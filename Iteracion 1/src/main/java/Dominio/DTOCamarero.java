package Dominio;

import java.sql.*;
import java.util.*;

import Persistencia.CamareroDAO;

@SuppressWarnings("static-access")
public class DTOCamarero {

	static CamareroDAO camDAO = new CamareroDAO();
	
	public static void leerCamareros(LinkedList<Camarero> lista) {
		String consulta = "SELECT * FROM Camarero";
		camDAO.Read(consulta, lista);
	}

	public static void leerCamarero(int id, LinkedList<Camarero> lista) {
		String consulta = "SELECT * FROM Camarero WHERE idCamarero= "+id+";";
		camDAO.Read(consulta, lista);
	}
}
