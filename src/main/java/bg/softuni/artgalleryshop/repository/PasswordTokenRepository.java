package bg.softuni.artgalleryshop.repository;

import bg.softuni.artgalleryshop.model.entity.PasswordTokenEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordTokenEntity,Long> {
    PasswordTokenEntity findByToken(String token);
}
