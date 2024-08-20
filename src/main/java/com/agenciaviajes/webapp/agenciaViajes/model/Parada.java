package com.agenciaviajes.webapp.agenciaViajes.model;
 
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
 
@Entity
@Data
@Table (name="Paradas")
 
public class Parada {
    @Id
    private Long id;
    private String nombre;
    private String ubicacion;
    private String tiempo;
    private String tipoParada;
 
}
