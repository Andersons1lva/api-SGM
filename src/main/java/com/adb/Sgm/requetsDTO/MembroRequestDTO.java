package com.adb.Sgm.requetsDTO;

import com.adb.Sgm.model.Endereco;

import java.time.LocalDate;
import java.util.UUID;

public record MembroRequestDTO(UUID id,String nome, String sobrenome, String sexo, String email, Integer idade, String numero_celular, String telefone_fixo,
                               Endereco endereco, String rg, String cpf, String nome_pai,
                               LocalDate data_nascimento, LocalDate data_batismo, String nome_mae,
                               String naturalidade, String nascionalidade, String funcao_ministerial, String estado_civil, String tempo_membro
                              , LocalDate data_casamento) {
}
