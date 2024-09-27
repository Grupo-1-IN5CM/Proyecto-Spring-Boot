package com.agenciaviajes.webapp.agenciaViajes.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenciaviajes.webapp.agenciaViajes.model.Vehiculo;
import com.agenciaviajes.webapp.agenciaViajes.service.VehiculoService;
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
public class VehiculoFXController implements Initializable{
    @Setter
    private Main stage;

    @FXML
    TextField tfBuscar, tfId, tfMarca, tfModelo, tfTipo, tfCapacidad, tfDisponibilidad;

    @FXML
    TableView tblVehiculos;

    @FXML
    TableColumn colId, colMarca, colModel, colTipo, colCapacidad, colDisponibilidad;

    @FXML
    Button btnBuscar, btnAgregar, btnEliminar, btnClear, btnReturn;

    @Autowired
    VehiculoService vehiculoService;

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnAgregar) {
            if (tfId.getText().isBlank() ) {
                agregarVehiculo();
            } else {
                editarVehiculo();
            }
        } else if (event.getSource() == btnEliminar) {
            eliminarVehiculo();
        } else if (event.getSource() == btnBuscar) {
            buscarVehiculoPorId();
        }else if(event.getSource() == btnReturn){
            stage.inicioView();
        }
    }

    public void cargarDatos(){
        tblVehiculos.getItems().clear();
        tblVehiculos.setItems(listarVehiculo());
        colId.setCellValueFactory(new PropertyValueFactory<Vehiculo, Long>("id"));
        colMarca.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("marca"));
        colModel.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("modelo"));
        colTipo.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("tipoVehiculo"));
        colCapacidad.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("capacidad"));
        colDisponibilidad.setCellValueFactory(new PropertyValueFactory<Vehiculo, String>("disponibilidad"));
    }

    public void borrarTextField(){
        tfId.clear();
        tfMarca.clear();
        tfModelo.clear();
        tfTipo.clear();
        tfCapacidad.clear();
        tfDisponibilidad.clear();
    }

    public void cargarTextField(){
        Vehiculo vehiculo = (Vehiculo)tblVehiculos.getSelectionModel().getSelectedItem();
        if (vehiculo != null) {
            tfId.setText(Long.toString(vehiculo.getId()));
            tfMarca.setText(vehiculo.getMarca());
            tfModelo.setText(vehiculo.getModelo());
            tfTipo.setText(vehiculo.getTipoVehiculo());
            tfCapacidad.setText(vehiculo.getCapacidad());
            tfDisponibilidad.setText(Boolean.toString(vehiculo.getDisponibilidad()));
        }
    }
    

    public ObservableList<Vehiculo> listarVehiculo(){
        return FXCollections.observableArrayList(vehiculoService.ListarVehiculo());
    }

    public void agregarVehiculo(){
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setMarca(tfMarca.getText());
        vehiculo.setModelo(tfModelo.getText());
        vehiculo.setTipoVehiculo(tfTipo.getText());
        vehiculo.setCapacidad(tfCapacidad.getText());
        vehiculo.setDisponibilidad(Boolean.parseBoolean(tfDisponibilidad.getText()));
        vehiculoService.guardarVehiculo(vehiculo);
        cargarDatos();
    }

    public void editarVehiculo(){
        Vehiculo vehiculo = vehiculoService.busVehiculoPorId(Long.parseLong(tfId.getText()));
        vehiculo.setMarca(tfMarca.getText());
        vehiculo.setModelo(tfModelo.getText());
        vehiculo.setTipoVehiculo(tfTipo.getText());
        vehiculo.setCapacidad(tfCapacidad.getText());
        vehiculo.setDisponibilidad(Boolean.parseBoolean(tfDisponibilidad.getText()));
        vehiculoService.guardarVehiculo(vehiculo);
        cargarDatos();
    }

    public void eliminarVehiculo(){
        Vehiculo vehiculo = vehiculoService.busVehiculoPorId(Long.parseLong(tfId.getText()));
        vehiculoService.eliminarVehiculo(vehiculo);
        cargarDatos();
    }

    public void buscarVehiculoPorId() {
        String idTexto = tfBuscar.getText();

        if (idTexto.isBlank()) {
            cargarDatos();
            return;
        }
        try {
            Long id = Long.parseLong(idTexto);
            Vehiculo vehiculo = vehiculoService.busVehiculoPorId(id);
            if (vehiculo != null) {
                tblVehiculos.getItems().clear();
                ObservableList<Vehiculo> vehiculosEncontrados = FXCollections.observableArrayList(vehiculo);
                tblVehiculos.setItems(vehiculosEncontrados);   
                tblVehiculos.getSelectionModel().select(vehiculo);
                cargarTextField();
            } else {
                borrarTextField();
                System.out.println("Vehículo no encontrado.");
            }
        } catch (NumberFormatException e) {
            System.out.println("ID no válido.");
        }
    }        
}