package com.matchwork.jobservice.dto;

import java.time.LocalDateTime;

public class PostulanteConPerfilDTO {
    private Long postulacionId;
    private Long usuarioId;
    private String nombreUsuario;
    private String tituloProfesional;
    private String fotoUrl;
    private String presentacion;      
    private LocalDateTime fechaPostulacion;

    public PostulanteConPerfilDTO(
        Long postulacionId,
        Long usuarioId,
        String nombreUsuario,
        String tituloProfesional,
        String fotoUrl,
        String presentacion,
        LocalDateTime fechaPostulacion
    ) {
        this.postulacionId = postulacionId;
        this.usuarioId      = usuarioId;
        this.nombreUsuario  = nombreUsuario;
        this.tituloProfesional = tituloProfesional;
        this.fotoUrl        = fotoUrl;
        this.presentacion   = presentacion;
        this.fechaPostulacion = fechaPostulacion;
    }

    public Long getPostulacionId() {
        return postulacionId;
    }

    public void setPostulacionId(Long postulacionId) {
        this.postulacionId = postulacionId;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getTituloProfesional() {
        return tituloProfesional;
    }

    public void setTituloProfesional(String tituloProfesional) {
        this.tituloProfesional = tituloProfesional;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public LocalDateTime getFechaPostulacion() {
        return fechaPostulacion;
    }

    public void setFechaPostulacion(LocalDateTime fechaPostulacion) {
        this.fechaPostulacion = fechaPostulacion;
    }
   
}
