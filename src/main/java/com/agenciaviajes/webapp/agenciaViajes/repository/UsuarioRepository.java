package com.agenciaviajes.webapp.agenciaViajes.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import com.agenciaviajes.webapp.agenciaViajes.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    List<Usuario> findByNombre(String nombre);
}
