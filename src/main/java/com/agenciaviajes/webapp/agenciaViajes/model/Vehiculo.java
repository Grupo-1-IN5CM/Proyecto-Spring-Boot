package com.agenciaviajes.webapp.agenciaViajes.model;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name="Vehiculos")
public class Vehiculo {
    @Id
    private Long id;
    private String Marca;
    private String Modelo;
    private Time TipoVehiculo;
    private String Capacidad;
}
