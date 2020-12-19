package Dominio;

public class Camarero {

	private int idCamarero;
	private String nombre;
	
	public Camarero(int idCamarero, String nombre) {
		super();
		this.setIdCamarero(idCamarero);
		this.setNombre(nombre);
	}
	
	
	public int getIdCamarero() {
		return idCamarero;
	}
	
	
	public void setIdCamarero(int idCamarero) {
		this.idCamarero = idCamarero;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}
