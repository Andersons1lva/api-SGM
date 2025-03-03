package com.adb.Sgm.service;



import com.adb.Sgm.model.Evento;
import com.adb.Sgm.model.User;
import com.adb.Sgm.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class EventoService {

    @Autowired
    EventoRepository eventoRepository;

    public Evento salvarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public List<Evento> listarEventosPorUsuario(User user) {
        return eventoRepository.findByUser(user);
    }

    public void deletarEvento(UUID id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> buscarEventosDoMesAtual(User user){
        return eventoRepository.findEventosByMesAtual(user);
    }
}
