package dam.samuel.vista;

import java.net.URL;
import java.util.ResourceBundle;

import dam.samuel.MainApp;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControladorLogin implements Initializable {

	@FXML
	private TextField nombre;
	@FXML
	private TextField clave;
	@FXML
	private Label error;

	private MainApp stage;

	public ControladorLogin() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		error.setVisible(false);

		nombre.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						error.setVisible(false);
					}
				});
		clave.focusedProperty()
				.addListener((ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) -> {
					if (newValue) {
						error.setVisible(false);
					}
				});
	}

	public void setStage(MainApp stage) {
		this.stage = stage;
	}

	@FXML
	public void entrar() {
		String admin = stage.getAdmin();
		String psswd = stage.getClave();

		if (nombre.getText().equals(admin) && clave.getText().equals(psswd)) {
			stage.mostrarPrincipal(true);
		} else if (nombre.getText().equals("") && clave.getText().equals("")) {
			stage.mostrarPrincipal(false);
		} else {
			error.setVisible(true);
		}
	}
}
