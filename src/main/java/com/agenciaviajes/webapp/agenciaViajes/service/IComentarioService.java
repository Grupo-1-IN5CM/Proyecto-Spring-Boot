
package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import com.agenciaviajes.webapp.agenciaViajes.model.Comentario;

public interface IComentarioService {

    public List<Comentario> ListarComentario();

    public Comentario guardarComentario(Comentario comentario);
    
    public Comentario busComentarioPorId(Long id);

    public void eliminarComentario(Comentario comentario);

    public Boolean validarCalificacionEnRango(Integer calificacion);

    public Boolean validarComentarioNoVacio(String comentario);

}