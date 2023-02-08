package br.com.murilo.service;

import br.com.murilo.model.Usuario;
import br.com.murilo.repository.UsuarioRepository;
import io.quarkus.elytron.security.common.BcryptUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public List<Usuario> listar(){

        return usuarioRepository.listAll();

    }

    public Usuario adicionar(Usuario usuario){

        usuario.setPassword(BcryptUtil.bcryptHash(usuario.getPassword()));
        usuario.setRole(validaUsername(usuario.getUsername()));
        usuario.persist();

        return usuario;
    }

    public String validaUsername(String username){
        if (username.equals("murilo1212")){
            return "admin";
        }return "user";
    }


}
