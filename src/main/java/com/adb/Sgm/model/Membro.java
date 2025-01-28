package com.adb.Sgm.model;

import com.adb.Sgm.requetsDTO.MembroRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Entity(name = "membros")
@Table(name = "membros")
@Builder
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
    @Column(name = "sobre_nome")
    private String sobrenome;
    private Integer idade;
    private String sexo;
    private String rg;
    private String cpf;
    private String nome_pai;
    private String nome_mae;
    private String numero_celular;
    private String email;
    @Column(name = "estado_civil")
    private String estado_civil;
    private String funcao_ministerial;
    private LocalDate data_batismo;
    private LocalDate data_casamento;
    private LocalDate data_nascimento;
    private String tempo_membro;
    private String nascionalidade;
    private String naturalidade;
    private String telefone_fixo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName ="id")
    private Endereco endereco;

    //Criação do construtor para o DTO em uma entidade
    public Membro(MembroRequestDTO data){
        this.id = data.id();
        this.nome = data.nome();
        this.sobrenome = data.sobrenome();
        this.idade = data.idade();
        this.rg = data.rg();
        this.sexo = data.sexo();
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
        this.tempo_membro = data.tempo_membro();
        this.nascionalidade = data.nascionalidade();
        this.naturalidade = data.naturalidade();
        this.telefone_fixo = data.telefone_fixo();
        this.endereco = data.endereco();
    }
}
