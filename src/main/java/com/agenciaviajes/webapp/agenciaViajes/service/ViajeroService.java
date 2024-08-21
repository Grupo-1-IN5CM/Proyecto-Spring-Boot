package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenciaviajes.webapp.agenciaViajes.model.Viajero;
import com.agenciaviajes.webapp.agenciaViajes.repository.ViajeroRepository;

import java.util.Date;

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

    @Override
    public Boolean esCorreoUnico(String correo) {
        return viajeroRepository.findByCorreo(correo) == null;  // Devuelve true si no existe un viajero con ese correo
    }

    @Override
    public Boolean validarFechaRegistroNoFutura(Date fechaRegistro) {
        return !fechaRegistro.after(new Date());  // Retorna true si la fecha de registro no es futura
    }
}
