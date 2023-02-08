package br.com.murilo.model;

import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import java.net.URL;
import java.util.Set;


@Data
@ApplicationScoped
public class TvSerie {

    private Long id;

    private String name;

    private URL url;

    private String summary;

    private String language;

    private Set<Episodes> genres;

    private URL officialSite;


    public TvSerie() {
    }

    public TvSerie(Long id, String name, URL url, String summary, String language, Set<Episodes> genres, URL officialSite) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.summary = summary;
        this.language = language;
        this.genres = genres;
        this.officialSite = officialSite;
    }

}
