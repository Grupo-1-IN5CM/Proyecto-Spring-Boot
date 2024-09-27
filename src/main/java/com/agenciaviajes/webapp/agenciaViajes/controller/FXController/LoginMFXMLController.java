package com.agenciaviajes.webapp.agenciaViajes.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.agenciaviajes.webapp.agenciaViajes.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import lombok.Setter;

@Component
public class LoginMFXMLController implements Initializable{
    @Setter
    private Main stage;
    @FXML
    private Button btnAcceder;
    @Override
    public void initialize(URL url, ResourceBundle resources) {
    }
    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAcceder) {
            stage.inicioView();
        }
    }

}