package com.agenciaviajes.webapp.agenciaViajes.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.agenciaviajes.webapp.agenciaViajes.model.Viajero;
import com.agenciaviajes.webapp.agenciaViajes.service.IViajeroService;

@Controller
@RestController
@RequestMapping("viajero")
public class ViajeroController {

    @Autowired
    private IViajeroService viajeroService;

    //Listar
    @GetMapping("/")
    public List<Viajero> listaViajeros(){
        return viajeroService.listarViajeros();
    }

    //Buscar
    @GetMapping("/id={id}")
    public ResponseEntity<Viajero> buscarViajeroPorId(@PathVariable Long id){
        try {
            Viajero viajero = viajeroService.buscarViajeroPorId(id);
            return ResponseEntity.ok(viajero);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //Agregar
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> agregarViajero(@RequestBody Viajero viajero){
        Map<String, String> response = new HashMap<>();
        try{
            viajeroService.guardarViajero(viajero);
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
    public ResponseEntity<Map<String, String>> editarEmpleado(@PathVariable Long id, @RequestBody Viajero viajeroNuevo){
        Map<String, String> response = new HashMap<>();
        
        try {
            Viajero viajero = viajeroService.buscarViajeroPorId(id);    
            viajero.setNombre(viajeroNuevo.getNombre());
            viajero.setApellido(viajeroNuevo.getApellido());
            viajero.setCorreo(viajeroNuevo.getTelefono());
            viajero.setTelefono(viajeroNuevo.getTelefono());
            viajero.setFechaRegistro(viajeroNuevo.getFechaRegistro());
            viajero.setItinerario(viajeroNuevo.getItinerario());
            viajeroService.guardarViajero(viajero);
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
    public ResponseEntity<Map<String, String>> eliminarEmpleado(@PathVariable Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Viajero viajero = viajeroService.buscarViajeroPorId(id);
            viajeroService.eliminarViajero(viajero);
            response.put("message", "El viajero se ha eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "El viajero no se ha podido eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
