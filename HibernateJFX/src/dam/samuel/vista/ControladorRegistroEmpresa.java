package dam.samuel.vista;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dam.samuel.dao.EmpresaDAO;
import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.ValoratorException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ControladorRegistroEmpresa {

	private EmpresaDAO empresaDAO = new EmpresaDAO();
	private Stage dialogRegistroEmpresa;

	@FXML
	private TextField textoNombre;

	public ControladorRegistroEmpresa() {
	}

	public void setDialog(Stage stage) {
		this.dialogRegistroEmpresa = stage;
	}

	public void cancelar() {
		dialogRegistroEmpresa.close();
	}

	public void registrar() {
		String nombre = textoNombre.getText();
		try {
			if (nombre.length() == 0) {
				Empresa nuevaEmpresa = new Empresa(nombre);
				empresaDAO.guardar(nuevaEmpresa);

				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Confirmacion");
				alerta.setHeaderText("Mensaje de registro");
				alerta.setContentText("Se ha registrado una nueva Empresa desarrolladora");
				alerta.showAndWait();

				cancelar();
			} else {
				mostrarError("Es necesario introducir un nombre");
			}
		} catch (ValoratorException e) {
			mostrarError("No se pudo guardar la nueva empresa");
		} catch (MySQLIntegrityConstraintViolationException e) {
			mostrarError("Ya existe una empresa con ese nombre");
		}
	}

	private void mostrarError(String mensaje) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error");
		alerta.setHeaderText("Error de registro");
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}
}
