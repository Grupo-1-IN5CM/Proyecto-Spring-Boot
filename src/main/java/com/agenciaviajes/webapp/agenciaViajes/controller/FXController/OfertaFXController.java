package com.agenciaviajes.webapp.agenciaViajes.controller.FXController;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenciaviajes.webapp.agenciaViajes.model.Oferta;
import com.agenciaviajes.webapp.agenciaViajes.model.Ruta;
import com.agenciaviajes.webapp.agenciaViajes.model.Vehiculo;
import com.agenciaviajes.webapp.agenciaViajes.model.Viajero;
import com.agenciaviajes.webapp.agenciaViajes.service.OfertaService;
import com.agenciaviajes.webapp.agenciaViajes.service.RutaService;
import com.agenciaviajes.webapp.agenciaViajes.service.VehiculoService;
import com.agenciaviajes.webapp.agenciaViajes.service.ViajeroService;
import com.agenciaviajes.webapp.agenciaViajes.system.Main;

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
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.agenciaviajes.webapp.agenciaViajes.system.Main;

import javafx.fxml.Initializable;

import lombok.Setter;

@Component
public class OfertaFXController implements Initializable{
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfFIni, tfFfin, tfTiempo, tfTparada, tfBuscar;

    @FXML
    TableView tblOfertas;

    @FXML
    TableColumn  colId,colRuta, colInicio, colFin, colTiempo, colTipo, colVehiculo, colViajero;

    @FXML
    Button btnEliminar, btnClear, btnAgregar, btnBuscar, btnReturn ;

    @FXML
    ComboBox cmbVehiculo, cmbViajero, cmbRuta;

    @Autowired
    OfertaService ofertaService;

    @Autowired
    VehiculoService vehiculoService;

    @Autowired
    RutaService rutaService;
    
