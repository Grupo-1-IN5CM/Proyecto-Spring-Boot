package com.agenciaviajes.webapp.agenciaViajes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenciaviajes.webapp.agenciaViajes.model.Viajero;


public interface ViajeroRepository extends JpaRepository <Viajero,Long> {

    Optional<Viajero> findByCorreo(String correo);
}
