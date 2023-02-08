package br.com.murilo;

import org.jetbrains.annotations.NotNull;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/categorias")
public class CategoriaResource {

    @Inject
    CategoriaRepository categoriaRepository;

    @Inject
    Categoria categoria;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Categoria> categoriaList(){
        return categoriaRepository.listAll();
    }

    @Path("/id")
    @GET
//    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Categoria categoriaId(@NotNull Categoria categoria){
        return categoriaRepository.findById(categoria.id);
    }


    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @POST
    public Categoria insereCategoria(@Valid Categoria categoria){
        categoria.persist();
        return categoria;

    }

    @Transactional
    @Path("/id")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PUT
    public Categoria atualizaCategoria(@Valid Categoria categoria){
         categoria.persist();
         return categoria;


    }





}
