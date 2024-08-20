package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import com.agenciaviajes.webapp.agenciaViajes.model.Oferta;

public interface IOfertaService {
    public List<Oferta> ListarOferta();

    public Oferta guardarOferta(Oferta oferta);
    
    public Oferta busOfertaPorId(Long id);

    public void eliminarOferta(Oferta oferta);
}
