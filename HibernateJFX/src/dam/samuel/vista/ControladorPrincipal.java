package dam.samuel.vista;

import dam.samuel.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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
			btnNuevoJuego.setDisable(true);
			btnNuevoDesarrollador.setDisable(true);
		} else {
			btnNuevoJuego.setDisable(false);
			btnNuevoDesarrollador.setDisable(false);
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
		stage.mostrarDesarrolladores();
	}

	@FXML
	public void nuevoJuego() {

	}

	@FXML
	public void nuevoDesarrollador() {

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
