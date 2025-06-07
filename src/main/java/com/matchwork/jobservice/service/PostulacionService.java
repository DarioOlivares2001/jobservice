package com.matchwork.jobservice.service;

import com.matchwork.jobservice.dto.MiniPerfilDTO;
import com.matchwork.jobservice.dto.PostulacionUsuarioResponse;
import com.matchwork.jobservice.dto.PostulanteConPerfilDTO;
import com.matchwork.jobservice.model.Job;
import com.matchwork.jobservice.model.Postulacion;
import com.matchwork.jobservice.model.Usuario;
import com.matchwork.jobservice.repository.JobRepository;
import com.matchwork.jobservice.repository.PostulacionRepository;
import com.matchwork.jobservice.repository.UsuarioRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@Service
public class PostulacionService {

    private final PostulacionRepository postulacionRepository;
    private final JobRepository jobRepository;


    private final UsuarioRepository usuarioRepository;



    public PostulacionService(PostulacionRepository postulacionRepository, JobRepository jobRepository, UsuarioRepository usuarioRepository) {
        this.postulacionRepository = postulacionRepository;
        this.jobRepository = jobRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public Postulacion postular(Long usuarioId, Long trabajoId) {
        if (postulacionRepository.existsByUsuarioIdAndTrabajo_Id(usuarioId, trabajoId)) {
            throw new RuntimeException("Ya estás postulado a este trabajo.");
        }

        Job trabajo = jobRepository.findById(trabajoId)
                .orElseThrow(() -> new RuntimeException("Trabajo no encontrado"));

        Postulacion postulacion = new Postulacion();
        postulacion.setUsuarioId(usuarioId);
        postulacion.setTrabajo(trabajo);

        return postulacionRepository.save(postulacion);
    }

    public List<Postulacion> obtenerPostulacionesPorUsuario(Long usuarioId) {
        return postulacionRepository.findByUsuarioId(usuarioId);
    }

    public void eliminarPostulacion(Long id) {
        postulacionRepository.deleteById(id);
    }

    public List<Postulacion> obtenerPostulacionesPorTrabajo(Long trabajoId) {
        return postulacionRepository.findByTrabajo_Id(trabajoId);
    }

    public List<PostulacionUsuarioResponse> obtenerPostulacionesConUsuarioPorTrabajo(Long trabajoId) {
        List<Postulacion> postulaciones = postulacionRepository.findByTrabajo_Id(trabajoId);
        List<PostulacionUsuarioResponse> respuesta = new ArrayList<>();

        for (Postulacion p : postulaciones) {
            Usuario usuario = usuarioRepository.findById(p.getUsuarioId()).orElse(null);

            if (usuario != null) {
                respuesta.add(PostulacionUsuarioResponse.from(p, usuario.getNombre(), usuario.getCorreo()));
            } else {
                respuesta.add(PostulacionUsuarioResponse.from(p, "Desconocido", "No encontrado"));
            }
        }

        return respuesta;
    }

    public boolean yaPostulado(Long usuarioId, Long trabajoId) {
        return postulacionRepository.existsByUsuarioIdAndTrabajo_Id(usuarioId, trabajoId);
    }


  
    public List<PostulanteConPerfilDTO> listarMiniPerfilesPorTrabajo(Long trabajoId) {
        return postulacionRepository.findPostulantesConPerfilByTrabajoId(trabajoId);
    }

    
}
