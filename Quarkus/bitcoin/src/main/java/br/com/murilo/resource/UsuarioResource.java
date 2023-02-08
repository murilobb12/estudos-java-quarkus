package br.com.murilo.resource;


import br.com.murilo.model.Usuario;
import br.com.murilo.service.UsuarioService;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/usuarios")
public class UsuarioResource {


    @Inject
    UsuarioService usuarioService;


    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    @POST
    public Usuario inserirUsuario(Usuario usuario){

        usuarioService.adicionar(usuario);

        return usuario;

    }

    @PermitAll
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @GET
    public List<Usuario> listar(){

        return usuarioService.listar();

    };



}
