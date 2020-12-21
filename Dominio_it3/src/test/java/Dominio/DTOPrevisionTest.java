package Dominio;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Locale;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Persistencia.Agente;

public class DTOPrevisionTest {
	
	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy:MM:dd HH:mm").localizedBy(new Locale("es-ES"));
	static DTOPrevision dtoPrev;
	static Agente agente;
	static LocalDateTime pruebaTime;
	public ExpectedException expectedException = ExpectedException.none();
	
	@BeforeClass
	public static void SetUpBeforeClass() {
		pruebaTime= LocalDateTime.parse("2020:12:15 14:30", df);
		dtoPrev=new DTOPrevision();
		try {
			agente.Insert("INSERT INTO Ingrediente (nombre, stock) VALUES('test',5)");
			agente.Insert("INSERT INTO Ingrediente (nombre, stock) VALUES('test2',5)");
			agente.Insert("INSERT INTO Ingrediente (nombre, stock) VALUES('test3',5)");
			agente.Insert("INSERT INTO Prevision (ingrediente, cantidad, fecha) VALUES('test3'"
					+ ",5, '2018-12-12')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testLeerIngredientes() {
		LinkedList<Ingrediente>listaAux=new LinkedList<Ingrediente>();
		dtoPrev.leerIngredientes(listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	
	@Test
	public void testLeerStock() {
		int actual=dtoPrev.leerStock("test");
		int expected=5;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLeerStockException() {
		expectedException.expect(SQLException.class);
		dtoPrev.leerStock("fail");
	}
	
	@Test
	public void testActualizarStock() {
		int actual=dtoPrev.actualizarStock("test2",10);
		int expected=1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testActualizarStockException() {
		expectedException.expect(SQLException.class);
		dtoPrev.actualizarStock("fail",10);
	}
	
	@Test
	public void testHacerPrevision() {
		int actual=dtoPrev.hacerPrevision("test", 10, pruebaTime);
		int expected=1;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testComprobarFecha() {
		LinkedList<Prevision>listaAux=new LinkedList<Prevision>();
		dtoPrev.comprobarFecha(listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	
	@AfterClass
	public static void TearDownAfterClass() {
		try {
			agente.Delete("DELETE FROM Ingrediente WHERE nombre='test'");
			agente.Delete("DELETE FROM Ingrediente WHERE nombre='test2'");
			agente.Delete("DELETE FROM Ingrediente WHERE nombre='test3'");
			agente.Delete("DELETE FROM Prevision WHERE ingrediente='test' AND "
					+ "fecha='"+pruebaTime+"'");
			agente.Delete("DELETE FROM Prevision WHERE fecha='2020:12:15'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
