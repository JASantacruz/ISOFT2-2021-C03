package Dominio;

public class Repeticion {

	private String nombre;
	private int numRepeticiones;
	public Repeticion(String nombre, int numRepeticiones) {
		super();
		this.nombre = nombre;
		this.numRepeticiones = numRepeticiones;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getRepeticiones() {
		return numRepeticiones;
	}
	public void setRepeticiones(int repeticiones) {
		this.numRepeticiones = repeticiones;
	}

}
