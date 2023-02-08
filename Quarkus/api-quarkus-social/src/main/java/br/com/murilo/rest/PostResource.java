package br.com.murilo.rest;


import br.com.murilo.dto.PostRequest;
import br.com.murilo.dto.PostResponse;
import br.com.murilo.model.Post;
import br.com.murilo.model.User;
import br.com.murilo.repository.PostRepository;
import br.com.murilo.repository.UserRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Sort;
import org.jboss.logging.annotations.Pos;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("users/{id}/posts")
public class PostResource {


    @Inject
    UserRepository userRepository;

    @Inject
    PostRepository postRepository;

    //Esse método irá nos retornar todos os dados que estão cadastrados
//    @GET
//    public Response listPosts(@PathParam("id")Long id){
//
//        User user = userRepository.findById(id);
//
//        if (user == null){
//
//            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
//        }
//
//        PanacheQuery<Post> posts = postRepository.find("user", user);
//
//        List<Post> postListlist = posts.list();
//
//        return Response.status(Response.Status.OK.getStatusCode()).entity(postListlist).build();
//
//    }

    //Para filtrarmos o que iremos mostrar na API, devemos utilizar um DTO postResponse
    @GET
    public Response listPosts(@PathParam("id")Long id){

        User user = userRepository.findById(id);

        if (user == null){
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }

        PanacheQuery<Post> posts = postRepository.find("user", Sort.descending("dateTime"), user);
        List<Post> listPosts = posts.list();
//        listPosts.stream().map(post -> PostResponse.fromEntity(post)).collect(Collectors.toList());
        List<PostResponse> postResponseList = listPosts.stream()
                .map(PostResponse::fromEntity)
//                .sorted(Comparator.comparing(PostResponse::getLocalDateTime).reversed()) estamos passando o sort no find
                .collect(Collectors.toList());


        return Response.status(Response.Status.OK.getStatusCode()).entity(postResponseList).build();

    }


    @Transactional
    @POST
    public Response savePost(@PathParam("id")Long id, PostRequest postRequest){

        User user = userRepository.findById(id);

        if (user == null){

            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();

        }

        Post post = new Post();
        post.setPostText(postRequest.getPostText());
        post.setUser(user);

        postRepository.persist(post);

        return Response.status(Response.Status.CREATED.getStatusCode()).entity(post).build();



    }


}