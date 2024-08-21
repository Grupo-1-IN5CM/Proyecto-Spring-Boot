package com.agenciaviajes.webapp.agenciaViajes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.sql.Time;

@Entity
@Data
@Table (name="Ofertas")

public class Oferta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date fechaInicio;
    private Date fechaFinal;
    private Time tiempo;
    private String tipoParada;
    @ManyToOne
    private Vehiculo vehiculo;
    @ManyToOne
    private Ruta ruta;
    @ManyToMany
    @JoinTable(name = "oferta_viajero",
    joinColumns = @JoinColumn(name = "ofertas_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "viajeros_id", referencedColumnName = "id"))
    private List<Viajero> viajero;
}
