package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenciaviajes.webapp.agenciaViajes.model.Ruta;
import com.agenciaviajes.webapp.agenciaViajes.repository.RutaRepository;

@Service
public class RutaService implements IRutaService {

    @Autowired 
    RutaRepository rutaRepository;

    @Override
    public List<Ruta> ListarRuta() {
        return rutaRepository.findAll();
    }

    @Override
    public Ruta guardarRuta(Ruta ruta) {
      return rutaRepository.save(ruta);
    }

    @Override
    public Ruta busRutaPorId(Long id) {
       return rutaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarRuta(Ruta ruta) {
        rutaRepository.delete(ruta);
    }
}
