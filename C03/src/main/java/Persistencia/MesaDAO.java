package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import Dominio.Mesa;

public class MesaDAO {

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

	public static void Read(String consulta, Connection con, LinkedList<Mesa> lista) {
		try {
			PreparedStatement ps = con.prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Mesa mesa = new Mesa(rs.getInt(1), 0, rs.getString(2), 0);
				lista.add(mesa);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
}
