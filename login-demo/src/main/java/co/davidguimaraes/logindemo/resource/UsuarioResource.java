package co.davidguimaraes.logindemo.resource;

import co.davidguimaraes.logindemo.model.Usuario;
import co.davidguimaraes.logindemo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public Page<Usuario> listar(Pageable pageable) {
        return service.findAll(pageable);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public ResponseEntity<Usuario> pesquisarPeloId(@PathVariable Long id) {
        Optional<Usuario> usuario = service.findById(id);
        return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping(params = "email", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public ResponseEntity<Usuario> pesquisarPeloEmail(@RequestParam String email) {
        Optional<Usuario> usuario = service.findByEmail(email);
        return usuario.isPresent() ? ResponseEntity.ok(usuario.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
        Usuario usuarioSalvo = service.save(usuario);
//        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_REMOVER_USUARIO') and #oauth2.hasScope('write')")
    public void remover(@PathVariable Long id) { service.delete(id); }
}
