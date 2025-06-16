package com.matchwork.jobservice.controller;

import com.matchwork.jobservice.dto.MiniPerfilDTO;
import com.matchwork.jobservice.dto.PostulacionUsuarioResponse;
import com.matchwork.jobservice.dto.PostulanteConPerfilDTO;
import com.matchwork.jobservice.model.Postulacion;
import com.matchwork.jobservice.service.PostulacionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postulaciones")
@CrossOrigin(origins = "*")
public class PostulacionController {

    private final PostulacionService postulacionService;

    public PostulacionController(PostulacionService postulacionService) {
        this.postulacionService = postulacionService;
    }

    @PostMapping
    public ResponseEntity<Postulacion> postular(@RequestParam Long usuarioId, @RequestParam Long trabajoId, @RequestParam(required = false) String cvUrl) 
    {
        Postulacion p = postulacionService.postular(usuarioId, trabajoId);
        // Asigna el cvUrl si viene
        if (cvUrl != null) {
            p.setCvUrl(cvUrl);
            postulacionService.guardar(p);
        }
        return ResponseEntity.ok(p);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Postulacion>> obtenerPostulaciones(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(postulacionService.obtenerPostulacionesPorUsuario(usuarioId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        postulacionService.eliminarPostulacion(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/postulaciones/trabajo/{trabajoId}")
    public ResponseEntity<List<Postulacion>> obtenerPostulacionesDeTrabajo(@PathVariable Long trabajoId) {
        return ResponseEntity.ok(postulacionService.obtenerPostulacionesPorTrabajo(trabajoId));
    }

    @GetMapping("/trabajo/{trabajoId}")
    public ResponseEntity<List<PostulacionUsuarioResponse>> obtenerPostulantesPorTrabajo(@PathVariable Long trabajoId) {
        return ResponseEntity.ok(postulacionService.obtenerPostulacionesConUsuarioPorTrabajo(trabajoId));
    }

    @GetMapping("/existe")
    public ResponseEntity<Boolean> yaPostulado(
            @RequestParam Long usuarioId,
            @RequestParam Long trabajoId
    ) {
        boolean exists = postulacionService.yaPostulado(usuarioId, trabajoId);
        return ResponseEntity.ok(exists);
    }


    
    @GetMapping("/trabajo/{trabajoId}/postulantes-con-perfil")
    public ResponseEntity<List<PostulanteConPerfilDTO>> listarMiniPerfilesPorTrabajo(@PathVariable Long trabajoId) {
        List<PostulanteConPerfilDTO> lista = postulacionService.listarMiniPerfilesPorTrabajo(trabajoId);
        return ResponseEntity.ok(lista);
    }


}
