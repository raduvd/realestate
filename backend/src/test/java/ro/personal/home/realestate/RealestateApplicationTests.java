package ro.personal.home.realestate;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ro.personal.home.realestate.persistance.repository.AdJpaRepository;
import ro.personal.home.realestate.service.WebDriverService;

@SpringBootTest
class RealestateApplicationTests {
    @Autowired
    private WebDriverService webDriverService;

    @Autowired
    private AdJpaRepository adJpaRepository;

    @Test
    void calculateFluctuationInPercentageTest() {
        Assertions.assertEquals(-10D, webDriverService.calculateFluctuationInPercentage(100D, 90D));
        Assertions.assertEquals(10D, webDriverService.calculateFluctuationInPercentage(100D, 110D));
    }

    @Test
    @Ignore
    void DBTest() {


    }
}
