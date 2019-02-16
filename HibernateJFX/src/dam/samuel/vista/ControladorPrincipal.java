package dam.samuel.vista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ControladorPrincipal {

	@FXML
	private Button btnNuevoJuego;

	@FXML
	private Button btnNuevoDesarrollador;

	private Stage stage;

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

	public void setStage(Stage dialogStage) {
		this.stage = dialogStage;
	}

	@FXML
	public void verJuegos() {

	}

	@FXML
	public void verDesarrolladores() {

	}

	@FXML
	public void nuevoJuego() {

	}

	@FXML
	public void nuevoDesarrollador() {

	}

	@FXML
	public void volver() {
		stage.close();
	}

	@FXML
	public void salir() {
		System.exit(0);
	}
}
