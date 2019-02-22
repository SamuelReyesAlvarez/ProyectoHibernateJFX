package dam.samuel;

import java.io.IOException;

import dam.samuel.modelo.HibernateUtil;
import dam.samuel.modelo.Juego;
import dam.samuel.vista.ControladorDetallesJuego;
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
 * @version 0.9.10 (21/02/2019)
 *
 */
public class MainApp extends Application {

	private Stage stage;
	private Stage dialogPrincipal;
	private Stage dialogVerJuegos;
	private ControladorVerJuegos verJuegos;

	private boolean esAdministrador;

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

			ControladorLogin login = loader.getController();
			login.setStage(this);

			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarPrincipal(boolean esAdmin) {
		esAdministrador = esAdmin;
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

			ControladorPrincipal principal = loader.getController();
			principal.setMainApp(this);
			principal.setDialog(dialogPrincipal);
			principal.controlarOpciones(esAdministrador);

			stage.hide();
			configurarSesion();
			dialogPrincipal.showAndWait();
			cerrarSesion();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarVerJuegos() {
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

			verJuegos = loader.getController();
			verJuegos.setMainApp(this);
			verJuegos.setDialog(dialogVerJuegos);

			dialogVerJuegos.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarVerDesarrolladores() {
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

			ControladorVerDesarrolladores verDesarrolladores = loader.getController();
			verDesarrolladores.setDialog(dialogVerDesarrolladores);

			dialogVerDesarrolladores.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarNuevoJuego() {
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

			ControladorRegistroJuego registroJuego = loader.getController();
			registroJuego.setDialog(dialogRegistroJuego);

			dialogRegistroJuego.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarNuevoDesarrollador() {
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

			ControladorRegistroEmpresa registroEmpresa = loader.getController();
			registroEmpresa.setDialog(dialogRegistroEmpresa);

			dialogRegistroEmpresa.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarValorarJuego(Juego juego) {
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

			ControladorValorarJuego valorarJuego = loader.getController();
			valorarJuego.setJuego(juego);
			valorarJuego.setDialog(dialogValorarJuego);
			valorarJuego.mostrarDatosJuego();

			dialogValorarJuego.showAndWait();
			verJuegos.cargarPorEstilo();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void mostrarDetallesJuego(Juego juego) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/DetallesJuego.fxml"));

			Stage dialogDetallesJuego = new Stage();
			dialogDetallesJuego.setTitle("Valorator");
			dialogDetallesJuego.initOwner(dialogVerJuegos);
			dialogDetallesJuego.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogDetallesJuego.setScene(scene);
			dialogDetallesJuego.setResizable(false);

			ControladorDetallesJuego detallesJuego = loader.getController();
			detallesJuego.setJuego(juego);
			detallesJuego.setDialog(dialogDetallesJuego);
			detallesJuego.controlarOpciones(esAdministrador);
			detallesJuego.cargarDetallesJuego();

			dialogDetallesJuego.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
