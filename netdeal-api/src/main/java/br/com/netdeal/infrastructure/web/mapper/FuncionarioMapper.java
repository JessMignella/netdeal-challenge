package br.com.netdeal.infrastructure.web.mapper;

        import br.com.netdeal.application.dto.FuncionarioDto;
        import br.com.netdeal.domain.entity.Funcionario;
        import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface FuncionarioMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FuncionarioDto toDto(Funcionario funcionario);
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Funcionario toEntity(FuncionarioDto funcionarioDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lideranca", ignore = true)
    void updateFuncionarioFromDto(FuncionarioDto funcionarioDto, @MappingTarget Funcionario funcionario);
}


