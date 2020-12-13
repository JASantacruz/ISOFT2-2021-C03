package Persistencia;

import java.sql.*;

public class Agente {

	private static Connection con;
	public Agente() {
		String url="jdbc:mysql://172.20.48.70:3306/C03dbservice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user="C03";
		String password="@ISoft2.2020#";

		try {
			con=DriverManager.getConnection(url, user, password);
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

		}catch(SQLException ex) {
			System.out.println();
		}
		return res;
	}


	public static int Delete(String consulta) throws SQLException{
		int res=0;
		try {
			PreparedStatement stmt = con.prepareStatement(consulta);
			res = stmt.executeUpdate();
			stmt.close();
		}catch(SQLException ex) {
			System.out.println();
		}
		return res;
	}


	public static int Insert(String consulta) throws SQLException{
		int res=0;
		try {
			PreparedStatement stmt;
			stmt = con.prepareStatement(consulta);
			res = stmt.executeUpdate();
			stmt.close();
		}catch(SQLException ex) {
			System.out.println();
		}
		return res;
	}

	public static ResultSet Read(String consulta) {
		ResultSet rs=null;
		try {
			PreparedStatement ps = con.prepareStatement(consulta);
			rs = ps.executeQuery();
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return rs;
	}
}