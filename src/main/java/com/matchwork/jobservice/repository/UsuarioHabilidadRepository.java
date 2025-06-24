package com.matchwork.jobservice.repository;

import com.matchwork.jobservice.model.UsuarioHabilidad;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioHabilidadRepository
    extends JpaRepository<UsuarioHabilidad, Long> {

    
    List<UsuarioHabilidad> findByUsuario_Id(Long usuarioId);
}
