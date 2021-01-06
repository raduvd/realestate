package ro.personal.home.realestate.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ro.personal.home.realestate.persistance.model.Ad;
import ro.personal.home.realestate.persistance.model.AdId;
import ro.personal.home.realestate.persistance.model.view.IAverages;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AdJpaRepository extends JpaRepository<Ad, AdId>, JpaSpecificationExecutor<Ad> {

    @Query(value =
            "select avg(fluctuationInPercentageSinceLastDate) as squareMeterPriceAverage,  count(fluctuationInPercentageSinceLastDate) numberOfAds, addedAtDate\n" +
                    "from (select p.addedAtDate, p.fluctuationInPercentageSinceLastDate\n" +
                    "      from public.adprice p\n" +
                    "               left join public.ad a\n" +
                    "                         ON a.adId = p.adId AND a.squareMeters = p.squareMeters AND a.pageType = p.pageType\n" +
                    "      where a.pageType = :pageType and p.state = 'VALID' and p.fluctuationInPercentageSinceLastDate is not null\n" +
                    "\t and :maxSquareMeters >= a.squareMeters and a.squareMeters >= :minSquareMeters and p.squareMeterPrice <= :maxSquareMeterPrice and p.squareMeterPrice >= :minSquareMetersPrice  ) as x\n" +
                    "group by (addedAtDate);", nativeQuery = true)
    public List<IAverages> customFluctuations(
            @Param("pageType") String pageType,
            @Param("maxSquareMeters") Integer maxSquareMeters,
            @Param("minSquareMeters") Integer minSquareMeters,
            @Param("maxSquareMeterPrice") Integer maxSquareMeterPrice,
            @Param("minSquareMetersPrice") Integer minSquareMetersPrice);
}
