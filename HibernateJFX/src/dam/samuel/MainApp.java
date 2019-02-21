package dam.samuel;

import java.io.IOException;

import dam.samuel.modelo.HibernateUtil;
import dam.samuel.modelo.Juego;
import dam.samuel.vista.ControladorLogin;
import dam.samuel.vista.ControladorPrincipal;
import dam.samuel.vista.ControladorRegistroEmpresa;
import dam.samuel.vista.ControladorRegistroJuego;
import dam.samuel.vista.ControladorValorarJuego;
import dam.samuel.vista.ControladorVerDesarrolladores;
import dam.samuel.vista.ControladorVerJuegos;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * 
 * @author Samuel Reyes Alvarez
 * 
 * @version 0.8.9 (21/02/2019)
 *
 */
public class MainApp extends Application {

	private Stage stage;
	private Stage dialogPrincipal;
	private Stage dialogVerJuegos;
	private ControladorVerJuegos verJuegos;

	@Override
	public void start(Stage primaryStage) {

		try {
			stage = primaryStage;
			stage.setTitle("Inicio de sesion");

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/Login.fxml"));

			VBox anchor = (VBox) loader.load();
			Scene scene = new Scene(anchor);
			stage.setScene(scene);
			stage.setResizable(false);

			ControladorLogin login = loader.<ControladorLogin>getController();
			login.setStage(this);

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarPrincipal(boolean esAdmin) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/Principal.fxml"));

			dialogPrincipal = new Stage();
			dialogPrincipal.setTitle("Valorator");
			dialogPrincipal.initOwner(stage);
			dialogPrincipal.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogPrincipal.setScene(scene);
			dialogPrincipal.setResizable(false);

			ControladorPrincipal principal = loader.<ControladorPrincipal>getController();
			principal.setMainApp(this);
			principal.setDialog(dialogPrincipal);
			principal.controlarOpciones(esAdmin);

			stage.hide();
			dialogPrincipal.showAndWait();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarVerJuegos() {
		configurarSesion();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/VerJuegos.fxml"));

			dialogVerJuegos = new Stage();
			dialogVerJuegos.setTitle("Valorator");
			dialogVerJuegos.initOwner(dialogPrincipal);
			dialogVerJuegos.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogVerJuegos.setScene(scene);
			dialogVerJuegos.setResizable(false);

			verJuegos = loader.<ControladorVerJuegos>getController();
			verJuegos.setMainApp(this);
			verJuegos.setDialog(dialogVerJuegos);

			dialogVerJuegos.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cerrarSesion();
	}

	public void mostrarVerDesarrolladores() {
		configurarSesion();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/VerDesarrolladores.fxml"));

			Stage dialogVerDesarrolladores = new Stage();
			dialogVerDesarrolladores.setTitle("Valorator");
			dialogVerDesarrolladores.initOwner(dialogPrincipal);
			dialogVerDesarrolladores.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogVerDesarrolladores.setScene(scene);
			dialogVerDesarrolladores.setResizable(false);

			ControladorVerDesarrolladores verDesarrolladores = loader.<ControladorVerDesarrolladores>getController();
			verDesarrolladores.setDialog(dialogVerDesarrolladores);

			dialogVerDesarrolladores.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cerrarSesion();
	}

	public void mostrarNuevoJuego() {
		configurarSesion();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/RegistroJuego.fxml"));

			Stage dialogRegistroJuego = new Stage();
			dialogRegistroJuego.setTitle("Valorator");
			dialogRegistroJuego.initOwner(dialogPrincipal);
			dialogRegistroJuego.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogRegistroJuego.setScene(scene);
			dialogRegistroJuego.setResizable(false);

			ControladorRegistroJuego registroJuego = loader.<ControladorRegistroJuego>getController();
			registroJuego.setDialog(dialogRegistroJuego);

			dialogRegistroJuego.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cerrarSesion();
	}

	public void mostrarNuevoDesarrollador() {
		configurarSesion();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/RegistroEmpresa.fxml"));

			Stage dialogRegistroEmpresa = new Stage();
			dialogRegistroEmpresa.setTitle("Valorator");
			dialogRegistroEmpresa.initOwner(dialogPrincipal);
			dialogRegistroEmpresa.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogRegistroEmpresa.setScene(scene);
			dialogRegistroEmpresa.setResizable(false);

			ControladorRegistroEmpresa registroEmpresa = loader.<ControladorRegistroEmpresa>getController();
			registroEmpresa.setDialog(dialogRegistroEmpresa);

			dialogRegistroEmpresa.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
		cerrarSesion();
	}

	public void mostrarValorarJuego(Juego juego) {
		configurarSesion();
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/ValorarJuego.fxml"));

			Stage dialogValorarJuego = new Stage();
			dialogValorarJuego.setTitle("Valorator");
			dialogValorarJuego.initOwner(dialogVerJuegos);
			dialogValorarJuego.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogValorarJuego.setScene(scene);
			dialogValorarJuego.setResizable(false);

			ControladorValorarJuego valorarJuego = loader.<ControladorValorarJuego>getController();
			valorarJuego.setJuego(juego);
			valorarJuego.setDialog(dialogValorarJuego);
			valorarJuego.mostrarDatosJuego();

			dialogValorarJuego.showAndWait();
			verJuegos.cargarPorEstilo();

		} catch (IOException e) {
			e.printStackTrace();
		}
		cerrarSesion();
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void cerrarSesion() {
		HibernateUtil.closeSessionFactory();
	}

	public void configurarSesion() {
		HibernateUtil.buildSessionFactory();
		HibernateUtil.openSessionAndBindToThread();
	}
}
