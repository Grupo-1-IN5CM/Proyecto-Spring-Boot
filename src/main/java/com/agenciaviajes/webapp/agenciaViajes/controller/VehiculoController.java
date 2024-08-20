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

import com.agenciaviajes.webapp.agenciaViajes.model.Vehiculo;
import com.agenciaviajes.webapp.agenciaViajes.service.VehiculoService;

@Controller
@RestController
@RequestMapping("vehiculo")
public class VehiculoController {

    @Autowired
    VehiculoService vehiculoService;

    //listar
    @GetMapping("/")
    public ResponseEntity<List<Vehiculo>> listarVehiculo(){
       try {
            return  ResponseEntity.ok(vehiculoService.ListarVehiculo());
       } catch (Exception e) {
            return  ResponseEntity.badRequest().body(null);
       }
    } 

    // buscar
    @GetMapping("/{id}")
    public ResponseEntity<Vehiculo> busVehiculoPorId(@PathVariable Long id){
        try{
            Vehiculo vehiculo = vehiculoService.busVehiculoPorId(id);
            return ResponseEntity.ok(vehiculo);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);

        }
    }

    //agregar
    @PostMapping("/")
    public ResponseEntity<Map<String,String>> agregarVehiculo(@RequestBody Vehiculo vehiculo){
        Map<String,String> response = new HashMap<>();
        try{
            vehiculoService.guardarVehiculo(vehiculo);
            response.put("message","Vehiculo se creo");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message","Error");
            response.put("err","Error al crear el Vehiculo");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> editarVehiculo(@PathVariable Long id,@RequestBody Vehiculo vehiculoNuevo){
        Map<String,String> response = new HashMap<>();
        try {
            Vehiculo vehiculo = vehiculoService.busVehiculoPorId(id);
            vehiculo.setMarca(vehiculoNuevo.getMarca());
            vehiculo.setModelo(vehiculoNuevo.getModelo());
            vehiculo.setTipoVehiculo(vehiculoNuevo.getTipoVehiculo());
            vehiculo.setCapacidad(vehiculoNuevo.getCapacidad());
            vehiculoService.guardarVehiculo(vehiculo);
            response.put("message","Vehiculo fue modificado ");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al editar el Vehiculo");
            return ResponseEntity.badRequest().body(response);
        }
    }
    //eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> eliminarVehiculo(@PathVariable Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Vehiculo vehiculo = vehiculoService.busVehiculoPorId(id);
            vehiculoService.eliminarVehiculo(vehiculo);
            response.put("message", "El Vehiculo se elimino"); 
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al intentar eliminar el Vehiculo");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
