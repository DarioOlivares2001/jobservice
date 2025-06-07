// src/main/java/com/matchwork/jobservice/repository/UsuarioRepository.java
package com.matchwork.jobservice.repository;

import com.matchwork.jobservice.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
