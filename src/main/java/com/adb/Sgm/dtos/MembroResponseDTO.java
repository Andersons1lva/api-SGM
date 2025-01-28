package com.adb.Sgm.dtos;

import com.adb.Sgm.model.Endereco;
import com.adb.Sgm.model.Membro;

import java.time.LocalDate;
import java.util.UUID;

public record MembroResponseDTO(UUID id, String nome, String rg, String cpf, String nome_pai, String nome_mae,
                                String numero_celular, String email, String estado_civil, String funcao_ministerial,
                                LocalDate data_batismo, LocalDate data_casamento,
                                LocalDate data_nascimento, String tempo_membro,
                                String nascionalidade, String naturalidade, String telefone_fixo, Endereco endereco) {

    public MembroResponseDTO(Membro membro) {
        this(membro.getId(), membro.getNome(), membro.getRg(),
                membro.getCpf(), membro.getEmail(),
                membro.getNome_pai(), membro.getNome_mae(),
                membro.getEstado_civil(), membro.getFuncao_ministerial(),
                membro.getNumero_celular(), membro.getData_batismo(),
                membro.getData_casamento(), membro.getData_nascimento(),
                membro.getTempo_membro(), membro.getNascionalidade(),
                membro.getNaturalidade(), membro.getTelefone_fixo(), membro.getEndereco());

    }
}
