package com.adb.Sgm.controller;

import com.adb.Sgm.dtos.MembroResponseDTO;
import com.adb.Sgm.model.Membro;
import com.adb.Sgm.repository.MembroRepository;
import com.adb.Sgm.requetsDTO.MembroRequestDTO;
import com.adb.Sgm.service.MembroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("auth")
public class MembroController {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private MembroService membroService;

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @PostMapping("/membros")
    public void saveMembro(@RequestBody MembroRequestDTO data){
        Membro membroData = new Membro(data);
        membroRepository.save(membroData);
    }

    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @GetMapping("/membros")
    public List<Membro> getMembros() {
        return membroRepository.findAll(); // Exemplo de chamada ao repositório
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> excluirMembro(@PathVariable UUID uuid){
        boolean isRemovd = membroService.deleteMembro(uuid);
        if (!isRemovd){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
