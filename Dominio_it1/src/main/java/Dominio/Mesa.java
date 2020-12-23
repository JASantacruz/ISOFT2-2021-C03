package Dominio;

public class Mesa {
	/**
	 * idMesa.
	 */
	private int id;
	/**
	 * Estado de la mesa.
	 */
	private String estado;
	/**
	 * Constructor clase Mesa.
	 * @param pId
	 * @param pEstado
	 */
	public Mesa(final int pId, final String pEstado) {
		this.setId(pId);
		this.setEstado(pEstado);
	}

	/**
	 * Obtener el estado de la mesa.
	 * @return String
	 */
	public String getEstado() {
		return estado;
	}
	/**
	 * Establecer el estado de la mesa.
	 * @param pEstado
	 */
	public void setEstado(final String pEstado) {
		this.estado = pEstado;
	}
	/**
	 * Obtener el id de la mesa.
	 * @return int
	 */
	public int getId() {
		return this.id;
	}
	/**
	 * Establecer el id de la mesa.
	 * @param pId
	 */
	public void setId(final int pId) {
		this.id = pId;
	}
}
