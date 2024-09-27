package com.agenciaviajes.webapp.agenciaViajes.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.agenciaviajes.webapp.agenciaViajes.utils.ContraUtils;
import com.agenciaviajes.webapp.agenciaViajes.model.Usuario;
import com.agenciaviajes.webapp.agenciaViajes.system.Main;
import com.agenciaviajes.webapp.agenciaViajes.service.UsuarioService;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import lombok.Setter;

@Component
public class RegistrarFXMLController  implements Initializable{
    @Setter
    private Main stage;
    @FXML
    TextField tfNombreU, tfContraseña;
    @FXML
    ComboBox combViajero;
    @FXML 
    Button btnRegistrar,btnCancelar,btnViajero;
    @Autowired
    ContraUtils contra;
    @Autowired
    UsuarioService usuario;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        
    }

    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnRegistrar) {
            agregarUsuario();
            stage.loginView();
        }else if(event.getSource() == btnCancelar){
            borrarTextField();
        }else if (event.getSource() == btnViajero){
            stage.registroViajeroView();
        }
    }

    public void agregarUsuario(){
        Usuario usuar = new Usuario();
        usuar.setNombre(tfNombreU.getText());
        usuar.setContraseña(contra.encryptedPassword(tfContraseña.getText()));
        usuario.guardarUsuario(usuar);
    }

    public void borrarTextField(){
        tfNombreU.clear();
        tfContraseña.clear();
        combViajero.getSelectionModel().clearSelection();
    }

}
