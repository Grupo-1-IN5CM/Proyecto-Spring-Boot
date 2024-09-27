package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenciaviajes.webapp.agenciaViajes.model.Vehiculo;
import com.agenciaviajes.webapp.agenciaViajes.repository.VehiculoRepository;

@Service
public class VehiculoService implements IVehiculoService {

    @Autowired 
    VehiculoRepository vehiculoRepository;

    @Override
    public List<Vehiculo> ListarVehiculo() {
        return vehiculoRepository.findAll();
    }

    @Override
    public Vehiculo guardarVehiculo(Vehiculo vehiculo) {
      return vehiculoRepository.save(vehiculo);
    }

    @Override
    public Vehiculo busVehiculoPorId(Long id) {
       return vehiculoRepository.findById(id).orElse(null);
    }

    @Override
    public void eliminarVehiculo(Vehiculo vehiculo) {
        vehiculoRepository.delete(vehiculo);
    }
}
