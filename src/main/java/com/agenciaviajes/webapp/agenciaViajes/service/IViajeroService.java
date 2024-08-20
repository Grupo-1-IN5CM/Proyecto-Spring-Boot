package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import com.agenciaviajes.webapp.agenciaViajes.model.Viajero;

public interface IViajeroService {
    public List<Viajero> listarViajeros();

    public void guardarViajero(Viajero viajero);

    public Viajero buscarViajeroPorId(Long Id);

    public void eliminarViajero(Viajero viajero);
}
