package com.agenciaviajes.webapp.agenciaViajes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

import java.util.Date;
import java.sql.Time;

@Entity
@Data
@Table (name="Ofertas")

public class Oferta {
    @Id
    private Long id;
    private Date FechaInicio;
    private Date FechaFinal;
    private Time tiempo;
    private String tipoParada;
    @ManyToOne
    private Vehiculo vehiculo;
    @ManyToOne
    private Ruta ruta;
}
