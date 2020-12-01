package Dominio;

public interface Estado {
	;

	/**
	 * Si nadie la ha reservado
	 */
	String LIBRE = "Nadie ha reservado la mesa.";
	/**
	 * Si alguien ha hecho una reserva
	 */
	 String RESERVADA = "Alguien ha reservado la mesa.";
	/**
	 * Cuando los comensales est�n sentados en la mesa
	 */
	 String OCUPADA = "Los comensales están sentados en la mesa.";
	/**
	 * Si el camarero est� recogiendo la comanda
	 */
	 String PIDIENDO = "El camarero está tomando la comanda.";
	/**
	 * Si los comensales est�n esperando la comida
	 */
	 String ESPERANDO_COMIDA = "Los comensales están esperando la comida.";
	/**
	 * Si los comensales est�n comiendo los platos que han pedido
	 */
	 String SERVIDO = "Los comensales están comiendo los platos que han pedido.";
	/**
	 * Si los comensales han pedido la cuenta.
	 */
	 String ESPERANDO_CUENTA= "Los comensales han pedido la cuenta.";
	/**
	 * Si los comensales ya tienen la cuenta en la mesa
	 */
	 String PAGANDO = "Los comnesales ya tienen la cuenta en la mesa.";
	/**
	 * Cuando los comensales se han retirado de la mesa, y los camareros la est�n preparando para que vuelva a estar libre
	 */
	 String EN_PREPARACIÓN = "Los comensales se han retirado, y se está preparando la mesa.";

}