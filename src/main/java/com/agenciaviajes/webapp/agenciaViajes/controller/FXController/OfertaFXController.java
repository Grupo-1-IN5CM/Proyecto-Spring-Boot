package com.agenciaviajes.webapp.agenciaViajes.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.agenciaviajes.webapp.agenciaViajes.system.Main;

import javafx.fxml.Initializable;
import lombok.Setter;

@Component
public class OfertaFXController implements Initializable{
    @Setter
    private Main stage;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
       
    }
    
}
