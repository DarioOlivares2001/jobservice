// src/main/java/com/matchwork/jobservice/dto/JobResponse.java
package com.matchwork.jobservice.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.*;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobResponse {
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
}
