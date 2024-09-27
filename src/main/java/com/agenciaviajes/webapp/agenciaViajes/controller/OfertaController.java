package com.agenciaviajes.webapp.agenciaViajes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenciaviajes.webapp.agenciaViajes.model.Oferta;
import com.agenciaviajes.webapp.agenciaViajes.model.Vehiculo;
import com.agenciaviajes.webapp.agenciaViajes.service.OfertaService;
import com.agenciaviajes.webapp.agenciaViajes.service.VehiculoService;

@Controller
@RestController
@RequestMapping("oferta")
public class OfertaController {

    @Autowired
    OfertaService ofertaService;

    @Autowired
    VehiculoService vehiculoService;  // Inyectar el servicio VehiculoService

    // Listar
    @GetMapping("/")
    public ResponseEntity<List<Oferta>> listarOferta() {
        try {
            return ResponseEntity.ok(ofertaService.ListarOferta());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Buscar
    @GetMapping("/{id}")
    public ResponseEntity<Oferta> busOfertaPorId(@PathVariable Long id) {
        try {
            Oferta oferta = ofertaService.busOfertaPorId(id);
            return ResponseEntity.ok(oferta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Agregar
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> agregarOferta(@RequestBody Oferta oferta) {
        Map<String, String> response = new HashMap<>();
        try {
            Vehiculo vehiculo = oferta.getVehiculo();  // Obtener el vehículo asociado a la oferta
            Vehiculo vehiculoActual = vehiculoService.busVehiculoPorId(vehiculo.getId());  // Llamada al servicio para buscar el vehículo

            // Verificar si el vehículo está disponible
            if (vehiculoActual == null || !vehiculoActual.getDisponibilidad()) {
                response.put("message", "El vehículo con ID " + vehiculo.getId() + " no está disponible.");
                return ResponseEntity.badRequest().body(response);
            }

            // Validar las fechas de la oferta
            if (!ofertaService.validarFechasConsistentes(oferta)) {
                response.put("message", "Error en la validación de la oferta");
                response.put("err", "Fechas inconsistentes.");
                return ResponseEntity.badRequest().body(response);
            }

            // Guardar la oferta
            ofertaService.guardarOferta(oferta);

            // Cambiar la disponibilidad del vehículo a false
            vehiculoActual.setDisponibilidad(false);
            vehiculoService.guardarVehiculo(vehiculoActual);  // Actualizar el estado del vehículo en la base de datos

            response.put("message", "La oferta se creó correctamente");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al crear la oferta: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    // Editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarOferta(@PathVariable Long id, @RequestBody Oferta ofertaNueva) {
        Map<String, String> response = new HashMap<>();
        try {
            // Buscar la oferta por su ID
            Oferta oferta = ofertaService.busOfertaPorId(id);

            if (oferta == null) {
                response.put("message", "Error");
                response.put("err", "La oferta no fue encontrada.");
                return ResponseEntity.badRequest().body(response);
            }

            // Validar disponibilidad del vehículo
            Vehiculo vehiculo = ofertaNueva.getVehiculo();
            Vehiculo vehiculoActual = vehiculoService.busVehiculoPorId(vehiculo.getId());

            if (vehiculoActual == null || !vehiculoActual.getDisponibilidad()) {
                response.put("message", "Error en la validación de la oferta");
                response.put("err", "El vehículo con ID " + vehiculo.getId() + " no está disponible.");
                return ResponseEntity.badRequest().body(response);
            }

            // Validar las fechas de la oferta
            if (!ofertaService.validarFechasConsistentes(ofertaNueva)) {
                response.put("message", "Error en la validación de la oferta");
                response.put("err", "Fechas inconsistentes.");
                return ResponseEntity.badRequest().body(response);
            }

            // Si todo es válido, actualizar la oferta
            oferta.setFechaInicio(ofertaNueva.getFechaInicio());
            oferta.setFechaFinal(ofertaNueva.getFechaFinal());
            oferta.setTiempo(ofertaNueva.getTiempo());
            oferta.setTipoParada(ofertaNueva.getTipoParada());
            oferta.setVehiculo(ofertaNueva.getVehiculo());
            oferta.setRuta(ofertaNueva.getRuta());

            // Guardar la oferta editada
            ofertaService.guardarOferta(oferta);

            // Actualizar la disponibilidad del vehículo a false
            vehiculo.setDisponibilidad(false);
            vehiculoService.guardarVehiculo(vehiculo);

            response.put("message", "La oferta fue modificada correctamente");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al editar la oferta: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }


    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarOferta(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            // Buscar la oferta por ID
            Oferta oferta = ofertaService.busOfertaPorId(id);
            
            if (oferta != null) {
                // Obtener el vehículo asociado a la oferta
                Vehiculo vehiculo = oferta.getVehiculo();
                
                // Eliminar la oferta
                ofertaService.eliminarOferta(oferta);

                // Cambiar la disponibilidad del vehículo a true
                vehiculo.setDisponibilidad(true);
                vehiculoService.guardarVehiculo(vehiculo);  // Guardar el cambio en la base de datos

                response.put("message", "La oferta se eliminó y el vehículo ahora está disponible nuevamente.");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Error");
                response.put("err", "La oferta no fue encontrada.");
                return ResponseEntity.badRequest().body(response);
            }

        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al intentar eliminar la oferta: " + e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

}
