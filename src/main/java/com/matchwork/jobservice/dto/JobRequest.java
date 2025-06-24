// src/main/java/com/matchwork/jobservice/dto/JobRequest.java
package com.matchwork.jobservice.dto;

import java.time.LocalDate;
import lombok.*;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {
    private String titulo;
    private String descripcion;
    private String empresa;
    private String ubicacion;
    private String tipo;               
    private Integer sueldo;

    
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
