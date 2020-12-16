package Dominio;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedList;
import org.junit.*;
import Persistencia.Agente;

@SuppressWarnings("static-access")
public class DTOAlimentoTest {

	static Agente ag = new Agente();
	static DTOAlimento dtoAl;
	
	@BeforeClass
	public static void beforeTest() throws SQLException {
		dtoAl = new DTOAlimento();
		ag.Insert("INSERT INTO Carta (codigo, nombre, tipo) VALUES (100, 'Croqueta', 'Entrante');");
		ag.Insert("INSERT INTO Carta (codigo, nombre, tipo) VALUES (101, 'Jamon', 'Entrante');");
	}
	
	@Test
	public void testLeerAlimentoTipo() {
		LinkedList<Alimento> al = new LinkedList<Alimento>();
		dtoAl.leerAlimentosPorTipo(al, "Entrante");
		assertNotEquals(al.size(), 0);
	}
	
	@Test
	public void testLeerNombreAlimentosPorTipo() {
		LinkedList<String> al = new LinkedList<String>();
		dtoAl.leerNombreAlimentosPorTipo(al, "Entrante");
		assertNotEquals(al.size(), 0);
	}
	
	@AfterClass
	public static void afterTest() throws SQLException {
		dtoAl = new DTOAlimento();
		ag.Delete("DELETE FROM Carta WHERE codigo=100;");
		ag.Delete("DELETE FROM Carta WHERE codigo=101;");
	}
}
