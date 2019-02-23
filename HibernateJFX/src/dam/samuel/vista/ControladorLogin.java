package dam.samuel.vista;

import java.net.URL;
import java.util.ResourceBundle;

import dam.samuel.MainApp;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorLogin implements Initializable {

	// Datos de acceso para el Administrador
	// <<No es final, se hace para gestionar las acciones de administrador sin tener
	// que establecer un sistema avanzado de logueo>>
	private static final String admin = "admin";
	private static final String psswd = "1234";

	@FXML
	private TextField nombre;
	@FXML
	private TextField clave;
	@FXML
	private Label error;

	private MainApp mainApp;

	/**
	 * Constructor estándar
	 */
	public ControladorLogin() {
	}

	/**
	 * Carga las características necesarias para las funciones de los componentes
	 * antes de que se muestre la ventana
	 */
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

	/**
	 * Recibe la clase principal de la aplicación para gestionar la llamada a la
	 * siguiente ventana
	 * 
	 * @param mainApp es la clase principal de la aplicación
	 */
	public void setStage(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Encargado de comprobar los datos de acceso y gestionar la autorización del
	 * usuario que se loguea
	 */
	@FXML
	public void entrar() {
		if (nombre.getText().equals(admin) && clave.getText().equals(psswd)) {
			mainApp.mostrarPrincipal(true);
		} else if (nombre.getText().equals("") && clave.getText().equals("")) {
			mainApp.mostrarPrincipal(false);
		} else {
			error.setVisible(true);
		}
		nombre.setText("");
		clave.setText("");
	}
}
