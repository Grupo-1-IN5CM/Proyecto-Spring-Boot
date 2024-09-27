package com.agenciaviajes.webapp.agenciaViajes.controller.FXController;

import java.net.URL;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenciaviajes.webapp.agenciaViajes.model.Ruta;
import com.agenciaviajes.webapp.agenciaViajes.service.RutaService;
import com.agenciaviajes.webapp.agenciaViajes.system.Main;

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
public class RutaFXController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfNombre, tfDistancia, tfDuracion, tfBuscar;

    @FXML
    TableView tblRutas;

    @FXML
    TableColumn colId, colNombre, colDistancia, colDuracion;

    @FXML
    Button btnEliminar, btnAgregar, btnClear, btnBuscar, btnReturn;

    @Autowired
    RutaService rutaService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }
    
    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnAgregar) {
            if (tfId.getText().isBlank() ) {
                agregarRuta();
            } else {
                editarRuta();
            }
        } else if (event.getSource() == btnEliminar) {
            eliminarRuta();
        } else if (event.getSource() == btnBuscar) {
            buscarPorId();
        }else if(event.getSource() == btnReturn){
            stage.inicioView();
        }
    }

    public void cargarDatos(){
        tblRutas.getItems().clear();
        tblRutas.setItems(listarRuta());
        colId.setCellValueFactory(new PropertyValueFactory<Ruta, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Ruta, String>("nombre"));
        colDistancia.setCellValueFactory(new PropertyValueFactory<Ruta, String>("distancia"));
        colDuracion.setCellValueFactory(new PropertyValueFactory<Ruta, Time>("duracion"));
    }

    public void cargarTextField(){
        Ruta ruta = (Ruta) tblRutas.getSelectionModel().getSelectedItem();
        if (ruta != null) {
            tfId.setText(Long.toString(ruta.getId()));
            tfNombre.setText(ruta.getNombre());
            tfDistancia.setText(ruta.getDistancia());
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String tiempoString = sdf.format(ruta.getDuracion());
            tfDuracion.setText(tiempoString);
        }
    }

    public void borrarTextField(){
        tfId.clear();
        tfNombre.clear();
        tfDistancia.clear();
        tfDuracion.clear();
    }

    public ObservableList<Ruta> listarRuta(){
        return FXCollections.observableArrayList(rutaService.ListarRuta());
    }

    public void agregarRuta(){
        Ruta ruta = new Ruta();
        ruta.setNombre(tfNombre.getText());
        ruta.setDistancia(tfDistancia.getText());
        String timeString = tfDuracion.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");        
        try {
            java.util.Date parsedDate = sdf.parse(timeString);
            Time time = new Time(parsedDate.getTime());
            ruta.setDuracion(time);   
            rutaService.guardarRuta(ruta);  
            cargarDatos();  
        } catch (ParseException e) {
            System.err.println("Error al parsear el tiempo: " + e.getMessage());
        }
    }

    public void editarRuta(){
        Ruta ruta = rutaService.busRutaPorId(Long.parseLong(tfId.getText()));
        ruta.setNombre(tfNombre.getText());
        ruta.setDistancia(tfDistancia.getText());
        String timeString = tfDuracion.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        try {
            java.util.Date parsedDate = sdf.parse(timeString);
            Time time = new Time(parsedDate.getTime());
            ruta.setDuracion(time);   
            rutaService.guardarRuta(ruta);
            cargarDatos();    
        } catch (ParseException e) {
            System.err.println("Error al parsear el tiempo: " + e.getMessage());
        }
    }

    public void eliminarRuta(){
        Ruta ruta = rutaService.busRutaPorId(Long.parseLong(tfId.getText()));
        rutaService.eliminarRuta(ruta);
        cargarDatos();
    }

    public void buscarPorId() {
        if (!tfBuscar.getText().isEmpty()) {
            Long id = Long.parseLong(tfBuscar.getText());
            Ruta ruta = rutaService.busRutaPorId(id);
            if (ruta != null) {
                ObservableList<Ruta> rutaEncontrada = FXCollections.observableArrayList(ruta);
                tblRutas.setItems(rutaEncontrada);
            } else {
                System.out.println("Ruta no encontrada");
                cargarDatos();
            }
        } else {
            cargarDatos();
        }
    }
    
}
