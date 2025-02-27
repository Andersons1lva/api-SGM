package com.adb.Sgm.controller;


import com.adb.Sgm.model.Membro;
import com.adb.Sgm.model.User;
import com.adb.Sgm.requetsDTO.MembroRequestDTO;
import com.adb.Sgm.service.MembroService;
import com.adb.Sgm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Slf4j
@RestController
@RequestMapping("/auth")
public class MembroController {

    @Autowired
    private MembroService membroService;

    @Autowired
    private UserService userService;

    @PostMapping("/membros")
    public Membro criarMembro(@RequestBody Membro membro) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = authentication.getName();

        User usuarioLogado = userService.buscarPorEmail(emailUsuario);
        membro.setUser(usuarioLogado);

        return membroService.criarMembro(membro);
    }

//    @PostMapping("/membros")
//    public ResponseEntity<Membro> saveMembro(@RequestBody MembroRequestDTO data){
//        Membro membroData = new Membro(data);
//        membroService.criarMembro(membroData);
//        return ResponseEntity.ok().build();
//    }


//    @GetMapping("/membros")
//    public List<Membro> getMembros() {
//        return membroService.buscarMembros();
//    }
    @GetMapping("/membros")
    public List<Membro> listarMembros() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = authentication.getName(); // Obtém o email do usuário logado

        User usuarioLogado = userService.buscarPorEmail(emailUsuario);
        return membroService.listarMembrosPorUsuario(usuarioLogado);
    }



    @DeleteMapping("/{uuid}")
    public ResponseEntity<Void> excluirMembro(@PathVariable UUID uuid){
        boolean isRemovd = membroService.deleteMembro(uuid);
        if (!isRemovd){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/aniversariantes")
    public List<Membro> getAniversariantes(){
        return membroService.getAniversarianteDoMes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Membro> buscarPorId(@PathVariable UUID id){
        Membro membro = membroService.buscarPorId(id);
        return ResponseEntity.ok(membro);
    }
    @PutMapping("/membros/{uuid}")
    public ResponseEntity<Membro> atualizarMembro(@PathVariable UUID uuid,@RequestBody Membro membro) {
        Membro atualizar = membroService.atualizarMembro(uuid, membro);
        return ResponseEntity.ok(atualizar);
    }


}