    @Autowired
    ViajeroService viajeroService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
       cargarDatos();
       cargarVehiculo();
       cargarRutas();
       cargarViajero();
    }
    
    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnAgregar) {
            if (tfId.getText().isBlank()) {
                agregarOferta();
            }else{
                editarOferta();
            }
        }else if(event.getSource() == btnBuscar){
            buscarOfertaPorId();
        }else if (event.getSource() == btnEliminar){
            eliminarOferta();
        }else if(event.getSource() == btnReturn){
            stage.inicioView();
        }
    }

    public void cargarDatos(){
        tblOfertas.getItems().clear();
        tblOfertas.setItems(listarOferta());
        colId.setCellValueFactory(new PropertyValueFactory<Oferta, Long>("id"));
        colInicio.setCellValueFactory(new PropertyValueFactory<Oferta, Date>("fechaInicio"));
        colFin.setCellValueFactory(new PropertyValueFactory<Oferta, Date>("fechaFinal"));
        colTiempo.setCellValueFactory(new PropertyValueFactory<Oferta, Time>("tiempo"));
        colTipo.setCellValueFactory(new PropertyValueFactory<Oferta, String>("tipoParada"));
        colVehiculo.setCellValueFactory(new PropertyValueFactory<>("vehiculo"));
        colRuta.setCellValueFactory(new PropertyValueFactory<>("ruta"));
        colViajero.setCellValueFactory(new PropertyValueFactory<>("viajero"));
    
    }

    public void cargarVehiculo() {
        ObservableList<Vehiculo> vehiculos = FXCollections.observableArrayList(vehiculoService.ListarVehiculo());
        cmbVehiculo.setItems(vehiculos);
    }  

    private void cargarRutas() {
        ObservableList<Ruta> rutas = FXCollections.observableArrayList(rutaService.ListarRuta());
        cmbRuta.setItems(rutas);
    }
    
    public void cargarViajero(){
        ObservableList<Viajero> viajeros = FXCollections.observableArrayList(viajeroService.listarViajeros());
        cmbViajero.setItems(viajeros);
    }

    public void cargarTextField(){
        Oferta oferta = (Oferta)tblOfertas.getSelectionModel().getSelectedItem();
        if (oferta != null) {
            tfId.setText(Long.toString(oferta.getId()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaString = dateFormat.format(oferta.getFechaInicio());
            tfFIni.setText(fechaString);
            String fechaFinString = dateFormat.format(oferta.getFechaFinal());
            tfFfin.setText(fechaFinString);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String tiempoString = sdf.format(oferta.getTiempo());
            tfTiempo.setText(tiempoString);
            tfTparada.setText(oferta.getTipoParada());
            cmbVehiculo.getSelectionModel().select(oferta.getVehiculo());
            cmbRuta.getSelectionModel().select(oferta.getRuta());
            cmbViajero.getSelectionModel().select(oferta.getViajero());
        }
    }

    public void borrarTextField(){
        tfId.clear();
        tfFIni.clear();
        tfFfin.clear();
        tfTiempo.clear();
        tfTparada.clear();
        cmbVehiculo.getSelectionModel().clearSelection();
        cmbRuta.getSelectionModel().clearSelection();
        cmbViajero.getSelectionModel().clearSelection();
    }

    public ObservableList<Oferta> listarOferta(){
        return FXCollections.observableArrayList(ofertaService.ListarOferta());
    }

    public void agregarOferta() {
        try {
            Oferta oferta = new Oferta();
            oferta.setFechaInicio(Date.valueOf(tfFIni.getText()));
            oferta.setFechaFinal(Date.valueOf(tfFfin.getText()));
            oferta.setTiempo(Time.valueOf(tfTiempo.getText()));
            oferta.setTipoParada(tfTparada.getText());
            oferta.setVehiculo((Vehiculo) cmbVehiculo.getSelectionModel().getSelectedItem());
            oferta.setRuta((Ruta) cmbRuta.getSelectionModel().getSelectedItem());
            List<Viajero> viajeros = new ArrayList<>();
            viajeros.add((Viajero) cmbViajero.getSelectionModel().getSelectedItem());
            oferta.setViajero(viajeros); // Establecer la lista en lugar de un solo viajero
    
            ofertaService.guardarOferta(oferta);
            cargarDatos(); 
            borrarTextField();
        } catch (IllegalArgumentException e) {
            System.out.println("Error en los datos ingresados: " + e.getMessage());
        }
    }
    
    
    public void editarOferta() {
        Oferta oferta = (Oferta) tblOfertas.getSelectionModel().getSelectedItem();
        if (oferta != null) {
            oferta.setFechaInicio(Date.valueOf(tfFIni.getText()));
            oferta.setFechaFinal(Date.valueOf(tfFfin.getText()));
            oferta.setTiempo(Time.valueOf(tfTiempo.getText()));
            oferta.setTipoParada(tfTparada.getText());
            oferta.setVehiculo((Vehiculo) cmbVehiculo.getSelectionModel().getSelectedItem());
            oferta.setRuta((Ruta) cmbRuta.getSelectionModel().getSelectedItem());
            Viajero viajeroSeleccionado = (Viajero) cmbViajero.getSelectionModel().getSelectedItem();
            if (viajeroSeleccionado != null) {
                if (oferta.getViajero() == null) {
                    oferta.setViajero(new ArrayList<>());
                }
                oferta.getViajero().add(viajeroSeleccionado); 
            }
    
            ofertaService.guardarOferta(oferta);
            cargarDatos();
            borrarTextField();
        } else {
            System.out.println("Selecciona una oferta para editar.");
        }
    }
    
    
    public void eliminarOferta() {
        Oferta oferta = (Oferta) tblOfertas.getSelectionModel().getSelectedItem();
        if (oferta != null) {
            ofertaService.eliminarOferta(oferta);
            cargarDatos();
            borrarTextField(); 
        } else {
            System.out.println("Selecciona una oferta para eliminar.");
        }
    }
    
    public void buscarOfertaPorId() {
        if (tfBuscar.getText().isBlank()) {
            cargarDatos();
        } else {
            try {
                Long id = Long.parseLong(tfBuscar.getText());
                Oferta oferta = ofertaService.busOfertaPorId(id);
                if (oferta != null) {
                    tblOfertas.getItems().clear();
                    tblOfertas.setItems(FXCollections.observableArrayList(oferta));
                } else {
                    System.out.println("Oferta no encontrada");
                    tblOfertas.getItems().clear();
                }
            } catch (NumberFormatException e) {
                System.out.println("ID inválido: " + e.getMessage());
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
       
    }

}
