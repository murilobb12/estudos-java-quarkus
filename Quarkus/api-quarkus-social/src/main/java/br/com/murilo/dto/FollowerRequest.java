package br.com.murilo.dto;

import br.com.murilo.model.User;
import lombok.Data;

import javax.enterprise.context.ApplicationScoped;

@Data
public class FollowerRequest {

    private Long followerId;


}
