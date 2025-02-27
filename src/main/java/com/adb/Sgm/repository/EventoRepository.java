package com.adb.Sgm.repository;

import com.adb.Sgm.model.Evento;
import com.adb.Sgm.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface EventoRepository extends JpaRepository<Evento, UUID> {
    List<Evento> findByUser(User user);

    @Query("SELECT e FROM eventos e WHERE MONTH(e.inicio) = MONTH(CURRENT_DATE)")
    List<Evento> findEventosByMesAtual();


}
