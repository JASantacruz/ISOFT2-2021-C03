//package Persistencia;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.LinkedList;
//
//
//public class BebidaDAO {
//	static Connection con;
//	public BebidaDAO(){
//		try {
//			this.con=Agente.conexion();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//	public static int Update(String consulta) {
//		int res=0;
//		try {
//			PreparedStatement stmt = con.prepareStatement(consulta);
//			res = stmt.executeUpdate();
//			stmt.close();
//
//		}catch(SQLException ex) {
//			System.out.println();
//		}
//		return res;
//	}
//
//
//	public static int Delete(String consulta) throws SQLException{
//		int res=0;
//		try {
//			PreparedStatement stmt = con.prepareStatement(consulta);
//			res = stmt.executeUpdate();
//			stmt.close();
//		}catch(SQLException ex) {
//			System.out.println();
//		}
//		return res;
//	}
//
//
//	public static int Insert(String consulta) throws SQLException{
//		int res=0;
//		try {
//			PreparedStatement stmt;
//			stmt = con.prepareStatement(consulta);
//			res = stmt.executeUpdate();
//			stmt.close();
//		}catch(SQLException ex) {
//			System.out.println();
//		}
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
//}
