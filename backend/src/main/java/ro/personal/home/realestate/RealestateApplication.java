package ro.personal.home.realestate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
TODO - make small and easy FE with react and readux, prof of concept, simple:
    --put a simple graph with mock date and mock average
    --call a simple service
DAY 1

TODO - create 3 new services coresponding to each old test -
 		--Transfer all the old app into a new package called populateDb..
		--Ad id in Anunt
		--unele preturi sunt calculate de imobiliare cele cu * apoi unele sunt + tva, aceste preturi sunt inconsistente
		--for each Anunt that I get, map it to Ad and AdPrice. if there is an entry
		--rename the classes from old app, and put them in the right packages that foloows the layers (repository, service..)
		--remove the logic that saves to an excel the results, i can do that with an sql or a simple service (get all ads from same date and calculate average)
		--Sa nu scot invalidari cum ca e prea mare sau prea mic un ad, dar sa fac un interval foarte mare (spre exemplu consider invalid un teren cu 1 euro dar nu si unul de 5)
		-- add valid or invalid functionality, if the process is done succesfuly and all the pages are reached only then the ads are valid.
		--the errors and things that I am printing, put them in the a FE page with this info:

		 public static void sumUpPrint() {
        System.out.println("..\nSUM-UP\n..\n");
        System.out.println("TREBUIA SA FIE " + numarulPaginii * numarAnunturiPerPagina + " ANUNTURI PROCESATE.");
        System.out.println("MOMENTAN SUNT: " + numarAnunturiProcesate + " ANUNTURI PROCESATE.");
        //System.out.println("ACESTE INFORMATII DE MAI SUS AU FOST SCRISE IN: " + PATH_TO_OUTPUT_FILE);
        System.out.println("TOTAL number of errors: " + allErrorsList.size());
        System.out.println(ErrorType.INVALID_VALUE.name() + " - number of errors: " + invalidValueList.size() + " ---- description: " + ErrorType.INVALID_VALUE.description);
        System.out.println(ErrorType.WAITING_TIMEOUT.name() + " - number of errors: " + waitingTimeoutList.size() + " ---- description: " + ErrorType.WAITING_TIMEOUT.description);
        System.out.println(ErrorType.CASTING_EXCEPTION.name() + " - number of errors: " + castingExceptionList.size() + " ---- description: " + ErrorType.CASTING_EXCEPTION.description);
        System.out.println(ErrorType.ELEMENT_NOT_FOUND.name() + " - number of errors: " + elementNotFoundList.size() + " ---- description: " + ErrorType.ELEMENT_NOT_FOUND.description);
        final List<Errors> sortedAllErrorList = allErrorsList.stream().
                sorted(Comparator.comparing(Errors::getValue)).collect(Collectors.toList());

        System.out.println("..\nSHORT ERRORS\n..\n");
        sortedAllErrorList.forEach(e -> System.out.println(e.value + " - " + e.getErrorType() + " - " + e.getErrorMessage() + " - " + e.getPageType().name()));

        Set<Errors> uniqueValueErrors = new TreeSet<>(Comparator.comparing(Errors::getValue));
        uniqueValueErrors.addAll(sortedAllErrorList);

        System.out.println("..\nSTACK TRACES\n..\n");
        uniqueValueErrors.forEach(e -> {
            if (e.getException() != null) {
                System.out.println(e.value + " - " + e.getErrorType() + " - " + e.getErrorMessage() + " - " + e.getPageType().name());
                e.getException().printStackTrace();
            }
        });
    }
    		-- Message when rolling these services //Aici ar trebui sa mearga pana la maxim tot timpul, deci pune un numar mare ca 500,
    // deoarece vreau sa vad cate anunturi sunt pe piata. Daca sunt putine inseamna ca e cerere mare.
DAY 2
TODO - get results from DB - task 1 - make new service that returns all the averages from all the dates in the db
		--make view? or do plain java and reuse some of the existing calculations?
		--search all the unique dates and their coresponing ads
		--at this point each date should have a list of ads
		--get all the ads frome those dates and make an average for each type of ad (casa, teren, apartament)
		--display them in 3 graphs that represent date and average
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
		*/
@SpringBootApplication
@EnableJpaRepositories(basePackages = "ro.personal.home.realestate.persistance.repository")
public class RealestateApplication {

    public static void main(String[] args) {
        SpringApplication.run(RealestateApplication.class, args);
    }

}
