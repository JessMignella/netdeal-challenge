
package br.com.netdeal.application.service;

import br.com.netdeal.application.dto.FuncionarioDto;

import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FuncionarioService {


    List<FuncionarioDto> getAllFuncionarios();

    FuncionarioDto getFuncionarioById(Long id);

    FuncionarioDto createFuncionario(FuncionarioDto FuncionarioDto);

    FuncionarioDto updateFuncionario(Long id, FuncionarioDto funcionarioDto);

    void deleteFuncionario(Long id);

    List<FuncionarioDto> getFuncionariosByLiderancaId(@Param("LiderancaId") Long LiderancaId);
    List<FuncionarioDto> getFuncionariosByCargoNivel(Long nivel);


}

