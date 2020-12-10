package Persistencia;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.Assert;

import Dominio.Mesa;
import Persistencia.MesaDAO;

public class MesaDAOTest {

	static MesaDAO mesaDAO;
	public ExpectedException expectedException = ExpectedException.none();//no sabemos si se pone rule antes
	@BeforeClass
	public static void setUpBeforeClass() {
		mesaDAO=new MesaDAO();
		mesaDAO.Create("INSERT INTO Mesa (idMesa, idCamarero) VALUES (100,1)");
		mesaDAO.Create("INSERT INTO Mesa (idMesa, idCamarero) VALUES (99,1)");
		mesaDAO.Create("INSERT INTO Mesa (idMesa, idCamarero) VALUES (97,1)");
	}
	@Test
	public void testCreateException() {
		//insertar una mesa que ya existe
		expectedException.expect(SQLException.class);
		mesaDAO.Create("INSERT INTO Mesa (idMesa, idCamarero) VALUES (100,1)");
	}
	@Test
	public void testUpdate() {
		int actual=mesaDAO.Update("UPDATE Mesa SET idMesa=98 WHERE idMesa=100");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testUpdateException() {
		//actualizar un idCamarero cuando idCamarero no existe
		expectedException.expect(SQLException.class);
		mesaDAO.Update("UPDATE Mesa SET idCamarero=98 WHERE idMesa=97");
	}
	@Test
	public void testDelete() {
		int actual=mesaDAO.Delete("DELETE From Mesa WHERE idMesa=99");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testDeleteException() {
		//borrar una mesa cuando el idMesa con formato erroneo
		expectedException.expect(SQLException.class);
		mesaDAO.Delete("DELETE From Mesa WHERE idMesa=fail");
	}
	@Test
	public void testRead() {
		LinkedList<Mesa>listaAux=new LinkedList<Mesa>();
		mesaDAO.Read("SELECT * FROM Mesa",listaAux);
		int actual=listaAux.size();
		int expected=0;
		assertNotEquals(expected, actual);
	}
	@Test
	public void testReadException() {
		//seleccionar una columna que no existe
		LinkedList<Mesa>listaAux=new LinkedList<Mesa>();
		expectedException.expect(SQLException.class);
		mesaDAO.Read("SELECT fail From Mesa",listaAux);
	}
	@AfterClass
	public static void tearDownAfterClass() {
		mesaDAO.Delete("DELETE From Mesa WHERE idMesa=98");
		mesaDAO.Delete("DELETE From Mesa WHERE idMesa=97");
	}
}
