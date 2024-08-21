package com.agenciaviajes.webapp.agenciaViajes.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table (name="Comentarios")

public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer calificacion;
    private String comentario;
    private Date fecha;

    @ManyToOne
    private Oferta oferta;

    @ManyToOne
    private Viajero viajero;
}


