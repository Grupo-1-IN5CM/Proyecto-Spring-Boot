package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import com.agenciaviajes.webapp.agenciaViajes.model.Usuario;

public interface IUsuarioService {
    
    public List<Usuario> ListarUsuario();

    public Usuario guardarUsuario(Usuario usuario);
    
    public Usuario busUsuarioPorNombre(String nombre);
    
    public Usuario busUsuarioPorId(Long id);

    public void eliminarUsuario(Usuario usuario);
}
