package com.matchwork.jobservice.dto;

public class MiniPerfilDTO {
    private Long usuarioId;      
    private String nombreUsuario; 
    private String fotoUrl;
    private String tituloProfesional;

    public MiniPerfilDTO(Long usuarioId, String nombreUsuario, String fotoUrl, String tituloProfesional) {
        this.usuarioId = usuarioId;
        this.nombreUsuario = nombreUsuario;
        this.fotoUrl = fotoUrl;
        this.tituloProfesional = tituloProfesional;
    }

    // Getters / Setters
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
    public String getFotoUrl() {
        return fotoUrl;
    }
    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }
    public String getTituloProfesional() {
        return tituloProfesional;
    }
    public void setTituloProfesional(String tituloProfesional) {
        this.tituloProfesional = tituloProfesional;
    }
}
