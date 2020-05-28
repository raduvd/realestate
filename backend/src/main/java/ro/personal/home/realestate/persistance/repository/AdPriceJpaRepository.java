package ro.personal.home.realestate.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ro.personal.home.realestate.persistance.model.AdPrice;
import ro.personal.home.realestate.persistance.model.AdPriceId;

@Repository
public interface AdPriceJpaRepository extends JpaRepository<AdPrice, AdPriceId>, JpaSpecificationExecutor<AdPrice> {
}
