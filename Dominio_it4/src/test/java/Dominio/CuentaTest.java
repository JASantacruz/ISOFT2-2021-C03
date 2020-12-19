package Dominio;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.BeforeClass;
import org.junit.Test;

public class CuentaTest {
	
	static LinkedList<Alimento> entrante;
	static LinkedList<Alimento> primerPlato;
	static LinkedList<Alimento> segundoPlato;
	static LinkedList<Alimento> postre;
	static LinkedList<Bebida> bebida;
	static Alimento testAlimento;
	static Bebida testBebida;
	static Cuenta cuenta;
	
	@BeforeClass
	public static void SetUpBeforeClass() {
		testAlimento=new Alimento(1000,"alimentoTest", "tipoTest",1000);
		testBebida= new Bebida(1000,"bebidaTest",1000);
		entrante=new LinkedList<Alimento>();
		entrante.add(testAlimento);
		primerPlato=new LinkedList<Alimento>();
		primerPlato.add(testAlimento);
		segundoPlato=new LinkedList<Alimento>();
		segundoPlato.add(testAlimento);
		postre=new LinkedList<Alimento>();
		postre.add(testAlimento);
		bebida= new LinkedList<Bebida>();
		bebida.add(testBebida);
		cuenta= new Cuenta(entrante, primerPlato, segundoPlato, postre, bebida);
	}

	@Test
	public void testGetEntrante() {
		String actual=cuenta.getEntrante().get(0).getNombre();
		LinkedList<Alimento> aux=new LinkedList<Alimento>();
		aux.add(testAlimento);
		String expected=aux.get(0).getNombre();
		assertEquals(actual, expected);
	}
	
	@Test
	public void testGetPrimerPlato() {
		String actual=cuenta.getPrimerPlato().get(0).getNombre();
		LinkedList<Alimento> aux=new LinkedList<Alimento>();
		aux.add(testAlimento);
		String expected=aux.get(0).getNombre();
		assertEquals(actual, expected);
	}
	
	@Test
	public void testGetSegundoPlato() {
		String actual=cuenta.getSegundoPlato().get(0).getNombre();
		LinkedList<Alimento> aux=new LinkedList<Alimento>();
		aux.add(testAlimento);
		String expected=aux.get(0).getNombre();
		assertEquals(actual, expected);
	}
	
	@Test
	public void testGetPostre() {
		String actual=cuenta.getPostre().get(0).getNombre();
		LinkedList<Alimento> aux=new LinkedList<Alimento>();
		aux.add(testAlimento);
		String expected=aux.get(0).getNombre();
		assertEquals(actual, expected);
	}
	
	@Test
	public void testGetBebida() {
		String actual=cuenta.getBebida().get(0).getNombre();
		LinkedList<Bebida> aux=new LinkedList<Bebida>();
		aux.add(testBebida);
		String expected=aux.get(0).getNombre();
		assertEquals(actual, expected);
	}
}
