import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.application.NavigationHandler;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.io.Serializable;
import java.math.BigDecimal;


@Named
@SessionScoped
public class Netz_Controller implements Serializable {

    private int netzId;
    private String vorname;
    private String nachname;
    private String telefonnummer;
    private int durchmesser;
    private BigDecimal breitengrad;
    private BigDecimal laengengrad;

    public void netzBergungBevorstehend() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("geisternetzPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Geisternetz geisternetz = entityManager.find(Geisternetz.class, netzId);

        // Prüft ob Netz anderen Status als „gemeldet“ hat.
        if (geisternetz.getStatus().equals(Status.GEMELDET)) {
            try {
                Person person = new Person();
                person.setVorname(vorname);
                person.setNachname(nachname);
                person.setTelefonnummer(telefonnummer);
                person.setRolle(Rolle.BERGENDE_PERSON);

                geisternetz.setStatus(Status.BERGUNG_BEVORSTEHEND);
                geisternetz.setBergendePerson(person);

                entityManager.merge(geisternetz);
                entityManager.persist(person);
                transaction.commit();

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Meldung 'Netzbergung bevorstehend' war erfolgreich.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            } finally {
                entityManager.close();
                resetEingabeFelder();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bergen nicht möglich", "Netz wird bereits geborgen."));
            resetEingabeFelder();
        }
    }

    public void netzGeborgen() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("geisternetzPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Geisternetz geisternetz = entityManager.find(Geisternetz.class, netzId);

        // Prüft ob Netz anderen Status als „gemeldet“ hat.
        if (geisternetz.getStatus().equals(Status.BERGUNG_BEVORSTEHEND)) {
            try {
                Person person = new Person();
                person.setVorname(vorname);
                person.setNachname(nachname);
                person.setTelefonnummer(telefonnummer);
                person.setRolle(Rolle.BERGENDE_PERSON);

                geisternetz.setStatus(Status.GEBORGEN);
                geisternetz.setBergendePerson(person);

                entityManager.merge(geisternetz);
                entityManager.persist(person);
                transaction.commit();

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Meldung 'Netz geborgen' war erfolgreich.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            } finally {
                entityManager.close();
                resetEingabeFelder();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Bergen nicht möglich", "Netz wurde bereits geborgen."));
            resetEingabeFelder();
        }
    }

    public void netzVerschollen() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("geisternetzPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Geisternetz geisternetz = entityManager.find(Geisternetz.class, netzId);

        if (!geisternetz.getStatus().equals(Status.GEBORGEN)) {
            try {
                Person person = new Person();
                person.setVorname(vorname);
                person.setNachname(nachname);
                person.setTelefonnummer(telefonnummer);

                geisternetz.setStatus(Status.VERSCHOLLEN);
                geisternetz.setBergendePerson(person);

                entityManager.merge(geisternetz);
                entityManager.persist(person);
                transaction.commit();

                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolg", "Meldung 'Netz verschollen' war erfolgreich.");
                FacesContext.getCurrentInstance().addMessage(null, message);

            } catch (Exception e) {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            } finally {
                entityManager.close();
                resetEingabeFelder();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Verschollen Meldung nicht möglich", "Netz wurde bereits geborgen."));
            resetEingabeFelder();
        }
    }

    public void resetEingabeFelder() {
        setVorname(null);
        setNachname(null);
        setTelefonnummer(null);
        setDurchmesser(0);
        setLaengengrad(null);
        setBreitengrad(null);
    }

    public void netzBergenFormular(int netzId, int durchmesser, BigDecimal laengengrad, BigDecimal breitengrad) {
        this.netzId = netzId;
        this.durchmesser = durchmesser;
        this.laengengrad = laengengrad;
        this.breitengrad = breitengrad;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(facesContext, null, "netzbergung?faces-redirect=true");
    }

    public void netzGeborgenFormular(int netzId, int durchmesser, BigDecimal laengengrad, BigDecimal breitengrad) {
        this.netzId = netzId;
        this.durchmesser = durchmesser;
        this.laengengrad = laengengrad;
        this.breitengrad = breitengrad;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(facesContext, null, "netzgeborgen?faces-redirect=true");
    }

    public void netzVerschollenFormular(int netzId, int durchmesser, BigDecimal laengengrad, BigDecimal breitengrad) {
        this.netzId = netzId;
        this.durchmesser = durchmesser;
        this.laengengrad = laengengrad;
        this.breitengrad = breitengrad;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
        navigationHandler.handleNavigation(facesContext, null, "netzverschollen?faces-redirect=true");
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
