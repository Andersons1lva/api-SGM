package com.adb.Sgm.repository;

import com.adb.Sgm.model.Membro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MembroRepository extends JpaRepository<Membro, UUID> {
}
