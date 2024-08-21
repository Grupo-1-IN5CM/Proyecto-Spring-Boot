package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import com.agenciaviajes.webapp.agenciaViajes.model.Itinerario;

public interface IItinerarioService { 

    public List<Itinerario> listarItinerario();
    
    public void guardarItinerario(Itinerario itinerario);

    public Itinerario buscarItinerarioPorId(Long id);

    public void eliminarItinerario(Itinerario itinerario);
   
}
