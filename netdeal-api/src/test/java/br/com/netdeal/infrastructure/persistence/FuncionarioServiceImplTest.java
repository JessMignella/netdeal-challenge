package br.com.netdeal.infrastructure.persistence;

import br.com.netdeal.application.dto.FuncionarioDto;
import br.com.netdeal.domain.entity.Funcionario;
import br.com.netdeal.infrastructure.web.mapper.FuncionarioMapper;
import br.com.netdeal.utils.SenhaEncryptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FuncionarioServiceImplTest {

    @Mock
    private FuncionarioJPARepository funcionarioRepository;

    @Mock
    private SenhaEncryptor senhaEncryptor;

    @Mock
    private FuncionarioMapper funcionarioMapper;

    @InjectMocks
    private FuncionarioServiceImpl funcionarioService;

    private Funcionario funcionario;
    private FuncionarioDto funcionarioDto;

    @BeforeEach
    void setUp() {
        funcionario = new Funcionario();
        funcionario.setId(1L);
        funcionario.setNome("Ana");
        funcionario.setSenha("senha123");

        funcionarioDto = new FuncionarioDto();
        funcionarioDto.setId(1L);
        funcionarioDto.setNome("Ana");
        funcionarioDto.setSenha("senha123");
    }

    @Test
    void getAllFuncionarios() {
        when(funcionarioRepository.findAll()).thenReturn(List.of(funcionario));
        when(funcionarioMapper.toDto(any(Funcionario.class))).thenReturn(funcionarioDto);

        List<FuncionarioDto> result = funcionarioService.getAllFuncionarios();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(funcionarioRepository, times(1)).findAll();
    }

    @Test
    void getFuncionarioById() {
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(funcionario));
        when(funcionarioMapper.toDto(any(Funcionario.class))).thenReturn(funcionarioDto);

        FuncionarioDto result = funcionarioService.getFuncionarioById(1L);

        assertNotNull(result);
        assertEquals("Ana", result.getNome());
        verify(funcionarioRepository, times(1)).findById(anyLong());
    }

    @Test
    void createFuncionario() {
        when(senhaEncryptor.encrypt(anyString())).thenReturn("encryptedSenha123");
        when(funcionarioMapper.toEntity(any(FuncionarioDto.class))).thenReturn(funcionario);
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);
        when(funcionarioMapper.toDto(any(Funcionario.class))).thenReturn(funcionarioDto);

        FuncionarioDto result = funcionarioService.createFuncionario(funcionarioDto);

        assertNotNull(result);
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }

    @Test
    void updateFuncionario() {
        when(funcionarioRepository.findById(anyLong())).thenReturn(Optional.of(funcionario));
        when(senhaEncryptor.encrypt(anyString())).thenReturn("encryptedSenha123");
        when(funcionarioRepository.save(any(Funcionario.class))).thenReturn(funcionario);
        when(funcionarioMapper.toDto(any(Funcionario.class))).thenReturn(funcionarioDto);

        FuncionarioDto result = funcionarioService.updateFuncionario(1L, funcionarioDto);

        assertNotNull(result);
        verify(funcionarioRepository, times(1)).findById(anyLong());
        verify(funcionarioRepository, times(1)).save(any(Funcionario.class));
    }

    @Test
    void deleteFuncionario() {
        doNothing().when(funcionarioRepository).deleteById(anyLong());

        funcionarioService.deleteFuncionario(1L);

        verify(funcionarioRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void getFuncionariosByLiderancaId() {
        when(funcionarioRepository.findAllByLiderancaId(anyLong())).thenReturn(List.of(funcionario));
        when(funcionarioMapper.toDto(any(Funcionario.class))).thenReturn(funcionarioDto);

        List<FuncionarioDto> result = funcionarioService.getFuncionariosByLiderancaId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(funcionarioRepository, times(1)).findAllByLiderancaId(anyLong());
    }
}
