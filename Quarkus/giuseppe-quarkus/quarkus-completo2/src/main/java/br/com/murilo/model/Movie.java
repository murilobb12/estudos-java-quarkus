package br.com.murilo.model;

import lombok.Data;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Data
@Schema(name = "Movie", description = "Movie representation")
public class Movie {

    private Long id;

    @Schema(required = true)
    private String title;

}
