package br.com.murilo.rest;


import br.com.murilo.dto.FollowerPerUseResponse;
import br.com.murilo.dto.FollowerRequest;
import br.com.murilo.model.Follower;
import br.com.murilo.model.User;
import br.com.murilo.repository.FollowerRepository;
import br.com.murilo.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

@Path("users/{id}/followers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class FollowerResource {

    @Inject
    UserRepository userRepository;

    @Inject
    FollowerRepository followerRepository;

    //ESSE GET NÃO TEM TRATAMENTO PARA A RESPOSTA
    @GET
    public Response listFollowers(@PathParam("id")Long id){

        User user = userRepository.findById(id);

        if (user == null){
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }

        PanacheQuery<Follower> followers = followerRepository.find("user", user);
        List<Follower> followerList = followers.list();


        return Response.status(Response.Status.OK.getStatusCode()).entity(followerList).build();

    }

//    //ESSE GET TEM TRATAMENTO PARA A RESPOSTA
//    @GET
//    public Response listFollowers(@PathParam("id")Long id){
//
//        List<Follower> byUser = followerRepository.findByUser(id);
//
//        FollowerPerUseResponse responseObject = new FollowerPerUseResponse();
//
//        responseObject.setFollowersCount(byUser.size());
//        responseObject.set
//
//
//    }




    @Transactional
    @PUT
    public Response followUser(@PathParam("id")Long id, FollowerRequest followerRequest){

        if (id.equals(followerRequest.getFollowerId())){
            return Response.status(Response.Status.CONFLICT.getStatusCode()).entity("You can't follow yourself.").build();
        }

        User userById = userRepository.findById(id);

        if (userById==null){
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }

        User hasFollower = userRepository.findById(followerRequest.getFollowerId());

        if (hasFollower == null){

            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();

        }

        User followerById = userRepository.findById(followerRequest.getFollowerId());
        boolean follows = followerRepository.follows(followerById, userById);



        if (!follows){

            Follower follower = new Follower();

            follower.setFollower(followerById);
            follower.setUser(userById);
            followerRepository.persist(follower);
        }

        return Response.status(Response.Status.OK.getStatusCode()).build();

    }


    @Path("{follower_id}")
    @Transactional
    @DELETE
    public Response deleteFollower(@PathParam("id")Long idUser, @PathParam("follower_id")Long idFollower){

        User userById = userRepository.findById(idUser);

        if (userById==null){

            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();

        }

        Follower followerById = followerRepository.findById(idFollower);

        if (followerById == null){

            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();

        }

        followerRepository.delete(followerById);

        return Response.status(Response.Status.OK.getStatusCode()).entity(idFollower).build();


    }



}
