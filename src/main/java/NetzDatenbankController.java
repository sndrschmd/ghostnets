import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
public class NetzDatenbankController implements Serializable {

    private List<Geisternetz> netzliste; // Liste aller Geisternetze
    private List<Geisternetz> netzeBergungBevorstehend; // Gefilterte Liste für bevorstehende Bergungen
    
    // Filterkriterien (optional, falls du serverseitige Filter anwenden möchtest)
    private String filterLaengengrad;
    private String filterBreitengrad;

    // Getter und Setter für Filterkriterien
    public String getFilterLaengengrad() {
        return filterLaengengrad;
    }

    public void setFilterLaengengrad(String filterLaengengrad) {
        this.filterLaengengrad = filterLaengengrad;
    }

    public String getFilterBreitengrad() {
        return filterBreitengrad;
    }

    public void setFilterBreitengrad(String filterBreitengrad) {
        this.filterBreitengrad = filterBreitengrad;
    }

    // Methode wird nach der Konstruktion der Bean aufgerufen
    @PostConstruct
    public void init() {
        // Initialisiere die Liste aller Netze
        loadNetze();
        // Filtere die Liste für Netze mit bevorstehender Bergung
        filterNetzeBergungBevorstehend();
    }

    // Lädt alle Netze aus der Datenbank
    public void loadNetze() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("geisternetzPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        // Abfrage aller Geisternetze aus der Datenbank
        netzliste = entityManager.createQuery("SELECT e FROM Geisternetz e", Geisternetz.class).getResultList();
        
        entityManager.close();
        entityManagerFactory.close();
    }

    // Filtert Netze mit dem Status "BERGUNG_BEVORSTEHEND"
    public void filterNetzeBergungBevorstehend() {
        if (netzliste != null) {
            netzeBergungBevorstehend = netzliste.stream()
                .filter(netz -> netz.getStatus() == Status.BERGUNG_BEVORSTEHEND)
                .collect(Collectors.toList());
        }
    }

    // Getter für die gefilterte Liste, die in der unterstuetzer.xhtml verwendet wird
    public List<Geisternetz> getNetzeBergungBevorstehend() {
        return netzeBergungBevorstehend;
    }

    // Getter für die allgemeine netzliste, die von der DataTable verwendet wird
    public List<Geisternetz> getNetzliste() {
        return netzliste;
    }

    // Dummy-Aktionen für die CommandButtons
    public void netzBergenFormular(int id, double laengengrad, double breitengrad) {
        // Implementiere hier deine Logik für das Bergen eines Netzes
        System.out.println("Netz bergen: ID = " + id + ", Längengrad = " + laengengrad + ", Breitengrad = " + breitengrad);
    }

    public void netzVerschollenFormular(int id, double laengengrad, double breitengrad) {
        // Implementiere hier deine Logik für das Markieren eines Netzes als verschollen
        System.out.println("Netz verschollen: ID = " + id + ", Längengrad = " + laengengrad + ", Breitengrad = " + breitengrad);
    }
}
