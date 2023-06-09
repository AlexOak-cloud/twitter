package doob.repositoryes;


import doob.entity.Twit;
import doob.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TwitRepository extends JpaRepository<Twit, Integer> {


    @Override
    List<Twit> findAll();

    @Override
    <S extends Twit> S save(S entity);

    @Override
    Optional<Twit> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Twit entity);

    List<Twit> findAllByUser(User user);
}
