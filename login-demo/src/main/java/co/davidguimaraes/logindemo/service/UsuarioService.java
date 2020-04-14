package co.davidguimaraes.logindemo.service;

import co.davidguimaraes.logindemo.model.Usuario;
import co.davidguimaraes.logindemo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository dao;


    public Optional<Usuario> findByEmail(String email){
        return dao.findByEmail(email);
    }
}
