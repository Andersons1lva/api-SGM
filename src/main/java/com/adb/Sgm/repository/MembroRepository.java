package com.adb.Sgm.repository;


import com.adb.Sgm.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;

@Repository
public interface MembroRepository extends JpaRepository<Membro, UUID> {
    boolean existsByCpfAndIdNot(String cpf, UUID id);
    boolean existsByRgAndIdNot(String rg, UUID id);

    @Query("SELECT m FROM membros m WHERE MONTH(m.data_nascimento) = :mes")
    List<Membro> findByMesAniversario(int mes);
}
