package dam.samuel.vista;

import java.util.Optional;

import dam.samuel.MainApp;
import dam.samuel.modelo.Empresa;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorPrincipal {

	@FXML
	private Button btnNuevoJuego;

	@FXML
	private Button btnNuevoDesarrollador;

	private MainApp stage;
	private Stage dialogPrincipal;

	public ControladorPrincipal() {

	}

	public void controlarOpciones(boolean esAdmin) {
		if (!esAdmin) {
			btnNuevoJuego.setVisible(esAdmin);
			btnNuevoDesarrollador.setVisible(esAdmin);
		}
	}

	public void setMainApp(MainApp stage) {
		this.stage = stage;
	}

	public void setDialog(Stage stage) {
		this.dialogPrincipal = stage;
	}

	@FXML
	public void verJuegos() {
		stage.mostrarVerJuegos();
	}

	@FXML
	public void verDesarrolladores() {
		stage.mostrarVerDesarrolladores();
	}

	@FXML
	public void nuevoJuego() {
		stage.mostrarNuevoJuego();
	}

	@FXML
	public void nuevoDesarrollador() {
		TextInputDialog entradaTexto = new TextInputDialog();
		entradaTexto.setTitle("Registro de Empresa desarrolladora");
		entradaTexto.setHeaderText("Nueva empresa desarrolladora");
		entradaTexto.setContentText("Nombre:");

		Optional<String> nombre = entradaTexto.showAndWait();

		if (!nombre.get().isEmpty()) {
			Empresa nuevaEmpresa = new Empresa(nombre.get());
			// TODO almacenar el objeto en la BD
			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Confirmacion");
			alerta.setHeaderText("Mensaje de registro");
			alerta.setContentText("Se ha registrado una nueva Empresa desarrolladora");
			alerta.showAndWait();
		} else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setHeaderText("Error de registro");
			alerta.setContentText("Es necesario introducir un nombre");
			alerta.showAndWait();
		}
	}

	@FXML
	public void volver() {
		dialogPrincipal.close();
	}

	@FXML
	public void salir() {
		System.exit(0);
	}
}
