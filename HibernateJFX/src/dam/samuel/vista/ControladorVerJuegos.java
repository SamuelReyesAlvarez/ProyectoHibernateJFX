package dam.samuel.vista;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import dam.samuel.MainApp;
import dam.samuel.dao.JuegoDAO;
import dam.samuel.modelo.Juego;
import dam.samuel.modelo.Juego.EstiloJuego;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorVerJuegos implements Initializable {

	private JuegoDAO juegoDAO = new JuegoDAO();
	private ObservableList<Juego> listaJuegos;
	private ObservableList<String> listaEstilos;
	private MainApp mainApp;
	private Stage dialogVerJuegos;

	@FXML
	private TableView<Juego> tabla;
	@FXML
	private TableColumn<Juego, String> columnaNombre;
	@FXML
	private TableColumn<Juego, EstiloJuego> columnaEstilo;
	@FXML
	private TableColumn<Juego, Date> columnaFecha;
	@FXML
	private TableColumn<Juego, Double> columnaPrecio;
	@FXML
	private TableColumn<Juego, String> columnaValoracion;
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private Button botonValorar;
	@FXML
	private Button botonVer;

	/**
	 * Constructor estándar
	 */
	public ControladorVerJuegos() {
	}

	/**
	 * Carga los datos necesarios para iniciar los componentes antes de mostrar la
	 * ventana
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Carga el combobox con los estilos de juego disponibles más un campo extra
		// para visualizarlos todos
		listaEstilos = FXCollections.observableArrayList();
		listaEstilos.add("todos".toUpperCase());
		for (EstiloJuego estilo : EstiloJuego.values()) {
			listaEstilos.add(estilo.toString().toUpperCase());
		}
		comboBox.setItems(listaEstilos);
		// Marca el primer item del combobox como seleción por defecto para cargar los
		// primeros datos
		comboBox.getSelectionModel().selectFirst();
		cargarPorEstilo();

		// Desactiva los botones de acción hasta que el usuario seleccione un item de la
		// tabla
		botonValorar.setDisable(true);
		botonVer.setDisable(true);

		// No permite que el usuario haga un selección multiple de objeto en la tabla
		tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		// Crea un escuchar de cambios de selección en la tabla para
		// habilitar los botones de acción cuando halla un elemento seleccionado
		tabla.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Juego>() {

			@Override
			public void changed(ObservableValue<? extends Juego> observable, Juego oldValue, Juego newValue) {
				if (newValue == null) {
					botonValorar.setDisable(true);
					botonVer.setDisable(true);
				} else {
					botonValorar.setDisable(false);
					botonVer.setDisable(false);
				}
			}
		});
	}

	/**
	 * Recibe la clase principal de la aplicación para gestionar la llamada a la
	 * siguiente ventana
	 * 
	 * @param mainApp es la clase principa de la aplicación
	 */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	/**
	 * Recibe el marco de la ventana que será controlada por la clase
	 * 
	 * @param stage es el marco de la ventana VerJuegos
	 */
	public void setDialog(Stage stage) {
		this.dialogVerJuegos = stage;
	}

	/**
	 * Cierra la ventana controlada por la clase y vuelve a la ventana Principal
	 */
	@FXML
	public void volver() {
		dialogVerJuegos.close();
	}

	/**
	 * Permite abrir la ventana DetallesJuego cuando se ha seleccionado un juego de
	 * la tabla
	 */
	@FXML
	public void ver() {
		Juego juego = tabla.getSelectionModel().getSelectedItem();
		mainApp.mostrarDetallesJuego(juego);
	}

	/**
	 * Permite abrir la ventana de ValorarJuego cuando se ha seleccionado un juego
	 * de la tabla
	 */
	@FXML
	public void valorar() {
		Juego juego = tabla.getSelectionModel().getSelectedItem();
		mainApp.mostrarValorarJuego(juego);
	}

	/**
	 * Carga en la tabla un listado de juegos cuyo estilo coincida con el
	 * seleccionado en el combobox de estilos
	 */
	public void cargarPorEstilo() {
		switch (comboBox.getSelectionModel().getSelectedItem()) {
		case "TODOS":
			listaJuegos = FXCollections.observableArrayList(juegoDAO.consultarTodas());
			break;
		case "ROL":
			listaJuegos = FXCollections.observableArrayList(juegoDAO.consultarPorEstilo(EstiloJuego.rol));
			break;
		case "ACCION":
			listaJuegos = FXCollections.observableArrayList(juegoDAO.consultarPorEstilo(EstiloJuego.accion));
			break;
		case "AVENTURA":
			listaJuegos = FXCollections.observableArrayList(juegoDAO.consultarPorEstilo(EstiloJuego.aventura));
			break;
		case "ESTRATEGIA":
			listaJuegos = FXCollections.observableArrayList(juegoDAO.consultarPorEstilo(EstiloJuego.estrategia));
			break;
		}
		// Calcula el porcentaje de votos positivos por cada juego encontrado
		for (Juego juego : listaJuegos) {
			juego.setValoracion();
		}

		// Prepara la tabla para recibir los datos de los objetos de la lista
		columnaNombre.setCellValueFactory(new PropertyValueFactory<Juego, String>("nombre"));
		columnaEstilo.setCellValueFactory(new PropertyValueFactory<Juego, EstiloJuego>("estilo"));
		columnaFecha.setCellValueFactory(new PropertyValueFactory<Juego, Date>("publicacion"));
		columnaPrecio.setCellValueFactory(new PropertyValueFactory<Juego, Double>("precio"));
		columnaValoracion.setCellValueFactory(new PropertyValueFactory<Juego, String>("valoracion"));

		// Actualiza la tabla por si tuviera información anterior y establece los nuevos
		// datos
		tabla.refresh();
		tabla.setItems(listaJuegos);
	}
}
