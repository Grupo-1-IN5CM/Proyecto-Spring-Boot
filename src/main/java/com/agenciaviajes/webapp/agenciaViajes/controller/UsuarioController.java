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

import com.agenciaviajes.webapp.agenciaViajes.model.Usuario;
import com.agenciaviajes.webapp.agenciaViajes.service.UsuarioService;

@Controller
@RestController
@RequestMapping("usuario")
public class UsuarioController {
        
    @Autowired
    UsuarioService usuarioService;
    
    //Listar
    @GetMapping("/")
    public ResponseEntity<List<?>> ListarUsuario(){
       try {
            return  ResponseEntity.ok(usuarioService.ListarUsuario());
       } catch (Exception e) {
            return  ResponseEntity.badRequest().body(null);
       }
    } 

    //Buscar
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Long id){
        try{
            Usuario usuario = usuarioService.busUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(null);

        }
    }

    //Agragar
    @PostMapping("/")
    public ResponseEntity<Map<String,String>> agragarUsuario(@RequestBody Usuario usuario){
        Map<String,String> response = new HashMap<>();
        try{
            usuarioService.guardarUsuario(usuario);
            response.put("message","Se ha creado una usuario de forma exitosa");
            return ResponseEntity.ok(response);
        }catch(Exception e){
            response.put("message","Error");
            response.put("err","Ha ocurrido un error al agregar el usuario");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar
    @PutMapping("/{id}")
    public ResponseEntity<Map<String,String>> editarUsuario(@PathVariable Long id,@RequestBody Usuario usuarioNuevo){
        Map<String,String> response = new HashMap<>();
        try {
            Usuario usuario = usuarioService.busUsuarioPorId(id);
            usuario.setNombre(usuarioNuevo.getNombre());
            usuario.setContraseña(usuarioNuevo.getContraseña());
            usuario.setViajero(usuarioNuevo.getViajero());
            usuarioService.guardarUsuario(usuario);
            response.put("message","Se ha modificado el usuario");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Ha ocurrido un error al editar el usuario");
            return ResponseEntity.badRequest().body(response);
        }
    }
    //Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String,String>> eliminarUsuario(@PathVariable Long id){
        Map<String,String> response = new HashMap<>();
        try {
            Usuario usuario = usuarioService.busUsuarioPorId(id);
            usuarioService.eliminarUsuario(usuario);
            response.put("message", "Sa ha eliminado un usuario");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Error al intentar eliminar un usuario");
            return ResponseEntity.badRequest().body(response);
        }
    }
}


