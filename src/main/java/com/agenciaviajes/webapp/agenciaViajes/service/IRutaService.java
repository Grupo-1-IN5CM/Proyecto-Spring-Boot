package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import com.agenciaviajes.webapp.agenciaViajes.model.Ruta;

public interface IRutaService {

    public List<Ruta> ListarRuta();

    public Ruta guardarRuta(Ruta ruta);
    
    public Ruta busRutaPorId(Long id);

    public void eliminarRuta(Ruta ruta);
}
