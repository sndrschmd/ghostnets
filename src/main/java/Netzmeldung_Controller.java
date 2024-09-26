import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.Serializable;
import java.math.BigDecimal;


@Named
@ViewScoped
public class Netzmeldung_Controller implements Serializable {

    private int netzId;
    private String vorname;
    private String nachname;
    private String telefonnummer;
    private int durchmesser;
    private BigDecimal breitengrad;
    private BigDecimal laengengrad;


    public void netzMelden() {

        Person person = new Person();
        person.setVorname(vorname);
        person.setNachname(nachname);
        person.setTelefonnummer(telefonnummer);
        person.setRolle(Rolle.MELDENDE_PERSON);

        Geisternetz netz = new Geisternetz();

        netz.setStatus(Status.GEMELDET);
        netz.setMeldendePerson(person);
        netz.setDurchmesser(durchmesser);
        netz.setLaengengrad(laengengrad);
        netz.setBreitengrad(breitengrad);
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("geisternetzPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        entityManager.persist(person);
        entityManager.persist(netz);

        transaction.commit();
        entityManagerFactory.close();

        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Netz wurde erfolgreich gemeldet.");
        FacesContext.getCurrentInstance().addMessage(null, message);

    }


    public int getNetzId() {
        return netzId;
    }

    public void setNetzId(int netzId) {
        this.netzId = netzId;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public int getDurchmesser() {
        return durchmesser;
    }

    public void setDurchmesser(int durchmesser) {
        this.durchmesser = durchmesser;
    }

    public BigDecimal getLaengengrad() {
        return laengengrad;
    }

    public void setLaengengrad(BigDecimal laengengrad) {
        this.laengengrad = laengengrad;
    }

    public BigDecimal getBreitengrad() {
        return breitengrad;
    }

    public void setBreitengrad(BigDecimal breitengrad) {
        this.breitengrad = breitengrad;
    }
}
