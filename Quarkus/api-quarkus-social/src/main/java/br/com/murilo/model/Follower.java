package br.com.murilo.model;


import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.persistence.*;

@Entity
@Data
@RequestScoped
@Table(name = "FOLLOWERS")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id")
    private User follower;

}
