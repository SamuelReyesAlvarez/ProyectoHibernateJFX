package dam.samuel.vista;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import dam.samuel.dao.EmpresaDAO;
import dam.samuel.dao.JuegoDAO;
import dam.samuel.modelo.Desarrolla;
import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.Juego;
import dam.samuel.modelo.Juego.EstiloJuego;
import dam.samuel.modelo.ValoratorException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

	private JuegoDAO juegoDAO = new JuegoDAO();
	private EmpresaDAO empresaDAO = new EmpresaDAO();
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
		comboEstilo.getSelectionModel().selectFirst();

		configurarTextPrecio();
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
			EstiloJuego estilo;
			if (comboEstilo.getValue().contains("<")) {
				estilo = null;
			} else {
				estilo = EstiloJuego.values()[comboEstilo.getSelectionModel().getSelectedIndex() - 1];
			}
			LocalDate publicacion = datePublicacion.getValue();
			String descripcion = textoDescripcion.getText();
			Double precio = Double.parseDouble(textoPrecio.getText());

			Juego juego = new Juego(nombre, estilo, publicacion, descripcion, precio);
			juego.setListaDesarrolladores(crearListaDesarrolladores(juego, crearListaEmpresas()));
			juegoDAO.guardar(juego);

			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Confirmacion");
			alerta.setHeaderText("Mensaje de registro");
			alerta.setContentText("Se ha registrado un nuevo Juego");
			alerta.showAndWait();
			volver();
		} catch (ValoratorException ve) {
			mostrarError("No se pudo guardar el nuevo juego");
		} catch (IllegalArgumentException iae) {
			mostrarError("Compruebe que todos los campos sean correctos");
		} catch (NullPointerException npe) {
			mostrarError("Compruebe que todos los campos estan rellenos");
		} catch (MySQLIntegrityConstraintViolationException e) {
			mostrarError("Ya existe un juego con ese nombre");
		}
	}

	private List<Empresa> crearListaEmpresas() {
		List<Empresa> empresas = new ArrayList<>();
		String[] nombres = textoDesarrolladores.getText().split(",");
		for (String nombre : nombres) {
			Empresa empresa = empresaDAO.consultarPorNombre(new Empresa(nombre.trim()));
			if (empresa != null) {
				empresas.add(empresa);
			}
		}
		return empresas;
	}

	private List<Desarrolla> crearListaDesarrolladores(Juego juego, List<Empresa> empresas) throws ValoratorException {
		List<Desarrolla> desarrollo = new ArrayList<>();
		for (Empresa empresa : empresas) {
			Desarrolla desarrolla = new Desarrolla(empresa, juego);
			desarrollo.add(desarrolla);
		}
		return desarrollo;
	}

	private void mostrarError(String mensaje) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error");
		alerta.setHeaderText("Error de registro");
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}

	private void configurarTextPrecio() {
		textoPrecio.textProperty().addListener(new ChangeListener<String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if (!Character.isDigit(newValue.charAt(0))) {
					textoPrecio.setText(oldValue);
				} else {
					if (!newValue.matches("\\d{1,4}([\\.\\,]\\d{0,2})?")) {
						textoPrecio.setText(oldValue);
					}
				}
			}
		});
	}
}
