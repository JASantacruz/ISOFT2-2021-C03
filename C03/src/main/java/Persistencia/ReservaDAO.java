package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import Dominio.Reserva;

public class ReservaDAO {
	
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

	public static void Read(String consulta, Connection con, LinkedList<Reserva> lista) {
		try {
			PreparedStatement ps = con.prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Reserva reserva = new Reserva(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs.getString(6));
				lista.add(reserva);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
