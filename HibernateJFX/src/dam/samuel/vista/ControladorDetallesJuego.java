package dam.samuel.vista;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dam.samuel.dao.DesarrolloDAO;
import dam.samuel.dao.EmpresaDAO;
import dam.samuel.dao.JuegoDAO;
import dam.samuel.dao.ValoracionDAO;
import dam.samuel.modelo.Desarrolla;
import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.Juego;
import dam.samuel.modelo.Juego.EstiloJuego;
import dam.samuel.modelo.Valoracion;
import dam.samuel.modelo.ValoratorException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorDetallesJuego implements Initializable {

	private static final String PATRON_FECHA = "dd/MM/yyyy";
	private ValoracionDAO valoracionDAO = new ValoracionDAO();
	private EmpresaDAO empresaDAO = new EmpresaDAO();
	private DesarrolloDAO desarrolloDAO = new DesarrolloDAO();
	private JuegoDAO juegoDAO = new JuegoDAO();
	private ObservableList<Valoracion> listaValoracion;
	private ObservableList<String> listaEstilos;
	private Stage dialogDetallesJuego;
	private Juego juego;

	@FXML
	private TextField textoNombre;
	@FXML
	private ComboBox<String> comboEstilo;
	@FXML
	private DatePicker textoPublicacion;
	@FXML
	private TextField textoPrecio;
	@FXML
	private TextArea textoDesarrolladores;
	@FXML
	private TextArea textoDescripcion;
	@FXML
	private TableView<Valoracion> tablaValoracion;
	@FXML
	private TableColumn<Valoracion, String> columnaVoto;
	@FXML
	private TableColumn<Valoracion, String> columnaComentario;
	@FXML
	private Button botonGuardar;
	@FXML
	private Button botonBorrar;

	/**
	 * Constructor estándar
	 */
	public ControladorDetallesJuego() {
	}

	/**
	 * Carga información previa a mostrar la ventana controlada por la clase.
	 * Rellena el combobox de la ventana con los estilos de juego disponibles
	 * añadiendo uno extra por si el juego no tiene estilo definido.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listaEstilos = FXCollections.observableArrayList();
		listaEstilos.add("<sin definir>");
		for (EstiloJuego estilo : EstiloJuego.values()) {
			listaEstilos.add(estilo.toString().toUpperCase());
		}

		comboEstilo.setItems(listaEstilos);

		configurarTextPrecio();
	}

	/**
	 * Recibe el juego del que se va a consultar la información desde una consulta
	 * de Hibernate
	 * 
	 * @param juego es el juego seleccionado en VerJuegos
	 */
	public void setJuego(Juego juego) {
		this.juego = juegoDAO.consultarPorId(juego.getIdJuego());
	}

	/**
	 * Recibe el marco de la ventana que controla la clase
	 * 
	 * @param stage es el marco de la ventana DetallesJuego
	 */
	public void setDialog(Stage stage) {
		this.dialogDetallesJuego = stage;
	}

	/**
	 * Cierra la ventana actual y vuelve a la clase principal para continuar el
	 * flujo de la aplicación
	 */
	@FXML
	private void volver() {
		dialogDetallesJuego.close();
	}

	/**
	 * Ejecuta el borrado de una valoración del juego seleccionado. Este método solo
	 * podrá ser llamado si el usuario entró como Administrador
	 */
	@FXML
	public void borrar() {
		Valoracion valoracion = tablaValoracion.getSelectionModel().getSelectedItem();

		try {
			// En primer lugar se solicita confirmación para proceder con la eliminación de
			// la valoración
			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Peticion");
			alerta.setHeaderText("Se solicita confirmar la accion");
			alerta.setContentText("ï¿½Esta seguro de eliminar el objeto seleccionado?");

			Optional<ButtonType> result = alerta.showAndWait();

			// Si la respuesta fue afirmativa se elimina la valoración de la lista del
			// juego, luego Hibernate elimina la valoración
			if (result.get() == ButtonType.OK) {
				juego.getListaValoraciones().remove(valoracion);
				valoracionDAO.borrar(valoracion);

				// Muestra el mensaje de accion completada
				Alert hecho = new Alert(AlertType.INFORMATION);
				hecho.setTitle("Confirmacion");
				hecho.setHeaderText("Mensaje de borrado");
				hecho.setContentText("Se ha eliminado el objeto");

				cargarValoracionesJuego();
			}
		} catch (ValoratorException e) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setHeaderText("Error de borrado");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		}
	}

	/**
	 * Gestiona la actualización del juego con los datos modificacos. Este método
	 * solo podrá ser invocado si el usuario entró como Administrador
	 */
	@FXML
	private void guardar() {
		try {
			// En primer lugar se recogen los nuevos datos establecidos por el Administrador
			// Nombre del juego
			juego.setNombre(textoNombre.getText());
			// Estilo de juego (que puede ser nulo)
			if (comboEstilo.getValue().contains("<")) {
				juego.setEstilo(null);
			} else {
				juego.setEstilo(EstiloJuego.values()[(comboEstilo.getSelectionModel().getSelectedIndex() - 1)]);
			}
			// Fecha de publicación
			if (textoPublicacion.getValue() != null) {
				juego.setPublicacion(textoPublicacion.getValue());
			} else {
				juego.setPublicacion(
						LocalDate.parse(textoPublicacion.getPromptText(), DateTimeFormatter.ofPattern(PATRON_FECHA)));
			}
			// Precio de venta en euros
			juego.setPrecio(Double.parseDouble(textoPrecio.getText()));
			// Descripción del juego
			juego.setDescripcion(textoDescripcion.getText());
			// Empresas desarrolladoras del juego
			// En este caso primero se guardan los valores antiguos
			List<Desarrolla> listaABorrar = new ArrayList<>();
			for (Desarrolla desarrolla : juego.getListaDesarrolladores()) {
				listaABorrar.add(desarrolla);
			}
			// Se pone la lista de desarrolladores en Juego a null para eliminarlos de la
			// relacion Desarrolla
			juego.setListaDesarrolladores(null);
			for (Desarrolla desarrolla : listaABorrar) {
				desarrolloDAO.borrar(desarrolla);
			}
			// Se crea una nueva lista de relacion Desarrolla para el juego con los datos
			// introducidos por el Administrador
			List<Desarrolla> desarrolladores = crearListaDesarrolladores(juego, crearListaEmpresas());
			// Lo añade al juego en memoria
			juego.setListaDesarrolladores(desarrolladores);
			// Al actualizar juego, Hibernate introducira la relacion en la tabla Desarrolla
			juegoDAO.actualizar(juego);

			// Muestra el mensaje de accion completada
			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Confirmacion");
			alerta.setHeaderText("Mensaje de actualizacion");
			alerta.setContentText("Se han actualizado los datos del Juego");
			alerta.showAndWait();
			volver();
		} catch (ValoratorException e) {
			mostrarError("No se pudo actualizar los datos del juego");
		} catch (IllegalArgumentException e) {
			mostrarError("Compruebe que todos los campos sean correctos");
		} catch (NullPointerException e) {
			mostrarError("Compruebe que todos los campos estan rellenos");
		}
	}

	/**
	 * Genera un cuadro de alerta de error para ser lanzado desde diferentes partes
	 * del código
	 * 
	 * @param mensaje a mostrar
	 */
	private void mostrarError(String mensaje) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error");
		alerta.setHeaderText("Error de actualizacion");
		alerta.setContentText(mensaje);
		alerta.showAndWait();
	}

	/**
	 * Genera una lista de empresas a partir de los nombres introducidos por la UI
	 * 
	 * @return lista de empresas desarrolladores del juego
	 */
	private List<Empresa> crearListaEmpresas() {
		List<Empresa> empresas = new ArrayList<>();
		String[] nombres = textoDesarrolladores.getText().split(",");
		for (String nombre : nombres) {
			Empresa empresa = empresaDAO.consultarPorNombre(nombre.trim());
			if (empresa != null) {
				empresas.add(empresa);
			}
		}
		return empresas;
	}

	/**
	 * Genera una lista de relación entre las empresas desarrolladores y el juego
	 * desarrollado
	 * 
	 * @param juego
	 * @param empresas
	 * @return lista de relación de desarrollores con el juego
	 */
	private List<Desarrolla> crearListaDesarrolladores(Juego juego, List<Empresa> empresas) {
		List<Desarrolla> desarrolladores = new ArrayList<>();
		for (Empresa empresa : empresas) {
			Desarrolla desarrolla = new Desarrolla(empresa, juego);
			desarrolladores.add(desarrolla);
		}
		return desarrolladores;
	}

	/**
	 * Encargado de activar o desactivar las funciones de Administración según el
	 * usuario logueado
	 * 
	 * @param esAdministrador indica la autorización
	 */
	public void controlarOpciones(boolean esAdministrador) {
		botonGuardar.setVisible(esAdministrador);
		botonBorrar.setVisible(esAdministrador);
		textoNombre.setEditable(esAdministrador);
		comboEstilo.setDisable(!esAdministrador);
		textoPublicacion.setDisable(!esAdministrador);
		textoPrecio.setEditable(esAdministrador);
		textoDesarrolladores.setEditable(esAdministrador);
		textoDescripcion.setEditable(esAdministrador);

		// Evita que los componentes deshabilitados se vean oscuros o poco legibles para
		// el usuario sin autorzación
		comboEstilo.setStyle("-fx-opacity: 1;");
		textoPublicacion.setStyle("-fx-opacity: 1;");
	}

	/**
	 * Rellena los componentes que muestran la información del juego seleccionado
	 */
	public void cargarDetallesJuego() {
		textoNombre.setText(juego.getNombre());
		if (juego.getEstilo() != null) {
			comboEstilo.getSelectionModel().select(juego.getEstilo().ordinal() + 1);
		} else {
			comboEstilo.getSelectionModel().select(0);
		}
		textoPublicacion.setPromptText(new SimpleDateFormat(PATRON_FECHA).format(juego.getPublicacion()));
		textoPrecio.setText(String.valueOf(juego.getPrecio()));

		StringBuilder desarrolladores = new StringBuilder();
		for (Desarrolla desarrollador : juego.getListaDesarrolladores()) {
			desarrolladores.append(desarrollador.getEmpresa().getNombre() + ", ");
		}
		textoDesarrolladores.setText(desarrolladores.toString());
		textoDescripcion.setText(juego.getDescripcion());

		cargarValoracionesJuego();
	}

	/**
	 * Rellena la tabla de valoraciones de los usuarios sobre el juego seleccionado
	 */
	private void cargarValoracionesJuego() {
		listaValoracion = FXCollections.observableArrayList(juego.getListaValoraciones());

		columnaVoto.setCellValueFactory(new PropertyValueFactory<Valoracion, String>("tipoVoto"));
		columnaComentario.setCellValueFactory(new PropertyValueFactory<Valoracion, String>("comentario"));

		tablaValoracion.setItems(listaValoracion);
		tablaValoracion.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		tablaValoracion.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Valoracion>() {

			@Override
			public void changed(ObservableValue<? extends Valoracion> observable, Valoracion oldValue,
					Valoracion newValue) {
				if (newValue == null) {
					botonBorrar.setDisable(true);
				} else {
					botonBorrar.setDisable(false);
				}
			}

		});
	}

	/**
	 * Establece el formato de entrada de texto en el componente destinado al precio
	 * del juego
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
