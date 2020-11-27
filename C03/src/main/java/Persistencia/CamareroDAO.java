package Persistencia;

import java.util.*;
import java.sql.*;
import Dominio.*;

<<<<<<< HEAD
@SuppressWarnings("static-access")
public class CamareroDAO {
	
	
	private static Connection con;
	
	public CamareroDAO() {
		try {
			this.con = Agente.conexion();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
=======
public class CamareroDAO {
>>>>>>> main

	public static int Update(String consulta, Connection con) throws SQLException{
		PreparedStatement stmt = con.prepareStatement(consulta);
		int res = stmt.executeUpdate();
		stmt.close();
		return res;
	}


<<<<<<< HEAD
	public static int Delete(String consulta){
		PreparedStatement stmt;
		int res = 0;
		try {
			stmt = con.prepareStatement(consulta);
			res = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
=======
	public static int Delete(String consulta, Connection con) throws SQLException{
		PreparedStatement stmt = con.prepareStatement(consulta);
		int res = stmt.executeUpdate();
		stmt.close();
>>>>>>> main
		return res;
	}


<<<<<<< HEAD
	public static int Create(String consulta){
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

	public static void Read(String consulta,LinkedList<Camarero> lista) {
=======
	public static int Insert(String consulta, Connection con) throws SQLException{
		PreparedStatement stmt;
		stmt = con.prepareStatement(consulta);
		int res = stmt.executeUpdate();
		stmt.close();
		return res;
	}

	public static void Read(String consulta, Connection con, LinkedList<Camarero> lista) {
>>>>>>> main
		try {
			PreparedStatement ps = con.prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Camarero camarero = new Camarero(rs.getInt(1), rs.getString(2));
				lista.add(camarero);
			}
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}
}