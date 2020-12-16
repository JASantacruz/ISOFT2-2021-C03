package Dominio;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.*;

import Persistencia.Agente;

public class DTOComandaTest {

	
	static Agente ag = new Agente();
	DTOComanda dtoCom;
	
	@BeforeClass
	public static void beforeClass() throws SQLException {
		ag.Insert("INSERT INTO Comanda (idComanda, idMesa, turno) VALUES (100, 4, '2020-12-16 22:00:00');");
	}
	
	@Test
	public void testComprobarStockBebida() {
		LinkedList<String> lista = new LinkedList<String>();
		lista.add("Nestea");
		dtoCom.comprobarStock(lista);
		assertNotEquals(lista.size(), 0);
	}
	
	@Test
	public void testComprobarStockComida() {
		LinkedList<String> lista = new LinkedList<String>();
		lista.add("Tortilla de patata");
		dtoCom.comprobarStock(lista);
		assertNotEquals(lista.size(), 0);
	}
	
	@Test
	public void testIngredienteComprobado() throws SQLException {
		String ce = "Cebolla";
		Ingrediente in;
		LinkedList<Ingrediente> ig = new LinkedList<>();
		ResultSet rs = ag.Read("SELECT * FROM Ingrediente");
		while(rs.next()) {
			in = new Ingrediente(rs.getString(1), rs.getInt(2), 3);
			ig.add(in);
		}
		assertEquals(true, dtoCom.ingredienteComprobado(ce, ig));
	}

	@Test
	public void testIndiceIngrediente() throws SQLException {
		String ce = "Cebolla";
		Ingrediente in;
		LinkedList<Ingrediente> ig = new LinkedList<>();
		ResultSet rs = ag.Read("SELECT * FROM Ingrediente");
		while(rs.next()) {
			in = new Ingrediente(rs.getString(1), rs.getInt(2), 3);
			ig.add(in);
		}
		assertEquals(0, dtoCom.indiceListaIngrediente(ce, ig));
	}

	@Test
	public void testGuardarComanda() throws SQLException {
		Mesa me;
		LinkedList<String> comanda = new LinkedList<>();
		comanda.add("Coca Cola");
		comanda.add("Helado");
		LinkedList<Mesa> ig = new LinkedList<>();
		ResultSet rs = ag.Read("SELECT * FROM Mesa");
		while(rs.next()) {
			me = new Mesa(rs.getInt(1), "Libre");
			ig.add(me);
		}
		assertEquals(1, dtoCom.guardarComanda(ig.get(0).getId(), comanda));
	}
	
	
	@AfterClass
	public static void afterClass() throws SQLException{
		ag.Delete("DELETE FROM Comanda WHERE idComanda=100;");
	}
}
