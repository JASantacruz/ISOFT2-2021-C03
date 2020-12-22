package Dominio;
public interface Estado {
	/**
	 * Si nadie la ha reservado.
	 */
	String LIBRE = "Nadie ha reservado la mesa.";
	/**
	 * Si alguien ha hecho una reserva.
	 */
	 String RESERVADA = "Alguien ha reservado la mesa.";
	/**
	 * Cuando los comensales estan sentados en la mesa.
	 */
	 String OCUPADA = "Los comensales estan sentados en la mesa.";
	/**
	 * Si el camarero esta recogiendo la comanda.
	 */
	 String PIDIENDO = "El camarero esta tomando la comanda.";
	/**
	 * Si los comensales estan esperando la comida.
	 */
	 String ESPERANDO_COMIDA = "Los comensales estan esperando la comida.";
	/**
	 * Si los comensales estan comiendo los platos que han pedido.
	 */
	 String SERVIDO =
			 "Los comensales estan comiendo los platos que han pedido.";
	/**
	 * Si los comensales han pedido la cuenta.
	 */
	 String ESPERANDO_CUENTA = "Los comensales han pedido la cuenta.";
	/**
	 * Si los comensales ya tienen la cuenta en la mesa.
	 */
	 String PAGANDO =
			 "Los comensales ya tienen la cuenta en la mesa.";
	/**
	 * Cuando los comensales se han retirado de la mesa,
	 * y los camareros la estan preparando para que vuelva a estar libre.
	 */
	 String EN_PREPARACION =
			 "Los comensales se han retirado, "
			 + "y se esta preparando la mesa.";
}