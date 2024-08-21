package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenciaviajes.webapp.agenciaViajes.model.Oferta;
import com.agenciaviajes.webapp.agenciaViajes.model.Vehiculo;
import com.agenciaviajes.webapp.agenciaViajes.repository.OfertaRepository;

@Service
public class OfertaService  implements IOfertaService{
    @Autowired 
    OfertaRepository ofertaRepository;

    @Override
    public List<Oferta> ListarOferta() {
        return ofertaRepository.findAll();
    }

    @Override
    public Oferta guardarOferta(Oferta oferta) {
      return ofertaRepository.save(oferta);
    }

    @Override
    public Oferta busOfertaPorId(Long id) {
       return ofertaRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarOferta(Oferta oferta) {
        ofertaRepository.delete(oferta);
    }

    @Override
    public Boolean validarFechasConsistentes(Oferta oferta) {
        return oferta.getFechaInicio() != null && oferta.getFechaFinal() != null
               && !oferta.getFechaInicio().after(oferta.getFechaFinal());
    }

    public Boolean verificarDisponibilidadVehiculo(Vehiculo vehiculo) {
    return vehiculo != null && vehiculo.getDisponibilidad().equals(Boolean.TRUE);
}
}
