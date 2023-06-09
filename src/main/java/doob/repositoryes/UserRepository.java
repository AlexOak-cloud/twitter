package doob.repositoryes;


import doob.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Override
    List<User> findAllById(Iterable<Integer> integers);

    @Override
    <S extends User> S save(S entity);

    User findByName(String username);

    @Override
    Optional<User> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(User entity);

}
