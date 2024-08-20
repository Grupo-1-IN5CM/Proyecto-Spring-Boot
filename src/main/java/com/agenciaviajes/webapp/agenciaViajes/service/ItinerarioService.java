package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.agenciaviajes.webapp.agenciaViajes.model.Itinerario;
import com.agenciaviajes.webapp.agenciaViajes.repository.ItinerarioRepository;

@Service
public class ItinerarioService implements IItinerarioService {

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    @Override
    public List<Itinerario> listarItinerario() {
       return itinerarioRepository.findAll();
    }

    @Override
    public void guardarItinerario(Itinerario itinerario) {
       itinerarioRepository.save(itinerario);
    }

    @Override
    public Itinerario buscarItinerarioPorId(Long id) {
        return itinerarioRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarItinerario(Itinerario itinerario) {
        itinerarioRepository.delete(itinerario);
    }

    
}

