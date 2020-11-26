package Dominio;

public class Mesa {

	private int id;
	private int n_comensales;
	private String estado;
	private int contador;
	
	public Mesa(int id, int n_comensales, String estado, int contador) {
		this.setId(id);
		this.setN_comensales(n_comensales);
		this.setEstado(estado);
		this.setContador(contador);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getN_comensales() {
		return this.n_comensales;
	}

	public void setN_comensales(int n_comensales) {
		this.n_comensales = n_comensales;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getContador() {
		return this.contador;
	}
	
	public void setContador(int contador) {
		this.contador = contador;
	}

	public String toString(){
		return this.estado;
	}
	
}