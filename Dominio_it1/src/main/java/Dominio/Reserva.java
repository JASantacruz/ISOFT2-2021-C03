package Dominio;

public class Reserva implements Estado {
	/**
	 * id de la reserva.
	 */
	private int id;
	/**
	 * numero comensales reserva.
	 */
	private int numComensales;
	/**
	 * nombre reserva.
	 */
	private String nombre;
	/**
	 * Constructor clase Reserva
	 * @param pId
	 * @param pNumComensales
	 * @param pNombre
	 */
	public Reserva(final int pId, final int pNumComensales, final String pNombre) {
		this.setId(pId);
		this.setNumComensales(pNumComensales);
		this.setNombre(pNombre);
	}
	/**
	 * Modificar nombre de reserva.
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * Devuelve el nombre de quien es la reserva.
	 * @param pNombre
	 */
	public void setNombre(final String pNombre) {
		this.nombre = pNombre;
	}
	/**
	 * Devuelve id de reserva.
	 * @return
	 */
	public int getId() {
		return id;
	}
	/**
	 * Modificar id reserva.
	 * @param pId
	 */
	public void setId(final int pId) {
		this.id = pId;
	}
	/**
	 * Devuelve numero de comensales de la reserva.
	 * @return
	 */
	public int getNumComensales() {
		return numComensales;
	}
	/**
	 * Modificar numero de comensales de la reserva.
	 * @param pNumComensales
	 */
	public void setNumComensales(final int pNumComensales) {
		this.numComensales = pNumComensales;
	}
}
