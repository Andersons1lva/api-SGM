package com.adb.Sgm.requetsDTO;

import com.adb.Sgm.model.Endereco;

import java.time.LocalDate;

public record MembroRequestDTO(String nome,String sobrenome,String email,String numero_celular,String telefone_fixo,
                               Endereco endereco, String rg, String cpf, String nome_pai,
                               LocalDate data_nascimento,LocalDate data_batismo,String nome_mae,
                               String naturalidade,String nascionalidade,String funcao_ministerial, String estado_civil
                              , LocalDate data_casamento, LocalDate data_tempo_membro) {
}
