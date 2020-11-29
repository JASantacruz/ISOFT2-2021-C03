package Dominio;

<<<<<<< HEAD
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
=======
import java.awt.Color;
import java.sql.*;
import java.util.LinkedList;

import Persistencia.*;
import Presentacion.IU_Principal;

public class DTOMesa {

	public ReservaDAO reserDAO = new ReservaDAO();

	public static void CambiarEstadoMesa(Mesa mesa, String estado, Connection con) {
		try {
			ReservaDAO.Update("UPDATE Mesa SET estado = '"+estado+"' WHERE (idMesa = '"+mesa.getId()+"');", con);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void leerMesas(Connection con, LinkedList<Mesa> lista) {
		MesaDAO.Read("SELECT * FROM Mesa", con, lista);
	}

	public static void ReservarMesa(Mesa mesa, Connection con) {
		try {
			ReservaDAO.Update("UPDATE Mesa SET estado = 'Reservado' WHERE (idMesa = '"+mesa.getId()+"');", con);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void CancelarReserva(Mesa mesa, Connection con) {
		try {
			ReservaDAO.Update("UPDATE Mesa SET estado = 'Libre' WHERE (idMesa = '"+mesa.getId()+"');", con);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public static void leerMesasLibres(Connection con, LinkedList<Mesa> listaMesaLibre) {
		MesaDAO.Read("SELECT * FROM Mesa WHERE estado='Libre';", con, listaMesaLibre);
	}
	
	public static void asignarCamarero(Camarero camarero, Mesa mesa, Connection con) {
		try {
			MesaDAO.Update("UPDATE Mesa SET idCamarero = '"+camarero.getIdCamarero()+"' WHERE (`idMesa` = '"+mesa.getId()+"');", con);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void leerMesa(Connection con, LinkedList<Mesa> lista, String id) {
		MesaDAO.Read("SELECT * FROM Mesa WHERE IdMesa= "+id+";", con, lista);
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