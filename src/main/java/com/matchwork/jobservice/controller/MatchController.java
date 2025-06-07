// MatchController.java
package com.matchwork.jobservice.controller;

import com.matchwork.jobservice.dto.TrabajoSugeridoDTO;
import com.matchwork.jobservice.service.MatchService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/match")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @GetMapping("/trabajos")
    public ResponseEntity<List<TrabajoSugeridoDTO>> recomendarTrabajos(
            @RequestParam Long usuarioId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(matchService.recomendarTrabajos(usuarioId, page, size));
    }
}
