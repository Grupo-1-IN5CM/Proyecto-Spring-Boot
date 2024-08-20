package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.agenciaviajes.webapp.agenciaViajes.model.Vehiculo;
import com.agenciaviajes.webapp.agenciaViajes.repository.VehiculoRepository;

public class VehiculoService implements IVehiculoService {

    @Autowired 
    VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> ListarRuta() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo guardarRuta(Vehiculo vehiculo) {
      return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo busRutaPorId(Long id) {
       return vehiculoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarRuta(Vehiculo vehiculo) {
        vehiculoRepository.delete(vehiculo);
    }
}
