package co.davidguimaraes.logindemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "permissao")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permissao {

    @Id
    @GeneratedValue
    @Column(name = "id_permissao")
    @NotNull
    private Long idPermissao;

    @Column(name = "descricao")
    @NotNull
    private String descricao;
}
