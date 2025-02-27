package com.adb.Sgm.service;

import com.adb.Sgm.configuration.BusinessException;
import com.adb.Sgm.configuration.ResourceNotFoundException;
import com.adb.Sgm.model.Endereco;
import com.adb.Sgm.model.Membro;
import com.adb.Sgm.model.User;
import com.adb.Sgm.repository.MembroRepository;
import com.adb.Sgm.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MembroService {

    @Autowired
    private MembroRepository membroRepository;

    @Autowired
    private UserRepository userRepository;

    public Membro criarMembro(Membro membro) {
        return membroRepository.save(membro);
    }

    // Metodo para excluir um membro pelo ID
    public boolean deleteMembro(UUID id) {
        Optional<Membro> membro = membroRepository.findById(id);
        if (membro.isPresent()) {
            membroRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Membro> getAniversarianteDoMes(){
        int mesAtual = LocalDate.now().getMonthValue();
        return membroRepository.findByMesAniversario(mesAtual);
    }

    public Membro buscarPorId(UUID id) {
        return membroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membro não encontrado com o ID: " + id));
    }

//    public List<Membro> buscarMembros(){
//        return membroRepository.findAll();
//    }

    public List<Membro> listarMembrosPorUsuario(User user) {
        return membroRepository.findByUser(user);
    }

    @Transactional
    public Membro atualizarMembro(UUID id, Membro membroDTO) {
        // 1. Busca o membro existente
        Membro membroExistente = membroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Membro não encontrado com o id: " + id));

        // 2. Verifica se o CPF atualizado já existe para outro membro
        if (!membroExistente.getCpf().equals(membroDTO.getCpf())) {
            boolean cpfExiste = membroRepository.existsByCpfAndIdNot(membroDTO.getCpf(), id);
            if (cpfExiste) {
                throw new BusinessException("CPF já cadastrado para outro membro");
            }
        }

        // 3. Verifica se o RG atualizado já existe para outro membro
        if (!membroExistente.getRg().equals(membroDTO.getRg())) {
            boolean rgExiste = membroRepository.existsByRgAndIdNot(membroDTO.getRg(), id);
            if (rgExiste) {
                throw new BusinessException("RG já cadastrado para outro membro");
            }
        }

        // 4. Atualiza os dados do membro
        membroExistente.setNome(membroDTO.getNome());
        membroExistente.setSobrenome(membroDTO.getSobrenome());
        membroExistente.setEmail(membroDTO.getEmail());
        membroExistente.setIdade(membroDTO.getIdade());
        membroExistente.setNumero_celular(membroDTO.getNumero_celular());
        membroExistente.setTelefone_fixo(membroDTO.getTelefone_fixo());
        membroExistente.setRg(membroDTO.getRg());
        membroExistente.setCpf(membroDTO.getCpf());
        membroExistente.setNome_pai(membroDTO.getNome_pai());
        membroExistente.setData_nascimento(membroDTO.getData_nascimento());
        membroExistente.setData_batismo(membroDTO.getData_batismo());
        membroExistente.setNome_mae(membroDTO.getNome_mae());
        membroExistente.setNaturalidade(membroDTO.getNaturalidade());
        membroExistente.setNascionalidade(membroDTO.getNascionalidade());
        membroExistente.setFuncao_ministerial(membroDTO.getFuncao_ministerial());
        membroExistente.setSexo(membroDTO.getSexo());
        membroExistente.setEstado_civil(membroDTO.getEstado_civil());
        membroExistente.setData_casamento(membroDTO.getData_casamento());
        membroExistente.setTempo_membro(membroDTO.getTempo_membro());

        // 5. Atualiza o endereço
        if (membroDTO.getEndereco() != null) {
            Endereco enderecoExistente = membroExistente.getEndereco();
            if (enderecoExistente == null) {
                enderecoExistente = new Endereco();
                membroExistente.setEndereco(enderecoExistente);
            }

            enderecoExistente.setRua(membroDTO.getEndereco().getRua());
            enderecoExistente.setNumero(membroDTO.getEndereco().getNumero());
            enderecoExistente.setComplemento(membroDTO.getEndereco().getComplemento());
            enderecoExistente.setCidade(membroDTO.getEndereco().getCidade());
            enderecoExistente.setCep(membroDTO.getEndereco().getCep());
        }

        // 6. Salva as alterações
        return  membroRepository.save(membroExistente);
    }


}


