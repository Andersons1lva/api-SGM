package com.adb.Sgm.controller;
import com.adb.Sgm.dtos.EventoDTO;
import com.adb.Sgm.model.Evento;
import com.adb.Sgm.model.Membro;
import com.adb.Sgm.model.User;
import com.adb.Sgm.service.EventoService;
import com.adb.Sgm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth/eventos")
public class EventoController {
    @Autowired
    EventoService eventoService;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<EventoDTO> createEvent(@RequestBody EventoDTO eventoDTO, @AuthenticationPrincipal UserDetails userDetails) {
        String email = userDetails.getUsername();
        EventoDTO savedEvento = eventoService.criarEvento(eventoDTO, email);
        return ResponseEntity.ok(savedEvento);
    }
//
//    @GetMapping
//    public List<Evento> getEvents() {
//        return eventoService.buscarEventos();
//    }

    @GetMapping
    public List<Evento> getAllEventos() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = authentication.getName();

        User usuarioLogado = userService.buscarPorEmail(emailUsuario);
        return eventoService.listarEventosPorUsuario(usuarioLogado);
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
