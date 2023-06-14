package doob.repositoryes;


import doob.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Override
    List<Message> findAll();

    @Override
    <S extends Message> S save(S entity);

    @Override
    Optional<Message> findById(Integer integer);

    @Override
    void delete(Message entity);

    @Override
    void deleteById(Integer integer);
}
