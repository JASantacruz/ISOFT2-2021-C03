package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Dominio.Mesa;


@SuppressWarnings("static-access")
public class MesaDAO {

	private static Connection con;

	public MesaDAO() {
		try {
			this.con = Agente.conexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static int Update(String consulta){

		PreparedStatement stmt;
		int res = 0;
		try {
			stmt = con.prepareStatement(consulta);
			res = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	public static int Delete(String consulta) {
		PreparedStatement stmt;
		int res = 0;
		try {
			stmt = con.prepareStatement(consulta);
			res = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


	public static int Create(String consulta) {
		PreparedStatement stmt;
		int res = 0;
		try {
			stmt = con.prepareStatement(consulta);
			res = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public static void Read(String consulta, LinkedList<Mesa> lista) {
		try {
			PreparedStatement ps = con.prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Mesa mesa = new Mesa(rs.getInt(1), 0);
				lista.add(mesa);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}


}
