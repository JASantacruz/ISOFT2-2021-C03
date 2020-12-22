package Dominio;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import org.junit.*;

import Persistencia.Agente;

public class DTOComandaTest {

	
	static Agente ag = new Agente();
	DTOComanda dtoCom;
	
	@BeforeClass
	public static void beforeClass() throws SQLException {
		ag.Insert("INSERT INTO Camarero (idCamarero, nombre) VALUES (100,'prueba')");
		ag.Insert("INSERT INTO Mesa (idMesa, estado) VALUES (900,'prueba')");
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
		String ce = "Calabacin";
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
		assertEquals(1, dtoCom.guardarComanda(ig.get(0).getId()+"", comanda));
	}
	
	@Test
	public void testActualizarStock() throws SQLException {
		LinkedList<String> lista = new LinkedList<>();
		lista.add("Fanta");
		assertEquals(1, DTOComanda.actualizarStock(lista));
	}
	
	@Test
	public void testLeerAlimentosComanda() throws SQLException {
		LinkedList<String> lista = new LinkedList<>();
		lista.add("Fanta");
		assertEquals(1, DTOComanda.leerAlimentosDeComandas(lista));
	}
	
	@Test
	public void testSubirAvisoCocina() {
		int actual=DTOComanda.subirAvisoCocina("900", "prueba");
		int expected=1;
		assertEquals(expected, actual);
	}
	@Test
	public void testSubirAvisoCamareroBarra() {
		int actual=DTOComanda.subirAvisoCamareroBarra("900", "prueba");
		int expected=1;
		assertEquals(expected, actual);
	}
	
	@AfterClass
	public static void afterClass() throws SQLException{
		LocalDateTime turno= DTOReserva.obtenerTurno();
		Mesa me;
		LinkedList<Mesa> ig = new LinkedList<>();
		ResultSet rs = ag.Read("SELECT * FROM Mesa");
		while(rs.next()) {
			me = new Mesa(rs.getInt(1), "Libre");
			ig.add(me);
		}
		ag.Delete("DELETE FROM Comanda WHERE idMesa="+ig.get(0).getId()+" AND turno='"+turno+"'");
		ag.Delete("DELETE FROM Comanda WHERE idComanda=100;");
		ag.Delete("DELETE FROM Pedido WHERE comanda=100;");
		ag.Delete("DELETE FROM Mesa WHERE idMesa=900");
		ag.Delete("DELETE FROM Camarero WHERE idCamarero=100");
	}
}
