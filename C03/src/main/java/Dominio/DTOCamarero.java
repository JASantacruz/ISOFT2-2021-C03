package Dominio;

<<<<<<< HEAD
import java.sql.*;
import java.util.*;

import Persistencia.CamareroDAO;

@SuppressWarnings("static-access")
public class DTOCamarero {

	static CamareroDAO camDAO = new CamareroDAO();
	
	public static void leerCamareros(LinkedList<Camarero> lista) {
		String consulta = "SELECT * FROM Camarero";
		camDAO.Read(consulta, lista);
=======
import java.awt.Color;
import java.sql.*;
import java.util.*;

import Persistencia.Agente;
import Persistencia.CamareroDAO;
import Presentacion.IU_Principal;


public class DTOCamarero {

	public static void leerCamareros(Connection con, LinkedList<Camarero> lista) {
		String consulta = "SELECT * FROM Camarero";
		CamareroDAO.Read(consulta, con, lista);
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
>>>>>>> main
	}

	public static void leerCamarero(int id, Connection con, LinkedList<Camarero> lista) {
		String consulta = "SELECT * FROM Camarero WHERE idCamarero= "+id+";";
<<<<<<< HEAD
		camDAO.Read(consulta, lista);
=======
		CamareroDAO.Read(consulta, con, lista);
>>>>>>> main
	}
}
