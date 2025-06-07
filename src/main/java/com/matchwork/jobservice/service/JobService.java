// src/main/java/com/matchwork/jobservice/service/JobService.java
package com.matchwork.jobservice.service;

import com.matchwork.jobservice.dto.JobRequest;
import com.matchwork.jobservice.dto.JobResponse;
import com.matchwork.jobservice.model.Job;
import com.matchwork.jobservice.repository.JobRepository;
import com.matchwork.jobservice.repository.PostulacionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;
import org.springframework.web.server.ResponseStatusException;
import static org.springframework.http.HttpStatus.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class JobService {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PostulacionRepository postulacionRepository;

    /**
     * Crear un nuevo Job a partir de JobRequest.
     * → Se asigna fechaPublicacion y estado en @PrePersist
     */

     /**
     * Devuelve la lista de JobResponse para un creatorId dado,
     * ordenada de más reciente a más antiguo (fechaPublicacion DESC).
     */
    public List<JobResponse> findByCreator(Long creatorId) {
        // Verificar si el creator existe (opcional, dependiendo de tu lógica)
        // Ejemplo simple: asumo que si no hay trabajos, devuelvo lista vacía
        return jobRepository.findByCreatorIdOrderByFechaPublicacionDesc(creatorId)
                            .stream()
                            .filter(job -> "ACTIVO".equalsIgnoreCase(job.getEstado()))
                            .map(this::mapToResponse)
                            .collect(Collectors.toList());
    }



    public JobResponse crear(Long creatorId, JobRequest req) {
        // Validación básica: creatorId NO puede venir nulo
        if (creatorId == null) {
            throw new ResponseStatusException(BAD_REQUEST, "creatorId es obligatorio");
        }

        Job job = Job.builder()
                .creatorId(creatorId)
                .titulo(req.getTitulo())
                .descripcion(req.getDescripcion())
                .empresa(req.getEmpresa())
                .ubicacion(req.getUbicacion())
                .tipo(req.getTipo())
                .sueldo(req.getSueldo())
                // Campos opcionales
                .fechaLimitePostulacion(req.getFechaLimitePostulacion())
                .nivelExperiencia(req.getNivelExperiencia())
                .categoria(req.getCategoria())
                .departamento(req.getDepartamento())
                .vacantes(req.getVacantes())
                .remoto(req.getRemoto() != null ? req.getRemoto() : false)
                .duracionContrato(req.getDuracionContrato())
                .requisitos(req.getRequisitos())
                .habilidadesRequeridas(req.getHabilidadesRequeridas())
                .beneficios(req.getBeneficios())
                .idiomas(req.getIdiomas())
                .companyWebsite(req.getCompanyWebsite())
                .logoUrl(req.getLogoUrl())
                .etiquetas(req.getEtiquetas())
                .build();

        Job saved = jobRepository.save(job);
        return mapToResponse(saved);
    }

    /**
     * Eliminar el Job con ID = id (hard delete).
     */
    public void delete(Long id) {
        if (!jobRepository.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND, "Trabajo no encontrado con ID: " + id);
        }
        jobRepository.deleteById(id);
    }

    /**
     * Listar todos los trabajos “ACTIVO”. Mapeados a JobResponse.
     */
    public List<JobResponse> findAll() {
        return jobRepository.findAll().stream()
                .filter(job -> "ACTIVO".equalsIgnoreCase(job.getEstado()))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Obtener un solo trabajo por ID (sin importar estado),
     * y devolverlo como JobResponse.
     */
    public JobResponse findById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Trabajo no encontrado con ID: " + id));
        return mapToResponse(job);
    }

    /**
     * Actualizar un trabajo existente:
     * Se reemplazan título, descripción, empresa, ubicación, tipo, sueldo,
     * y todos los campos opcionales si vienen en JobRequest.
     */
   public JobResponse update(Long id, Long creatorId, JobRequest req) {
        Job existente = jobRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Trabajo no encontrado con ID: " + id));
        
           if (!existente.getCreatorId().equals(creatorId)) {
        throw new ResponseStatusException(FORBIDDEN, "No tienes permiso para editar este job");
    }

        // Si quisieras forzar que SOLO el mismo creatorId edite, podrías chequear:
        // if (!Objects.equals(existente.getCreatorId(), req.getCreatorId())) {
        //     throw new ResponseStatusException(FORBIDDEN, "No tienes permiso para editar este job");
        // }

        existente.setTitulo(req.getTitulo());
        existente.setDescripcion(req.getDescripcion());
        existente.setEmpresa(req.getEmpresa());
        existente.setUbicacion(req.getUbicacion());
        existente.setTipo(req.getTipo());
        existente.setSueldo(req.getSueldo());

        // Sobreescribir opcionales
        existente.setFechaLimitePostulacion(req.getFechaLimitePostulacion());
        existente.setNivelExperiencia(req.getNivelExperiencia());
        existente.setCategoria(req.getCategoria());
        existente.setDepartamento(req.getDepartamento());
        existente.setVacantes(req.getVacantes());
        existente.setRemoto(req.getRemoto() != null ? req.getRemoto() : existente.getRemoto());
        existente.setDuracionContrato(req.getDuracionContrato());
        existente.setRequisitos(req.getRequisitos());
        existente.setHabilidadesRequeridas(req.getHabilidadesRequeridas());
        existente.setBeneficios(req.getBeneficios());
        existente.setIdiomas(req.getIdiomas());
        existente.setCompanyWebsite(req.getCompanyWebsite());
        existente.setLogoUrl(req.getLogoUrl());
        existente.setEtiquetas(req.getEtiquetas());

        Job updated = jobRepository.save(existente);
        return mapToResponse(updated);
    }

    /**
     * Convertir entidad Job → DTO JobResponse, incluyendo todos los campos.
     */
    private JobResponse mapToResponse(Job job) {
        return JobResponse.builder()
                .id(job.getId())
                .creatorId(job.getCreatorId())
                .titulo(job.getTitulo())
                .descripcion(job.getDescripcion())
                .empresa(job.getEmpresa())
                .ubicacion(job.getUbicacion())
                .tipo(job.getTipo())
                .sueldo(job.getSueldo())
                .fechaPublicacion(job.getFechaPublicacion())
                .estado(job.getEstado())
                .fechaLimitePostulacion(job.getFechaLimitePostulacion())
                .nivelExperiencia(job.getNivelExperiencia())
                .categoria(job.getCategoria())
                .departamento(job.getDepartamento())
                .vacantes(job.getVacantes())
                .remoto(job.getRemoto())
                .duracionContrato(job.getDuracionContrato())
                .requisitos(job.getRequisitos())
                .habilidadesRequeridas(job.getHabilidadesRequeridas())
                .beneficios(job.getBeneficios())
                .idiomas(job.getIdiomas())
                .companyWebsite(job.getCompanyWebsite())
                .logoUrl(job.getLogoUrl())
                .etiquetas(job.getEtiquetas())
                .build();
    }

    /**
     * Buscar N trabajos aleatorios (solo los ACTIVO) y devolver JobResponse.
     */
    public List<JobResponse> findRandom(int limit) {
        return jobRepository.findRandomJobs(limit).stream()
                .filter(job -> "ACTIVO".equalsIgnoreCase(job.getEstado()))
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Listar los últimos (más recientes) trabajos ACTIVOS, hasta un límite.
     */
    public List<JobResponse> findLatest(int limit) {
        return jobRepository.findAll(Sort.by(Sort.Direction.DESC, "fechaPublicacion")).stream()
                .filter(job -> "ACTIVO".equalsIgnoreCase(job.getEstado()))
                .limit(limit)
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    /**
     * Archivar (poner estado = INACTIVO) o eliminar (hard delete)
     * según si ya existen postulaciones para ese trabajo.
     */
    public boolean archivarOEliminarTrabajo(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Trabajo no encontrado con ID: " + jobId));

        long postulaciones = postulacionRepository.countByTrabajoId(jobId);

        if (postulaciones > 0) {
            job.setEstado("INACTIVO");
            jobRepository.save(job);
            return false; // no se eliminó, se archivó
        } else {
            jobRepository.deleteById(jobId);
            return true; // sí se eliminó
        }
    }
}
