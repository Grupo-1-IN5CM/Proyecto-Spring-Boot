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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.agenciaviajes.webapp.agenciaViajes.service.ComentarioService;
import com.agenciaviajes.webapp.agenciaViajes.model.Comentario;

@Controller
@RestController
@RequestMapping("comentario")
public class ComentarioController {

    @Autowired
    ComentarioService comentarioService;

    @GetMapping("/")
    public ResponseEntity<List<?>> ListarComentario() {
        try {
            return ResponseEntity.ok(comentarioService.ListarComentario());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/")
    public ResponseEntity<Map<String, String>> agregarComentario(@RequestBody Comentario comentario) {
        Map<String, String> response = new HashMap<>();
        try {
            if (comentarioService.validarCalificacionEnRango(comentario.getCalificacion()) && comentarioService.validarComentarioNoVacio(comentario.getComentario())) {
                comentarioService.guardarComentario(comentario);
                response.put("message", "El comentario se creó con éxito");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Error en la validación del comentario");
                response.put("err", "Calificación fuera de rango o comentario vacío");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el comentario");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarComentario(@PathVariable Long id, @RequestBody Comentario comentarioNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Comentario comentario = comentarioService.busComentarioPorId(id);

            // Validación de calificación y comentario no vacío
            if (comentarioService.validarCalificacionEnRango(comentarioNuevo.getCalificacion()) && comentarioService.validarComentarioNoVacio(comentarioNuevo.getComentario())) {
                comentario.setViajero(comentarioNuevo.getViajero());
                comentario.setCalificacion(comentarioNuevo.getCalificacion());
                comentario.setComentario(comentarioNuevo.getComentario());
                comentario.setFecha(comentarioNuevo.getFecha());
                comentarioService.guardarComentario(comentario);
                response.put("message", "Comentario modificado con éxito");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Error en la validación del comentario");
                response.put("err", "Calificación fuera de rango o comentario vacío");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al modificar el comentario");
            return ResponseEntity.badRequest().body(response);
        }
    }


    @DeleteMapping("/{Id}")
    public ResponseEntity<Map<String, String>> eliminarComentario(@RequestParam Long id) {
        Map<String, String> response = new HashMap<>();
        try {
            Comentario comentario = comentarioService.busComentarioPorId(id);
            comentarioService.eliminarComentario(comentario);
            response.put("message", "Comentario eliminado con éxito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el comentario");
            return ResponseEntity.badRequest().body(response);
        }
    }

}
