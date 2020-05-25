package ro.personal.home.realestate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.personal.home.realestate.persistance.model.Ad;
import ro.personal.home.realestate.persistance.repository.AdJpaRepository;

@Service
public class AdService {

    @Autowired
    private AdJpaRepository adJpaRepository;

    public Ad findById(String adId) {
        return adJpaRepository.findById(adId).get();
    }
}
