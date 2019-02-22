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

	public ControladorVerDesarrolladores() {
	}

	@FXML
	private void initialize() {
		listaEmpresas = FXCollections.observableArrayList();
		List<Empresa> list = empresaDAO.consultarTodas();

		for (Empresa empresa : list) {
			listaEmpresas.add(empresa);
		}

		columnaEmpresa.setCellValueFactory(new PropertyValueFactory<Empresa, String>("nombre"));
		tablaEmpresa.setItems(listaEmpresas);
		tablaEmpresa.getSelectionModel().select(listaEmpresas.get(0));

		mostrarJuegosDeEmpresa(listaEmpresas.get(0));
		tablaEmpresa.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		tablaEmpresa.getSelectionModel().selectedItemProperty().addListener((observable, oldValue,
				newValue) -> mostrarJuegosDeEmpresa(tablaEmpresa.getSelectionModel().getSelectedItem()));
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
		Empresa emp;
		if (empresa != null) {
			emp = empresaDAO.consultarPorNombre(empresa);

			List<Juego> juegos = desarrollaDAO.consultarJuegosPorEmpresa(emp);

			for (Juego juego : juegos) {
				listaJuegos.add(juego);
			}
		}

		columnaJuego.setCellValueFactory(new PropertyValueFactory<Juego, String>("nombre"));
		columnaEstilo.setCellValueFactory(new PropertyValueFactory<Juego, EstiloJuego>("estilo"));
		columnaPublicacion.setCellValueFactory(new PropertyValueFactory<Juego, Date>("publicacion"));
		columnaPrecio.setCellValueFactory(new PropertyValueFactory<Juego, Double>("precio"));

		tablaJuego.setItems(listaJuegos);
		tablaJuego.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
	}
}
