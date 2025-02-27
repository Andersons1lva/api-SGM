package com.adb.Sgm.service;


import com.adb.Sgm.dtos.EventoDTO;
import com.adb.Sgm.model.Evento;
import com.adb.Sgm.model.User;
import com.adb.Sgm.repository.EventoRepository;
import com.adb.Sgm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UserRepository userRepository;


    public EventoDTO criarEvento(EventoDTO eventDTO, String email) {
        User user = (User) userRepository.findByEmail(email);

        Evento event = new Evento();
        event.setTitulo(eventDTO.getTitulo());
        event.setInicio(converterParaLocalDateTime(eventDTO.getInicio()));
        event.setFim(converterParaLocalDateTime(eventDTO.getFim()));
        event.setDiaTodo(eventDTO.isDia_todo());
        event.setUser(user);

        Evento savedEvent = eventoRepository.save(event);
        return convertToDTO(savedEvent);
    }

    public EventoDTO convertToDTO(Evento evento) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        EventoDTO dto = new EventoDTO();
        dto.setId(evento.getId());
        dto.setTitulo(evento.getTitulo());
        dto.setInicio(evento.getInicio() != null ? evento.getInicio().format(formatter) : null);
        dto.setFim(evento.getFim() != null ? evento.getFim().format(formatter) : null);
        dto.setDia_todo(evento.isDiaTodo());
        dto.setDia(evento.getDia());
        return dto;
    }

    public List<Evento> listarEventosPorUsuario(User user) {
        return eventoRepository.findByUser(user);
    }

    public void deletarEvento(UUID id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> buscarEventosDoMesAtual(){
        return eventoRepository.findEventosByMesAtual();
    }

    private LocalDateTime converterParaLocalDateTime(String dataRecebida) {
        if (dataRecebida == null || dataRecebida.isEmpty()) {
            return null;
        }
        return ZonedDateTime.parse(dataRecebida).toLocalDateTime();
    }
}
