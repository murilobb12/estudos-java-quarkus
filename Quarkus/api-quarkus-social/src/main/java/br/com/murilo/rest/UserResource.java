package br.com.murilo.rest;

import br.com.murilo.dto.UserRequest;
import br.com.murilo.model.User;
import br.com.murilo.repository.UserRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserRepository userRepository;

    @Inject
    User user;

    @Transactional
    @POST
    //mètodo publico que irá retornar um Response recebendo um JSON com as infromações de User Request
    public Response createUser(@Valid UserRequest userRequest) {

        User user1 = new User();

        //Setando a idade do usuários com o JSON recebido
        user1.setAge(userRequest.getAge());
        //Setando o nome do usuários com o JSON recebido
        user1.setName(userRequest.getName());

        //salvando as informações recebidas em um novo usuário.
        userRepository.persist(user1);

        //Retornando o status criado, com o usuário criado no método entity
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(user1).build();
    }

    @GET
    //método public que irá retornar todos us usuários em forma de Response
    public Response listAllUsers() {

        //essa parte, não tinhamos a classe vinculada a um repositório
//        PanacheQuery<PanacheEntityBase> users = User.findAll();

        //classe repositório devolvendo uma lista de usuários
        List<User> users = userRepository.listAll();

        //retornando o status OK para a listagem de usuários
        return Response.status(Response.Status.OK.getStatusCode()).entity(users).build();
    }

    @GET
    //Método para retornar um usuário pelo seu ID, através do path /id onde o id será um parâmetro passado pelo método
    @Path("{id}")
    public Response listUserById(@PathParam("id")Long id){

        //Passando para a variável user o usuário encontrado pelo id passado no path
        user = userRepository.findById(id);

        //caso encontre o id passado pelo parâmetro, ele irá retornar na API o status 200-OK com o usuário buscado pelo ID
        if (user != null){

            return Response.status(Response.Status.OK.getStatusCode()).entity(user).build();

        }

        //caso não econtre o id passado pelo parâmetro, irá ser retornado na API o status 404-NOT_FOUND
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

    @Transactional
    @DELETE
    //Criando o caminho /id onde o ID será um parametro recebido via método
    @Path("{id}")
    public Response deleteUser(@PathParam("id") Long id) {

        //Nessa implementação passamos a busca de um usuário por ID, para uma variável Optional, pois não sabemos se vamos conseguir retornar um ID
        Optional<User> userOptional = userRepository.findByIdOptional(id);

        //Se não acharmos um ID, devemos retornar o status na API de NOT_FOND
        if (userOptional.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }

        //Caso encontremos o usuário, o usuário que iremos tratar será o identificado no Optional de User
        user = userOptional.get();

        //Deletando o usuário encontrado em Optional
        userRepository.delete(user);

        //Retornando um status de OK para a deleção
        return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();

    }


    @Transactional
    @PUT
    //Criando o caminho ID que será de um tipo PUT(Update) onde o campo ID será um parametro recebido via método
    @Path("{id}")
    public Response updateUser(@PathParam("id") Long id, UserRequest userRequest) {

        //Passando para a variável user o usuário encontrado pelo id passado no path
        user = userRepository.findById(id);

        //Se o usuário achar algum registro com o ID informado devemos seguir com a atualização do nome e da idade do usuários
        if (user != null) {
            user.setAge(userRequest.getAge());
            user.setName(userRequest.getName());

            //Retornar um statos ok para a atualização realizada
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        //Caso não encontre nenhum usuário com o id informado, deverá retronar um status de não encontrado
        return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
    }

}