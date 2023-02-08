package br.com.murilo.model;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;

@Data
@ApplicationScoped
public class Episodes
{

    private String nomeGenero;

    public Episodes(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

    public Episodes() {
    }
}
