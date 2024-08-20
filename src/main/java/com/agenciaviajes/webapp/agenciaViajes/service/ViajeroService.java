package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenciaviajes.webapp.agenciaViajes.model.Viajero;
import com.agenciaviajes.webapp.agenciaViajes.repository.ViajeroRepository;

@Service
public class ViajeroService implements IViajeroService{

    @Autowired
    ViajeroRepository viajeroRepository;

    @Override
    public List<Viajero> listarViajeros() {
       return  viajeroRepository.findAll();
    }

    @Override
    public void guardarViajero(Viajero viajero) {
        viajeroRepository.save(viajero);
    }

    @Override
    public Viajero buscarViajeroPorId(Long Id) {
        return viajeroRepository.findById(Id).orElse(null);
    }

    @Override
    public void eliminarViajero(Viajero viajero) {
        viajeroRepository.delete(viajero);
    }
}
