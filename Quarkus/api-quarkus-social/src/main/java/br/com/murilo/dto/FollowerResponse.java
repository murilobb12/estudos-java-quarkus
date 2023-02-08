package br.com.murilo.dto;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;

@Data
@ApplicationScoped
public class FollowerResponse {

    private Long id;
    private String name;

}
