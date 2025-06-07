// src/main/java/com/matchwork/jobservice/dto/JobRequest.java
package com.matchwork.jobservice.dto;

import java.time.LocalDate;
import lombok.*;

/**
 * DTO que representa el JSON que llega en POST o PUT /api/jobs.
 * Ahora incluye el campo creatorId y todos los campos opcionales.
 */
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequest {
    private String titulo;
    private String descripcion;
    private String empresa;
    private String ubicacion;
    private String tipo;               // "Full_Time" / "Part_Time" / "Freelance"
    private Integer sueldo;

    /*** Campos opcionales (ahora soportados) ***/
    private LocalDate fechaLimitePostulacion; // Ej: "2025-07-31"
    private String nivelExperiencia;          // "Junior" / "Semi Senior" / "Senior"
    private String categoria;                 // "Fintech", "Salud", ...
    private String departamento;              // "TI", "Marketing", ...
    private Integer vacantes;                 // Cantidad de vacantes idénticas
    private Boolean remoto;                   // true = admite remoto/híbrido
    private String duracionContrato;          // "6 meses", "Indefinido"
    private String requisitos;                // Texto con requisitos
    private String habilidadesRequeridas;     // Ej: "Java, Spring, SQL"
    private String beneficios;                // "Seguro médico, flexibilidad"
    private String idiomas;                   // "Inglés avanzado, Español nativo"
    private String companyWebsite;            // "https://www.miempresa.cl"
    private String logoUrl;                   // "https://cdn.miempresa.cl/logo.png"
    private String etiquetas;                 // CSV: "Java,Backend,Spring"
}
