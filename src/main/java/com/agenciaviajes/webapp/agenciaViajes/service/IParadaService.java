package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import com.agenciaviajes.webapp.agenciaViajes.model.Parada;

public interface IParadaService {

    public List<Parada> ListarParada();

    public Parada guardarParada(Parada parada);
    
    public Parada busParadaPorId(Long id);

    public void eliminarParada(Parada parada);
}
