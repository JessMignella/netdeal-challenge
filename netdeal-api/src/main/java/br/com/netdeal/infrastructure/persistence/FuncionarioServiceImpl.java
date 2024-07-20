
package br.com.netdeal.infrastructure.persistence;

import br.com.netdeal.application.dto.FuncionarioDto;
import br.com.netdeal.application.service.FuncionarioService;
import br.com.netdeal.domain.entity.ForcaSenha;
import br.com.netdeal.domain.entity.Funcionario;
import br.com.netdeal.infrastructure.web.mapper.FuncionarioMapper;
import br.com.netdeal.utils.SenhaEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {
    @Autowired
    private FuncionarioJPARepository funcionarioRepository;
    private SenhaEncryptor senhaEncryptor;

    @Autowired
    private FuncionarioMapper funcionarioMapper;

    public List<FuncionarioDto> getAllFuncionarios() {
        return funcionarioRepository.findAll().stream()
                .map(funcionarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public FuncionarioDto getFuncionarioById(Long id) {
        return funcionarioRepository.findById(id)
                .map(funcionarioMapper::toDto)
                .orElse(null);
    }

    public FuncionarioDto createFuncionario(FuncionarioDto funcionarioDto) {
        funcionarioDto.setSenha(senhaEncryptor.encrypt(funcionarioDto.getSenha()));
        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDto);
        funcionario.setForcaSenha(ForcaSenha.of(funcionarioDto.getSenha()));
        return funcionarioMapper.toDto(
                funcionarioRepository.save(funcionarioMapper.toEntity(funcionarioDto))
        );
    }

    public FuncionarioDto updateFuncionario(Long id, FuncionarioDto funcionarioDto) {
        funcionarioDto.setSenha(senhaEncryptor.encrypt(funcionarioDto.getSenha()));

        return funcionarioRepository.findById(id)
                .map(funcionario -> {
                    funcionarioMapper.updateFuncionarioFromDto(funcionarioDto, funcionario);
                    funcionario.setForcaSenha(ForcaSenha.of(funcionarioDto.getSenha()));
                    return funcionarioMapper.toDto(funcionarioRepository.save(funcionario));
                })
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));
    }

    public void deleteFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }

    public List<FuncionarioDto> getFuncionariosByLiderancaId(Long liderancaId) {
        return Optional.ofNullable(funcionarioRepository.findAllByLiderancaId(liderancaId))
                .orElse(List.of())
                .stream()
                .map(funcionarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FuncionarioDto> getFuncionariosByCargoNivel(Long nivel) {
        return Optional.ofNullable(funcionarioRepository.findAllByCargoNivel(nivel))
                .orElse(List.of())
                .stream()
                .map(funcionarioMapper::toDto)
                .collect(Collectors.toList());
    }
}
