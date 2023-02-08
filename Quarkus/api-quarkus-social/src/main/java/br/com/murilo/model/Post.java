package br.com.murilo.model;


import lombok.Data;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.time.LocalDateTime;

@ApplicationScoped
@Entity
@Data
@Table(name = "POSTS")
public class Post {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "post_text")
    private String postText;

    @Column(name = "date_time")
    private LocalDateTime dateTime;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @PrePersist
    public void prePersist(){
        setDateTime(LocalDateTime.now());
    }


}
