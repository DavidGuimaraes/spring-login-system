package co.davidguimaraes.logindemo.resource.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PermissaoDto {

    private Long id;
    private String descricao;
}
