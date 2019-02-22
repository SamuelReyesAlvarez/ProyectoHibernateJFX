package dam.samuel.vista;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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

public class ControladorDetallesJuego implements Initializable {

	private ValoracionDAO valoracionDAO = new ValoracionDAO();
	private EmpresaDAO empresaDAO = new EmpresaDAO();
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

	public ControladorDetallesJuego() {
	}

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

	@FXML
	private void volver() {
		dialogDetallesJuego.close();
	}

	@FXML
	public void borrar() {
		Valoracion valoracion = tablaValoracion.getSelectionModel().getSelectedItem();

		try {
			Alert alerta = new Alert(AlertType.CONFIRMATION);
			alerta.setTitle("Peticion");
			alerta.setHeaderText("Se solicita confirmar la accion");
			alerta.setContentText("¿Esta seguro de borrar el objeto seleccionado?");

			Optional<ButtonType> result = alerta.showAndWait();

			if (result.get() == ButtonType.OK) {
				valoracionDAO.borrar(valoracion);

				Alert hecho = new Alert(AlertType.CONFIRMATION);
				hecho.setTitle("Confirmacion");
				hecho.setHeaderText("Mensaje de borrado");
				hecho.setContentText("Se ha borrado el objeto");
			}
		} catch (ValoratorException e) {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setHeaderText("Error de borrado");
			alerta.setContentText(e.getMessage());
			alerta.showAndWait();
		}
	}

	@FXML
	private void guardar() {
		try {
			juego.setNombre(textoNombre.getText());
			if (comboEstilo.getValue().contains("<")) {
				juego.setEstilo(null);
			} else {
				juego.setEstilo(EstiloJuego.values()[(comboEstilo.getSelectionModel().getSelectedIndex() - 1)]);
			}
			juego.setPublicacion(textoPublicacion.getValue());
			juego.setPrecio(Double.parseDouble(textoPrecio.getText()));
			juego.setDescripcion(textoDescripcion.getText());
			juego.setListaDesarrolladores(crearListaDesarrolladores(juego, crearListaEmpresas()));
			juegoDAO.guardar(juego);

			Alert alerta = new Alert(AlertType.INFORMATION);
			alerta.setTitle("Confirmacion");
			alerta.setHeaderText("Mensaje de actualizacion");
			alerta.setContentText("Se han actualizado los datos del Juego");
			alerta.showAndWait();
			volver();
		} catch (ValoratorException ve) {
			mostrarError("No se pudo actualizar los datos del juego");
		} catch (IllegalArgumentException iae) {
			mostrarError("Compruebe que todos los campos sean correctos");
		} catch (NullPointerException npe) {
			mostrarError("Compruebe que todos los campos estan rellenos");
		} catch (MySQLIntegrityConstraintViolationException e) {
			mostrarError("No deberia lanzar esta excepcion");
		}
	}

	private void mostrarError(String mensaje) {
		Alert alerta = new Alert(AlertType.ERROR);
		alerta.setTitle("Error");
		alerta.setHeaderText("Error de actualizacion");
		alerta.setContentText(mensaje);
		alerta.showAndWait();
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

	public void setJuego(Juego juego) {
		this.juego = juegoDAO.consultarPorId(juego);
	}

	public void setDialog(Stage stage) {
		this.dialogDetallesJuego = stage;
	}

	public void controlarOpciones(boolean esAdministrador) {
		botonGuardar.setVisible(esAdministrador);
		botonBorrar.setVisible(esAdministrador);
		textoNombre.setEditable(esAdministrador);
		comboEstilo.setDisable(!esAdministrador);
		textoPublicacion.setDisable(!esAdministrador);
		textoPrecio.setEditable(esAdministrador);
		textoDesarrolladores.setEditable(esAdministrador);
		textoDescripcion.setEditable(esAdministrador);

		comboEstilo.setStyle("-fx-opacity: 1;");
		textoPublicacion.setStyle("-fx-opacity: 1;");
	}

	public void cargarDetallesJuego() {
		textoNombre.setText(juego.getNombre());
		if (juego.getEstilo() != null) {
			comboEstilo.getSelectionModel().select(juego.getEstilo().ordinal() + 1);
		} else {
			comboEstilo.getSelectionModel().select(0);
		}
		textoPublicacion.setPromptText(new SimpleDateFormat("dd-MM-yyyy").format(juego.getPublicacion()));
		textoPrecio.setText(String.valueOf(juego.getPrecio()));

		StringBuilder desarrolladores = new StringBuilder();
		for (Desarrolla desarrollador : juego.getListaDesarrolladores()) {
			desarrolladores.append(desarrollador.getEmpresa().getNombre() + ", ");
		}
		textoDesarrolladores.setText(desarrolladores.toString());
		textoDescripcion.setText(juego.getDescripcion());

		listaValoracion = FXCollections.observableArrayList();
		List<Valoracion> lista = valoracionDAO.consultarPorJuego(juego);

		for (Valoracion valoracion : lista) {
			listaValoracion.add(valoracion);
		}

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
