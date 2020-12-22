package Dominio;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.LinkedList;

import Persistencia.Agente;

public class DTOCuenta {
	public static Agente agente = new Agente();

	public static double calcularPrecio(String mesa) {
		double precioTotal=0;
		int cantidad=0;
		LocalDateTime turnoActual=DTOReserva.obtenerTurno();
		String consultaComanda="(SELECT idComanda FROM Comanda WHERE idMesa="+mesa+" AND turno='"+turnoActual+"')";
		String consultaPlatos="SELECT codigo, cantidad FROM Pedido WHERE comanda="+consultaComanda;
		String consultaPlato;
		int plato;
		double precio;
		try {
			ResultSet rsPlatos=agente.Read(consultaPlatos);
			while(rsPlatos.next()) {
				plato=rsPlatos.getInt(1);
				cantidad=rsPlatos.getInt(2);
				consultaPlato="SELECT precio FROM Carta WHERE codigo="+plato;
				ResultSet rsPlato=agente.Read(consultaPlato);
				if(rsPlato.next()) {
					precio=rsPlato.getDouble(1);
					precioTotal+=cantidad*precio;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return precioTotal;
	}
	public static Cuenta devolverCuenta(String mesa){
		LinkedList<Alimento> primerp= new LinkedList<Alimento>();
		LinkedList<Alimento> segundop= new LinkedList<Alimento>();
		LinkedList<Alimento> entrante= new LinkedList<Alimento>();
		LinkedList<Alimento> postre= new LinkedList<Alimento>();
		LinkedList<Bebida> bebida= new LinkedList<Bebida>();
		String consultaComanda="(SELECT idComanda FROM Comanda WHERE idMesa="+mesa+")";
		String consultaPlatos="SELECT codigo, cantidad FROM Pedido WHERE comanda="+consultaComanda;
		String consultaPlato;
		String tipoP,nombre;
		int codigo,precio;
		try {
			ResultSet rsPlatos=agente.Read(consultaPlatos);
			while(rsPlatos.next()) {
				codigo=rsPlatos.getInt(1);
				consultaPlato="SELECT nombre, tipo, precio FROM Carta WHERE codigo="+codigo;
				ResultSet rsPlato=agente.Read(consultaPlato);
				if(rsPlato.next()) {
					nombre=rsPlato.getString(1);
					tipoP=rsPlato.getString(2);
					precio=rsPlato.getInt(3);
					if(tipoP.equals("Primer Plato")) {
						Alimento aux = new Alimento(codigo, nombre, tipoP, precio);
						primerp.add(aux);
					}else if(tipoP.equals("Segundo Plato")) {
						Alimento aux2 = new Alimento(codigo, nombre, tipoP, precio);
						segundop.add(aux2);
					}else if(tipoP.equals("Entrante")) {
						Alimento aux3 = new Alimento(codigo, nombre, tipoP, precio);
						entrante.add(aux3);
					}else if(tipoP.equals("Postre")){
						Alimento aux4 = new Alimento(codigo, nombre, tipoP, precio);
						postre.add(aux4);
					}else {
						Bebida aux5 = new Bebida(codigo, nombre, 0);
						bebida.add(aux5);
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new Cuenta(entrante, primerp, segundop, postre, bebida);
	}
}
