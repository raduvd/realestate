package ro.personal.home.realestate.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.personal.home.realestate.persistance.model.Ad;

@Repository
public interface AdJpaRepository extends JpaRepository<Ad, String> {
}
