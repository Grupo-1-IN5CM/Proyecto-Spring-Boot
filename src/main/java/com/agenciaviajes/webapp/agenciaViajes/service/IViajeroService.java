package com.agenciaviajes.webapp.agenciaViajes.service;

import java.util.List;

import com.agenciaviajes.webapp.agenciaViajes.model.Viajero;

import java.util.Date;


public interface IViajeroService {
    public List<Viajero> listarViajeros();

    public void guardarViajero(Viajero viajero);

    public Viajero buscarViajeroPorId(Long Id);

    public void eliminarViajero(Viajero viajero);

    public Boolean esCorreoUnico(String correo);

    public Boolean validarFechaRegistroNoFutura(Date fechaRegistro);
}
