package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Dominio.Mesa;

<<<<<<< HEAD

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
=======
public class MesaDAO {

	public static int Update(String consulta, Connection con) throws SQLException{
		PreparedStatement stmt = con.prepareStatement(consulta);
		int res = stmt.executeUpdate();
		stmt.close();
>>>>>>> main
		return res;
	}


<<<<<<< HEAD
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
=======
	public static int Delete(String consulta, Connection con) throws SQLException{
		PreparedStatement stmt = con.prepareStatement(consulta);
		int res = stmt.executeUpdate();
		stmt.close();
>>>>>>> main
		return res;
	}


<<<<<<< HEAD
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
=======
	public static int Insert(String consulta, Connection con) throws SQLException{
		PreparedStatement stmt;
		stmt = con.prepareStatement(consulta);
		int res = stmt.executeUpdate();
		stmt.close();
		return res;
	}

	public static void Read(String consulta, Connection con, LinkedList<Mesa> lista) {
>>>>>>> main
		try {
			PreparedStatement ps = con.prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
<<<<<<< HEAD
				Mesa mesa = new Mesa(rs.getInt(1), 0, 0);
=======
				Mesa mesa = new Mesa(rs.getInt(1), 0, rs.getString(2), 0);
>>>>>>> main
				lista.add(mesa);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

<<<<<<< HEAD

=======
	
>>>>>>> main
}
