package Persistencia;

import java.util.*;
import java.sql.*;
import Dominio.*;

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

	public static void Read(String consulta,LinkedList<Camarero> lista) {
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
