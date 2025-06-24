package com.matchwork.jobservice.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trabajos")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

   
    private String titulo;

    @Column(length = 5000)
    private String descripcion;

    private String empresa;
    private String ubicacion;
    private String tipo;     
    private Integer sueldo;

    
    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    @Column(nullable = false)
    private String estado;    

    
    @Column(name = "fecha_limite_postulacion")
    private LocalDate fechaLimitePostulacion;   

    private String nivelExperiencia;            
    private String categoria;                   
    private String departamento;                
    private Integer vacantes;                   
    private Boolean remoto;                     
    private String duracionContrato;            
    
    @Column(length = 2000)
    private String requisitos;                  
    
    @Column(length = 2000)
    private String habilidadesRequeridas;       
    
    @Column(length = 2000)
    private String beneficios;                  
    
    private String idiomas;                     
    private String companyWebsite;              
    private String logoUrl;                     
    private String etiquetas;                   

    @PrePersist
    protected void onCreate() {
        this.fechaPublicacion = LocalDateTime.now();
        this.estado = "ACTIVO";
    }
}
