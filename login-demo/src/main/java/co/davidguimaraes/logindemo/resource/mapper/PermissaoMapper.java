package co.davidguimaraes.logindemo.resource.mapper;

import co.davidguimaraes.logindemo.model.Permissao;
import co.davidguimaraes.logindemo.resource.dto.PermissaoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PermissaoMapper {

    @Mappings({
            @Mapping(target = "id", source = "idPermissao")
    })
    PermissaoDto entityToDto(Permissao permissao);

    List<PermissaoDto> entityToDto(List<Permissao> permissao);

    @Mappings({
            @Mapping(target = "idPermissao", source = "id")
    })
    Permissao dtoToEntity(PermissaoDto permissaoDto);

    List<Permissao> dtoToEntity(List<PermissaoDto> permissaoDto);
}
