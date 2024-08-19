package com.agenciaviajes.webapp.agenciaViajes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agenciaviajes.webapp.agenciaViajes.model.Itinerario;
import com.agenciaviajes.webapp.agenciaViajes.service.IItinerarioService;

@RestController
@RequestMapping("/itinerarios")
public class ItinerarioController {

    @Autowired
    private IItinerarioService itinerarioService;

    // Listar
    @GetMapping("/")
    public List<Itinerario> listarItinerarios() {
        return itinerarioService.listarItinerarios();
    }

    // Buscar
    @GetMapping("/id={id}")
    public ResponseEntity<Itinerario> buscarItinerarioPorId(@PathVariable Long id) {
        Itinerario itinerario = itinerarioService.buscarItinerarioPorId(id);
        if (itinerario != null) {
            return ResponseEntity.ok(itinerario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Agregar
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> agregarItinerario(@RequestBody Itinerario itinerario) {
        Map<String, String> response = new HashMap<>();
        try {
            itinerarioService.guardarItinerario(itinerario);
            response.put("message", "Itinerario agregado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("error", "Hubo un error al intentar agregar el itinerario");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarItinerario(@PathVariable Long id, @RequestBody Itinerario itinerarioNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Itinerario itinerarioExistente = itinerarioService.buscarItinerarioPorId(id);
            if (itinerarioExistente != null) {
                itinerarioExistente.setFecha(itinerarioNuevo.getFecha());
                itinerarioExistente.setHora(itinerarioNuevo.getHora());
                itinerarioService.guardarItinerario(itinerarioExistente);
                response.put("message", "El Itinerario se ha modificado con éxito");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Error");
                response.put("error", "Itinerario no encontrado");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("error", "Hubo un error al intentar modificar el itinerario");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarItinerario(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Itinerario itinerario = itinerarioService.buscarItinerarioPorId(id);
            if (itinerario != null) {
                itinerarioService.eliminarItinerario(id);
                response.put("message", "El itinerario se ha eliminado");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Error");
                response.put("error", "Itinerario no encontrado");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("error", "El itinerario no se ha eliminado");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
