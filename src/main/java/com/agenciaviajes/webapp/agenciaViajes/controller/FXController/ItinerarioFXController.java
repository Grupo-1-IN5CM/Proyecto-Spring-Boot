package com.agenciaviajes.webapp.agenciaViajes.controller.FXController;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.agenciaviajes.webapp.agenciaViajes.model.Itinerario;
import com.agenciaviajes.webapp.agenciaViajes.model.Parada;
import com.agenciaviajes.webapp.agenciaViajes.model.Ruta;
import com.agenciaviajes.webapp.agenciaViajes.service.ItinerarioService;
import com.agenciaviajes.webapp.agenciaViajes.service.ParadaService;
import com.agenciaviajes.webapp.agenciaViajes.service.RutaService;
import com.agenciaviajes.webapp.agenciaViajes.system.Main;
<<<<<<< HEAD
=======
import com.agenciaviajes.webapp.agenciaViajes.utils.Alerta; // Importar la clase Alerta
>>>>>>> Giovanni-Carrera

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
public class ItinerarioFXController implements Initializable {
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfFecha, tfHora, tfBuscar;

    @FXML
<<<<<<< HEAD
    TableView tblItinerario;

    @FXML
    TableColumn colId, colFecha, colHora, colRuta, colParada;

    @FXML
    ComboBox cmbRuta, cmbParada;
=======
    TableView<Itinerario> tblItinerario;

    @FXML
    TableColumn<Itinerario, Long> colId, colFecha, colHora, colRuta, colParada;

    @FXML
    ComboBox<Ruta> cmbRuta;
    
    @FXML
    ComboBox<Parada> cmbParada;
>>>>>>> Giovanni-Carrera

    @FXML
    Button btnEliminar, btnClear, btnAgregar, btnReturn, btnBuscar;

    @Autowired
    ItinerarioService itinerarioService;

    @Autowired
    ParadaService paradaService;

