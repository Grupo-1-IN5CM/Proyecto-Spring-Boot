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
@RequestMapping("itinerario")
public class ItinerarioController {

    @Autowired
    private IItinerarioService itinerarioService;

    //Listar
    @GetMapping("/")
    public ResponseEntity<List<?>> ListarItinerario() {
        try {
            return ResponseEntity.ok(itinerarioService.listarItinerario());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Buscar
    @GetMapping("/{id}")
    public ResponseEntity<Itinerario> buscarItinerario(@PathVariable Long id){
        try {
            Itinerario itinerario = itinerarioService.buscarItinerarioPorId(id);
            return ResponseEntity.ok(itinerario);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Agregar
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> agregarItinerario(@RequestBody Itinerario itinerario){
        Map<String, String> response = new HashMap<>();
        try{
            itinerarioService.guardarItinerario(itinerario);
            response.put("message: ", "Viajero agregado con exito");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message: ", "Error");
            response.put("err", "No se ha podido agregar el Viajero");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarItinerario(@PathVariable Long id, @RequestBody Itinerario itinerarioNuevo){
        Map<String, String> response = new HashMap<>();
        
        try {
            Itinerario itinerario = itinerarioService.buscarItinerarioPorId(id);    
            itinerario.setFecha(itinerarioNuevo.getFecha());
            itinerario.setHora(itinerarioNuevo.getHora());
            itinerario.setRuta(itinerarioNuevo.getRuta());
            itinerario.setParada(itinerarioNuevo.getParada());
            itinerarioService.guardarItinerario(itinerario);
            response.put("message", "El viajero se ha modificado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al intentar modificar al viajero");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> eliminarItinerario(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Itinerario itinerario = itinerarioService.buscarItinerarioPorId(id);
            itinerarioService.eliminarItinerario(itinerario);
            response.put("message", "El viajero se ha eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "El viajero no se ha podido eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
