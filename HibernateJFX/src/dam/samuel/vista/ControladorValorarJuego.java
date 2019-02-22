package dam.samuel.vista;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dam.samuel.dao.JuegoDAO;
import dam.samuel.dao.ValoracionDAO;
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

	private ValoracionDAO valoracionDAO = new ValoracionDAO();
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

	public ControladorValorarJuego() {
	}

	public void setDialog(Stage stage) {
		this.dialogValorarJuego = stage;
	}

	@FXML
	public void volver() {
		dialogValorarJuego.close();
	}

	@FXML
	public void valorar() {
		boolean voto;
		if (!botonPositivo.isSelected() && !botonNegativo.isSelected()) {
			mostrarError("Debes votar positivo o negativo");
		} else {
			if (botonPositivo.isSelected()) {
				voto = true;
			} else {
				voto = false;
			}
			Valoracion valoracion = new Valoracion(voto, textoComentario.getText());
			valoracion.setJuego(juego);

			volver();
			try {
				valoracionDAO.guardar(valoracion);

				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Confirmacion");
				alerta.setHeaderText("Mensaje de registro");
				alerta.setContentText("Se ha registrado la valoracion");
				alerta.showAndWait();
			} catch (MySQLIntegrityConstraintViolationException e) {
				mostrarError("No se pudo guardar la valoracion");
			} catch (ValoratorException e) {
				System.out.println(e.getMessage());
				mostrarError(e.getMessage());
			}
		}
	}

	private void mostrarError(String mensaje) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error");
		alerta.setHeaderText("Error de registro");
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}

	public void mostrarDatosJuego() {
		textoNombre.setText(juego.getNombre());
		textoPrecio.setText(String.valueOf(juego.getPrecio()));
		if (juego.getEstilo() != null) {
			textoEstilo.setText(juego.getEstilo().toString());
		}
		textoPublicacion.setText(juego.getPublicacion().toString());
		textoDescripcion.setText(juego.getDescripcion());
	}

	public void setJuego(Juego juego) {
		this.juego = juegoDAO.consultarPorId(juego);
	}
}
