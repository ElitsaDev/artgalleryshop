package bg.softuni.artgalleryshop.repository;

import bg.softuni.artgalleryshop.model.entity.StatisticEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticRepository extends JpaRepository<StatisticEntity, Long> {
//    @Modifying
//    @Query("DELETE FROM StatisticEntity")
//    void dropTable();
}
