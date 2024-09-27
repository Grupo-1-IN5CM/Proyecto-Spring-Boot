package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenciaviajes.webapp.agenciaViajes.model.Usuario;
import com.agenciaviajes.webapp.agenciaViajes.repository.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {
    @Autowired 
    UsuarioRepository usuarioRepository;

    
    @Override
    public List<Usuario> ListarUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario guardarUsuario(Usuario usuario) {
      return usuarioRepository.save(usuario);
    }

    @Override
public Usuario busUsuarioPorNombre(String nombre) {
    List<Usuario> usuarios = usuarioRepository.findByNombre(nombre);
    
    if (usuarios.isEmpty()) {
        return null;  // Retorna null si no se encuentra ningún usuario
    }
    
    if (usuarios.size() > 1) {
        throw new RuntimeException("Se encontraron múltiples usuarios con ese nombre.");
    }
    
    return usuarios.get(0);  // Retorna el único usuario encontrado
}

    
    @Override
    public Usuario busUsuarioPorId(Long id) {
       return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarUsuario(Usuario usuario) {
        usuarioRepository.delete(usuario);
    }
}
