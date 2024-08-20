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
import com.agenciaviajes.webapp.agenciaViajes.service.OfertaService;

@Controller
@RestController
@RequestMapping("oferta")
public class OfertaController {

    @Autowired
    OfertaService ofertaService;

    //listar
    @GetMapping("/")
    public ResponseEntity<List<Oferta>> listarOferta(){
       try {
            return  ResponseEntity.ok(ofertaService.ListarOferta());
       } catch (Exception e) {
            return  ResponseEntity.badRequest().body(null);
       }
    } 

    // buscar
    @GetMapping("/{id}")
    public ResponseEntity<Oferta> busOfertaPorId(@PathVariable Long id){
        try{
            Oferta oferta = ofertaService.busOfertaPorId(id);
            return ResponseEntity.ok(oferta);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);

        }
    }

    //agregar
    @PostMapping("/")
    public ResponseEntity<Map<String,String>> agregarOferta(@RequestBody Oferta oferta){
        Map<String,String> response = new HashMap<>();
        try{
            ofertaService.guardarOferta(oferta);
            response.put("message","Oferta se creo");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message","Error");
            response.put("err","Error al crear el libro");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> editarOferta(@PathVariable Long id,@RequestBody Oferta ofertaNueva){
        Map<String,String> response = new HashMap<>();
        try {
            Oferta oferta = ofertaService.busOfertaPorId(id);
            oferta.setFechaInicio(ofertaNueva.getFechaInicio());
            oferta.setFechaFinal(ofertaNueva.getFechaFinal());
            oferta.setTiempo(ofertaNueva.getTiempo());
            oferta.setTipoParada(ofertaNueva.getTipoParada());
            oferta.setVehiculo(ofertaNueva.getVehiculo());
            oferta.setRuta(ofertaNueva.getRuta());
            ofertaService.guardarOferta(oferta);
            response.put("message","La oferta fue modificado ");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al editar la oferta");
            return ResponseEntity.badRequest().body(response);
        }
    }
    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> eliminarOferta(@PathVariable Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Oferta oferta = ofertaService.busOfertaPorId(id);
            ofertaService.eliminarOferta(oferta);
            response.put("message", "La oferta se elimino");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al intentar eliminar la oferta");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
