package dam.samuel.vista;

import dam.samuel.dao.JuegoDAO;
import dam.samuel.modelo.Juego;
import dam.samuel.modelo.Valoracion;
import dam.samuel.modelo.ValoratorException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorValorarJuego {

	private JuegoDAO juegoDAO = new JuegoDAO();
	private Stage dialogValorarJuego;
	private Juego juego;

	@FXML
	private TextField textoNombre;
	@FXML
	private TextField textoPrecio;
	@FXML
	private TextField textoEstilo;
	@FXML
	private TextField textoPublicacion;
	@FXML
	private TextArea textoDescripcion;
	@FXML
	private TextArea textoComentario;
	@FXML
	private Button botonValorar;
	@FXML
	private ToggleButton botonPositivo;
	@FXML
	private ToggleButton botonNegativo;

	/**
	 * Constructor estándar
	 */
	public ControladorValorarJuego() {
	}

	/**
	 * Recibe el marco de la ventana controlada por la clase
	 * 
	 * @param stage es el marco de la ventana ValorarJuego
	 */
	public void setDialog(Stage stage) {
		this.dialogValorarJuego = stage;
	}

	/**
	 * Recibe el juego seleccionado en la ventana VerJuegos
	 * 
	 * @param juego seleccionado
	 */
	public void setJuego(Juego juego) {
		this.juego = juegoDAO.consultarPorId(juego.getIdJuego());
	}

	/**
	 * Cierra la ventana controlada por la clase y vuelve a la ventana VerJuegos
	 */
	@FXML
	public void volver() {
		dialogValorarJuego.close();
	}

	/**
	 * Encargado de crear un nuevo objeto Valoracion en el que el usuario puede
	 * escribir su opinión del juego y votarlo positiva o negativamente
	 */
	@FXML
	public void valorar() {
		boolean voto;

		// Primero comprueba si se ha seleccionado el voto
		if (!botonPositivo.isSelected() && !botonNegativo.isSelected()) {
			mostrarError("Debes votar positivo o negativo");
		} else {
			// Luego comprueba cual se ha seleccionado
			if (botonPositivo.isSelected()) {
				voto = true;
			} else {
				voto = false;
			}
			// Crea un objeto Valoracion con los datos introducidos
			Valoracion valoracion = new Valoracion(voto, textoComentario.getText());
			// Asigna el juego al que va dirigida la valoración
			valoracion.setJuego(juego);
			try {
				// Establece la valoración en el juego especificado
				juego.getListaValoraciones().add(valoracion);
				// Actualiza el juego para que Hibernate guarde la valoración
				juegoDAO.actualizar(juego);

				// Muestra un mensaje de acción completada
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Confirmacion");
				alerta.setHeaderText("Mensaje de registro");
				alerta.setContentText("Se ha registrado la valoracion");
				alerta.showAndWait();

				// Cierra la ventana actual y regresa a VerJuegos
				volver();
			} catch (ValoratorException e) {
				mostrarError(e.getMessage());
			}
		}
	}

	/**
	 * Genera un mensaje de error que puede ser lanzado desde cualquier parte del
	 * código
	 * 
	 * @param mensaje a mostrar
	 */
	private void mostrarError(String mensaje) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error");
		alerta.setHeaderText("Error de registro");
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}

	/**
	 * Carga los datos del juego en los componentes de la ventana para mostrarlos al
	 * usuario
	 */
	public void mostrarDatosJuego() {
		textoNombre.setText(juego.getNombre());
		textoPrecio.setText(String.valueOf(juego.getPrecio()));
		if (juego.getEstilo() != null) {
			textoEstilo.setText(juego.getEstilo().toString());
		}
		textoPublicacion.setText(juego.getPublicacion().toString());
		textoDescripcion.setText(juego.getDescripcion());
	}
}
