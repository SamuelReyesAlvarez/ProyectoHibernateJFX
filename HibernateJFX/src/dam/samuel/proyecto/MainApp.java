package dam.samuel.proyecto;

import java.io.IOException;

import dam.samuel.proyecto.vista.ControladorLogin;
import dam.samuel.proyecto.vista.ControladorPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainApp extends Application {

	private static final String admin = "admin";
	private static final String clave = "123456";

	private Stage stage;

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

			Stage dialogStage = new Stage();
			dialogStage.setTitle("Valorator");
			dialogStage.initOwner(stage);
			dialogStage.initModality(Modality.WINDOW_MODAL);

			BorderPane border = (BorderPane) loader.load();
			Scene scene = new Scene(border);
			dialogStage.setScene(scene);
			dialogStage.setResizable(false);

			ControladorPrincipal principal = loader.<ControladorPrincipal>getController();
			principal.setStage(dialogStage);
			principal.controlarOpciones(esAdmin);

			stage.hide();
			dialogStage.showAndWait();
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
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
