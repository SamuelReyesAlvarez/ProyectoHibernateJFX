package dam.samuel.vista;

import java.util.Date;

import dam.samuel.modelo.Empresa;
import dam.samuel.modelo.Juego;
import dam.samuel.modelo.Juego.EstiloJuego;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorVerDesarrolladores {

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

	public ControladorVerDesarrolladores() {

	}

	@FXML
	private void initialize() {
		listaEmpresas = FXCollections.observableArrayList();

		listaEmpresas.add(null);

		columnaEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nombre"));

		mostrarJuegosDeEmpresa(null);
	}

	public void setDialog(Stage stage) {
		this.dialogVerDesarrolladores = stage;
	}

	@FXML
	public void volver() {
		dialogVerDesarrolladores.close();
	}

	public void mostrarJuegosDeEmpresa(Empresa empresa) {
		listaJuegos = FXCollections.observableArrayList();

		if (empresa == null) {
			listaJuegos = null;
		} else {
			// TODO buscar en BD juegos de una empresa y cargar en el listado
			listaJuegos.add(null);
		}

		columnaJuego.setCellValueFactory(new PropertyValueFactory<Juego, String>("nombre"));
		columnaEstilo.setCellValueFactory(new PropertyValueFactory<Juego, EstiloJuego>("estilo"));
		columnaPublicacion.setCellValueFactory(new PropertyValueFactory<Juego, Date>("publicacion"));
		columnaPrecio.setCellValueFactory(new PropertyValueFactory<Juego, Double>("precio"));

		tablaJuego.setItems(listaJuegos);
	}
}
