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

/**
 * 
 * @author Samuel Reyes Alvarez
 * 
 *         Esta clase controla la ventana de registros de empresa que solo
 *         estará disponible para el Administrador
 *
 */
public class ControladorRegistroEmpresa {

	private EmpresaDAO empresaDAO = new EmpresaDAO();
	private Stage dialogRegistroEmpresa;

	@FXML
	private TextField textoNombre;

	/**
	 * Constructor estándar
	 */
	public ControladorRegistroEmpresa() {
	}

	/**
	 * Recibe el marco de la ventana controlada por la clase
	 * 
	 * @param stage es el marco de la ventana RegistroEmpresa
	 */
	public void setDialog(Stage stage) {
		this.dialogRegistroEmpresa = stage;
	}

	/**
	 * Cierra la ventana controlada por la clase y vuelve a la ventana Principal
	 */
	public void cancelar() {
		dialogRegistroEmpresa.close();
	}

	/**
	 * Permite al Administrador registrar una nueva empresa desarrolladora
	 */
	public void registrar() {
		// Obtiene el nombre introducido por el administrador
		String nombre = textoNombre.getText();
		try {
			if (nombre.length() != 0) {
				// Crea un nuevo objeto Empresa y lo guarda a travñes de Hibernate
				Empresa nuevaEmpresa = new Empresa(nombre);
				empresaDAO.guardar(nuevaEmpresa);

				// Muestra un mensaje de acción completada
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Confirmacion");
				alerta.setHeaderText("Mensaje de registro");
				alerta.setContentText("Se ha registrado una nueva Empresa desarrolladora");
				alerta.showAndWait();

				// Regresa a la ventana Principal
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

	/**
	 * Genera una alerta de tipo ERROR que puede ser lanzada desde diferentes partes
	 * del código
	 * 
	 * @param mensaje a mostrar en la alerta
	 */
	private void mostrarError(String mensaje) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error");
		alerta.setHeaderText("Error de registro");
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}
}
