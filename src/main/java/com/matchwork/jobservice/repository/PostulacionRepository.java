package com.matchwork.jobservice.repository;

import com.matchwork.jobservice.model.Postulacion;
import com.matchwork.jobservice.dto.MiniPerfilDTO;
import com.matchwork.jobservice.dto.PostulanteConPerfilDTO;
import com.matchwork.jobservice.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostulacionRepository extends JpaRepository<Postulacion, Long> {
    List<Postulacion> findByUsuarioId(Long usuarioId);
    List<Postulacion> findByTrabajo(Job trabajo);
    boolean existsByUsuarioIdAndTrabajo_Id(Long usuarioId, Long trabajoId);
    long countByTrabajoId(Long trabajoId);
    List<Postulacion> findByTrabajo_Id(Long trabajoId);



    /**
     * Obtiene todos los postulantes (mini‐perfil) para un trabajo específico.
     * JOIN a `perfil_profesional` consultando por la columna `usuario_id`.
     */
   /* @Query("""
      SELECT new com.matchwork.jobservice.dto.MiniPerfilDTO(
         p.id,
         u.nombre,           
         p.fotoUrl,
         p.titulo
      )
      FROM Postulacion pst
      JOIN PerfilProfesional p ON p.id = pst.usuarioId
      JOIN Usuario u ON u.id = p.id
      WHERE pst.trabajo.id = :trabajoId
    """)
    List<MiniPerfilDTO> findMiniPerfilesByTrabajoId(@Param("trabajoId") Long trabajoId);*/



    @Query("""
    SELECT new com.matchwork.jobservice.dto.PostulanteConPerfilDTO(
      pst.id,
      p.id,
      u.nombre,
      p.titulo,
      p.fotoUrl,
      p.presentacion,
      pst.fechaPostulacion
    )
    FROM Postulacion pst
    JOIN PerfilProfesional p ON p.id = pst.usuarioId
    JOIN Usuario u ON u.id = p.id
    WHERE pst.trabajo.id = :trabajoId
  """)
  List<PostulanteConPerfilDTO> findPostulantesConPerfilByTrabajoId(@Param("trabajoId") Long trabajoId);

}
