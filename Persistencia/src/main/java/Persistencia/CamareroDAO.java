package Persistencia;

import java.util.*;
import java.sql.*;

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

	public static int Update(String consulta) {
		int res=0;
		try {
			PreparedStatement stmt = con.prepareStatement(consulta);
			res = stmt.executeUpdate();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}


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
		return res;
	}


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

	public static ResultSet Read(String consulta) {
		ResultSet rs=null;
		try {
			PreparedStatement ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}
}
