package dam.samuel.vista;

import java.util.Optional;

import org.hibernate.Session;

import dam.samuel.MainApp;
import dam.samuel.dao.EmpresaDAO;
import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.HibernateUtil;
import dam.samuel.modelo.ValoratorException;
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

	private EmpresaDAO empresaDAO = new EmpresaDAO();
	private Session session;

	@FXML
	private Button btnNuevoJuego;

	@FXML
	private Button btnNuevoDesarrollador;

	private MainApp stage;
	private Stage dialogPrincipal;

	public ControladorPrincipal() {
		configurarSesion();
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

		try {
			if (!nombre.get().isEmpty()) {
				Empresa nuevaEmpresa = new Empresa(nombre.get());
				empresaDAO.guardar(nuevaEmpresa);

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
		} catch (ValoratorException e) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setHeaderText("Error de registro");
			alerta.setContentText("No se pudo guardar la nueva empresa");
			alerta.showAndWait();
		}
	}

	@FXML
	public void volver() {
		cerrarSesion();
		dialogPrincipal.close();
	}

	@FXML
	public void salir() {
		cerrarSesion();
		System.exit(0);
	}

	private void cerrarSesion() {
		HibernateUtil.closeSessionFactory();
	}

	private void configurarSesion() {
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSessionAndBindToThread();
		session = HibernateUtil.getSessionFactory().getCurrentSession();
	}
}
