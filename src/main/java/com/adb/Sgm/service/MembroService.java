package com.adb.Sgm.service;

import com.adb.Sgm.model.Membro;
import com.adb.Sgm.repository.MembroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    // Método para excluir um membro pelo ID
    public boolean deleteMembro(UUID id) {
        Optional<Membro> membro = membroRepository.findById(id);
        if (membro.isPresent()) {
            membroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
