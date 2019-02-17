package dam.samuel.vista;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import dam.samuel.modelo.Juego;
import dam.samuel.modelo.Juego.EstiloJuego;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
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

	private ObservableList<Juego> listaJuegos;
	private ObservableList<String> listaEstilos;

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
	private TableColumn<Juego, Integer> columnaValoracion;
	@FXML
	private ComboBox<String> comboBox;

	private Stage dialogVerJuegos;

	public ControladorVerJuegos() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		listaEstilos = FXCollections.observableArrayList();
		listaEstilos.add("todos".toUpperCase());
		for (EstiloJuego estilo : EstiloJuego.values()) {
			listaEstilos.add(estilo.toString().toUpperCase());
		}

		comboBox.setItems(listaEstilos);
		comboBox.getSelectionModel().selectFirst();

		listaJuegos = FXCollections.observableArrayList();
		// TODO buscar en BD juegos por estilo seleccionado y cargar liestado
		listaJuegos = null;

		columnaNombre.setCellValueFactory(new PropertyValueFactory<Juego, String>("nombre"));
		columnaEstilo.setCellValueFactory(new PropertyValueFactory<Juego, EstiloJuego>("estilo"));
		columnaFecha.setCellValueFactory(new PropertyValueFactory<Juego, Date>("publicacion"));
		columnaPrecio.setCellValueFactory(new PropertyValueFactory<Juego, Double>("precio"));
		columnaValoracion.setCellValueFactory(new PropertyValueFactory<Juego, Integer>("valoracion"));

		tabla.setItems(listaJuegos);
	}

	public void setDialog(Stage stage) {
		this.dialogVerJuegos = stage;
	}

	@FXML
	public void volver() {
		dialogVerJuegos.close();
	}

	public void cargarPorEstilo() {
		// TODO busqueda en BD por el estilo seleccionado
	}
}
