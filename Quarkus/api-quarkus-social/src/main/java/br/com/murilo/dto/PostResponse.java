package br.com.murilo.dto;


import br.com.murilo.model.Post;
import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
@Data
public class PostResponse {

    private String postText;
    private LocalDateTime localDateTime;



    public static PostResponse fromEntity(Post post){

        PostResponse postResponse = new PostResponse();

        postResponse.setPostText(post.getPostText());
        postResponse.setLocalDateTime(post.getDateTime());


        return postResponse;

    }

}
