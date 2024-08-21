package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import com.agenciaviajes.webapp.agenciaViajes.model.Vehiculo;

public interface IVehiculoService {
    public List<Vehiculo> ListarVehiculo();

    public Vehiculo guardarVehiculo(Vehiculo vehiculo);
    
    public Vehiculo busVehiculoPorId(Long id);

    public void eliminarVehiculo(Vehiculo vehiculo);
}
