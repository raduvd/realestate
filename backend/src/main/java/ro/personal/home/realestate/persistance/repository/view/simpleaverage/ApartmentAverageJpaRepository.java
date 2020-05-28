package ro.personal.home.realestate.persistance.repository.view.simpleaverage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ro.personal.home.realestate.persistance.model.AdId;
import ro.personal.home.realestate.persistance.model.view.simpleaverage.ApartmentAverages;

/**
 * Created by vancer at 5/28/2020
 */
@Repository
public interface ApartmentAverageJpaRepository extends JpaRepository<ApartmentAverages, AdId>, JpaSpecificationExecutor<ApartmentAverages> {
}
