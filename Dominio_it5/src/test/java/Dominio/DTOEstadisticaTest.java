package Dominio;

import static org.junit.Assert.*;

import java.awt.Label;
import java.sql.SQLException;
import java.util.LinkedList;

import org.junit.*;
import Persistencia.Agente;

public class DTOEstadisticaTest {

	static Agente ag = new Agente();
	
	@BeforeClass
	public static void beforeClassTest() throws SQLException {
		String consulta = "INSERT into Reserva (idReserva, num_comensales, nombre, tiempoLibre,"
				+ " tiempoReservada, tiempoOcupada, tiempoPidiendo,"
				+ " tiempoEnEsperaComida, tiempoServido, tiempoEsperandoCuenta,"
				+ " tiempoPagando, tiempoEnPreparacion, restaurante) VALUES (250, 6, 'Carlos',"
				+ " '2020-12-18 15:41:00', '2020-12-17 15:00:00', '2020-12-18 14:30:00', '2020-12-18 14:33:00',"
				+ " '2020-12-18 14:35:00', '2020-12-18 14:45:00', '2020-12-18 15:30:00', '2020-12-18 15:37:00',"
				+ " '2020-12-18 15:40:00', 2)";
		ag.Insert(consulta);
	}
	
	
	@Test
	public void calcularTiempoTomaComandasRestauranteTest() {
		assertNotEquals(0, (int) DTOEstadistica.calcularTiempoTomaComandasRestaurante("6", "2"));
	}
	
	@Test
	public void calcularTiempoTomaComandasCiudadTest() {
		assertNotEquals(0, (int) DTOEstadistica.calcularTiempoTomaComandasCiudad("6", "Ciudad Real"));
	}
	
	@Test
	public void calcularTiempoPreparacionComandasRestauranteTest() {
		assertNotEquals(0, (int) DTOEstadistica.calcularTiempoPreparacionComandasRestaurante("6", "2"));
	}
	
	@Test
	public void calcularTiempoPreparacionComandasCiudadTest() {
		assertNotEquals(0, (int) DTOEstadistica.calcularTiempoPreparacionComandasCiudad("6", "Ciudad Real"));
	}
	
	@Test
	public void calcularTiempoEntregaNotaRestauranteTest() {
		assertNotEquals(0, (int) DTOEstadistica.calcularTiempoEntregaNotaRestaurante("6", "2"));
	}
	
	@Test
	public void calcularTiempoEntregaNotaCiudadTest() {
		assertNotEquals(0, (int) DTOEstadistica.calcularTiempoEntregaNotaCiudad("6", "Ciudad Real"));
	}
	
	@Test
	public void calcularTiempoPreparacionMesaLibreRestauranteTest() {
		assertNotEquals(0, (int) DTOEstadistica.calcularTiempoPreparacionMesaLibreRestaurante("6", "2"));
	}
	
	@Test
	public void calcularTiempoPreparacionMesaLibreCiudadTest() {
		assertNotEquals(0, (int) DTOEstadistica.calcularTiempoPreparacionMesaLibreCiudad("6", "Ciudad Real"));
	}
	
	@Test
	public void leerRestaurantesTest() {
		LinkedList<String> restaurantes = new LinkedList<>();
		DTOEstadistica.leerRestaurantes(restaurantes);
		assertNotEquals(0,restaurantes.size());
	}
	
	@Test
	public void leerCiudadesTest() {
		LinkedList<String> ciudades = new LinkedList<>();
		DTOEstadistica.leerCiudades(ciudades);
		assertNotEquals(0,ciudades.size());
	}
	
	@AfterClass
	public static void AfterClassTest() throws SQLException {
		String consulta = "DELETE FROM Reserva WHERE idReserva= 250 AND Restaurante=2;";
		ag.Delete(consulta);
	}

}
