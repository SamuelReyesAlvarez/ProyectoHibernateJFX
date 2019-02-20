package dam.samuel.vista;

import java.net.URL;
import java.util.ResourceBundle;

import dam.samuel.modelo.Juego;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * 
 * @author Samuel Reyes Alvarez
 *
 */
public class ControladorValorarJuego implements Initializable {

	@FXML
	private TextField textoNombre;
	@FXML
	private TextField textoPrecio;
	@FXML
	private TextField textoEstilo;
	@FXML
	private TextField textoPublicacion;
	@FXML
	private TextField textoDescripcion;
	@FXML
	private TextField textoComentario;
	@FXML
	private Button botonValorar;
	@FXML
	private ToggleButton botonPositivo;
	@FXML
	private ToggleButton botonNegativo;

	private Stage dialogValorarJuego;

	private Juego juego;

	public ControladorValorarJuego() {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		textoNombre.setText(juego.getNombre());
		textoPrecio.setText(String.valueOf(juego.getPrecio()));
		textoEstilo.setText(juego.getEstilo().toString());
		textoPublicacion.setText(String.format("dd/MM/yyyy", juego.getPublicacion()));
		textoDescripcion.setText(juego.getDescripcion());

		imagenesBotones();
	}

	public void setDialog(Stage stage) {
		this.dialogValorarJuego = stage;
	}

	@FXML
	public void volver() {
		dialogValorarJuego.close();
	}

	@FXML
	public void valorar() {

		// TODO crear valoracion e incluirla al juego
	}

	private void imagenesBotones() {
		URL linkPositivo = getClass().getResource("img/me-gusta.png");
		URL linkNegativo = getClass().getResource("img/no-me-gusta.png");

		Image imagenPositivo = new Image(linkPositivo.toString(), 24, 24, false, true);
		Image imagenNegativo = new Image(linkNegativo.toString(), 24, 24, false, true);

		botonPositivo.setGraphic(new ImageView(imagenPositivo));
		botonNegativo.setGraphic(new ImageView(imagenNegativo));
	}

	public void setJuego(Juego juego) {
		this.juego = juego;
	}
}
