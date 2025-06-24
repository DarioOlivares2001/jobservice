package com.matchwork.jobservice.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "perfil_profesional")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilProfesional {

    @Id
    private Long id;

  
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "id")
    @JsonBackReference
    private Usuario usuario;

    
    private String titulo;
    private String fotoUrl;

    @Column(length = 2000)
    private String presentacion;

    private String disponibilidad;
    private String modoTrabajo;

   
}
