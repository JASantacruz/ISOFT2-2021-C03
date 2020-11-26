package Persistencia;

import java.util.*;
import java.sql.*;
import Dominio.*;

public class CamareroDAO {

	public static int Update(String consulta, Connection con) throws SQLException{
		PreparedStatement stmt = con.prepareStatement(consulta);
		int res = stmt.executeUpdate();
		stmt.close();
		return res;
	}


	public static int Delete(String consulta, Connection con) throws SQLException{
		PreparedStatement stmt = con.prepareStatement(consulta);
		int res = stmt.executeUpdate();
		stmt.close();
		return res;
	}


	public static int Insert(String consulta, Connection con) throws SQLException{
		PreparedStatement stmt;
		stmt = con.prepareStatement(consulta);
		int res = stmt.executeUpdate();
		stmt.close();
		return res;
	}

	public static void Read(String consulta, Connection con, LinkedList<Camarero> lista) {
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
