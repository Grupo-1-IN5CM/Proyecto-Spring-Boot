

package com.agenciaviajes.webapp.agenciaViajes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenciaviajes.webapp.agenciaViajes.model.Comentario;

public interface ComentarioRepository  extends JpaRepository<Comentario,Long>{

}