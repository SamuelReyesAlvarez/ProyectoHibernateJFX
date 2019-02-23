package dam.samuel.vista;

import java.util.Date;
import java.util.List;

import dam.samuel.dao.DesarrolloDAO;
import dam.samuel.dao.EmpresaDAO;
import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.Juego;
import dam.samuel.modelo.Juego.EstiloJuego;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 *         Esta clase controla la ventana donde se pueden ver todas las empresas
 *         desarrolladoras registradas y los juegos que han desarrollado. Esta
 *         ventana es solo informativa
 */
public class ControladorVerDesarrolladores {

	private EmpresaDAO empresaDAO = new EmpresaDAO();
	private DesarrolloDAO desarrollaDAO = new DesarrolloDAO();
	private ObservableList<Empresa> listaEmpresas;
	private ObservableList<Juego> listaJuegos;
	private Stage dialogVerDesarrolladores;

	@FXML
	private TableView<Empresa> tablaEmpresa;
	@FXML
	private TableColumn<Empresa, String> columnaEmpresa;
	@FXML
	private TableView<Juego> tablaJuego;
	@FXML
	private TableColumn<Juego, String> columnaJuego;
	@FXML
	private TableColumn<Juego, EstiloJuego> columnaEstilo;
	@FXML
	private TableColumn<Juego, Date> columnaPublicacion;
	@FXML
	private TableColumn<Juego, Double> columnaPrecio;

	/**
	 * Constructor estándar
	 */
	public ControladorVerDesarrolladores() {
	}

	/**
	 * Carga los datos necesarios para la ventana antes de ser mostrada
	 */
	@FXML
	private void initialize() {
		listaEmpresas = FXCollections.observableArrayList();
		// Carga la lista completa de empresas registradas
		List<Empresa> list = empresaDAO.consultarTodas();

		for (Empresa empresa : list) {
			listaEmpresas.add(empresa);
		}

		// Prepara la tabla para recibir los datos de la lista
		columnaEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nombre"));
		tablaEmpresa.setItems(listaEmpresas);
		// Marca como seleccionado el primer item de la tabla por defecto
		tablaEmpresa.getSelectionModel().select(listaEmpresas.get(0));
		// Carga en la segunda tabla los juegos desarrollados por la empresa
		// seleccionado por defecto
		mostrarJuegosDeEmpresa(listaEmpresas.get(0));

		// No se permitirá al usuario seleccionar más de un item de la primera tabla al
		// mismo tiempo
		tablaEmpresa.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		// Escucha los cambios de seleccion en la primera tabla para actualizar los
		// datos en la segunda tabla
		tablaEmpresa.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> mostrarJuegosDeEmpresa(tablaEmpresa.getSelectionModel().getSelectedItem()));
	}

	/**
	 * Recibe el marco de la ventana controlada por la clase
	 * 
	 * @param stage es el marco de la ventana VerDesarrolladres
	 */
	public void setDialog(Stage stage) {
		this.dialogVerDesarrolladores = stage;
	}

	/**
	 * Cierra la ventana controlada por la clase y vuelve a la ventana Principal
	 */
	@FXML
	public void volver() {
		dialogVerDesarrolladores.close();
	}

	/**
	 * Carga los datos a mostrar en la segunda tabla según la empresa seleccionada
	 * en la primera
	 * 
	 * @param empresa que se ha seleccionado de la primera tabla y por la que se
	 *                buscarán los juegos desarrollados por ella
	 */
	public void mostrarJuegosDeEmpresa(Empresa empresa) {
		listaJuegos = FXCollections.observableArrayList();
		// Busca en la base de datos los juegos según la empresa que los desarrolló
		List<Juego> juegos = desarrollaDAO.consultarJuegosPorEmpresa(empresa);
		for (Juego juego : juegos) {
			listaJuegos.add(juego);
		}

		// Prepara la tabla para recibir los datos de la lista creada
		columnaJuego.setCellValueFactory(new PropertyValueFactory<Juego, String>("nombre"));
		columnaEstilo.setCellValueFactory(new PropertyValueFactory<Juego, EstiloJuego>("estilo"));
		columnaPublicacion.setCellValueFactory(new PropertyValueFactory<Juego, Date>("publicacion"));
		columnaPrecio.setCellValueFactory(new PropertyValueFactory<Juego, Double>("precio"));
		tablaJuego.setItems(listaJuegos);

		// No se permitirá al usuario seleccionar más de un item al mismo tiempo en la
		// segunda tabla
		tablaJuego.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
}
