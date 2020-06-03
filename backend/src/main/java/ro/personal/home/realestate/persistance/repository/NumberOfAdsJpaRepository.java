package ro.personal.home.realestate.persistance.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.personal.home.realestate.persistance.model.AdId;
import ro.personal.home.realestate.persistance.model.NumberOfAds;

import java.util.List;

@Repository
public interface NumberOfAdsJpaRepository extends JpaRepository<NumberOfAds, AdId>, JpaSpecificationExecutor<NumberOfAds> {

    @Query(value =
            "(SELECT * from numberofads where pageType = 'APARTAMENT' order by addedAtDate DESC LIMIT 1)" +
                    "UNION" +
                    "(SELECT * from numberofads where pageType = 'CASE' order by addedAtDate DESC LIMIT 1)" +
                    "UNION" +
                    "(SELECT * from numberofads where pageType = 'TEREN' order by addedAtDate DESC LIMIT 1)",
            nativeQuery = true)
    public List<NumberOfAds> getLatestNumberOfAds();
}
