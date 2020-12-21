package Presentacion;

import java.util.LinkedList;

import org.junit.Test;

import Dominio.Prevision;

public class IU_AlmacenTest {

	@Test
	public void test() {
		LinkedList<Prevision> historial=new LinkedList<Prevision>();
		IU_Almacen iuAlmacen= new IU_Almacen(historial);
	}

}
