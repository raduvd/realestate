package ro.personal.home.realestate.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.personal.home.realestate.persistance.model.AdPrice;
import ro.personal.home.realestate.persistance.model.AdPriceId;

import java.time.LocalDate;

@Repository
public interface AdPriceJpaRepository extends JpaRepository<AdPrice, AdPriceId>, JpaSpecificationExecutor<AdPrice> {

    @Modifying
    @Query(value = "UPDATE adprice \n" +
            "SET state=:state \n" +
            "WHERE addedAtDate = :fromDate",
            nativeQuery = true)
    public void updateState(@Param("state") String state, @Param("fromDate") LocalDate fromDate);

}
