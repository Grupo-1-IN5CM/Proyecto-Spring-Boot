package com.agenciaviajes.webapp.agenciaViajes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.agenciaviajes.webapp.agenciaViajes.model.Viajero;
import com.agenciaviajes.webapp.agenciaViajes.service.IViajeroService;

@RestController
@RequestMapping("/viajeros")
public class ViajeroController {

    @Autowired
    private IViajeroService viajeroService;

    // Listar
    @GetMapping("/")
    public List<Viajero> listarViajeros() {
        return viajeroService.listarViajeros();
    }

    // Buscar
    @GetMapping("/id={id}")
    public ResponseEntity<Viajero> buscarViajeroPorId(@PathVariable Long id) {
        Viajero viajero = viajeroService.buscarViajeroPorId(id);
        if (viajero != null) {
            return ResponseEntity.ok(viajero);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Agregar
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> agregarViajero(@RequestBody Viajero viajero) {
        Map<String, String> response = new HashMap<>();
        try {
            viajeroService.guardarViajero(viajero);
            response.put("message", "Viajero agregado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("error", "Hubo un error al intentar agregar el viajero");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarViajero(@PathVariable Long id, @RequestBody Viajero viajeroNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Viajero viajeroExistente = viajeroService.buscarViajeroPorId(id);
            if (viajeroExistente != null) {
                viajeroExistente.setNombre(viajeroNuevo.getNombre());
                viajeroExistente.setApellido(viajeroNuevo.getApellido());
                viajeroExistente.setCorreo(viajeroNuevo.getCorreo());
                viajeroExistente.setTelefono(viajeroNuevo.getTelefono());
                viajeroExistente.setFechaRegistro(viajeroNuevo.getFechaRegistro());
                viajeroService.guardarViajero(viajeroExistente);
                response.put("message", "El Viajero se ha modificado con éxito");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Error");
                response.put("error", "Viajero no encontrado");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("error", "Hubo un error al intentar modificar el viajero");
            return ResponseEntity.badRequest().body(response);
        }
    }

    // Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarViajero(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Viajero viajero = viajeroService.buscarViajeroPorId(id);
            if (viajero != null) {
                viajeroService.eliminarViajero(id);
                response.put("message", "El viajero se ha eliminado");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Error");
                response.put("error", "Viajero no encontrado");
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("error", "El viajero no se ha eliminado");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
