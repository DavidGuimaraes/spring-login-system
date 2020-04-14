package co.davidguimaraes.logindemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue
    @Column(name = "id_usuario")
    @NotNull
    private Long idUsuario;

    @Column(name = "nome")
    @NotNull
    private String nome;

    @Column(name = "email")
    @NotNull
    private String email;

    @Column(name = "senha")
    @NotNull
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_permissao", joinColumns = @JoinColumn(name = "id_usuario")
            , inverseJoinColumns = @JoinColumn(name = "id_permissao"))
    private List<Permissao> permissoes;
}
