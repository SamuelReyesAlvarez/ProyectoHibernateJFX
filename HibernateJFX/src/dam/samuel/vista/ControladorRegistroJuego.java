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
 *         Esta clase controla la ventana de registros de juegos y solo estarña
 *         disponible para el Administrador
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

	/**
	 * Constructor estándar
	 */
	public ControladorRegistroJuego() {
	}

	/**
	 * Carga datos iniciales para rellenar componentes
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Carga los estilos de juego disponibles más un campo vacío
		listaEstilos = FXCollections.observableArrayList();
		listaEstilos.add("<sin definir>");
		for (EstiloJuego estilo : EstiloJuego.values()) {
			listaEstilos.add(estilo.toString().toUpperCase());
		}

		// Rellena el combobox con la lista de estilos y selecciona la primera opción
		// por defecto
		comboEstilo.setItems(listaEstilos);
		comboEstilo.getSelectionModel().selectFirst();

		configurarTextPrecio();
	}

	/**
	 * Recibe el marco de la ventana controlada por la clase
	 * 
	 * @param stage es el marco de la ventana RegistroJuego
	 */
	public void setDialog(Stage stage) {
		this.dialogRegistroJuego = stage;
	}

	/**
	 * Cierra la ventana controlada por la clase y vuelve a la ventana Principal
	 */
	@FXML
	private void volver() {
		dialogRegistroJuego.close();
	}

	/**
	 * Gestiona el registro de un nuevo juego a través de la cumplimentación de un
	 * formulario por parte del Administrador
	 */
	@FXML
	private void registrar() {
		try {
			// Recoge el nombre introducido
			String nombre = textoNombre.getText();
			// Comprueba el estilo de juego seleccionado
			// Si selecciona la primera opción, el juego no tendrá estilo definido
			EstiloJuego estilo;
			if (comboEstilo.getValue().contains("<")) {
				estilo = null;
			} else {
				estilo = EstiloJuego.values()[comboEstilo.getSelectionModel().getSelectedIndex() - 1];
			}
			// Obtiene la fecha de publicación
			LocalDate publicacion = datePublicacion.getValue();
			// Recoge la descripción del juego
			String descripcion = textoDescripcion.getText();
			// Transforma la cantidad introducida a formato precio
			Double precio = Double.parseDouble(textoPrecio.getText());

			// Crea un nuevo objeto Juego
			Juego juego = new Juego(nombre, estilo, publicacion, descripcion, precio);
			// Establece la lista de desarrolladores del juego
			juego.setListaDesarrolladores(crearListaDesarrolladores(juego, crearListaEmpresas()));
			// Guarda el nuevo juego en la base de datos a través de Hibernate y éste se
			// encarga de crear la relación de Desarrolla con las empresas indicadas
			juegoDAO.guardar(juego);

			// Muestra un mensaje de acción completada
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Confirmacion");
			alerta.setHeaderText("Mensaje de registro");
			alerta.setContentText("Se ha registrado un nuevo Juego");
			alerta.showAndWait();

			// Cierra la ventana de registro y vuelve a Principal
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

	/**
	 * Genera un lista de empresas a partir de los nombre introducidos por el
	 * Administrador
	 * 
	 * @return empresas que desarrollaron el juego
	 */
	private List<Empresa> crearListaEmpresas() {
		List<Empresa> empresas = new ArrayList<>();
		String[] nombres = textoDesarrolladores.getText().split(",");
		for (String nombre : nombres) {
			// Comprueba que las empresas estan registradas en la base de datos
			Empresa empresa = empresaDAO.consultarPorNombre(nombre.trim());
			if (empresa != null) {
				empresas.add(empresa);
			}
		}
		return empresas;
	}

	/**
	 * Crea una lista de relación de Desarrolla entre el juego y las empresas que lo
	 * desarrollaron
	 * 
	 * @param juego    desarrollado
	 * @param empresas que lo desarrollaron
	 * @return desarrollo es la relación entre las empresas y el juego
	 */
	private List<Desarrolla> crearListaDesarrolladores(Juego juego, List<Empresa> empresas) {
		List<Desarrolla> desarrollo = new ArrayList<>();
		for (Empresa empresa : empresas) {
			Desarrolla desarrolla = new Desarrolla(empresa, juego);
			desarrollo.add(desarrolla);
		}
		return desarrollo;
	}

	/**
	 * Genera un mensaje de error con el mensaje especificado para que pueda ser
	 * lanzado desde cualquier parte del código
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

	/**
	 * Establece el formato de escritura permitida en el componente donde se
	 * introducirá el precio de venta del juego
	 */
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
