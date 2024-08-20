package com.agenciaviajes.webapp.agenciaViajes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agenciaviajes.webapp.agenciaViajes.model.Itinerario;

public interface ItinerarioRepository extends JpaRepository<Itinerario,Long> {

}
