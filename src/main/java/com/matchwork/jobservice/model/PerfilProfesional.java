// src/main/java/com/matchwork/jobservice/model/PerfilProfesional.java
package com.matchwork.jobservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

/**
 * Entidad simplificada de PERFIL_PROFESIONAL,
 * sin colecciones de experiencias ni estudios.
 */
@Entity
@Table(name = "perfil_profesional")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilProfesional {

    @Id
    private Long id;

    /**
     * Relación uno a uno con Usuario. 
     * Usamos JsonBackReference para evitar loops al serializar.
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private Usuario usuario;

    // Campos de la tabla perfil_profesional que realmente nos importan:
    private String titulo;
    private String fotoUrl;

    @Column(length = 2000)
    private String presentacion;

    private String disponibilidad;
    private String modoTrabajo;

    // NO incluimos las listas de experiencias ni estudios:
    // @OneToMany(...) private List<PerfilExperiencia> experiencias;
    // @OneToMany(...) private List<PerfilEstudio> estudios;
}
