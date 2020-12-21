package Dominio;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class DTORecetaTest {

	@Test
	public void test() {
		String nombre = "Helado";
		LinkedList<Receta> lista = new LinkedList<>();
		assertEquals(1, DTOReceta.leerReceta(nombre, lista));
	}

}
