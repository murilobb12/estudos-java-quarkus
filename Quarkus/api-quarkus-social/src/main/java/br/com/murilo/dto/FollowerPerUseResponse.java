package br.com.murilo.dto;


import br.com.murilo.model.Follower;
import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
@Data
public class FollowerPerUseResponse {

    private Integer followersCount;

    private List<FollowerResponse> content;






}
