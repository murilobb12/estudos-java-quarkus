package br.com.murilo.dto;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApplicationScoped
@Data
public class UserRequest {

    @NotBlank(message = "Name is required!")
    private String name;

    @NotNull(message = "Age is required!")
    private Integer age;

}
