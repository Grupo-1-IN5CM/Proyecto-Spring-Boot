package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenciaviajes.webapp.agenciaViajes.model.Parada;
import com.agenciaviajes.webapp.agenciaViajes.repository.ParadaRepository;

@Service
public class ParadaService implements IParadaService {

    @Autowired 
    ParadaRepository paradaRepository;

    @Override
    public List<Parada> ListarParada() {
        return paradaRepository.findAll();
    }

    @Override
    public Parada guardarParada(Parada parada) {
      return paradaRepository.save(parada);
    }

    @Override
    public Parada busParadaPorId(Long id) {
       return paradaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarParada(Parada parada) {
        paradaRepository.delete(parada);
    }
}