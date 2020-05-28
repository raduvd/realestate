package ro.personal.home.realestate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
DAY 3
TODO - get results from DB - task 2 - make new service sa verific anunturile individuale (cu acelasi id) daca au crescut sau au scazut, gen anuntul cu id=ul 4543 l-am gasit si luna asta in db dar la alt pret. Pret care a crescut cu 2%. Iar toate anunturile care le=am gasit au crescut cu average de 3 spre exemplu
		--make view? or do plain java?
		--search all the unique dates and their coresponing ads
		--at this point each date should have a list of ads, ex Map {febr: {a,b,c}, mar: {c,f,g}, apr {f,g,i}}
		--get all the ads that are the same in febr + mar, and mar+apr (each element should be compared with the next included: febr with mar, and mar with febr)
		--put it all in 3 the graph
TODO - get results from DB - task 3 - sa am posibiliatea sa selectez anumite anunturi individuale daca au scazut sau au crescut dar sa le restrang dupa metru patrat, pret (gen casele care au intre 50 mp si 100 mp + pret intre 90 de mi si 150 mi au scazut in ultima luna sau au crescut?)
		--for the BE service, reuse the above service and beside ImobilType (case, teren..) add price or mp restrictions (add new parameteers to this services)
		--for the FE, basicaly to the above graps add new fields with max price and min price and
TODO - get results from DB - task 4 - sa iau numarul de anunturi de la fiecare search si sa le pun intr-un grafic
TODO - ideas to consider for next tasks? -
		--add a new columne in adprice called ad status with vaules like (NotFound_SoldOrRemoved, doubleRepresentingPercentIncreasement, doubleRepresentinfPercentDecrease)
TODO- sa fac update la state to INVALID pentru pageType APARTAMENT si date"2020-05-28" deoarece sunt date de test
TODO - put both apps (FE and BE) together as a build/package and run them locally
TODO - implement a graph with nummber of anounces on each day, and get the data out of the existing pages not from what anounces you find
TODO - foarte multe terene nu au pret, deci implementeaza o logica prin care sa iei pretul si mtri patrati si sa calculezi tu pretul pe metru patrat
TODO - retureneaza si numerul de anunturi in grafic pentru fiecare zi, ca daca sunt 3 anunturi e irelevant
		*/
@SpringBootApplication
@EnableJpaRepositories(basePackages = "ro.personal.home.realestate.persistance.repository")
public class RealestateApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealestateApplication.class, args);
    }

}
