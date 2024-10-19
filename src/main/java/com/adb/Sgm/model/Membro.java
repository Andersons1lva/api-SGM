package com.adb.Sgm.model;

import com.adb.Sgm.requetsDTO.MembroRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "membros")
@Table(name = "membros")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of ="id")
public class Membro {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;
    private String sobreNome;
    private String rg;
    private String cpf;
    private String nome_pai;
    private String nome_mae;
    private String numero_celular;
    private String email;
    private String estado_civil;
    private String funcao_ministerial;
    private LocalDate data_batismo;
    private LocalDate data_casamento;
    private LocalDate data_nascimento;
    private LocalDate data_tempo_membro;
    private String nascionalidade;
    private String naturalidade;
    private String telefone_fixo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName ="id")
    private Endereco endereco;

    //Criação do construtor para o DTO em uma entidade
    public Membro(MembroRequestDTO data){
        this.nome = data.nome();
        this.sobreNome = data.sobrenome();
        this.rg = data.rg();
        this.cpf = data.cpf();
        this.nome_pai = data.nome_pai();
        this.nome_mae = data.nome_mae();
        this.numero_celular = data.numero_celular();
        this.email = data.email();
        this.estado_civil = data.estado_civil();
        this.funcao_ministerial = data.funcao_ministerial();
        this.data_batismo = data.data_batismo();
        this.data_casamento = data.data_casamento();
        this.data_nascimento = data.data_nascimento();
        this.data_tempo_membro = data.data_tempo_membro();
        this.nascionalidade = data.nascionalidade();
        this.naturalidade = data.naturalidade();
        this.telefone_fixo = data.telefone_fixo();
        this.endereco = data.endereco();
    }
}
