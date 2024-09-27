package com.agenciaviajes.webapp.agenciaViajes.system;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.agenciaviajes.webapp.agenciaViajes.AgenciaViajesApplication;
import com.agenciaviajes.webapp.agenciaViajes.controller.FXController.InicioFXMLController;
import com.agenciaviajes.webapp.agenciaViajes.controller.FXController.ItinerarioFXController;
import com.agenciaviajes.webapp.agenciaViajes.controller.FXController.LoginFXMLController;
import com.agenciaviajes.webapp.agenciaViajes.controller.FXController.LoginMFXMLController;
import com.agenciaviajes.webapp.agenciaViajes.controller.FXController.ParadaFXController;
import com.agenciaviajes.webapp.agenciaViajes.controller.FXController.RegistrarFXMLController;
import com.agenciaviajes.webapp.agenciaViajes.controller.FXController.RutaFXController;
import com.agenciaviajes.webapp.agenciaViajes.controller.FXController.VehiculoFXController;
import com.agenciaviajes.webapp.agenciaViajes.controller.FXController.ViajeroFXController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{

    private ConfigurableApplicationContext applicationContext;
    private Stage stage;
    private Scene scene;

    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(AgenciaViajesApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage;
        stage.setTitle("Agencia de Viajes");
        Image image = new Image(getClass().getResource("/image/Logo.png").toString());
        stage.getIcons().add(image);
        loginView();
        stage.show();
    }

    public Initializable cambioEscena(String fxmlName, int width, int heigth) throws IOException{
        Initializable initializable = null;
        FXMLLoader loader = new FXMLLoader();
        
        loader.setControllerFactory(applicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName));

        scene = new Scene((AnchorPane)loader.load(archivo), width, heigth);
        stage.setScene(scene);
        stage.sizeToScene();

        initializable = (Initializable)loader.getController();

        return initializable;
    }

    public void loginView() {
        try {
            LoginFXMLController loginView = (LoginFXMLController) cambioEscena("Login.fxml", 700, 450);
            loginView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loginMView() {
        try {
            LoginMFXMLController loginMView = (LoginMFXMLController) cambioEscena("LoginM.fxml", 700, 450);
            loginMView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registroView() {
        try {
            RegistrarFXMLController registroView = (RegistrarFXMLController) cambioEscena("Registro.fxml", 470, 700);
            registroView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void inicioView(){
        try {
            InicioFXMLController inicioView = (InicioFXMLController)cambioEscena("Inicio.fxml", 1200, 750);
            inicioView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void vehiculoFXView(){
        try {
            VehiculoFXController vehiculoFXView = (VehiculoFXController)cambioEscena("VehiculoView.fxml", 1200, 750);
            vehiculoFXView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void paradaFXView(){
        try {
            ParadaFXController paradaFXView = (ParadaFXController)cambioEscena("Parada.fxml", 1200, 750);
            paradaFXView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void rutaFXView(){
        try {
            RutaFXController rutaFXView = (RutaFXController)cambioEscena("Ruta.fxml", 1200, 750);
            rutaFXView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void itinerarioFXView(){
        try {
            ItinerarioFXController itinerarioFXView = (ItinerarioFXController)cambioEscena("Itinerario.fxml", 1200, 750);
            itinerarioFXView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viajeroFXView(){
        try {
            ViajeroFXController viajeroFXView = (ViajeroFXController)cambioEscena("Viajero.fxml", 1200, 750);
            viajeroFXView.setStage(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ofertaFXView(){
        
    }
}