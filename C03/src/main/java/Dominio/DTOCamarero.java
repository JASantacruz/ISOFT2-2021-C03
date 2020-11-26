package Dominio;

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
	}

	public static void leerCamarero(int id, Connection con, LinkedList<Camarero> lista) {
		String consulta = "SELECT * FROM Camarero WHERE idCamarero= "+id+";";
		CamareroDAO.Read(consulta, con, lista);
	}
}
