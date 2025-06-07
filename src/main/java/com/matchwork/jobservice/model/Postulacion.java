package com.matchwork.jobservice.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "postulaciones")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Mantengo sólo el ID primitivo que mapea a la columna física `usuario_id`.
     * Hibernate, por convención, mapeará esta propiedad a esa columna.
     */
    private Long usuarioId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trabajo_id", nullable = false)
    private Job trabajo;

    private LocalDateTime fechaPostulacion;

    @PrePersist
    public void prePersist() {
        this.fechaPostulacion = LocalDateTime.now();
    }

    // ─── Getters / Setters ───

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Job getTrabajo() {
        return trabajo;
    }

    public void setTrabajo(Job trabajo) {
        this.trabajo = trabajo;
    }

    public LocalDateTime getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(LocalDateTime fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }
}
