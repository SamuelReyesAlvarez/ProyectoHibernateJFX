package dam.samuel.vista;

import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import dam.samuel.modelo.Juego;
import dam.samuel.modelo.Juego.EstiloJuego;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorRegistroJuego implements Initializable {

	private ObservableList<String> listaEstilos;

	private Stage dialogRegistroJuego;

	@FXML
	private TextField textoNombre;

	@FXML
	private ComboBox<String> comboEstilo;

	@FXML
	private DatePicker datePublicacion;

	@FXML
	private TextArea textoDescripcion;

	@FXML
	private TextField textoPrecio;

	@FXML
	private TextField textoDesarrolladores;

	public ControladorRegistroJuego() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listaEstilos = FXCollections.observableArrayList();
		listaEstilos.add("<sin definir>");
		for (EstiloJuego estilo : EstiloJuego.values()) {
			listaEstilos.add(estilo.toString().toUpperCase());
		}

		comboEstilo.setItems(listaEstilos);
	}

	public void setDialog(Stage stage) {
		this.dialogRegistroJuego = stage;
	}

	@FXML
	private void volver() {
		dialogRegistroJuego.close();
	}

	@FXML
	private void registrar() {
		try {
			String nombre = textoNombre.getText();
			EstiloJuego estilo = EstiloJuego.valueOf(comboEstilo.getValue());
			Date publicacion = Date
					.from(datePublicacion.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
			String descripcion = textoDescripcion.getText();
			Double precio = Double.parseDouble(textoPrecio.getText());

			Juego juego = new Juego(nombre, estilo, publicacion, descripcion, precio);

			// TODO comprobar las empresas desarrolladoras
			// TODO almacenar el objeto en la BD y su relacion en Desarrolla

			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Confirmacion");
			alerta.setHeaderText("Mensaje de registro");
			alerta.setContentText("Se ha registrado un nuevo Juego");
			alerta.showAndWait();
			dialogRegistroJuego.close();
		} catch (NullPointerException | IllegalArgumentException e) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setHeaderText("Error de registro");
			alerta.setContentText("Compruebe que todos los campos sean correctos");
			alerta.showAndWait();
		}
	}
}
