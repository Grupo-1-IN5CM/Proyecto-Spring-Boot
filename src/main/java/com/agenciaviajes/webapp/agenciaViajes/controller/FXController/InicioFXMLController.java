package com.agenciaviajes.webapp.agenciaViajes.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.agenciaviajes.webapp.agenciaViajes.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import lombok.Setter;

@Component
public class InicioFXMLController implements Initializable {

    @Setter
    private Main stage;

    @FXML
    private MenuItem btnRuta,btnParada,btnItinerario,btnViajero,btnVehiculo,btnOferta,btnComentario;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if(event.getSource() == btnRuta){
            stage.rutaFXView();
        }else if(event.getSource() == btnParada){
            stage.paradaFXView();
        }else if(event.getSource() == btnItinerario){
            stage.itinerarioFXView();
        }else if(event.getSource() == btnViajero){
            stage.viajeroFXView();
        }else if(event.getSource() == btnVehiculo){
            stage.vehiculoFXView();
        }else if(event.getSource() == btnOferta){
            stage.ofertaFXView();
        }else if(event.getSource() == btnComentario){
            //stage.comentarioView();
        }
    }
}
