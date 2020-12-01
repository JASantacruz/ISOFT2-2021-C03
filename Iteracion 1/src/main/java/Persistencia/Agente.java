package Persistencia;


import java.sql.*;

public class Agente {

	public static Connection conexion() throws SQLException{
		Connection con=null;

		String url="jdbc:mysql://172.20.48.70:3306/C03dbservice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user="C03";
		String password="@ISoft2.2020#";

		con=DriverManager.getConnection(url, user, password);
		return con;
	}


}