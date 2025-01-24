package com.adb.Sgm.controller;
import com.adb.Sgm.dtos.EventoDTO;
import com.adb.Sgm.model.Evento;
import com.adb.Sgm.model.Membro;
import com.adb.Sgm.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth/eventos")
public class EventoController {
    @Autowired
    EventoService eventoService;

    @PostMapping
    public ResponseEntity<EventoDTO> createEvent(@RequestBody EventoDTO eventoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        EventoDTO savedEvento = eventoService.criarEvento(eventoDTO,userDetails.getUsername());
        return ResponseEntity.ok(savedEvento);
    }

    @GetMapping
    public List<Evento> getEvents() {
        return eventoService.buscarEventos();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable UUID id) {
        eventoService.deletarEvento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/mes_atual")
    public List<Evento> getEventosDoMesAtual(){
        return eventoService.buscarEventosDoMesAtual();
    }
}
