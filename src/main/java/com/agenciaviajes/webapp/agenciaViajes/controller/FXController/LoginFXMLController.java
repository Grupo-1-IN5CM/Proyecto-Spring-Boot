package com.agenciaviajes.webapp.agenciaViajes.controller.FXController;

import com.agenciaviajes.webapp.agenciaViajes.model.Usuario;
import com.agenciaviajes.webapp.agenciaViajes.service.UsuarioService;
import com.agenciaviajes.webapp.agenciaViajes.system.Main;
import com.agenciaviajes.webapp.agenciaViajes.utils.ContraUtils;
import com.agenciaviajes.webapp.agenciaViajes.utils.Alerta;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.Initializable;
import java.net.URL;

import java.util.ResourceBundle;
import lombok.Setter;


import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginFXMLController implements Initializable {
    @Setter
    private Main stage;

    @Autowired
    private UsuarioService usuarioService;

    @FXML
    private Button btnAcceder, btnRegistrarse;

    @FXML
    private TextField tfUsuar;

    @Autowired
    ContraUtils contra;

    @FXML
    private PasswordField tfContraseña;

    // Método de inicialización de Initializable
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Aquí puedes inicializar componentes o cargar datos si es necesario.
        // Si hay alguna configuración que dependa de `stage`, hazla aquí.
    }

   @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnAcceder) {
            Usuario usuario = usuarioService.busUsuarioPorNombre(tfUsuar.getText());
            if (usuario != null) {
                // Verificar la contraseña con BCrypt
                if (BCrypt.checkpw(tfContraseña.getText(), usuario.getContraseña())) {
                    stage.loginMView();  // Asegúrate de que `stage` esté correctamente inicializado antes de llamar a este método
                } else {
                    // Contraseña incorrecta
                    System.out.println("Contraseña incorrecta");
                }
            } else {
                // Usuario no encontrado
                System.out.println("Usuario no encontrado");
            }
        } else if (event.getSource() == btnRegistrarse) {
            stage.registroView();
        }
    }

}

