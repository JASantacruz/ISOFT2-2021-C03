package Persistencia;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Dominio.Camarero;
import Persistencia.CamareroDAO;

public class CamareroDAOTest {

	static CamareroDAO camareroDAO;
	public ExpectedException expectedException = ExpectedException.none();//no sabemos si se pone rule antes
	@BeforeClass
	public static void setUpBeforeClass() {
		camareroDAO=new CamareroDAO();
		camareroDAO.Create("INSERT INTO Camarero (idCamarero, nombre) VALUES (100,'Susana')");
		camareroDAO.Create("INSERT INTO Camarero (idCamarero, nombre) VALUES (99,'Pedro')");
		//camareroDAO.Create("INSERT INTO Camarero (idCamarero, idCamarero) VALUES (97,1)");
	}
	@Test
	public void testCreateException() {
		//insertar una Camarero que ya existe
		expectedException.expect(SQLException.class);
		camareroDAO.Create("INSERT INTO Camarero (idCamarero, nombre) VALUES (100,'Susana')");
	}
	@Test
	public void testUpdate() {
		int actual=camareroDAO.Update("UPDATE Camarero SET nombre='Jesus' WHERE idCamarero=100");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testUpdateException() {
		//actualizar una columna que no existe
		expectedException.expect(SQLException.class);
		camareroDAO.Update("UPDATE Camarero SET fail=98 WHERE idCamarero=97");
	}
	@Test
	public void testDelete() {
		int actual=camareroDAO.Delete("DELETE From Camarero WHERE idCamarero=99");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testDeleteException() {
		//borrar una Camarero cuando el idCamarero con formato erroneo
		expectedException.expect(SQLException.class);
		camareroDAO.Delete("DELETE From Camarero WHERE idCamarero=fail");
	}
	@Test
	public void testRead() {
		LinkedList<Camarero>listaAux=new LinkedList<Camarero>();
		camareroDAO.Read("SELECT * FROM Camarero",listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@Test
	public void testReadException() {
		//seleccionar una columna que no existe
		LinkedList<Camarero>listaAux=new LinkedList<Camarero>();
		expectedException.expect(SQLException.class);
		camareroDAO.Read("SELECT fail From Camarero",listaAux);
	}
	@AfterClass
	public static void tearDownAfterClass() {
		camareroDAO.Delete("DELETE From Camarero WHERE idCamarero=100");
	}
}
