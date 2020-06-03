package ro.personal.home.realestate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
DAY 3
//
TODO - run again all from Populate Page and test the fluctuations if they are there in in the DB
TODO - add field in Populate Page that contains just the page number, not the entire page url, I only need to add pageNumber at the following link https://www.imobiliare.ro/vanzare-apartamente/cluj-napoca?pagina=pageNumber
TODO - the pageNumebr field from Populate Page is reprezenting the number of loops, not the page number, so meke sure that is clear
TODO - feedback in urma la anumite servicii, si intre aceste feedbackuri sa am un loading screen
        --la validare ar fi fain un feedback, daca s-a terminat validarea sau nu
        --la popularea cu NumberOfAds ar fi fain un feedback care sa imi updateze tabelul
            + sa fie un loading screen
            + daca e failed sa imi fie un prompt care sa ma trimita la loguri
        --la popularea cu Ads sa am un loading screen si apoi sa imi apara un prompt cu 3-4 randuri de informatii,
            cel putin un mesaj care sa ma trimita in consola
TODO - cand apas pe Submit care merge la WebDriver si incepe un proces greoi, ar trebui sa am un
 prompt de confirmare in caz ca apas din reseala
		*/
@SpringBootApplication
@EnableJpaRepositories(basePackages = "ro.personal.home.realestate.persistance.repository")
public class RealestateApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealestateApplication.class, args);
    }

}
