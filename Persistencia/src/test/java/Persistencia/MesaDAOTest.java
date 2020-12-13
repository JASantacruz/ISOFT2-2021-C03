package Persistencia;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class MesaDAOTest {

	static MesaDAO mesaDAO;
	public ExpectedException expectedException = ExpectedException.none();//no sabemos si se pone rule antes
	@BeforeClass
	public static void Before() {
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
		ResultSet rs=mesaDAO.Read("SELECT * FROM Mesa");
		boolean expected=true;
		try {
			boolean actual = rs.next();
			assertEquals(expected, actual);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testReadException() {
		//seleccionar una columna que no existe
		expectedException.expect(SQLException.class);
		mesaDAO.Read("SELECT fail From Mesa");
	}
	@AfterClass
	public static void After() {
		mesaDAO.Delete("DELETE From Mesa WHERE idMesa=98");
		mesaDAO.Delete("DELETE From Mesa WHERE idMesa=97");
	}
}
