package com.agenciaviajes.webapp.agenciaViajes.model;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name="Paradas")

public class Parada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String ubicacion;
    private Time tiempo;
    private String tipoParada;

}
