package dam.samuel.proyecto;

import java.io.IOException;

import dam.samuel.proyecto.modelo.Empresa;
import dam.samuel.proyecto.modelo.Juego;
import dam.samuel.proyecto.vista.ControladorLogin;
import dam.samuel.proyecto.vista.ControladorPrincipal;
import dam.samuel.proyecto.vista.ControladorVerJuegos;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private static final String admin = "admin";
	private static final String clave = "123456";

	private ObservableList<Juego> listaJuegos = FXCollections.observableArrayList();
	private ObservableList<Empresa> listaEmpresas = FXCollections.observableArrayList();

	private Stage stage;
	private static Stage dialogPrincipal;

	@Override
	public void start(Stage primaryStage) {
		try {
			stage = primaryStage;
			stage.setTitle("Inicio de sesion");

			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/Login.fxml"));

			AnchorPane anchor = (AnchorPane) loader.load();
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
			principal.setStage(dialogPrincipal);
			principal.controlarOpciones(esAdmin);

			stage.hide();
			dialogPrincipal.showAndWait();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Juego> getListaJuegos() {
		return listaJuegos;
	}

	public static void mostrarVerJuegos() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("vista/Principal.fxml"));

			Stage dialogVerJuegos = new Stage();
			dialogVerJuegos.setTitle("Valorator");
			dialogVerJuegos.initOwner(dialogPrincipal);
			dialogVerJuegos.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogVerJuegos.setScene(scene);
			dialogVerJuegos.setResizable(false);

			ControladorVerJuegos principal = loader.<ControladorVerJuegos>getController();
			principal.setStage(dialogVerJuegos);

			dialogPrincipal.hide();
			dialogVerJuegos.showAndWait();
			dialogPrincipal.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ObservableList<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public String getAdmin() {
		return admin;
	}

	public String getClave() {
		return clave;
	}
}
