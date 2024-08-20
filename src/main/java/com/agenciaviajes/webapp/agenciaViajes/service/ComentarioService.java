package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenciaviajes.webapp.agenciaViajes.model.Comentario;
import com.agenciaviajes.webapp.agenciaViajes.repository.ComentarioRepository;

@Service
public class ComentarioService implements IComentarioService {

    @Autowired
    ComentarioRepository comentarioRepository;

    @Override
    public List<Comentario> ListarComentario() {
        return comentarioRepository.findAll();

    }

    @Override
    public Comentario guardarComentario(Comentario comentario) {
        comentarioRepository.save(comentario);
        return comentario;

    }

    @Override
    public Comentario busComentarioPorId(Long id) {
        return comentarioRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarComentario(Comentario comentario) {
        comentarioRepository.delete(comentario);
    }

}
