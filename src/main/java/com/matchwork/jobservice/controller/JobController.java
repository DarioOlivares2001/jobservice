// src/main/java/com/matchwork/jobservice/controller/JobController.java
package com.matchwork.jobservice.controller;

import com.matchwork.jobservice.dto.JobRequest;
import com.matchwork.jobservice.dto.JobResponse;
import com.matchwork.jobservice.service.JobService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobController {

    private final JobService jobService;
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    /** Listar todos (solo los “ACTIVO”) **/
    @GetMapping
    public ResponseEntity<List<JobResponse>> listar() {
        return ResponseEntity.ok(jobService.findAll());
    }

    /** Crear nueva oferta vía query‐param creatorId **/
    @PostMapping            // equivale a POST "/api/jobs"
    public ResponseEntity<JobResponse> crear(
            @RequestParam("creatorId") Long creatorId,
            @RequestBody JobRequest payload
    ) {
        JobResponse created = jobService.crear(creatorId, payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

     /** Listar trabajos por creatorId **/
    @GetMapping("/creator/{creatorId}")
    public ResponseEntity<List<JobResponse>> listarPorCreator(@PathVariable Long creatorId) {
        List<JobResponse> lista = jobService.findByCreator(creatorId);
        return ResponseEntity.ok(lista);
    }


    /** Obtener un trabajo por ID **/
    @GetMapping("/{id}")
    public ResponseEntity<JobResponse> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobService.findById(id));
    }

    /** Eliminar (hard delete) **/
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJob(@PathVariable Long id) {
        jobService.delete(id);
        return ResponseEntity.noContent().build();
    }

    /** Actualizar oferta existente **/
    @PutMapping("/{id}")
    public ResponseEntity<JobResponse> updateJob(
            @PathVariable Long id,
            @RequestParam("creatorId") Long creatorId,
            @RequestBody JobRequest jobRequest
    ) {
        JobResponse updated = jobService.update(id, creatorId, jobRequest);
        return ResponseEntity.ok(updated);
    }

    /** Trabajos aleatorios **/
    @GetMapping("/random")
    public ResponseEntity<List<JobResponse>> trabajosAleatorios(
            @RequestParam(defaultValue = "5") int limit
    ) {
        return ResponseEntity.ok(jobService.findRandom(limit));
    }

    /** Últimos trabajos **/
    @GetMapping("/recientes")
    public ResponseEntity<List<JobResponse>> listarRecientes() {
        return ResponseEntity.ok(jobService.findLatest(10));
    }

    /** Archivar o eliminar según reglas de negocio **/
    @PutMapping("/{id}/archivar-o-eliminar")
    public ResponseEntity<String> archivarOEliminar(@PathVariable Long id) {
        boolean eliminado = jobService.archivarOEliminarTrabajo(id);
        return eliminado
          ? ResponseEntity.ok("Trabajo eliminado porque no tenía postulaciones.")
          : ResponseEntity.ok("Trabajo archivado porque ya tenía postulaciones.");
    }
}
