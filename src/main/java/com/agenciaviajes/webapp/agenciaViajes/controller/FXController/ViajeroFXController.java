package com.agenciaviajes.webapp.agenciaViajes.controller.FXController;

import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenciaviajes.webapp.agenciaViajes.model.Itinerario;
import com.agenciaviajes.webapp.agenciaViajes.model.Viajero;
import com.agenciaviajes.webapp.agenciaViajes.service.ItinerarioService;
import com.agenciaviajes.webapp.agenciaViajes.service.ViajeroService;
import com.agenciaviajes.webapp.agenciaViajes.system.Main;
import com.agenciaviajes.webapp.agenciaViajes.utils.Alerta;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class ViajeroFXController implements  Initializable{
    @Setter
    private Main stage;

    @FXML
    TextField tfId,tfApellido, tfNombre, tfCorreo, tfTelefono, tfReg, tfBuscar;

    @FXML
    TableView tblViajero;

    @FXML
    TableColumn colId, colNombre, colApellido, colCorreo, colTelefono, colReg, colItinerario;

    @FXML
    Button btnEliminar, btnClear, btnAgregar, btnReturn, btnBuscar;

    @FXML
    ComboBox cmbItinerario;

    @Autowired
    ViajeroService viajeroService;

    @Autowired
    ItinerarioService itinerarioService;

    @Autowired
    Alerta alerta;
    @Override
    public void initialize(URL url, ResourceBundle resources) {
       cargarDatos();
       cargarItinerario();
    }
    
    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnAgregar) {
            if (tfId.getText().isBlank()) {
                agregarViajero();
            }else{
                editarViajero();
            }
        }else if(event.getSource() == btnBuscar){
            buscarViajeroPorId();
        }else if (event.getSource() == btnEliminar){
            eliminarViajero();
        }else if(event.getSource() == btnReturn){
            stage.inicioView();
        }
    }

    public void cargarDatos(){
        tblViajero.getItems().clear();
        tblViajero.setItems(listarViajero());
        colId.setCellValueFactory(new PropertyValueFactory<Viajero, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Viajero, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Viajero, String>("apellido"));
        colCorreo.setCellValueFactory(new PropertyValueFactory<Viajero, String>("correo"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Viajero, String>("telefono"));
        colReg.setCellValueFactory(new PropertyValueFactory<Viajero, Date>("fechaRegistro"));
        colItinerario.setCellValueFactory(new PropertyValueFactory<>("itinerario"));
    }

    public void cargarItinerario() {
        ObservableList<Itinerario> itinerarios = FXCollections.observableArrayList(itinerarioService.listarItinerario());
        cmbItinerario.setItems(itinerarios);
    }    

    public void cargarTextField(){
        Viajero viajero = (Viajero)tblViajero.getSelectionModel().getSelectedItem();
        if (viajero != null ) {
            tfId.setText(Long.toString(viajero.getId()));
            tfNombre.setText(viajero.getNombre());
            tfApellido.setText(viajero.getApellido());
            tfCorreo.setText(viajero.getCorreo());
            tfTelefono.setText(viajero.getTelefono());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaString = dateFormat.format(viajero.getFechaRegistro());
            tfReg.setText(fechaString);
            cmbItinerario.getSelectionModel().select(viajero.getItinerario());
        }
    }

    public void borrarTextField(){
        tfId.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfCorreo.clear();
        tfTelefono.clear();
        tfReg.clear();
        cmbItinerario.getSelectionModel().clearSelection();
    }

    public ObservableList<Viajero> listarViajero(){
        return FXCollections.observableArrayList(viajeroService.listarViajeros());
    }
    
    public void agregarViajero() {
        try {
            Viajero viajero = new Viajero();
            viajero.setNombre(tfNombre.getText());
            viajero.setApellido(tfApellido.getText());
            viajero.setCorreo(tfCorreo.getText());
            viajero.setTelefono(tfTelefono.getText());
            viajero.setFechaRegistro(Date.valueOf(tfReg.getText()));
            viajero.setItinerario((Itinerario)cmbItinerario.getSelectionModel().getSelectedItem());
            viajeroService.guardarViajero(viajero);
            cargarDatos();
            borrarTextField();
            alerta.mostrarAlertInfo(123); // Viajero agregado exitosamente
        } catch (IllegalArgumentException e) {
            alerta.mostrarAlertInfo(341); // Error en los datos ingresados
            System.out.println("Error en los datos ingresados: " + e.getMessage());
        } catch (Exception e) {
            alerta.mostrarAlertInfo(341); // Error inesperado
            System.err.println("Error inesperado al agregar viajero: " + e.getMessage());
        }
    }

    public void editarViajero() {
        Viajero viajero = (Viajero) tblViajero.getSelectionModel().getSelectedItem();
        if (viajero != null) {
            viajero.setNombre(tfNombre.getText());
            viajero.setApellido(tfApellido.getText());
            viajero.setCorreo(tfCorreo.getText());
            viajero.setTelefono(tfTelefono.getText());
            viajero.setFechaRegistro(Date.valueOf(tfReg.getText()));
            viajero.setItinerario((Itinerario)cmbItinerario.getSelectionModel().getSelectedItem());
            viajeroService.guardarViajero(viajero);
            cargarDatos();
            borrarTextField();
            alerta.mostrarAlertInfo(789); // Viajero editado exitosamente
        } else {
            alerta.mostrarAlertInfo(456); // Selecciona un viajero para editar
            System.out.println("Selecciona un viajero para editar.");
        }
    }

    public void eliminarViajero() {
        Viajero viajero = (Viajero)tblViajero.getSelectionModel().getSelectedItem();
        if (viajero != null) {
            viajeroService.eliminarViajero(viajero);
            cargarDatos();
            borrarTextField();
            alerta.mostrarAlertInfo(123); // Viajero eliminado exitosamente
        } else {
            alerta.mostrarAlertInfo(456); // Selecciona un viajero para eliminar
            System.out.println("Selecciona un viajero para eliminar.");
        }
    }

    public void buscarViajeroPorId() {
        if (tfBuscar.getText().isBlank()) {
            cargarDatos();
        } else {
            try {
                Long id = Long.parseLong(tfBuscar.getText());
                Viajero viajero = viajeroService.buscarViajeroPorId(id);
                if (viajero != null) {
                    tblViajero.getItems().clear();
                    tblViajero.setItems(FXCollections.observableArrayList(viajero));
                    alerta.mostrarAlertInfo(123); // Viajero encontrado
                } else {
                    alerta.mostrarAlertInfo(456); // Viajero no encontrado
                    System.out.println("Viajero no encontrado");
                    tblViajero.getItems().clear();
                }
            } catch (NumberFormatException e) {
                alerta.mostrarAlertInfo(341); // ID inválido
                System.out.println("ID inválido: " + e.getMessage());
            } catch (Exception e) {
                alerta.mostrarAlertInfo(341); // Error inesperado
                System.err.println("Error inesperado al buscar viajero: " + e.getMessage());
            }
        }
    } 
}
