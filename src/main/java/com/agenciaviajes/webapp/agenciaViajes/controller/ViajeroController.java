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
    @GetMapping("/{id}")
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
    public ResponseEntity<Map<String, String>> agregarViajero(@RequestBody Viajero viajero) {
        Map<String, String> response = new HashMap<>();
        try {
            // Validación de correo único y fecha de registro
            if (!viajeroService.esCorreoUnico(viajero.getCorreo()) && viajeroService.validarFechaRegistroNoFutura(viajero.getFechaRegistro())) {
                viajeroService.guardarViajero(viajero);
                response.put("message", "El viajero se creó con éxito");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Error en la validación del viajero");
                if (!viajeroService.esCorreoUnico(viajero.getCorreo())) {
                    response.put("err", "El correo ya está en uso");
                } else if (!viajeroService.validarFechaRegistroNoFutura(viajero.getFechaRegistro())) {
                    response.put("err", "La fecha de registro no puede ser una fecha futura");
                }
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al crear el viajero");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> editarViajero(@PathVariable Long id, @RequestBody Viajero viajeroNuevo) {
        Map<String, String> response = new HashMap<>();
        try {
            Viajero viajero = viajeroService.buscarViajeroPorId(id);

            // Validación de correo único (excluyendo el viajero actual) y fecha de registro
            if ((viajero.getCorreo().equals(viajeroNuevo.getCorreo()) || viajeroService.esCorreoUnico(viajeroNuevo.getCorreo())) &&
                viajeroService.validarFechaRegistroNoFutura(viajeroNuevo.getFechaRegistro())) {

                viajero.setApellido(viajeroNuevo.getApellido());
                viajero.setCorreo(viajeroNuevo.getCorreo());
                viajero.setFechaRegistro(viajeroNuevo.getFechaRegistro());
                viajero.setNombre(viajeroNuevo.getNombre());
                viajero.setTelefono(viajeroNuevo.getTelefono());
                viajeroService.guardarViajero(viajero);

                response.put("message", "El viajero fue modificado con éxito");
                return ResponseEntity.ok(response);
            } else {
                response.put("message", "Error en la validación del viajero");
                response.put("err", "Correo ya está en uso o fecha de registro es futura");
                return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al modificar el viajero");
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