    @Autowired
    RutaService rutaService;

<<<<<<< HEAD
=======
    @Autowired
    Alerta alerta; // Inyectar la clase Alerta

>>>>>>> Giovanni-Carrera
    @Override
    public void initialize(URL url, ResourceBundle resources) {
       cargarDatos();
       cargarRutas(); 
       cargarParadas();
    }

    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnAgregar) {
            if (tfId.getText().isBlank()) {
                agregarItinerario();
<<<<<<< HEAD
            }else{
                editarItinerario();
            }
        }else if(event.getSource() == btnBuscar){
            buscarItinerarioPorId();
        }else if (event.getSource() == btnEliminar){
            eliminarItinerario();
        }else if(event.getSource() == btnReturn){
=======
            } else {
                editarItinerario();
            }
        } else if(event.getSource() == btnBuscar){
            buscarItinerarioPorId();
        } else if (event.getSource() == btnEliminar){
            eliminarItinerario();
        } else if(event.getSource() == btnReturn){
>>>>>>> Giovanni-Carrera
            stage.inicioView();
        }
    }

    public void cargarDatos(){
        tblItinerario.getItems().clear();
        tblItinerario.setItems(listarItinerario());
<<<<<<< HEAD
        colId.setCellValueFactory(new PropertyValueFactory<Itinerario, Long>("id"));
        colFecha.setCellValueFactory(new PropertyValueFactory<Itinerario, Date>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<Itinerario, Time>("hora"));
=======
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colHora.setCellValueFactory(new PropertyValueFactory<>("hora"));
>>>>>>> Giovanni-Carrera
        colRuta.setCellValueFactory(new PropertyValueFactory<>("ruta"));
        colParada.setCellValueFactory(new PropertyValueFactory<>("parada"));
    }

    private void cargarRutas() {
        ObservableList<Ruta> rutas = FXCollections.observableArrayList(rutaService.ListarRuta());
        cmbRuta.setItems(rutas);
    }
    
    private void cargarParadas() {
<<<<<<< HEAD
        ObservableList<Parada> paradas = FXCollections.observableArrayList((paradaService.ListarParada()));
=======
        ObservableList<Parada> paradas = FXCollections.observableArrayList(paradaService.ListarParada());
>>>>>>> Giovanni-Carrera
        cmbParada.setItems(paradas);
    }

    public void cargarTextField(){
<<<<<<< HEAD
        Itinerario itinerario = (Itinerario)tblItinerario.getSelectionModel().getSelectedItem();
=======
        Itinerario itinerario = tblItinerario.getSelectionModel().getSelectedItem();
>>>>>>> Giovanni-Carrera
        if (itinerario != null) {
            tfId.setText(Long.toString(itinerario.getId()));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String fechaString = dateFormat.format(itinerario.getFecha());
            tfFecha.setText(fechaString);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            String tiempoString = sdf.format(itinerario.getHora());
            tfHora.setText(tiempoString);
            cmbRuta.getSelectionModel().select(itinerario.getRuta());
            cmbParada.getSelectionModel().select(itinerario.getParada());
        }
    }

    public void borrarTextField(){
        tfId.clear();
        tfFecha.clear();
        tfHora.clear();
        cmbRuta.getSelectionModel().clearSelection();
        cmbParada.getSelectionModel().clearSelection();
    }

    public ObservableList<Itinerario> listarItinerario(){
        return FXCollections.observableArrayList(itinerarioService.listarItinerario());
    }

    public void agregarItinerario() {
        try {
<<<<<<< HEAD
            Itinerario itinerario = new Itinerario();     
            itinerario.setFecha(Date.valueOf(tfFecha.getText()));
            itinerario.setHora(Time.valueOf(tfHora.getText()));
            itinerario.setRuta((Ruta) cmbRuta.getSelectionModel().getSelectedItem());
            itinerario.setParada((Parada) cmbParada.getSelectionModel().getSelectedItem());
            itinerarioService.guardarItinerario(itinerario);
            cargarDatos(); 
            borrarTextField();        
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha o hora incorrecto: " + e.getMessage());
=======
            if (tfFecha.getText().isBlank() || tfHora.getText().isBlank() ||
                cmbRuta.getSelectionModel().isEmpty() || cmbParada.getSelectionModel().isEmpty()) {
                alerta.mostrarAlertInfo(341); // Campos faltantes
                return;
            }
            Itinerario itinerario = new Itinerario();     
            itinerario.setFecha(Date.valueOf(tfFecha.getText()));
            itinerario.setHora(Time.valueOf(tfHora.getText()));
            itinerario.setRuta(cmbRuta.getSelectionModel().getSelectedItem());
            itinerario.setParada(cmbParada.getSelectionModel().getSelectedItem());
            itinerarioService.guardarItinerario(itinerario);
            cargarDatos(); 
            borrarTextField();
            alerta.mostrarAlertInfo(123); // Registro completado
        } catch (IllegalArgumentException e) {
            alerta.mostrarAlertInfo(341); // Campos faltantes
>>>>>>> Giovanni-Carrera
        }
    }
    
    public void editarItinerario() {
        try {
<<<<<<< HEAD
            Itinerario itinerario = (Itinerario) tblItinerario.getSelectionModel().getSelectedItem();
            if (itinerario != null) {
                itinerario.setFecha(Date.valueOf(tfFecha.getText()));
                itinerario.setHora(Time.valueOf(tfHora.getText()));
                itinerario.setRuta((Ruta) cmbRuta.getSelectionModel().getSelectedItem());
                itinerario.setParada((Parada) cmbParada.getSelectionModel().getSelectedItem());
                itinerarioService.guardarItinerario(itinerario);
                cargarDatos();
                borrarTextField();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Formato de fecha o hora incorrecto: " + e.getMessage());
=======
            Itinerario itinerario = tblItinerario.getSelectionModel().getSelectedItem();
            if (itinerario != null) {
                itinerario.setFecha(Date.valueOf(tfFecha.getText()));
                itinerario.setHora(Time.valueOf(tfHora.getText()));
                itinerario.setRuta(cmbRuta.getSelectionModel().getSelectedItem());
                itinerario.setParada(cmbParada.getSelectionModel().getSelectedItem());
                itinerarioService.guardarItinerario(itinerario);
                cargarDatos();
                borrarTextField();
                alerta.mostrarAlertInfo(789); // Edición exitosa
            } else {
                alerta.mostrarAlertInfo(341); // Campos faltantes
            }
        } catch (IllegalArgumentException e) {
            alerta.mostrarAlertInfo(341); // Campos faltantes
>>>>>>> Giovanni-Carrera
        }
    }
    
    public void eliminarItinerario() {
<<<<<<< HEAD
        Itinerario itinerario = (Itinerario) tblItinerario.getSelectionModel().getSelectedItem();
        if (itinerario != null) {
            itinerarioService.eliminarItinerario(itinerario);
            cargarDatos(); 
            borrarTextField();
=======
        Itinerario itinerario = tblItinerario.getSelectionModel().getSelectedItem();
        if (itinerario != null) {
            boolean canDelete = true;
            if (canDelete) {
                itinerarioService.eliminarItinerario(itinerario);
                cargarDatos(); 
                borrarTextField();
                alerta.mostrarAlertInfo(123); // Registro completado
            } else {
                alerta.mostrarAlertInfo(456); // No se pueden eliminar los datos
            }
        } else {
            alerta.mostrarAlertInfo(341); // Campos faltantes
>>>>>>> Giovanni-Carrera
        }
    }
    
    public void buscarItinerarioPorId() {
        if (tfBuscar.getText().isBlank()) {
            cargarDatos();
        } else {
            try {
                Long id = Long.parseLong(tfBuscar.getText());
                Itinerario itinerario = itinerarioService.buscarItinerarioPorId(id);
                if (itinerario != null) {
                    tblItinerario.getItems().clear();
                    tblItinerario.setItems(FXCollections.observableArrayList(itinerario));
<<<<<<< HEAD
                } else {
                    System.out.println("Itinerario no encontrado");
                    tblItinerario.getItems().clear();
                }
            } catch (NumberFormatException e) {
                System.out.println("ID inválido: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error al buscar el itinerario: " + e.getMessage());
=======
                    alerta.mostrarAlertInfo(123); // Itinerario encontrado
                } else {
                    alerta.mostrarAlertInfo(341); // No se encontró el itinerario
                    tblItinerario.getItems().clear();
                }
            } catch (NumberFormatException e) {
                alerta.mostrarAlertInfo(341); // ID inválido
            } catch (Exception e) {
                alerta.mostrarAlertInfo(341); // Error al buscar el itinerario
>>>>>>> Giovanni-Carrera
            }
        }
    }
}
