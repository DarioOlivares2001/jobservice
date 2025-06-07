// src/main/java/com/matchwork/jobservice/model/Habilidad.java
package com.matchwork.jobservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "habilidades")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Habilidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
}
