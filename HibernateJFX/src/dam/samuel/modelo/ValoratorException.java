package dam.samuel.modelo;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 *         Clase que hereda de Exception que permite la gestión de las
 *         excepciones producidas
 */
public class ValoratorException extends Exception {

	private static final long serialVersionUID = 1L;

	public ValoratorException(String message) {
		super(message);
	}
}
