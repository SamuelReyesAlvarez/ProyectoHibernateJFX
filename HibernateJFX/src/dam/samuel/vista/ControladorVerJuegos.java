package dam.samuel.vista;

import java.net.URL;
import java.util.Date;
import java.util.List;
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
	private MainApp stage;
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
	private TableColumn<Juego, Integer> columnaValoracion;
	@FXML
	private ComboBox<String> comboBox;
	@FXML
	private Button botonValorar;
	@FXML
	private Button botonVer;

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

		cargarPorEstilo();

		botonValorar.setDisable(true);
		botonVer.setDisable(true);

		tabla.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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

	public void setMainApp(MainApp stage) {
		this.stage = stage;
	}

	public void setDialog(Stage stage) {
		this.dialogVerJuegos = stage;
	}

	@FXML
	public void volver() {
		dialogVerJuegos.close();
	}

	@FXML
	public void ver() {
		Juego juego = tabla.getSelectionModel().getSelectedItem();
		stage.mostrarDetallesJuego(juego);
	}

	@FXML
	public void valorar() {
		Juego juego = tabla.getSelectionModel().getSelectedItem();
		stage.mostrarValorarJuego(juego);
	}

	public void cargarPorEstilo() {
		listaJuegos = FXCollections.observableArrayList();
		List<Juego> list = null;

		switch (comboBox.getSelectionModel().getSelectedItem()) {
		case "TODOS":
			list = juegoDAO.consultarTodas();
			break;
		case "ROL":
			list = juegoDAO.consultarPorEstilo(EstiloJuego.rol);
			break;
		case "ACCION":
			list = juegoDAO.consultarPorEstilo(EstiloJuego.accion);
			break;
		case "AVENTURA":
			list = juegoDAO.consultarPorEstilo(EstiloJuego.aventura);
			break;
		case "ESTRATEGIA":
			list = juegoDAO.consultarPorEstilo(EstiloJuego.estrategia);
			break;
		}

		for (Juego juego : list) {
			juego.setValoracion();
			listaJuegos.add(juego);
		}

		columnaNombre.setCellValueFactory(new PropertyValueFactory<Juego, String>("nombre"));
		columnaEstilo.setCellValueFactory(new PropertyValueFactory<Juego, EstiloJuego>("estilo"));
		columnaFecha.setCellValueFactory(new PropertyValueFactory<Juego, Date>("publicacion"));
		columnaPrecio.setCellValueFactory(new PropertyValueFactory<Juego, Double>("precio"));
		columnaValoracion.setCellValueFactory(new PropertyValueFactory<Juego, Integer>("valoracion"));

		tabla.refresh();
		tabla.setItems(listaJuegos);
	}
}
