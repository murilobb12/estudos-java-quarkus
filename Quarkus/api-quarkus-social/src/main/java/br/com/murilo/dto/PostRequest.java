package br.com.murilo.dto;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotBlank;

@ApplicationScoped
@Data
public class PostRequest {

    @NotBlank
    private String postText;

}
