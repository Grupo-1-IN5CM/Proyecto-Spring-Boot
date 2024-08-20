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

import com.agenciaviajes.webapp.agenciaViajes.model.Ruta;
import com.agenciaviajes.webapp.agenciaViajes.service.RutaService;

@Controller
@RestController
@RequestMapping("ruta")
public class RutaController {
    @Autowired
    RutaService rutaService;
    //listar
    @GetMapping("/")
    public ResponseEntity<List<Ruta>> listarRuta(){
       try {
            return  ResponseEntity.ok(rutaService.ListarRuta());
       } catch (Exception e) {
            return  ResponseEntity.badRequest().body(null);
       }
    } 

    // buscar
    @GetMapping("/{id}")
    public ResponseEntity<Ruta> busRutaPorId(@PathVariable Long id){
        try{
            Ruta ruta = rutaService.busRutaPorId(id);
            return ResponseEntity.ok(ruta);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);

        }
    }

    //agregar
    @PostMapping("/")
    public ResponseEntity<Map<String,String>> agregarRuta(@RequestBody Ruta ruta){
        Map<String,String> response = new HashMap<>();
        try{
            rutaService.guardarRuta(ruta);
            response.put("message","Ruta se creo");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message","Error");
            response.put("err","Error al crear la Ruta");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> editarRuta(@PathVariable Long id,@RequestBody Ruta rutaNuevo){
        Map<String,String> response = new HashMap<>();
        try {
            Ruta ruta = rutaService.busRutaPorId(id);
            ruta.setNombre(rutaNuevo.getNombre());
            ruta.setDistancia(rutaNuevo.getDistancia());
            ruta.setDuracion(rutaNuevo.getDuracion());
            rutaService.guardarRuta(ruta);
            response.put("message","Ruta fue modificado ");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al editar la Ruta");
            return ResponseEntity.badRequest().body(response);
        }
    }
    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> eliminarRuta(@PathVariable Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Ruta ruta = rutaService.busRutaPorId(id);
            rutaService.eliminarRuta(ruta);
            response.put("message", "La Ruta se elimino");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al intentar eliminar la Ruta");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
