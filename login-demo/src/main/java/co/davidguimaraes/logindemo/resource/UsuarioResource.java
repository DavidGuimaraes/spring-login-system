package co.davidguimaraes.logindemo.resource;

import co.davidguimaraes.logindemo.model.Usuario;
import co.davidguimaraes.logindemo.resource.dto.UsuarioDto;
import co.davidguimaraes.logindemo.resource.mapper.UsuarioMapper;
import co.davidguimaraes.logindemo.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/rest/users")
public class UsuarioResource {

    @Autowired
    private UsuarioService service;

    @Autowired
    private UsuarioMapper mapper;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public Page<UsuarioDto> listar(Pageable pageable) {
        pageable = pageable.previousOrFirst();
        Page<Usuario> usuarios = service.findAll(pageable);
        return usuarios.map(mapper::entityToDto);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public ResponseEntity<UsuarioDto> pesquisarPeloId(@PathVariable Long id) {
        Optional<Usuario> usuario = service.findById(id);
        Optional<UsuarioDto> dto = usuario.map(mapper::entityToDto);
        return usuario.isPresent() ? ResponseEntity.ok(dto.get()) : ResponseEntity.notFound().build();
    }

    @GetMapping(params = "email", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_PESQUISAR_USUARIO') and #oauth2.hasScope('read')")
    public ResponseEntity<UsuarioDto> pesquisarPeloEmail(@RequestParam String email) {
        Optional<Usuario> usuario = service.findByEmail(email);
        Optional<UsuarioDto> dto = usuario.map(mapper::entityToDto);
        return usuario.isPresent() ? ResponseEntity.ok(dto.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_CADASTRAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<UsuarioDto> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
        Usuario usuarioSalvo = service.save(usuario);
//        publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioSalvo.getIdUsuario()).toUri();
        return ResponseEntity.created(uri).body(mapper.entityToDto(usuarioSalvo));
//        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.entityToDto(usuarioSalvo));
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_EDITAR_USUARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<UsuarioDto> editar(@PathVariable Long id, @Valid @RequestBody Usuario usuario){
        Usuario usuarioAtualizado = service.update(id, usuario);
        return ResponseEntity.ok(mapper.entityToDto(usuarioAtualizado));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("isAuthenticated() and hasAuthority('ROLE_REMOVER_USUARIO') and #oauth2.hasScope('write')")
    public void remover(@PathVariable Long id) { service.delete(id); }
}
