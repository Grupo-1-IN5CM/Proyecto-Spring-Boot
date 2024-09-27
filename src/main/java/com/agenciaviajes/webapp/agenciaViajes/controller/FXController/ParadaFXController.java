package com.agenciaviajes.webapp.agenciaViajes.controller.FXController;

import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenciaviajes.webapp.agenciaViajes.model.Parada;
import com.agenciaviajes.webapp.agenciaViajes.service.ParadaService;
import com.agenciaviajes.webapp.agenciaViajes.system.Main;

import com.agenciaviajes.webapp.agenciaViajes.utils.Alerta;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class ParadaFXController implements Initializable{
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfNombre, tfUbicacion, tfTiempo, tfTipoParada, tfBuscar;

    @FXML
    TableView tblParadas;

    @FXML
    TableColumn colId, colNombre, colUbicacion, colTiempo, colTipo;

    @FXML
    Button btnBuscar, btnAgregar, btnEliminar, btnClear , btnReturn;

    @Autowired
    ParadaService paradaService;

     @Autowired
    Alerta alerta;

    
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnAgregar) {
            if (tfId.getText().isBlank()) {
                agregarParada();
            }else{
                editarParada();
            }
        }else if(event.getSource() == btnBuscar){
            buscarPorId();
        }else if (event.getSource() == btnEliminar){
            eliminarParada();
        }else if(event.getSource() == btnReturn){
            stage.inicioView();
        }
    }

    public void cargarDatos(){
        tblParadas.getItems().clear();
        tblParadas.setItems(listarParada());
        colId.setCellValueFactory(new PropertyValueFactory<Parada, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Parada, String>("nombre"));
        colUbicacion.setCellValueFactory(new PropertyValueFactory<Parada, String>("ubicacion"));
        colTiempo.setCellValueFactory(new PropertyValueFactory<Parada, Time>("tiempo"));
        colTipo.setCellValueFactory(new PropertyValueFactory<Parada, String>("tipoParada"));  
    }

    public void borrarTextField(){
        tfId.clear();
        tfNombre.clear();
        tfUbicacion.clear();
        tfTiempo.clear();
        tfTipoParada.clear();
    }

    public void cargarTextField() {
        Parada parada = (Parada) tblParadas.getSelectionModel().getSelectedItem();
        if (parada != null) {
            tfId.setText(Long.toString(parada.getId()));
            tfNombre.setText(parada.getNombre());
            tfUbicacion.setText(parada.getUbicacion());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String tiempoString = sdf.format(parada.getTiempo());
            tfTiempo.setText(tiempoString);     
            tfTipoParada.setText(parada.getTipoParada());
        }
    }

    public ObservableList<Parada> listarParada(){
        return FXCollections.observableArrayList(paradaService.ListarParada());
    }

    public void agregarParada(){
        Parada parada = new Parada();
        parada.setNombre(tfNombre.getText());
        parada.setUbicacion(tfUbicacion.getText());
        parada.setTipoParada(tfTipoParada.getText());
        String timeString = tfTiempo.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
        cargarDatos();
        try {
            java.util.Date parsedDate = sdf.parse(timeString);
            Time time = new Time(parsedDate.getTime());
            parada.setTiempo(time);   
            paradaService.guardarParada(parada);    
        } catch (ParseException e) {
            System.err.println("Error al parsear el tiempo: " + e.getMessage());
        }
    }

    public void editarParada(){
        Parada parada = paradaService.busParadaPorId(Long.parseLong(tfId.getText()));
        parada.setNombre(tfNombre.getText());
        parada.setUbicacion(tfUbicacion.getText());
        String timeString = tfTiempo.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss"); 
        parada.setTipoParada(tfTipoParada.getText());
        try {
            java.util.Date parsedDate = sdf.parse(timeString);
            Time time = new Time(parsedDate.getTime());
            parada.setTiempo(time);   
            paradaService.guardarParada(parada);
            cargarDatos();    
        } catch (ParseException e) {
            System.err.println("Error al parsear el tiempo: " + e.getMessage());
        }
    }

    public void eliminarParada(){
        Parada parada = paradaService.busParadaPorId(Long.parseLong(tfId.getText()));
        paradaService.eliminarParada(parada);
        cargarDatos();
    
    public void agregarParada() {
        Parada parada = new Parada();
        try {
            parada.setNombre(tfNombre.getText());
            parada.setUbicacion(tfUbicacion.getText());
            parada.setTipoParada(tfTipoParada.getText());
            String timeString = tfTiempo.getText();
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            java.util.Date parsedDate = sdf.parse(timeString);
            Time time = new Time(parsedDate.getTime());
            parada.setTiempo(time);
            paradaService.guardarParada(parada);
            cargarDatos();
            borrarTextField();
            alerta.mostrarAlertInfo(123); // Registro completado
        } catch (ParseException e) {
            alerta.mostrarAlertInfo(341); // Campos faltantes
            System.err.println("Error al parsear el tiempo: " + e.getMessage());
        } catch (Exception e) {
            alerta.mostrarAlertInfo(341); // Campos faltantes
        }
    }

    public void editarParada() {
        try {
            Parada parada = paradaService.busParadaPorId(Long.parseLong(tfId.getText()));
            if (parada != null) {
                parada.setNombre(tfNombre.getText());
                parada.setUbicacion(tfUbicacion.getText());
                String timeString = tfTiempo.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
                parada.setTipoParada(tfTipoParada.getText());
                java.util.Date parsedDate = sdf.parse(timeString);
                Time time = new Time(parsedDate.getTime());
                parada.setTiempo(time);
                paradaService.guardarParada(parada);
                cargarDatos();
                borrarTextField();
                alerta.mostrarAlertInfo(789); // Edición exitosa
            } else {
                alerta.mostrarAlertInfo(341); // Campos faltantes
            }
        } catch (ParseException e) {
            alerta.mostrarAlertInfo(341); // Campos faltantes
            System.err.println("Error al parsear el tiempo: " + e.getMessage());
        } catch (NumberFormatException e) {
            alerta.mostrarAlertInfo(341); // ID inválido
            System.err.println("El ID ingresado no es válido: " + e.getMessage());
        } catch (Exception e) {
            alerta.mostrarAlertInfo(341); // Error inesperado
        }
    }

    public void eliminarParada() {
        try {
            Parada parada = paradaService.busParadaPorId(Long.parseLong(tfId.getText()));
            if (parada != null) {
                paradaService.eliminarParada(parada);
                cargarDatos();
                borrarTextField();
                alerta.mostrarAlertInfo(123); // Registro completado
            } else {
                alerta.mostrarAlertInfo(456); // No se pueden eliminar los datos
            }
        } catch (NumberFormatException e) {
            alerta.mostrarAlertInfo(341); // ID inválido
            System.err.println("El ID ingresado no es válido: " + e.getMessage());
        } catch (Exception e) {
            alerta.mostrarAlertInfo(341); // Error inesperado
        }
    }

    public void buscarPorId() {
        if (tfBuscar.getText().isBlank()) {
            cargarDatos();
        } else {
            try {
                Long id = Long.parseLong(tfBuscar.getText());
                Parada parada = paradaService.busParadaPorId(id);
                if (parada != null) {
                    tblParadas.getItems().clear();
                    tblParadas.getItems().add(parada);
                } else {
                    System.out.println("No se encontró ninguna parada con el ID: " + id);
                }
            } catch (NumberFormatException e) {
                System.err.println("El ID ingresado no es válido: " + e.getMessage());
            }
        }
    }    

                    alerta.mostrarAlertInfo(123); // Parada encontrada
                } else {
                    alerta.mostrarAlertInfo(341); // No se encontró la parada
                    System.out.println("No se encontró ninguna parada con el ID: " + id);
                }
            } catch (NumberFormatException e) {
                alerta.mostrarAlertInfo(341); // ID inválido
                System.err.println("El ID ingresado no es válido: " + e.getMessage());
            } catch (Exception e) {
                alerta.mostrarAlertInfo(341); // Error inesperado
            }
        }
    } 
}
