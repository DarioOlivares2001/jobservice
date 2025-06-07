package com.matchwork.jobservice.service;

import com.matchwork.jobservice.dto.TrabajoSugeridoDTO;
import com.matchwork.jobservice.model.Job;
import com.matchwork.jobservice.repository.JobRepository;
import com.matchwork.jobservice.repository.UsuarioHabilidadRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MatchService {

    private final JobRepository jobRepository;
    private final UsuarioHabilidadRepository habRepo;

    public MatchService(JobRepository jobRepository,
                        UsuarioHabilidadRepository habRepo) {
        this.jobRepository = jobRepository;
        this.habRepo       = habRepo;
    }

    public List<TrabajoSugeridoDTO> recomendarTrabajos(Long usuarioId, int page, int size) {
        // 1) Leer las habilidades del usuario desde Oracle
        List<String> habilidades = habRepo.findByUsuario_Id(usuarioId).stream()
            .map(uh -> uh.getHabilidad().getNombre())
            .collect(Collectors.toList());

        if (habilidades.isEmpty()) {
            return Collections.emptyList();
        }

        // 2) Filtrar solo trabajos con estado "ACTIVO"
        List<Job> trabajosActivos = jobRepository.findAll().stream()
            .filter(job -> "ACTIVO".equalsIgnoreCase(job.getEstado()))
            .collect(Collectors.toList());

        // 3) Calcular puntaje de afinidad y ordenar
        List<TrabajoSugeridoDTO> resultados = trabajosActivos.stream()
            .map(job -> {
                long coincidencias = habilidades.stream()
                    .filter(hab ->
                        job.getTitulo().toLowerCase().contains(hab.toLowerCase()) ||
                        job.getDescripcion().toLowerCase().contains(hab.toLowerCase())
                    ).count();
                int puntaje = (int) ((coincidencias * 100.0) / habilidades.size());
                return new TrabajoSugeridoDTO(job, puntaje);
            })
            .filter(dto -> dto.getPuntajeAfinidad() > 0)
            .sorted(Comparator.comparingInt(TrabajoSugeridoDTO::getPuntajeAfinidad).reversed())
            .skip((long) page * size)
            .limit(size)
            .collect(Collectors.toList());

        // 4) Fallback: si no hay coincidencias, devolvemos trabajos aleatorios
        if (resultados.isEmpty()) {
            return trabajosActivos.stream()
                .limit(size)
                .map(job -> new TrabajoSugeridoDTO(job, 0))
                .collect(Collectors.toList());
        }

        return resultados;
    }
}
