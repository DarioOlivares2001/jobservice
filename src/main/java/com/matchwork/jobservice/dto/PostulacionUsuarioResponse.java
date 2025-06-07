package com.matchwork.jobservice.dto;

import com.matchwork.jobservice.model.Postulacion;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostulacionUsuarioResponse {
    private Long id;
    private Long trabajoId;
    private Long usuarioId;
    private String nombre;
    private String correo;

    public static PostulacionUsuarioResponse from(Postulacion p, String nombre, String correo) {
        return new PostulacionUsuarioResponse(
            p.getId(),
            p.getTrabajo().getId(),
            p.getUsuarioId(),
            nombre,
            correo
        );
    }
}
