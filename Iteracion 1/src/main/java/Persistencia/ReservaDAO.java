package Persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import Dominio.Reserva;

@SuppressWarnings("static-access")
public class ReservaDAO {
	
	private static Connection con;
	
	public ReservaDAO() {
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

	public static void Read(String consulta, LinkedList<Reserva> lista) {
		try {
			PreparedStatement ps = con.prepareStatement(consulta);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Reserva reserva = new Reserva(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getTimestamp(4), rs.getInt(5), rs.getString(6), rs.getString(7));
				lista.add(reserva);
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
