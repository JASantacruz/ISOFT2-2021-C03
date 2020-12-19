package Presentacion;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

import Dominio.Mesa;

public class IU_ReservarMesaTest {

	@Test
	public void test() {
		LinkedList<Mesa> lista=new LinkedList<Mesa>();
		IU_ReservarMesa iuRM=new IU_ReservarMesa(lista);
	}

}
