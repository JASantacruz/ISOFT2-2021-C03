//package Persistencia;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.LinkedList;
//
//@SuppressWarnings("static-access")
//public class ReservaDAO {
//	
//	private static Connection con;
//	
//	public ReservaDAO() {
//		try {
//			this.con = Agente.conexion();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static int Update(String consulta){
//		PreparedStatement stmt;
//		int res = 0;
//		try {
//			stmt = con.prepareStatement(consulta);
//			res = stmt.executeUpdate();
//			stmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return res;
//	}
//
//
//	public static int Delete(String consulta){
//		PreparedStatement stmt;
//		int res = 0;
//		try {
//			stmt = con.prepareStatement(consulta);
//			res = stmt.executeUpdate();
//			stmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return res;
//	}
//
//
//	public static int Create(String consulta){
//		PreparedStatement stmt;
//		int res = 0;
//		try {
//			stmt = con.prepareStatement(consulta);
//			res = stmt.executeUpdate();
//			stmt.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return res;
//	}
//
//	public static ResultSet Read(String consulta) {
//		ResultSet rs=null;
//		try {
//			PreparedStatement ps = con.prepareStatement(consulta);
//			rs = ps.executeQuery();
//		}catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
//		return rs;
//	}
//
//}
