package dam.samuel.proyecto.vista;

import dam.samuel.proyecto.modelo.Juego;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ControladorVerJuegos {

	@FXML
	private TableView<Juego> tabla;
	@FXML
	private TableColumn<Juego, String> columnaNombre;
	@FXML
	private TableColumn<Juego, String> columnaEstilo;
	@FXML
	private TableColumn<Juego, String> columnaFecha;
	@FXML
	private TableColumn<Juego, String> columnaPrecio;
	@FXML
	private TableColumn<Juego, String> columnaValoracion;

	private Stage stage;

	public ControladorVerJuegos() {

	}

	@FXML
	private void initialize() {
		columnaNombre.setCellValueFactory(cellData -> cellData.getValue().propiedadNombre());
		columnaEstilo.setCellValueFactory(cellData -> cellData.getValue().propiedadEstilo());
		columnaFecha.setCellValueFactory(cellData -> cellData.getValue().propiedadPublicacion().asString());
		columnaPrecio.setCellValueFactory(cellData -> cellData.getValue().propiedadPrecio().asString());
		columnaValoracion.setCellValueFactory(cellData -> cellData.getValue().propiedadValoracion().asString());
	}

	public void setStage(Stage dialogStage) {
		this.stage = dialogStage;
	}

	@FXML
	public void volver() {
		stage.close();
	}
}
