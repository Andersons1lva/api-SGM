package com.adb.Sgm.service;


import com.adb.Sgm.dtos.EventoDTO;
import com.adb.Sgm.model.Evento;
import com.adb.Sgm.model.User;
import com.adb.Sgm.repository.EventoRepository;
import com.adb.Sgm.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    @Autowired
    UsersRepository usersRepository;


    public EventoDTO criarEvento(EventoDTO eventDTO, String email) {
        User user = (User) usersRepository.findByEmail(email);

        Evento event = new Evento();
        event.setTitulo(eventDTO.getTitulo());
        event.setInicio(LocalDate.parse(eventDTO.getInicio()));
        event.setFim(LocalDate.parse(eventDTO.getFim()));
        event.setDiaTodo(eventDTO.isDia_todo());
        event.setUser(user);

        Evento savedEvent = eventoRepository.save(event);
        return convertToDTO(savedEvent);
    }

    public EventoDTO convertToDTO(Evento evento) {
        EventoDTO dto = new EventoDTO();
        dto.setId(evento.getId());
        dto.setTitulo(evento.getTitulo());
        dto.setInicio(String.valueOf((evento.getInicio())));
        dto.setFim((evento.getFim().toString()));
        dto.setDia_todo(evento.isDiaTodo());
        return dto;
    }

    public List<Evento> buscarEventos(){
        return eventoRepository.findAll();
    }

    public void deletarEvento(UUID id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> buscarEventosDoMesAtual(){
        return eventoRepository.findEventosByMesAtual();
    }
}
