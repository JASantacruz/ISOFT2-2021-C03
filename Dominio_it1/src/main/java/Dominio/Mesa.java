package Dominio;

public class Mesa {

	private int id;
	private int n_comensales;
	private int contador;
	
	public Mesa(int id, int contador) {
		this.setId(id);
		this.setContador(contador);
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getContador() {
		return this.contador;
	}
	
	public void setContador(int contador) {
		this.contador = contador;
	}

}