package ro.personal.home.realestate.enums;

public enum PageType {

    APARTAMENT("https://www.imobiliare.ro/vanzare-apartamente/cluj-napoca"),
    CASE("https://www.imobiliare.ro/vanzare-case-vile/cluj-napoca"),
    TEREN("https://www.imobiliare.ro/vanzare-terenuri-constructii/cluj-napoca"),
    GENERAL(null);

    private PageType(String linkToPage) {
        this.linkToPage = linkToPage;
    }

    private String linkToPage;

    public String getLinkToPage() {
        return linkToPage;
    }
}
