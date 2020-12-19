package Dominio;

public class Mesa {

	private int id;
	private String estado;
	
	public Mesa(int id, String estado) {
		this.setId(id);
		this.setEstado(estado);
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
}