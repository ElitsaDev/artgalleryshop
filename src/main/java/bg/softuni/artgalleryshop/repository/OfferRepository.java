package bg.softuni.artgalleryshop.repository;

import bg.softuni.artgalleryshop.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, UUID> {
    List<OfferEntity> findAllByProduct_NameContains(String query);
}
