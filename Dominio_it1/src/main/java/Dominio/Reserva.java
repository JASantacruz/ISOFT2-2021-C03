package Dominio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Reserva implements Estado{

	private int id;
	private int numComensales;
	private String nombre;
	
	public Reserva(final int id, int numComensales,String nombre ) {
		this.setId(id);
		this.setNumComensales(numComensales);
		this.setNombre(nombre);

	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public int getNumComensales() {
		return numComensales;
	}
	
	public void setNumComensales(int numComensales) {
		this.numComensales = numComensales;
	}
}
