package Dominio;

public class Camarero {

	private int idCamarero;
	private String nombre;
	
	public Camarero(final int pIdCamarero,final String pNombre) {
		super();
		this.setIdCamarero(pIdCamarero);
		this.setNombre(pNombre);
	}
	public int getIdCamarero() {
		return idCamarero;
	}
	public void setIdCamarero(final int pIdCamarero) {
		this.idCamarero = pIdCamarero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String pNombre) {
		this.nombre = pNombre;
	}
}
