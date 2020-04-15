package co.davidguimaraes.logindemo.service;

import co.davidguimaraes.logindemo.model.Usuario;
import co.davidguimaraes.logindemo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository dao;


    public Optional<Usuario> findByEmail(String email){
        return dao.findByEmail(email);
    }

    public Page<Usuario> findAll(Pageable pageable){
        return this.dao.findAll(pageable);
    }

    public Optional<Usuario> findById(Long id){ return this.dao.findById(id); }

    public Usuario save(Usuario usuario){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        this.dao.save(usuario);
        return usuario;
    }

    public void delete(Long id) throws Exception{
        Optional<Usuario> usuarioSalvo = this.dao.findById(id);
        if(usuarioSalvo.isPresent()){
            this.dao.delete(usuarioSalvo.get());
        }else{
            throw new Exception("Não foi encontrada um usuário com o id informado");
        }
    }
}
