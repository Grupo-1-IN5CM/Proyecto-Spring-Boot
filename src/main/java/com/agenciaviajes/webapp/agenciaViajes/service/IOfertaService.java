package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import com.agenciaviajes.webapp.agenciaViajes.model.Oferta;
import com.agenciaviajes.webapp.agenciaViajes.model.Vehiculo;

public interface IOfertaService {
    public List<Oferta> ListarOferta();

    public Oferta guardarOferta(Oferta oferta);
    
    public Oferta busOfertaPorId(Long id);

    public void eliminarOferta(Oferta oferta);

    public Boolean validarFechasConsistentes(Oferta oferta);

    public Boolean verificarDisponibilidadVehiculo(Vehiculo vehiculo);
}
