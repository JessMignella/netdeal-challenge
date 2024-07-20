
package br.com.netdeal.infrastructure.persistence;

import br.com.netdeal.application.dto.FuncionarioDto;
import br.com.netdeal.application.service.FuncionarioService;
import br.com.netdeal.domain.entity.Cargo;
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
    @Autowired
    private ForcaSenhaJPARepository forcaSenhaRepository;

    @Autowired
    private CargoJPARepository cargoRepository;
    @Autowired
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

    @Override
    public FuncionarioDto createFuncionario(FuncionarioDto funcionarioDto) {
        Funcionario funcionario = funcionarioMapper.toEntity(funcionarioDto);
        funcionario.setSenha(senhaEncryptor.encrypt(funcionarioDto.getSenha()));

        var forcaSenha = forcaSenhaRepository.save(ForcaSenha.of(funcionarioDto.getSenha()));
        funcionario.setForcaSenha(forcaSenha);

        funcionario.setCargo(
                cargoRepository.findByTituloAndNivel(
                                funcionario.getCargo().getTitulo(), funcionarioDto.getCargo().getNivel())
                        .orElseGet(() -> cargoRepository.save(funcionario.getCargo())));

        if (funcionarioDto.getLideranca() != null && funcionarioDto.getLideranca().getId() != null) {
            Funcionario lideranca = funcionarioRepository.findById(funcionarioDto.getLideranca().getId())
                    .orElseThrow(() -> new RuntimeException("Lideran a n o encontrada"));
            funcionario.setLideranca(lideranca);
        }
        return funcionarioMapper.toDto(funcionarioRepository.save(funcionario));
    }

    @Override
    public FuncionarioDto updateFuncionario(Long id, FuncionarioDto funcionarioDto) {
        Funcionario funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        funcionarioMapper.updateFuncionarioFromDto(funcionarioDto, funcionario);
        if (funcionarioDto.getLideranca() != null && funcionarioDto.getLideranca().getId() != null) {
            Funcionario lideranca = funcionarioRepository.findById(funcionarioDto.getLideranca().getId())
                    .orElseThrow(() -> new RuntimeException("Liderança não encontrada"));
            funcionario.setLideranca(lideranca);
        }
        this.updateSenhaAndForcaSenha(funcionario, funcionarioDto.getSenha());
        this.updateCargo(funcionario, funcionarioDto.getCargo().getTitulo(), funcionarioDto.getCargo().getNivel());

        return funcionarioMapper.toDto(funcionarioRepository.save(funcionario));
    }

    private void updateSenhaAndForcaSenha(Funcionario funcionario, String senha) {
        funcionario.setForcaSenha(forcaSenhaRepository.save(ForcaSenha.of(senha)));
        funcionario.setSenha(senhaEncryptor.encrypt(senha));
    }

    private void updateCargo(Funcionario funcionario, String titulo, int nivel) {
        Cargo cargo = cargoRepository.findByTituloAndNivel(titulo, nivel)
                .orElseThrow(() -> new RuntimeException("Cargo não encontrado"));
        funcionario.setCargo(cargo);
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
