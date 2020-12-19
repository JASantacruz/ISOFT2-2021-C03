package Dominio;

import static org.junit.Assert.*;
import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.*;
import Persistencia.Agente;

public class DTOBebidaTest {

	static Agente ag = new Agente();
	static DTOBebida dtoBeb;
	
	
	@BeforeClass
	public static void beforeClass(){
		dtoBeb = new DTOBebida();
		try {
			ag.Insert("INSERT INTO Bebida (nombre, codigo, stock) VALUES ('test1', 7, 6);");
			ag.Insert("INSERT INTO Bebida (nombre, codigo, stock) VALUES ('test2', 8, 8);");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLeerBebidas() {
		LinkedList<Bebida> beb = new LinkedList<Bebida>();
		dtoBeb.leerBebidas(beb);
		assertNotEquals(beb.size(), 0);
	}
	
	@Test
	public void testLeerNombreBebidas() {
		LinkedList<String> beb = new LinkedList<String>();
		dtoBeb.leerNombreBebidas(beb);
		assertNotEquals(beb.size(), 0);
	}
	
	@Test
	public void testLeerStock() {
		Bebida bebida = new Bebida(7,"test1", 6);
		int stock = dtoBeb.leerStockBebida(bebida);
		assertEquals(stock, bebida.getStock());
	}
	
	@AfterClass
	public static void afterClass() throws SQLException {
		dtoBeb = new DTOBebida();
		ag.Delete("DELETE FROM Bebida WHERE nombre='test1';");
		ag.Delete("DELETE FROM Bebida WHERE nombre='test2';");
	}

}
