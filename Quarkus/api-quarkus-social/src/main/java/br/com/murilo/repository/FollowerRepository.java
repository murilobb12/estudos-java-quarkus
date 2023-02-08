package br.com.murilo.repository;

import br.com.murilo.model.Follower;
import br.com.murilo.model.User;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Parameters;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class FollowerRepository implements PanacheRepository<Follower> {


    public boolean follows(User follower, User user){

        Map<String, Object> params = Parameters.with("follower", follower).and("user", user).map();

        PanacheQuery<Follower> followerPanacheQuery = find("follower =:follower and user =:user", params);

        Optional<Follower> hasFollower = followerPanacheQuery.firstResultOptional();

        return hasFollower.isPresent();


    }

    public List<Follower> findByUser(Long userId){

        PanacheQuery<Follower> followerPanacheQuery = find("user.id", userId);
        List<Follower> list = followerPanacheQuery.list();

        return list;


    }

}
