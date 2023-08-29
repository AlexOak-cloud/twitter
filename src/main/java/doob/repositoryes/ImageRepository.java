package doob.repositoryes;


import doob.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ImageRepository extends JpaRepository<Image, Integer> {

    @Override
    <S extends Image> S save(S entity);

    @Override
    Optional<Image> findById(Integer integer);

    @Override
    void deleteById(Integer integer);

    @Override
    void delete(Image entity);
}
