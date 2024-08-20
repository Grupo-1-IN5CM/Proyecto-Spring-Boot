package com.agenciaviajes.webapp.agenciaViajes.model;

import java.sql.Time;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name="Rutas")
public class Ruta {
    @Id
    private Long id;
    private String nombre;
    private String Distancia;
    private Time Duracion;
}
   