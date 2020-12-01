package Persistencia;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class AgenteTest {

	@Test
	public void test() {
		try {
			Agente.conexion();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
	}

}
