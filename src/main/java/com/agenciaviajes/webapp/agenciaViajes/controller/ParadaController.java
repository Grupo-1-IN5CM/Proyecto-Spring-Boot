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

import com.agenciaviajes.webapp.agenciaViajes.model.Parada;
import com.agenciaviajes.webapp.agenciaViajes.service.ParadaService;

@Controller
@RestController
@RequestMapping("Parada")
public class ParadaController {
    
    @Autowired
    ParadaService paradaService;
    
    //Listar
    @GetMapping("/")
    public ResponseEntity<List<Parada>> listarParada(){
       try {
            return  ResponseEntity.ok(paradaService.ListarParada());
       } catch (Exception e) {
            return  ResponseEntity.badRequest().body(null);
       }
    } 

    //Buscar
    @GetMapping("/id={id}")
    public ResponseEntity<Parada> buscarParadaPorId(@PathVariable Long id){
        try{
            Parada parada = paradaService.busParadaPorId(id);
            return ResponseEntity.ok(parada);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);

        }
    }

    //Agragar
    @PostMapping("/")
    public ResponseEntity<Map<String,String>> agragarParada(@RequestBody Parada parada){
        Map<String,String> response = new HashMap<>();
        try{
            paradaService.guardarParada(parada);
            response.put("message","Se ha creado una parada de forma exitosa");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message","Error");
            response.put("err","Ha ocurrido un error al agregar la parada");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> editarParada(@PathVariable Long id,@RequestBody Parada paradaNueva){
        Map<String,String> response = new HashMap<>();
        try {
            Parada parada = paradaService.busParadaPorId(id);
            parada.setNombre(paradaNueva.getNombre());
            parada.setUbicacion(paradaNueva.getUbicacion());
            parada.setTiempo(paradaNueva.getTiempo());
            parada.setTipoParada(paradaNueva.getTipoParada());
            paradaService.guardarParada(parada);
            response.put("message","Se ha modificado la parada");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Ha ocurrido un error al editar la parada");
            return ResponseEntity.badRequest().body(response);
        }
    }
    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> eliminarParada(@PathVariable Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Parada parada = paradaService.busParadaPorId(id);
            paradaService.eliminarParada(parada);
            response.put("message", "Sa ha eliminado una parada");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al intentar eliminar una parada");
            return ResponseEntity.badRequest().body(response);
        }
    }
}