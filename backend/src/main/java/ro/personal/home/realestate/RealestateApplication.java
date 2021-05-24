package ro.personal.home.realestate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*

TODO - imi apare un pop-up nou in momentul in care fac refresh la pagina (fac astfel de refresh in Page lini 86). Acest lucru cauzeaza sa mi se inchida programul. Am incercat sa rezolv punand 2 refresuri, dar daca mai apare trebuie sa repar.
TODO - confirmation prompts, gen: "Are you sure?" before all Form Submisions
TODO - loading screen for all Form Submisions
TODO - When Form Submisions is finished again a prompt saying that all went well and no errors occured
TODO - when changing dropdowns, populate fields with known values:
    - In pagina de Populate sa mi se incarce direct numarul de pagini
    - in pagina de Custom Fluctuations sa mi se incarce cea ce vreau eu.

		*/
@SpringBootApplication
@EnableJpaRepositories(basePackages = "ro.personal.home.realestate.persistance.repository")
public class RealestateApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealestateApplication.class, args);
    }

}
