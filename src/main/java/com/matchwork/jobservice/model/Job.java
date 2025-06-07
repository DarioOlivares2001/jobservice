// src/main/java/com/matchwork/jobservice/model/Job.java
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

    /*** NUEVO: ID del usuario/empresa que creó esta oferta ***/
    @Column(name = "creator_id", nullable = false)
    private Long creatorId;

    /** Datos básicos (igual que antes) **/
    private String titulo;

    @Column(length = 5000)
    private String descripcion;

    private String empresa;
    private String ubicacion;
    private String tipo;      // Ej: "Full_Time", "Part_Time", "Freelance"
    private Integer sueldo;

    /** Fecha de creación (igual que antes) **/
    @Column(name = "fecha_publicacion")
    private LocalDateTime fechaPublicacion;

    @Column(nullable = false)
    private String estado;    // "ACTIVO" o "INACTIVO"

    /*** Campos adicionales “opcionales” sugeridos ***/
    @Column(name = "fecha_limite_postulacion")
    private LocalDate fechaLimitePostulacion;   // Ej: 2025-07-31

    private String nivelExperiencia;             // Ej: "Junior", "Semi Senior", "Senior"
    private String categoria;                    // Ej: "Fintech", "Salud", ...
    private String departamento;                 // Ej: "TI", "Marketing", ...
    private Integer vacantes;                    // P.ej. 1, 2, 3…
    private Boolean remoto;                      // true = admite remoto
    private String duracionContrato;             // Ej: "6 meses", "Indefinido"
    
    @Column(length = 2000)
    private String requisitos;                   // Texto con requisitos (puede contener varias líneas)
    
    @Column(length = 2000)
    private String habilidadesRequeridas;        // Ej: "Java, Spring, SQL"
    
    @Column(length = 2000)
    private String beneficios;                   // Ej: "Seguro médico, flexibilidad horaria"
    
    private String idiomas;                      // Ej: "Inglés avanzado, Español nativo"
    private String companyWebsite;               // Ej: "https://www.miempresa.cl"
    private String logoUrl;                      // URL al logo de la empresa
    private String etiquetas;                    // Ej: "Java,Backend,Spring" (CSV simple)

    @PrePersist
    protected void onCreate() {
        this.fechaPublicacion = LocalDateTime.now();
        this.estado = "ACTIVO";
    }
}
