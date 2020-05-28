package ro.personal.home.realestate.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.personal.home.realestate.persistance.model.Ad;
import ro.personal.home.realestate.persistance.model.AdId;

import java.time.LocalDate;

@Repository
public interface AdJpaRepository extends JpaRepository<Ad, AdId>, JpaSpecificationExecutor<Ad> {

    @Modifying
    @Query(value = "UPDATE ad \n" +
            "SET state=:state \n" +
            "FROM ad a LEFT JOIN adprice p\n" +
            "ON a.adId = p.adId AND a.squareMeters = p.squareMeters AND a.pageType = p.pageType\n" +
            "WHERE p.addedAtDate = :fromDate",
            nativeQuery = true)
    public void updateState(@Param("state") String state, @Param("fromDate") LocalDate fromDate);
}
