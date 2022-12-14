package bg.softuni.artgalleryshop.repository;

import bg.softuni.artgalleryshop.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findByEmail(String email);

  Optional<UserEntity> findByUsername(String username);

    List<UserEntity> findByFirstName(String firstName);
}
