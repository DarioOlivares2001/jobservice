package com.matchwork.jobservice.dto;

import com.matchwork.jobservice.model.Job;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class TrabajoSugeridoDTO {
    private Long id;
    private Long creatorId;
    private String titulo;
    private String descripcion;
    private String empresa;
    private String ubicacion;
    private String tipo;
    private Integer sueldo;
    private LocalDateTime fechaPublicacion;
    private String estado;
    private int puntajeAfinidad;

    /*** Campos adicionales (se incluyen para mostrar “más datos” en el cliente si se desea) ***/
    private LocalDate fechaLimitePostulacion;
    private String nivelExperiencia;
    private String categoria;
    private String departamento;
    private Integer vacantes;
    private Boolean remoto;
    private String duracionContrato;
    private String requisitos;
    private String habilidadesRequeridas;
    private String beneficios;
    private String idiomas;
    private String companyWebsite;
    private String logoUrl;
    private String etiquetas;

    /** Constructor que toma un Job y le agrega puntajeAfinidad **/
    public TrabajoSugeridoDTO(Job job, int puntajeAfinidad) {
        this.id = job.getId();
        this.creatorId = job.getCreatorId();
        this.titulo = job.getTitulo();
        this.descripcion = job.getDescripcion();
        this.empresa = job.getEmpresa();
        this.ubicacion = job.getUbicacion();
        this.tipo = job.getTipo();
        this.sueldo = job.getSueldo();
        this.fechaPublicacion = job.getFechaPublicacion();
        this.estado = job.getEstado();
        this.puntajeAfinidad = puntajeAfinidad;

        // Ahora asignamos también los campos opcionales
        this.fechaLimitePostulacion = job.getFechaLimitePostulacion();
        this.nivelExperiencia = job.getNivelExperiencia();
        this.categoria = job.getCategoria();
        this.departamento = job.getDepartamento();
        this.vacantes = job.getVacantes();
        this.remoto = job.getRemoto();
        this.duracionContrato = job.getDuracionContrato();
        this.requisitos = job.getRequisitos();
        this.habilidadesRequeridas = job.getHabilidadesRequeridas();
        this.beneficios = job.getBeneficios();
        this.idiomas = job.getIdiomas();
        this.companyWebsite = job.getCompanyWebsite();
        this.logoUrl = job.getLogoUrl();
        this.etiquetas = job.getEtiquetas();
    }
}
